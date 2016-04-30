package me.benjozork.hboss.object;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Created by Benjozork on 2016-04-27.
 */
public enum BossAttribute {

    EXPLOSION(new Runnable() {
        @Override
        public void run() {
            World w = e.getEntity().getWorld();
            Location l = e.getEntity().getLocation();
            w.createExplosion(l.getX(), l.getY(), l.getZ(), 3f, false, false);
        }
    }),
    FIREBALL(new Runnable() {
        @Override
        public void run() {

        }
    }),
    SPEED(new Runnable() {
        @Override
        public void run() {

        }
    }),
    LIGHTNING(new Runnable() {
        @Override
        public void run() {

        }
    }),
    FORCEFIELD(new Runnable() {
        @Override
        public void run() {

        }
    }),
    ARMY(new Runnable() {
        @Override
        public void run() {

        }
    }),
    TELEPORT(new Runnable() {
        @Override
        public void run() {

        }
    });

    private Runnable r;
    private static BossEntity e;

    BossAttribute(Runnable r) {
        this.r = r;
    }

    public void execute(BossEntity b) {
        e = b;
        r.run();
    }

}
