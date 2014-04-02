package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.Creature;
import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.enums.EmotionEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_CREATURE_EMOTE extends TeraServerPacket {

    private final TeraCreature teraCreature;
    private final Creature target;
    private final EmotionEnum emotion;
    private final boolean reset;
    
    public SM_CREATURE_EMOTE(TeraCreature teraCreature, Creature target, EmotionEnum emotion, boolean reset) {
        this.teraCreature = teraCreature;
        this.target = target;
        this.emotion = emotion;
        this.reset = reset;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeUid(byteBuffer, this.teraCreature);
        writeUid(byteBuffer, this.target);

        writeD(byteBuffer, reset ? 2 : 0);
        writeD(byteBuffer, this.emotion.value);
        writeD(byteBuffer, 0);
    }
}
