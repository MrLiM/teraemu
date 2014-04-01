package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.models.CreatureBaseStats;
import com.angelis.tera.game.models.CreatureCurrentStats;
import com.angelis.tera.game.models.enums.StatsEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;
import com.angelis.tera.game.services.PlayerService;

public class SM_PLAYER_STATS extends TeraServerPacket {

    private final Player player;

    public SM_PLAYER_STATS(Player player) {
        this.player = player;
    }

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        CreatureBaseStats baseStats = this.player.getBaseStats();
        CreatureCurrentStats currentStats = this.player.getCreatureCurrentStats();
        
        ////// TO REMOVE ///////////////
        currentStats.setAttack(1);
        currentStats.setDefense(1);
        currentStats.setAttackSpeed(60);
        currentStats.setBalance(10);
        currentStats.setBalanceFactor(10);
        currentStats.setCritChance(10);
        currentStats.setEndurance(10);
        currentStats.setImpact(10);
        currentStats.setImpactFactor(10);
        currentStats.setPower(10);
        currentStats.setStamina(100);
        
        /** LIFE & MANA BARS */
        writeD(byteBuffer, this.player.getCreatureCurrentStats().getHp()); // current hp
        writeD(byteBuffer, this.player.getCreatureCurrentStats().getMp()); // current mp
        writeD(byteBuffer, 0); // unk
        writeD(byteBuffer, this.player.getCreatureCurrentStats().getHp()); // max hp TODO
        writeD(byteBuffer, this.player.getCreatureCurrentStats().getMp()); // max mp TODO
        
        /** STATS */
        writeD(byteBuffer, currentStats.getPower());
        writeD(byteBuffer, currentStats.getEndurance());
        writeD(byteBuffer, currentStats.getImpactFactor());
        writeD(byteBuffer, currentStats.getBalanceFactor());
        writeH(byteBuffer, currentStats.getSpeed());
        writeH(byteBuffer, currentStats.getAttackSpeed());
        
        writeH(byteBuffer, 0);

        /** Critical stats */
        //baseStats.CritChanse
        writeF(byteBuffer, currentStats.getCritChance());
        //baseStats.CritResist
        writeF(byteBuffer, currentStats.getCritResist());
        
        writeB(byteBuffer, "7C42000000");
        writeC(byteBuffer, 0x40); // Crit how much times?

        writeD(byteBuffer, currentStats.getAttack()); //min attack
        writeD(byteBuffer, currentStats.getAttack()); //max attack
        writeD(byteBuffer, currentStats.getDefense());
        writeH(byteBuffer, currentStats.getImpact());
        writeH(byteBuffer, currentStats.getBalance());

        writeH(byteBuffer, 0);
        
        /** Resists */
        //baseStats.WeakeningResist
        writeF(byteBuffer, 0); // Weakening
        //baseStats.PeriodicResist
        writeF(byteBuffer, 0); // Periodic
        //baseStats.StunResist
        writeF(byteBuffer, 0); // Stun

        /** Region additional stats */
        writeD(byteBuffer, 0); // power bonus
        writeD(byteBuffer, 0); // endurance bonus
        writeH(byteBuffer, 0); // impact bonus
        writeH(byteBuffer, 0); // balance bonus
        writeH(byteBuffer, 0); // speed bonus
        writeH(byteBuffer, 0); // speed bonus again ?
        writeH(byteBuffer, 0); // attack speed

        //player.GameStats.CritChanse - baseStats.CritChanse
        writeF(byteBuffer, 0);
        //player.GameStats.CritResist - baseStats.CritResist
        writeF(byteBuffer, 0);
        writeD(byteBuffer, 0);

        //player.GameStats.Attack - baseStats.Attack
        writeD(byteBuffer, 0); //min attack
        //player.GameStats.Attack - baseStats.Attack
        writeD(byteBuffer, 0); //max attack
        //player.GameStats.Defense - baseStats.Defense
        writeD(byteBuffer, 0);
        //(short)(player.GameStats.Impact - baseStats.  Impact)
        writeH(byteBuffer, 0);
        //(short)(player.GameStats.Balance - baseStats.Balance)
        writeH(byteBuffer, 0);

        //player.GameStats.WeakeningResist - baseStats.WeakeningResist
        writeF(byteBuffer, 0); // Weakening
        //player.GameStats.PeriodicResist - baseStats.PeriodicResist
        writeF(byteBuffer, 0); // Periodic
        //player.GameStats.StunResist - baseStats.StunResist
        writeF(byteBuffer, 0); // Stun
        
        writeD(byteBuffer, 0);
        writeD(byteBuffer, 0);
        
        writeH(byteBuffer, this.player.getLevel()); // Level
        writeH(byteBuffer, this.player.getPlayerMode().getValue());

        if (this.player.getCreatureCurrentStats().getStamina() > 100) {
            writeC(byteBuffer, 4); // Abundnt stamina
        }
        else if (this.player.getCreatureCurrentStats().getStamina() > 20) {
            writeC(byteBuffer, 3); // Normal stamina
        }
        else {
            writeC(byteBuffer, 2); // Poor stamina
        }

        writeB(byteBuffer, "0001");

        // this.player.GameStats.MaxHp - this.player.GameStats.MaxHp;
        writeD(byteBuffer, (int) (PlayerService.getInstance().getCreatureBaseStats(this.player, StatsEnum.HP) * (this.player.getCreatureCurrentStats().getStamina() / 120.0)));
        
        // this.player.GameStats.MaxMp - this.player.GameStats.MaxMp
        writeD(byteBuffer, (int) (PlayerService.getInstance().getCreatureBaseStats(this.player, StatsEnum.HP) * (this.player.getCreatureCurrentStats().getStamina() / 120.0)));
        
        writeD(byteBuffer, this.player.getCreatureCurrentStats().getStamina());
        writeD(byteBuffer, 120);
        writeB(byteBuffer, "000000000000000000000000000000004D0000004D0000000000000000000000401F000003000000");
                            
    }
}
