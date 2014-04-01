package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.enums.StorageTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.models.storage.Storage;
import com.angelis.tera.game.models.storage.enums.InventorySlotEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

/**
 * @author Angelis
 *
 * This packet is sent to this client and hold information about his character (after he request enter world)
 */
public class SM_PLAYER_INIT extends TeraServerPacket {

    private final Player player;

    public SM_PLAYER_INIT(Player player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        writeH(byteBuffer, 0); //Name shift
        
        writeH(byteBuffer, 0); // Details1 shift
        writeH(byteBuffer, this.player.getPlayerAppearance().getDetails1().length);
        
        writeH(byteBuffer, 0); // Details2 shift
        writeH(byteBuffer, this.player.getPlayerAppearance().getDetails2().length);

        writeD(byteBuffer, this.player.getRaceGenderClassValue());
       
        //FC2A0000 1F2A0D00
        writeUid(byteBuffer, this.player);
        
        //0E000000
        writeD(byteBuffer, 15);
        
        //68130000
        writeD(byteBuffer, this.player.getId());

        writeB(byteBuffer, "000000000100000000460000006E000000");

        writeB(byteBuffer, this.player.getPlayerAppearance().getData()); //this.playerData

        writeH(byteBuffer, 1); //Online?    
        writeH(byteBuffer, this.player.getLevel()); //Level
        
        writeB(byteBuffer, "020000000400010001000000000000000000");
        
        writeQ(byteBuffer, this.player.getExperience());
        writeQ(byteBuffer, this.player.getExpShown());
        writeQ(byteBuffer, this.player.getExpNeeded());

        writeD(byteBuffer, 0); //unk
        
        writeB(byteBuffer, "8F190000");//rested current
        writeB(byteBuffer, "8F190000");//rested max

        writeB(byteBuffer, "0000803F00000000"); //unk
        
        Storage storage = this.player.getStorage(StorageTypeEnum.INVENTORY);
        writeD(byteBuffer, storage.getStorageItem(InventorySlotEnum.WEAPON).getItemId());
        writeD(byteBuffer, storage.getStorageItem(InventorySlotEnum.ARMOR).getItemId());
        writeD(byteBuffer, storage.getStorageItem(InventorySlotEnum.GLOVES).getItemId());
        writeD(byteBuffer, storage.getStorageItem(InventorySlotEnum.FOOT).getItemId());
        writeD(byteBuffer, storage.getStorageItem(InventorySlotEnum.HAIR).getItemId());
        writeD(byteBuffer, storage.getStorageItem(InventorySlotEnum.FACE).getItemId());
        
        writeD(byteBuffer, 0);

        writeB(byteBuffer, "A228B7110000000001000000000000000000000000000000000000000000000000");

        // TODO item color
        /*writeD(byteBuffer, InventoryService.getInstance().getItemAtSlot(this.player, InventorySlot.WEAPON).getColor());
        writeD(byteBuffer, InventoryService.getInstance().getItemAtSlot(this.player, InventorySlot.ARMOR).getColor());
        writeD(byteBuffer, InventoryService.getInstance().getItemAtSlot(this.player, InventorySlot.GLOVES).getColor());
        writeD(byteBuffer, InventoryService.getInstance().getItemAtSlot(this.player, InventorySlot.FOOT).getColor());
        writeD(byteBuffer, InventoryService.getInstance().getItemAtSlot(this.player, InventorySlot.HAIR).getColor());
        writeD(byteBuffer, InventoryService.getInstance().getItemAtSlot(this.player, InventorySlot.FACE).getColor());*/
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);

        writeB(byteBuffer, "0001");//On/offline?

        writeD(byteBuffer, 0); //Item (Skin Head)
        writeD(byteBuffer, 0); //Item (Skin Face)
        writeD(byteBuffer, 0); //Item (Skin Backpack)?
        writeD(byteBuffer, 0); //Item (Skin Weapon)
        writeD(byteBuffer, 0); //Item (Skin Armor)
        writeD(byteBuffer, 0); //Unk possible Item?

        writeB(byteBuffer, "000000000000000000000000010000000000000000000000000000803F"); //unk
        
        this.writeBufferPosition(byteBuffer, 4, Size.H);
        writeS(byteBuffer, this.player.getName());

        this.writeBufferPosition(byteBuffer, 6, Size.H);
        writeB(byteBuffer, this.player.getPlayerAppearance().getDetails1());

        this.writeBufferPosition(byteBuffer, 10, Size.H);
        writeB(byteBuffer, this.player.getPlayerAppearance().getDetails2());
    }
}
