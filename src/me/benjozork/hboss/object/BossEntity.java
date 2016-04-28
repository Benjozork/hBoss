package me.benjozork.hboss.object;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjozork on 2016-04-27.
 */
public class BossEntity {

    private LivingEntity entity;
    private List<BossAttribute> attributes = new ArrayList<>();
    private double health;

    public BossEntity(LivingEntity e, int healthMultiplier) {
        this.entity = e;
        this.health = Bukkit.getOnlinePlayers().size() * healthMultiplier - e.getMaxHealth();
    }

    public LivingEntity getEntity() {
        return this.entity;
    }

    public List<BossAttribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(BossAttribute a) {
        attributes.add(a);
    }

    public void removeAttribute(BossAttribute a) {
        attributes.remove(a);
    }

    public boolean hasAttribute(BossAttribute a) {
        return attributes.contains(a);
    }

    public void damage(double d) {
        if (health > 0) health = (health - d < 0 ? 0 : health - d); else entity.damage(d);
    }

}
