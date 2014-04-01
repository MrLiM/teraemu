package com.angelis.tera.game.services;

import java.util.Map;
import java.util.Map.Entry;

import javolution.util.FastMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.angelis.tera.game.command.admin.AbstractAdminCommand;
import com.angelis.tera.game.command.admin.AnnounceCommand;
import com.angelis.tera.game.command.admin.BanCommand;
import com.angelis.tera.game.command.admin.DemoteCommand;
import com.angelis.tera.game.command.admin.KickCommand;
import com.angelis.tera.game.command.admin.MuteCommand;
import com.angelis.tera.game.command.admin.PromoteCommand;
import com.angelis.tera.game.command.admin.ReloadCommand;
import com.angelis.tera.game.command.admin.SendFakePacketCommand;
import com.angelis.tera.game.command.admin.SetCommand;
import com.angelis.tera.game.command.admin.TeleportCommand;
import com.angelis.tera.game.command.admin.UnmuteCommand;
import com.angelis.tera.game.config.AdminConfig;
import com.angelis.tera.game.models.enums.ChatTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_CHAT;

public class AdminService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(AdminService.class.getName());
    
    /** INSTANCE */
    private static AdminService instance;
    
    private Map<String, AbstractAdminCommand> commands = new FastMap<String, AbstractAdminCommand>();

    private AdminService() {}
    
    public void onInit() {
        commands.put("announce", new AnnounceCommand());
        commands.put("teleport", new TeleportCommand());
        commands.put("mute", new MuteCommand());
        commands.put("unmute", new UnmuteCommand());
        commands.put("sendfakepacket", new SendFakePacketCommand());
        commands.put("promote", new PromoteCommand());
        commands.put("demote", new DemoteCommand());
        commands.put("set", new SetCommand());
        commands.put("reload", new ReloadCommand());
        commands.put("ban", new BanCommand());
        commands.put("kick", new KickCommand());
        
        log.info("AdminService started");
    }
    
    public void onDestroy() {}

    public boolean onPlayerChat(Player player, String chat) {
        String commandChat = chat.replace("<FONT>", "").replace("</FONT>", "");
        if (!commandChat.startsWith(AdminConfig.ADMIN_COMMAND_PREFIX)) {
            return false;
        }
        
        String[] arguments = commandChat.split(" ");
        if (arguments.length == 0) {
            return false;
        }
        
        String commandName = arguments[0].replace(AdminConfig.ADMIN_COMMAND_PREFIX, "");
        String[] commandArguments = ArrayUtils.removeElement(arguments, arguments[0]);
        for (Entry<String, AbstractAdminCommand> entry : commands.entrySet()) {
            if (commandName.startsWith(entry.getKey())) {
                if (player.getAccount().getAccess() >= entry.getValue().getAccessLevel()) {
                    if (commandArguments.length < entry.getValue().getArgumentCount() || !entry.getValue().checkArguments(commandArguments)) {
                        player.getConnection().sendPacket(new SM_PLAYER_CHAT(player, entry.getValue().getSyntax(), ChatTypeEnum.SYSTEM));
                        return true;
                    }
                    
                    entry.getValue().execute(player.getConnection(), commandArguments);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static final AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }
}
