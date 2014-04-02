package com.angelis.tera.game.services;

import org.apache.log4j.Logger;

import com.angelis.tera.common.lang.IsObserver;
import com.angelis.tera.game.config.PlayerConfig;
import com.angelis.tera.game.controllers.enums.Right;
import com.angelis.tera.game.models.CreatureEventTypeEnum;
import com.angelis.tera.game.models.enums.ChatTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.SystemMessages;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_CHAT;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_WHISPER;

public class ChatService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(ChatService.class.getName());
    
    /** INSTANCE */
    private static ChatService instance;

    private ChatService() {
    }

    public void onInit() {
        log.info("ChatService started");
    }

    public void onDestroy() {
    }

    public void onPlayerChat(Player player, String chat, ChatTypeEnum chatType) {
        // Chat is a admin command and player has access
        if (AdminService.getInstance().onPlayerChat(player, chat)) {
            return;
        }
        
        // Chat is an user command
        if (UserService.getInstance().onPlayerChat(player, chat)) {
            return;
        }
        
        if (!player.getController().can(Right.TALK)) {
            log.info("Muted player "+player.getName()+" tryed to talk");
            player.getConnection().sendPacket(new SM_PLAYER_CHAT(player, "Vous êtes muté", ChatTypeEnum.SYSTEM));
            return;
        }
        
        switch (chatType) {
            case GENERAL:
                if (player.getAccount().getAccess() == 0 && player.getLevel() < PlayerConfig.PLAYER_LEVEL_MIN_GLOBAL_CHAT) {
                    player.getConnection().sendPacket(SystemMessages.CHARACTER_MUST_BE_LEVEL_TO_USE_THIS_CHANNEL(String.valueOf(PlayerConfig.PLAYER_LEVEL_MIN_GLOBAL_CHAT)));
                    return;
                }
            break;
            
            default:
        }

        TeraServerPacket packet = new SM_PLAYER_CHAT(player, chat, chatType);
        switch (chatType) {
            case SAY:
                for (IsObserver<CreatureEventTypeEnum> observer : player.getObservers()) {
                    if (observer instanceof Player) {
                        ((Player) observer).getConnection().sendPacket(packet);
                    }
                }
                player.getConnection().sendPacket(packet);
            break;
            
            case AREA:
                int areaId = WorldService.getInstance().getAreaByMapId(player.getWorldPosition().getMapId());
                for (IsObserver<CreatureEventTypeEnum> observer : WorldService.getInstance().getPlayersByArea(areaId)) {
                    if (observer instanceof Player) {
                        ((Player) observer).getConnection().sendPacket(packet);
                    }
                }
            break;
            
            default:
                for (TeraGameConnection connection : WorldService.getInstance().getAllOnlineConnections()) {
                    connection.sendPacket(packet);
                }
        }
        
    }
    
    public void onPlayerWhisper(Player player, String targetName, String whisper) {
        Player targetPlayer = WorldService.getInstance().getOnlinePlayerWithName(targetName);
        if (targetPlayer == null) {
            player.getConnection().sendPacket(SystemMessages.WHISP_PLAYER_NOT_ONLINE());
            return;
        }
        
        if (targetPlayer.equals(player)) {
            player.getConnection().sendPacket(SystemMessages.WHISP_INVALID_TARGET());
            return;
        }

        if (targetPlayer.getBlockeds().contains(player)) {
            player.getConnection().sendPacket(SystemMessages.CHARACTER_IGNORE_YOUR_WHISP(targetPlayer.getName()));
            return;
        }

        TeraServerPacket packet = new SM_PLAYER_WHISPER(player, targetPlayer, whisper);
        player.getConnection().sendPacket(packet);
        targetPlayer.getConnection().sendPacket(packet);
    }

    /** SINGLETON */
    public static final ChatService getInstance() {
        if (instance == null) {
            instance = new ChatService();
        }
        return instance;
    }
}
