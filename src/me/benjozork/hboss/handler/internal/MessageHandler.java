package me.benjozork.hboss.handler.internal;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Benjozork on 2016-04-26.
 */
public class MessageHandler {
    private static FileConfiguration config;


    public static String getMessage(String id) {
        return ChatColor.translateAlternateColorCodes('&', ConfigurationHandler.getMessagesConfig().getString(id));
    }
}
