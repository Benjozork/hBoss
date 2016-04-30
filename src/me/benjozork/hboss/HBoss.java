package me.benjozork.hboss;

import me.benjozork.hboss.handler.internal.CommandHandler;
import me.benjozork.hboss.handler.internal.ConfigurationHandler;
import me.benjozork.hboss.handler.internal.MessageHandler;
import me.benjozork.hboss.handler.SpawnHandler;
import me.benjozork.hboss.internal.ConfigAccessor;

import me.benjozork.hboss.object.BossSpawner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Created by Benjozork on 2016-04-26.
 */
public class HBoss extends JavaPlugin {

    private ConfigAccessor spanwsConfig  = new ConfigAccessor(this, "spawns.yml");
    private Random rand = new Random();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        ConfigurationHandler.setConfig(getConfig());
        MessageHandler.setConfig(getConfig());
        getCommand("hboss").setExecutor(new CommandHandler(this));

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            @Override
            public void run() {
                SpawnHandler.getSpawns().get(rand.nextInt(SpawnHandler.getSpawns().size())).spawn();
            }

        }, 0L, getConfig().getLong("delay"));

    }

    @Override
    public void onDisable() {
        for (BossSpawner b : SpawnHandler.getSpawns()) {
            spanwsConfig.getConfig().set(b.getName(), b);
        }
        spanwsConfig.saveConfig();
        System.out.println("[hBoss] Saved " + SpawnHandler.getSpawns().size() + " spawns to config.");
    }
}
