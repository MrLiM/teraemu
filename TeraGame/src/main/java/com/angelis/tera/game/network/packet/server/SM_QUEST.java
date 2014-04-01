package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;
import java.util.List;

import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;
import com.angelis.tera.game.xml.entity.QuestEntity;

public class SM_QUEST extends TeraServerPacket {

    private final QuestEntity quest;
    private final List<TeraCreature> npcs;
    
    public SM_QUEST(QuestEntity quest, List<TeraCreature> npcs) {
        this.quest = quest;
        this.npcs = npcs;
    }
    
    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        // 0100 0E00 0C00 00010000 0E00 0000 0100 4500 0100 6100 6E050000EEF37601030000000100000000000000010000000001010000000000000000000000000000000045000000C7EE4F47D8DD87C768DCB0C5D5000000750400000D000000610000000A000000
        writeH(byteBuffer, 1);
        writeH(byteBuffer, 15);
        writeH(byteBuffer, 12);
        writeB(byteBuffer, "00010000");
        writeH(byteBuffer, 15);
        writeH(byteBuffer, 0);
        
        writeH(byteBuffer, (short) npcs.size());
        int npcShift = (int) byteBuffer.position();
        writeH(byteBuffer, 0);
        
        writeH(byteBuffer, (short) this.quest.getCounters().size());
        int countersShift = (int) byteBuffer.position();
        writeH(byteBuffer, 0);

        /*writeD(byteBuffer, QuestData.QuestId);
        writeD(byteBuffer, QuestData.QuestId); //QuestUId???
        writeD(byteBuffer, QuestData.Step + 1);
        writeD(byteBuffer, 1);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, VisiblitySwitch);

        WriteC(byteBuffer, 0);
        WriteC(byteBuffer, (byte) (CountersComplete ? 1 : 0));
        WriteC(byteBuffer, 1);

        writeD(byteBuffer, 0);

        for (Npc npc in NpcList)
        {
            byteBuffer.Seek(npcShift, SeekOrigin.Begin);
            writeH(byteBuffer, (short) byteBuffer.BaseStream.Length);
            byteBuffer.Seek(0, SeekOrigin.End);

            writeH(byteBuffer, (short) byteBuffer.BaseStream.Position);
            npcShift = (int) byteBuffer.BaseStream.Position;
            writeH(byteBuffer, 0);

            WriteF(byteBuffer, npc.Position.X);
            WriteF(byteBuffer, npc.Position.Y);
            WriteF(byteBuffer, npc.Position.Z);
            writeD(byteBuffer, npc.SpawnTemplate.Type);
            writeD(byteBuffer, npc.SpawnTemplate.NpcId);
            writeD(byteBuffer, 13); //???
        }

        for (int counter in QuestData.Counters)
        {
            byteBuffer.Seek(countersShift, SeekOrigin.Begin);
            writeH(byteBuffer, (short) byteBuffer.BaseStream.Length);
            byteBuffer.Seek(0, SeekOrigin.End);

            writeH(byteBuffer, (short) byteBuffer.BaseStream.Position);
            countersShift = (int) byteBuffer.BaseStream.Position;
            writeH(byteBuffer, 0);

            writeD(byteBuffer, counter);
        }*/
    }

}
