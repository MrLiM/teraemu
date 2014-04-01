package com.angelis.tera.game.config;

import com.angelis.tera.common.config.Property;

public class PlayerConfig {
    
    @Property(key = "player.name.pattern", defaultValue = "[A-Za-z]*")
    public static String PLAYER_NAME_PATTERN;
    
    @Property(key = "player.quit.timeout", defaultValue = "10")
    public static int PLAYER_QUIT_TIMEOUT;
    
    @Property(key = "player.delete.timeout", defaultValue = "3600")
    public static int PLAYER_DELETE_TIMEOUT;

    @Property(key = "player.max.level", defaultValue = "60")
    public static int PLAYER_MAX_LEVEL;

    @Property(key = "player.auto.save.delay", defaultValue = "900")
    public static int PLAYER_AUTO_SAVE_DELAY;
    
    @Property(key = "player.level.min.global.chat", defaultValue = "19")
    public static int PLAYER_LEVEL_MIN_GLOBAL_CHAT;
}
