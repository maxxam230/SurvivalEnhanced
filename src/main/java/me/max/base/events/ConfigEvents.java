package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConfigEvents implements Listener {

    Main  plugin;

    public ConfigEvents(Main instance) {
        plugin = instance;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!plugin.getConfig().contains(e.getPlayer().getName())){
            plugin.getConfig().set(e.getPlayer().getName() + ".coins" , 0);
            //HOSTILE MOB KILLS
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.zombie" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.skeleton" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.drowned" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.spider" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.ravenger" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.magma_cube" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.creeper" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.guardian" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.slime" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.blaze" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.enderman" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.witch" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.piglin" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.shulker" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.pillager" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.wither_skeleton" , 0);
            //PASSIVE MOB KILLS
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.parrot" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.squid" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.glow_squid" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.polarbear" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.turtle" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.goat" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.wolf" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.panda" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.fox" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.rabbit" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.strider" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.chicken" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.sheep" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.pig" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.cow" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.bee" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.mushroom_cow" , 0);

            plugin.saveConfig();
        }
    }

    @EventHandler
    public void droptnt(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(e.getItemDrop().getItemStack().getType() == Material.TNT){
            plugin.getConfig().set(p.getName(), null);

            plugin.getConfig().set(e.getPlayer().getName() + ".coins" , 0);
            //HOSTILE MOBS KILLS
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.zombie" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.skeleton" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.drowned" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.spider" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.ravenger" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.magma_cube" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.creeper" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.guardian" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.slime" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.blaze" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.enderman" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.witch" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.piglin" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.shulker" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.pillager" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.wither_skeleton" , 0);
            //PASSIVE MOB KILLS
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.parrot" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.squid" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.glow_squid" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.polarbear" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.turtle" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.goat" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.wolf" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.panda" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.fox" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.rabbit" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.strider" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.chicken" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.sheep" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.pig" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.cow" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.bee" , 0);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.mushroom_cow" , 0);
            plugin.saveConfig();
            p.sendMessage("§c§lConfig has been reset");
            e.setCancelled(true);
        } else if(e.getItemDrop().getItemStack().getType().equals(Material.NETHER_STAR)){
            plugin.getConfig().set(p.getName(), null);
            plugin.getConfig().set(e.getPlayer().getName() + ".coins" , 0);
            //HOSTILE MOB KILLS
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.zombie" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.skeleton" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.drowned" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.spider" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.ravenger" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.magma_cube" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.creeper" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.guardian" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.slime" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.blaze" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.enderman" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.witch" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.piglin" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.shulker" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.pillager" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".hostile.wither_skeleton" , 2000);
            //PASSIVE MOB KILLS
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.parrot" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.squid" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.glow_squid" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.polarbear" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.turtle" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.goat" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.wolf" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.panda" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.fox" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.rabbit" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.strider" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.chicken" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.sheep" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.pig" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.cow" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.bee" , 2000);
            plugin.getConfig().set(e.getPlayer().getName() + ".passive.mushroom_cow" , 2000);
            plugin.saveConfig();
            p.sendMessage("§c§lUnlocked all mobs");
            e.setCancelled(true);
        }

    }


}
