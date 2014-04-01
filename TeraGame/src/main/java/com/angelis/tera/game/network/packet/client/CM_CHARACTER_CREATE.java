package com.angelis.tera.game.network.packet.client;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.player.PlayerAppearance;
import com.angelis.tera.game.models.player.enums.GenderEnum;
import com.angelis.tera.game.models.player.enums.PlayerClassEnum;
import com.angelis.tera.game.models.player.enums.RaceEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraClientPacket;
import com.angelis.tera.game.services.PlayerService;

public class CM_CHARACTER_CREATE extends TeraClientPacket {

    private final Player player = new Player();
    
    public CM_CHARACTER_CREATE(ByteBuffer byteBuffer, TeraGameConnection connection) {
        super(byteBuffer, connection);
    }

    @Override
    protected void readImpl() {
        PlayerAppearance playerAppearance = new PlayerAppearance();
        
        readH(); // nameShift
        
        readH(); // detailsShift1
        short detailsLength1 = readH();
        
        readH(); // detailsShift2
        short detailsLength2 = readH();

        player.setGender(GenderEnum.fromValue(readD()));
        player.setRace(RaceEnum.fromValue(readD()));
        player.setPlayerClass(PlayerClassEnum.fromValue(readD()));
        
        playerAppearance.setData(readB(8));

        readD();
        readC();

        player.setName(readS());

        playerAppearance.setDetails1(readB(detailsLength1));
        playerAppearance.setDetails2(readB(detailsLength2));
        
        player.setPlayerAppearance(playerAppearance);
    }

    @Override
    protected void runImpl() {
        PlayerService.getInstance().createPlayer(this.getConnection(), player);
    }
}
