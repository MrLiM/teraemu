package com.angelis.tera.game.services;

import java.util.Map;
import java.util.Map.Entry;

import javolution.util.FastMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.angelis.tera.game.command.user.AbstractUserCommand;
import com.angelis.tera.game.command.user.SaveCommand;
import com.angelis.tera.game.config.UserConfig;
import com.angelis.tera.game.models.enums.ChatTypeEnum;
import com.angelis.tera.game.models.player.Player;
import com.angelis.tera.game.network.packet.server.SM_PLAYER_CHAT;

public class UserService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(UserService.class.getName());
    
    /** INSTANCE */
    private static UserService instance;
    
    private Map<String, AbstractUserCommand> commands = new FastMap<String, AbstractUserCommand>();
    
    @Override
    public void onInit() {
        commands.put("save", new SaveCommand());
        
        log.info("UserService started");
    }

    @Override
    public void onDestroy() {
    }
    
    public boolean onPlayerChat(Player player, String chat) {
        String commandChat = chat.replace("<FONT>", "").replace("</FONT>", "");
        if (!commandChat.startsWith(UserConfig.USER_COMMAND_PREFIX)) {
            return false;
        }
        
        String[] arguments = commandChat.split(" ");
        if (arguments.length == 0) {
            return false;
        }
        
        String commandName = arguments[0].replace(UserConfig.USER_COMMAND_PREFIX, "");
        String[] commandArguments = ArrayUtils.removeElement(arguments, arguments[0]);
        for (Entry<String, AbstractUserCommand> entry : commands.entrySet()) {
            if (commandName.startsWith(entry.getKey())) {
                if (!entry.getValue().getAllowedAccountTypes().contains(player.getAccount().getAccountType())) {
                    player.getConnection().sendPacket(new SM_PLAYER_CHAT(player, "You account type does not allow you to use this command", ChatTypeEnum.SYSTEM));
                    return true;
                }
                
                if (commandArguments.length < entry.getValue().getArgumentCount() || !entry.getValue().checkArguments(commandArguments)) {
                    player.getConnection().sendPacket(new SM_PLAYER_CHAT(player, entry.getValue().getSyntax(), ChatTypeEnum.SYSTEM));
                    return true;
                }
                
                entry.getValue().execute(player.getConnection(), commandArguments);
                return true;
            }
        }
        
        return false;
    }

    /** SINGLETON */
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
}
