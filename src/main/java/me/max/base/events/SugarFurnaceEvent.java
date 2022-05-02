package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class SugarFurnaceEvent implements Listener {

    Main plugin;

    public SugarFurnaceEvent(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void sugarfurnace(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getClickedBlock().getType().equals(Material.FURNACE)){
                if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.SUGAR)){
                    Block block = e.getClickedBlock();
                    Furnace furnace = (Furnace) block.getState();
                    if(furnace.getCookSpeedMultiplier()>1){e.getPlayer().sendMessage(Main.errorprefix + "§cYou cannot boost again"); return;};
                    furnace.setCookSpeedMultiplier(10);
                    furnace.update(true);

                    e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                    e.getPlayer().sendMessage(Main.prefix + "§6Boosted your furnace");




                    int particle = 1;
                    particle = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            block.getWorld().spawnParticle(Particle.FLAME,block.getLocation().add(0.5,0.5,0.5), 20, 0.5,0.5,0.5,0.00001);
                        }
                    },0,10);
                    Integer finalParticle = particle;
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Furnace furnace1 = (Furnace) e.getClickedBlock().getState();
                            furnace1.setCookSpeedMultiplier(1);
                            furnace1.update(true);
                            Bukkit.getScheduler().cancelTask(finalParticle);
                        }
                    },200);


                    e.setCancelled(true);
                    e.getPlayer().closeInventory();
                }
            }
        }
    }

}
