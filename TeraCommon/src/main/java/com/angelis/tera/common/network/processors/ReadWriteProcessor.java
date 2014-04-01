package com.angelis.tera.common.network.processors;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.AbstractServer;
import com.angelis.tera.common.network.connection.AbstractTeraConnection;
import com.angelis.tera.common.network.connection.ConnectionState;

public class ReadWriteProcessor extends Processor {

    /** LOGGER */
    private static Logger log = Logger.getLogger(ReadWriteProcessor.class.getName());

    public ReadWriteProcessor(AbstractServer abstractServer) {
        super(abstractServer);
    }

    public void dispatch() {
        try {
            int selection = this.selector.select();
            if (selection == 0) {
                return;
            }

            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (!selectionKey.isValid()) {
                    log.error("Key is invalid");
                    continue;
                }

                AbstractTeraConnection connection = (AbstractTeraConnection) selectionKey.attachment();
                if (connection == null) {
                    log.error("Connection is null");
                    continue;
                }

                switch (selectionKey.readyOps()) {
                    case SelectionKey.OP_READ:
                        this.doRead(selectionKey);
                    break;

                    case SelectionKey.OP_WRITE:
                        this.doWrite(selectionKey);
                    break;
                    
                    case SelectionKey.OP_READ | SelectionKey.OP_WRITE:
                        this.doRead(selectionKey);
                        if (selectionKey.isValid()) {
                            this.doWrite(selectionKey);
                        }
                    break;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("IOException in ReadProcessor");
        }
    }

    private final <C extends AbstractTeraConnection> void doRead(SelectionKey selectionKey) {
        @SuppressWarnings("unchecked")
        C connection = (C) selectionKey.attachment();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        
        ByteBuffer buffer = connection.readBuffer();
        int startPosition = buffer.position();
        
        int readCount;
        try {
            readCount = socketChannel.read(buffer);
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
            connection.close();
            return;
        }

        if (readCount == -1) {
            log.error("Closing connection due to reaching end of stream.");
            connection.close();
            return;
        } else if (readCount == 0) {
            return;
        }

        buffer.flip();
        ByteBuffer cryptedBuffer = ByteBuffer.wrap(buffer.array(), startPosition, buffer.limit());
        connection.getCryptSession().decrypt(cryptedBuffer);
        cryptedBuffer.flip();
        
        while (buffer.remaining() > 2) {
            if (connection.getState() == ConnectionState.AUTHENTICATED) {
                if (buffer.remaining() < buffer.getShort(buffer.position())) {
                    break;
                }
            }
            
            if (!parse(connection, buffer)) {
                log.error("Failed to parse data");
                connection.close();
                return;
            }
        }

        if (buffer.hasRemaining()) {
            log.error("Read buffer has remaining");
            buffer.compact();
        } else {
            buffer.clear();
        }
    }

    private final <C extends AbstractTeraConnection> void doWrite(SelectionKey selectionKey) {
        @SuppressWarnings("unchecked")
        C connection = (C) selectionKey.attachment();
        SocketChannel channel = (SocketChannel) selectionKey.channel();

        ByteBuffer buffer = connection.writeBuffer();

        /** Write remaining bytes before */
        if (buffer.hasRemaining()) {
            try {
                channel.write(buffer);
            }
            catch (IOException e) {
                connection.close();
                return;
            }
        }

        while (true) {
            if (!connection.hasPacketToWrite()) {
                break;
            }
            
            buffer.clear();
            boolean writeSuccess = connection.writePacket(buffer);
            if (!writeSuccess) {
                log.error("Write wasn't succefull");
                buffer.limit(0);
                break;
            }

            try {
                channel.write(buffer);
            }
            catch (IOException e) {
                log.error(e.getMessage(), e);
            }

            if (buffer.hasRemaining()) {
                log.info("Buffer has remaining");
                return;
            }
        }

        selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_WRITE);

        if (connection.isPendingClose()) {
            log.info("Closing connection");
            connection.close();
        }
    }

    private final boolean parse(AbstractTeraConnection connection, ByteBuffer buffer) {
        short size = 0;
        switch (connection.getState()) {
            case CONNECTED:
                size = 128;
            break;
            
            case AUTHENTICATED:
                size = buffer.getShort();
                if (size > 1) {
                    size -= 2;
                }
            break;
            
            default:
                // should never happend
                throw new RuntimeException();
        }
        
        try {
            /** Copy packet to temp buffer for reading */
            ByteBuffer tempBuffer = (ByteBuffer) buffer.slice().limit(size);
            tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
    
            /** Update buffer position after this packet */
            buffer.position(buffer.position() + size);
    
            return connection.readPacket(tempBuffer);
        } catch (IllegalArgumentException e) {
            log.warn("Error on parsing input from client - account: " + connection + " packet size: " + size + " real size:" + buffer.remaining(), e);
            return false;
        }
    }
}
