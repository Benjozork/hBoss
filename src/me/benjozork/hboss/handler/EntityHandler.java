package me.benjozork.hboss.handler;

import me.benjozork.hboss.object.BossEntity;

import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjozork on 2016-04-27.
 */
public class EntityHandler {

    private static List<BossEntity> entities = new ArrayList<>();

    public static List<BossEntity> getEntities() {
        return new ArrayList<>(entities);
    }

    public static void addEntity(BossEntity b) {
        entities.add(b);
    }

    public static void removeEntity(BossEntity b) {
        entities.remove(b);
    }

    public static BossEntity getBoss(LivingEntity e) {
        for (BossEntity b : entities) {
            if (b.getEntity().equals(e)) return b;
        }
        return null;
    }

}
