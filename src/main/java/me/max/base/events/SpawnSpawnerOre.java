package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.ArrayList;

public class SpawnSpawnerOre implements Listener {

    static Main plugin;

    public SpawnSpawnerOre(Main instance) {
        plugin = instance;
    }

    static ArrayList<Block> nore = new ArrayList<>();


    public void changeRandomBlock(Player player, int radius) {

        Location playerLoc = player.getLocation();
        double pX = playerLoc.getX();
        double pY = playerLoc.getY();
        double pZ = playerLoc.getZ();

        first:
        for (int x = -(radius); x <= radius; x ++)
        {
            second:
            for (int y = -(radius); y <= radius; y ++)
            {
                third:
                for (int z = -(radius); z <= radius; z ++)
                {
                    if(player.getWorld().getBlockAt((int)pX+x, (int)pY+y, (int)pZ+z).getType().equals(Material.AIR)){return;}
                    SpawnSpawnerOre.spawnOre(player.getWorld().getBlockAt((int)pX+x, (int)pY+y, (int)pZ+z));
                    nore.add(player.getWorld().getBlockAt((int)pX+x, (int)pY+y, (int)pZ+z));
                    break first;
                }
            }
        }
    }


    @EventHandler
    public void onOreBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        for(int i = 0; i<nore.size() ;i++){
            if(nore.get(i).getLocation().equals(e.getBlock().getLocation())){
                p.sendMessage("You mined spawnerpoints");
                nore.remove(e.getBlock());
            }
        }
    }


    @EventHandler
    public void onAppleEat(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        if(e.getItem().getType().equals(Material.APPLE)){
            changeRandomBlock(p,10);
            p.sendMessage("you ate an apple");
        }
    }



    public static void spawnOre(Block block){
        Location location = block.getLocation();
        Shulker shulker = (Shulker) location.getWorld().spawn(location.add(0.5,0,0.5), Shulker.class);
        shulker.setInvulnerable(true);
        shulker.setInvisible(true);
        shulker.setGlowing(true);
        shulker.setAI(false);
        shulker.setCollidable(true);
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                shulker.remove();
                nore.remove(block);
            }
        },300);
    }
}
