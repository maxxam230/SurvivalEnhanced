package me.max.base.events;

import me.max.base.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobEvent implements Listener {

    static Main plugin;

    public KillMobEvent(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void killmob(EntityDeathEvent e){
        Entity entity = e.getEntity();
        if(!(entity instanceof Player)){
            if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){
                EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
                if(entityDamageByEntityEvent.getDamager() instanceof Player){
                    Player p = (Player)  entityDamageByEntityEvent.getDamager();
                    if(entity.getType().equals(EntityType.ZOMBIE)){
                        plugin.addmobkill(p, "zombie", "hostile");
                    } else if(entity.getType().equals(EntityType.SKELETON)){
                        plugin.addmobkill(p, "skeleton", "hostile");
                    } else if(entity.getType().equals(EntityType.DROWNED)){
                        plugin.addmobkill(p, "drowned", "hostile");
                    } else if(entity.getType().equals(EntityType.SPIDER)){
                        plugin.addmobkill(p, "spider", "hostile");
                    } else if(entity.getType().equals(EntityType.RAVAGER)){
                        plugin.addmobkill(p, "ravenger", "hostile");
                    } else if(entity.getType().equals(EntityType.MAGMA_CUBE)){
                        plugin.addmobkill(p, "magma_cube", "hostile");
                    } else if(entity.getType().equals(EntityType.CREEPER)){
                        plugin.addmobkill(p, "creeper", "hostile");
                    } else if(entity.getType().equals(EntityType.GUARDIAN)){
                        plugin.addmobkill(p, "guardian", "hostile");
                    } else if(entity.getType().equals(EntityType.SLIME)){
                        plugin.addmobkill(p, "slime", "hostile");
                    } else if(entity.getType().equals(EntityType.BLAZE)){
                        plugin.addmobkill(p, "blaze", "hostile");
                    } else if(entity.getType().equals(EntityType.ENDERMAN)){
                        plugin.addmobkill(p, "enderman", "hostile");
                    } else if(entity.getType().equals(EntityType.WITCH)){
                        plugin.addmobkill(p, "witch", "hostile");
                    } else if(entity.getType().equals(EntityType.PIGLIN)){
                        plugin.addmobkill(p, "piglin", "hostile");
                    } else if(entity.getType().equals(EntityType.SHULKER)){
                        plugin.addmobkill(p, "shulker", "hostile");
                    } else if(entity.getType().equals(EntityType.PILLAGER)){
                        plugin.addmobkill(p, "pillager", "hostile");
                    } else if(entity.getType().equals(EntityType.WITHER_SKELETON)){
                        plugin.addmobkill(p, "wither_skeleton", "hostile");
                        //PASSIVE
                    } else if(entity.getType().equals(EntityType.PARROT)){
                        plugin.addmobkill(p,"parrot", "passive");
                    } else if(entity.getType().equals(EntityType.SQUID)){
                        plugin.addmobkill(p,"squid", "passive");
                    } else if(entity.getType().equals(EntityType.GLOW_SQUID)){
                        plugin.addmobkill(p,"glow_squid", "passive");
                    } else if(entity.getType().equals(EntityType.POLAR_BEAR)){
                        plugin.addmobkill(p,"polar_bear", "passive");
                    } else if(entity.getType().equals(EntityType.TURTLE)){
                        plugin.addmobkill(p,"turtle", "passive");
                    } else if(entity.getType().equals(EntityType.GOAT)){
                        plugin.addmobkill(p,"goat", "passive");
                    } else if(entity.getType().equals(EntityType.WOLF)){
                        plugin.addmobkill(p,"wolf", "passive");
                    } else if(entity.getType().equals(EntityType.PANDA)){
                        plugin.addmobkill(p,"panda", "passive");
                    } else if(entity.getType().equals(EntityType.FOX)){
                        plugin.addmobkill(p,"fox", "passive");
                    } else if(entity.getType().equals(EntityType.RABBIT)){
                        plugin.addmobkill(p,"rabbit", "passive");
                    } else if(entity.getType().equals(EntityType.STRIDER)){
                        plugin.addmobkill(p,"strider", "passive");
                    } else if(entity.getType().equals(EntityType.CHICKEN)){
                        plugin.addmobkill(p,"chicken", "passive");
                    } else if(entity.getType().equals(EntityType.SHEEP)){
                        plugin.addmobkill(p,"sheep", "passive");
                    } else if(entity.getType().equals(EntityType.PIG)){
                        plugin.addmobkill(p,"pig", "passive");
                    } else if(entity.getType().equals(EntityType.COW)){
                        plugin.addmobkill(p,"cow", "passive");
                    } else if(entity.getType().equals(EntityType.BEE)){
                        plugin.addmobkill(p,"bee", "passive");
                    } else if(entity.getType().equals(EntityType.MUSHROOM_COW)){
                        plugin.addmobkill(p,"mushroom_cow", "passive");
                    }
                }
            }
        }
    }



}
