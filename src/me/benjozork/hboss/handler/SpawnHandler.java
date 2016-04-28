package me.benjozork.hboss.handler;

import me.benjozork.hboss.object.BossSpawner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjozork on 2016-04-27.
 */
public class SpawnHandler {

    private static List<BossSpawner> spawns = new ArrayList<>();

    public static List<BossSpawner> getSpawns() {
         return new ArrayList<>(spawns);
    }

    public static void addSpawn(BossSpawner s) {
        spawns.add(s);
    }

    public static void removeSpawn(String name) {
        for (BossSpawner s : spawns) {
            if (s.getName().equalsIgnoreCase(name)) {
                spawns.remove(s);
                return;
            }
        }
    }

}
