package me.benjozork.hboss.handler;

import me.benjozork.hboss.handler.internal.ConfigurationHandler;
import me.benjozork.hboss.object.BossSpawner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjozork on 2016-04-27.
 */
public class SpawnHandler {

    private static List<BossSpawner> spawns = new ArrayList<>();

    public static List<BossSpawner> getSpawns() {
         return spawns;
    }

    public static BossSpawner getSpawn(String name) throws IllegalArgumentException {
        for (BossSpawner s : spawns) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        throw new IllegalArgumentException("invalid boss spawn name \"" + name + "\"");
    }

    public static void addSpawn(BossSpawner s) {
        spawns.add(s);
    }

    public static void removeSpawn(String name) throws IllegalArgumentException {
        for (BossSpawner s : spawns) {
            if (s.getName().equalsIgnoreCase(name)) {
                spawns.remove(s);
                ConfigurationHandler.getSpawnsConfig().set(s.getName(), null);
                return;
            }
        }
        throw new IllegalArgumentException("invalid boss spawn name \"" + name + "\"");
    }

}
