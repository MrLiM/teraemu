package com.angelis.tera.game.network.packet.client;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.AccountService;

public class CM_AUTH extends TeraClientPacket {

    private static Logger log = Logger.getLogger(CM_AUTH.class.getName());
    
    private String login;
    private String password;
    
    public CM_AUTH(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        this.readH(); // unk1
        this.readH(); // unk2
        int length = this.readH(); // length
        this.readB(5); // unk3
        this.readD(); // unk4
        this.login = this.readS();
        try {
            this.password = new String(this.readB(length), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void runImpl() {
        AccountService.getInstance().authorizeAccount(this.getConnection(), login, password);
    }
}
