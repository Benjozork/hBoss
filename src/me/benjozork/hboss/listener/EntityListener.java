package me.benjozork.hboss.listener;

import me.benjozork.hboss.handler.EntityHandler;
import me.benjozork.hboss.object.BossEntity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by Benjozork on 2016-04-27.
 */
public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) e.getEntity();

        if (EntityHandler.getBoss(entity) != null) { // Is a boss
            BossEntity boss = EntityHandler.getBoss(entity);
            boss.damage(e.getFinalDamage());
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) e.getEntity();

        if (EntityHandler.getBoss(entity) != null) { // Is a boss
            BossEntity boss = EntityHandler.getBoss(entity);
            //@TODO
        }
    }

}
