package com.angelis.tera.game.network.connection;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.angelis.tera.common.network.connection.AbstractTeraConnection;
import com.angelis.tera.common.network.connection.ConnectionState;
import com.angelis.tera.common.network.packet.AbstractClientPacket;
import com.angelis.tera.common.network.packet.AbstractServerPacket;
import com.angelis.tera.common.utils.PrintUtils;
import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.packet.ClientPacketHandler;
import com.angelis.tera.game.network.packet.ServerPacketHandler;
import com.angelis.tera.game.network.packet.client.key.KeyClientPacket;
import com.angelis.tera.game.network.packet.server.key.KeyServerPacket;
import com.angelis.tera.game.services.AccountService;
import com.angelis.tera.game.services.PlayerService;

public class TeraGameConnection extends AbstractTeraConnection {

    /** LOGGER */
    private static Logger log = Logger.getLogger(TeraGameConnection.class.getName());

    private final Deque<AbstractServerPacket<TeraGameConnection>> sendMsgQueue = new ArrayDeque<AbstractServerPacket<TeraGameConnection>>();

    private Account account;
    private Player activePlayer;

    private Lock lock = new ReentrantLock();

    public TeraGameConnection(SocketChannel socketChannel, SelectionKey selectionKey) {
        super(socketChannel, selectionKey);
    }

    public void onConnect() {
        log.info("Client connected");

        this.state = ConnectionState.CONNECTED;
        this.sendPacket(new KeyServerPacket());
    }

    public void onAuthenticate() {
        this.state = ConnectionState.AUTHENTICATED;
    }

    public void onDisconnect() {
        log.info("Client left");

        this.state = ConnectionState.NONE;

        if (this.account != null) {
            AccountService.getInstance().onAccountDisconnect(this.account);
            this.account = null;
        }

        if (this.activePlayer != null) {
            PlayerService.getInstance().onPlayerDisconnect(this.activePlayer);
            this.activePlayer = null;
        }
    }

    public final void setAccount(Account account) {
        this.account = account;
        account.setConnection(this);
    }

    public final Account getAccount() {
        return this.account;
    }

    public final void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public final Player getActivePlayer() {
        return this.activePlayer;
    }

    @Override
    public boolean readPacket(final ByteBuffer buffer) {
        AbstractClientPacket<TeraGameConnection> packet = null;

        switch (this.state) {
            case CONNECTED:
                packet = new KeyClientPacket(buffer, this);
            break;

            case AUTHENTICATED:
                short id = buffer.getShort();

                Class<? extends AbstractClientPacket<TeraGameConnection>> packetClass = ClientPacketHandler.getClientPacket(id);
                if (packetClass == null) {
                    log.error(PrintUtils.toHex(buffer) + "\n");
                    buffer.clear();
                    return true;
                }

                try {
                    packet = packetClass.getConstructor(ByteBuffer.class, TeraGameConnection.class).newInstance(buffer, this);
                }
                catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            break;

            default:
                throw new RuntimeException("Trying to readPacket while connection is in unknown state");
        }

        if (packet != null) {
            if (packet.read()) {
                lock.lock();
                new Thread(packet).start();
                lock.unlock();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean writePacket(final ByteBuffer buffer) {
        synchronized (gate) {
            if (this.sendMsgQueue.isEmpty()) {
                return false;
            }

            AbstractServerPacket<TeraGameConnection> packet = this.sendMsgQueue.removeFirst();

            Short opcode = ServerPacketHandler.getServerPacketId(packet.getClass());
            if (opcode == null) {
                return false;
            }

            packet.setOpcode(opcode);
            packet.write(this, buffer);
            return true;
        }
    }

    @Override
    public boolean hasPacketToRead() {
        return true;
    }

    public boolean hasPacketToWrite() {
        return !this.sendMsgQueue.isEmpty();
    }

    // UTILITY
    public void sendPacket(AbstractServerPacket<TeraGameConnection> packet) {
        synchronized (gate) {
            sendMsgQueue.addLast(packet);
            this.enableWriteInterest();
        }
    }
}
