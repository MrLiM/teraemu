package com.angelis.tera.common.network;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.List;

import javolution.util.FastList;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.config.ServerConfig;
import com.angelis.tera.common.network.processors.AcceptProcessor;
import com.angelis.tera.common.network.processors.Processor;
import com.angelis.tera.common.network.processors.ReadWriteProcessor;

public abstract class AbstractServer implements Runnable {
    
    /** LOGGER */
    private static Logger log = Logger.getLogger(AbstractServer.class.getName());

    private List<Processor> acceptProcessors = new FastList<Processor>();
    private List<Processor> readWriteProcessors = new FastList<Processor>();

    protected ServerSocketChannel serverSocket;
    
    private int currentReadWriteProcessor = 0;

    public AbstractServer(ServerConfig serverConfig) {
        try {
            this.serverSocket = ServerSocketChannel.open();
            this.serverSocket.configureBlocking(false);
            this.serverSocket.socket().bind(serverConfig.getInetSocketAddress());

            this.initAcceptProcessors(serverConfig);
            this.initReadWriteProcessors(serverConfig);
        }
        catch (IOException e) {
            throw new RuntimeException("Can't init AbstractServer");
        }
    }

    public final void run() {
        log.info("Server is now running with "+this.acceptProcessors.size()+" accept processor(s) and "+this.readWriteProcessors.size()+" read/write processor(s)");

        for (Processor processor : this.acceptProcessors) {
            new Thread(processor).start();
        }

        for (Processor processor : this.readWriteProcessors) {
            new Thread(processor).start();
        }
    }

    public final void stop() {
        
    }

    private final void initAcceptProcessors(ServerConfig serverConfig) {
        this.acceptProcessors.add(new AcceptProcessor(this, serverConfig.getConnectionFactory()));
    }

    private final void initReadWriteProcessors(ServerConfig serverConfig) {
        for (int i = 0 ; i < serverConfig.getReadWriteProcessorCount() ; i++) {
            this.readWriteProcessors.add(new ReadWriteProcessor(this));
        }
    }

    public final Processor getAcceptProcessor() {
        return this.acceptProcessors.get(0); // TODO maybe multi
    }

    public final Processor getReadWriteProcessor() {
        if (++this.currentReadWriteProcessor >= this.readWriteProcessors.size()) {
            this.currentReadWriteProcessor = 0;
        }
        
        return this.readWriteProcessors.get(this.currentReadWriteProcessor);
    }

    public final ServerSocketChannel getServerSocketChannel() {
        return this.serverSocket;
    }
}
