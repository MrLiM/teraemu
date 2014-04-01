package com.angelis.tera.game.services;

import org.apache.log4j.Logger;

import com.angelis.tera.game.config.AccountConfig;
import com.angelis.tera.game.delegate.AccountDelegate;
import com.angelis.tera.game.models.account.Account;
import com.angelis.tera.game.models.account.Options;
import com.angelis.tera.game.models.account.enums.AccountTypeEnum;
import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.server.SM_OPCODE_LESS_PACKET;

public class AccountService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(AccountService.class.getName());
    
    /** INSTANCE */
    private static AccountService instance;
    
    public void onInit() {
        log.info("AccountService started");
    }
    
    public void onDestroy() {}
    
    public void onAccountConnect(Account account) {
        TeraGameConnection connection = account.getConnection();
        
        // TODO those packets
        connection.sendPacket(new SM_OPCODE_LESS_PACKET("F8F000"));
        connection.sendPacket(new SM_OPCODE_LESS_PACKET("A6D20600000000000000"));
        connection.sendPacket(new SM_OPCODE_LESS_PACKET("EEBB01000200010000000000060000000100000000"));
        connection.sendPacket(new SM_OPCODE_LESS_PACKET("C1770E0056FF00000000000050006C0061006E0065007400440042005F00570034000000"));
    }
    
    public void onAccountDisconnect(Account account) {
        AccountDelegate.updateAccountModel(account);
    }
    
    public void authorizeAccount(TeraGameConnection connection, String login, String password) {
        log.info("Try authorizing "+login);
        Account account = AccountDelegate.readAccountModelByLogin(login);
        if (account == null && AccountConfig.ACCOUNT_AUTOCREATE) {
            account = new Account();
            account.setLogin(login);
            account.setPassword(password);
            account.setAccess(0);
            account.setAccountType(AccountTypeEnum.VETERAN);
            account.setOptions(new Options());
            AccountDelegate.createAccountModel(account);
        }
        
        if (account == null) {
            log.info("Account "+login+" tryed to login but this account isn't registered. Closing connection.");
            connection.close();
            return;
        }
        
        if (!account.getPassword().equals(password)) {
            log.info("Account "+login+" tryed to login with incorrect password. Closing connection.");
            connection.close();
            return;
        }
        
        if (account.isBanned()) {
            log.info("Banned account tryed to login. Closing connection.");
            connection.close();
            return;
        }
        
        connection.setAccount(account);

        this.onAccountConnect(account);
    }
    
    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }
}
