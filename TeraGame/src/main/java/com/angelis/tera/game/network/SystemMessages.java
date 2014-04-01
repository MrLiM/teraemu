package com.angelis.tera.game.network;

import com.angelis.tera.game.network.packet.server.SM_SYSTEM_MESSAGE;

public class SystemMessages {

    // PLAYER
    public static final SM_SYSTEM_MESSAGE CONGRATULATION_YOU_ARE_LEVEL(String level) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@24", "level", level });
    }
    public static final SM_SYSTEM_MESSAGE YOU_HAVE_ABUNDANT_STAMINA() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@636" });
    }
    public static SM_SYSTEM_MESSAGE NO_INSTANCE_TO_REINIT() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@1269" });
    }
    public static SM_SYSTEM_MESSAGE FALL_DAMAGE(String damage) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@675", "Damage", damage });
    }

    // CHAT
    public static final SM_SYSTEM_MESSAGE WHISP_INVALID_TARGET() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@111" });
    }
    public static final SM_SYSTEM_MESSAGE WHISP_PLAYER_NOT_ONLINE() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@113" });
    }
    public static final SM_SYSTEM_MESSAGE CHARACTER_MUST_BE_LEVEL_TO_USE_THIS_CHANNEL(String level) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@1946", "Level", level });
    }
    public static final SM_SYSTEM_MESSAGE CHARACTER_IGNORE_YOUR_WHISP(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@1338", "UserName", playerName });
    }
    
    // FRIEND
    public static final SM_SYSTEM_MESSAGE FRIEND_ADD_UNKNOWN_TARGET() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@430" });
    }
    public static final SM_SYSTEM_MESSAGE FRIEND_ADD_SUCCESS(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@432", "UserName", playerName });
    }
    public static final SM_SYSTEM_MESSAGE FRIEND_ADDED_YOU(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@433", "UserName", playerName });
    }
    public static final SM_SYSTEM_MESSAGE FRIEND_MAX_ADD_REQUEST_IS_3_PER_DAY() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@434" });
    }
    public static final SM_SYSTEM_MESSAGE FRIEND_REMOVE_SUCCESS(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@436", "UserName", playerName });
    }
    public static final SM_SYSTEM_MESSAGE FRIEND_REMOVED_YOU(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@437", "UserName", playerName }); // NOT SURE
    }
    public static final SM_SYSTEM_MESSAGE FRIEND_ADD_INVALID_TARGET() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@441" });
    }
    
    
    // ACCOUNT
    public static final SM_SYSTEM_MESSAGE ACCOUNT_TERA_CLUB() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@2694", "AccountBenefitName", "@accountBenefit:433" });
    }
    public static final SM_SYSTEM_MESSAGE ACCOUNT_VETERAN() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@2694", "AccountBenefitName", "@accountBenefit:434" });
    }
    
    // LOADING SCREEN
    public static final SM_SYSTEM_MESSAGE YOU_CAN_JOIN_GUILD_VIA_SOCIAL_LINK() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@2810" });
    }
    
    // TERA
    public static final SM_SYSTEM_MESSAGE CONNECT_ON_TERA_EUROPE_FOR_LAST_NEWS() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@888" });
    }

    // TEAM
    public static final SM_SYSTEM_MESSAGE PLAYER_JOIN_THE_TEAM(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@151", "player", playerName });
    }
    public static final SM_SYSTEM_MESSAGE PLAYER_LEFT_THE_TEAM(String playerName) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@152", "player", playerName });
    }
    
    // REQUEST
    public static final SM_SYSTEM_MESSAGE REQUEST_PLAYER_NOT_FOUND() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@2090" });
    }
    public static SM_SYSTEM_MESSAGE REQUEST_CHOOSE_ANOTHER_PLAYER() {
        return new SM_SYSTEM_MESSAGE(new String[] { "@332" });
    }

    // CRAFT
    public static SM_SYSTEM_MESSAGE YOUR_MINING_HAS_INCREASED_TO(String level) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@67", "prof", level });
    }
    public static SM_SYSTEM_MESSAGE YOUR_PLANT_COLLECTING_HAS_INCREASED_TO(String level) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@68", "prof", level });
    }
    public static SM_SYSTEM_MESSAGE YOUR_BUG_HUNTING_HAS_INCREASED_TO(String level) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@69", "prof", level });
    }
    public static SM_SYSTEM_MESSAGE YOUR_ESSENCE_GATHERING_HAS_INCREASED_TO(String level) {
        return new SM_SYSTEM_MESSAGE(new String[] { "@70", "prof", level });
    }
    
    // CUSTOM
    public static final SM_SYSTEM_MESSAGE CUSTOM(String value) {
        return new SM_SYSTEM_MESSAGE(new String[] { value });
    }
}
