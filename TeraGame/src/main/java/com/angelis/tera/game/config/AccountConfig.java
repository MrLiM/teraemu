package com.angelis.tera.game.config;

import com.angelis.tera.common.config.Property;

public class AccountConfig {
    @Property(key = "account.autocreate", defaultValue = "true")
    public static boolean ACCOUNT_AUTOCREATE;
}
