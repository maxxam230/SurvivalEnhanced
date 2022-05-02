package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnerPlaceEvent implements Listener {



    @EventHandler
    public void spawnerplace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(e.getBlockPlaced().getType().equals(Material.SPAWNER)){
            CreatureSpawner cs = (CreatureSpawner) e.getBlockPlaced().getState();
            cs.setSpawnedType(EntityType.valueOf(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().toUpperCase()));
            p.sendMessage(Main.prefix + "§2Placed an " + cs.getCreatureTypeName() + " spawner");
        }
    }

}
