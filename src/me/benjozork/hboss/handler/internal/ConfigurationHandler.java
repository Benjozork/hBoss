package me.benjozork.hboss.handler.internal;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Benjozork on 2016-04-29.
 */
public class ConfigurationHandler {

    private static FileConfiguration _config, _spawnsConfig, _messagesConfig;

    public static void init(FileConfiguration config, FileConfiguration spawnsConfig, FileConfiguration messagesConfig) {
        _config = config;
        _spawnsConfig = spawnsConfig;
        _messagesConfig = messagesConfig;
    }

    public static FileConfiguration getConfig() {
        return _config;
    }

    public static FileConfiguration getSpawnsConfig() {
        return _spawnsConfig;
    }

    public static FileConfiguration getMessagesConfig() {
        return _messagesConfig;
    }
}
