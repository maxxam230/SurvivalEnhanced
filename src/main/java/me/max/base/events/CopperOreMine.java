package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class CopperOreMine implements Listener {

    static Main plugin;

    public CopperOreMine(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void CopperOreMine(BlockBreakEvent e){
        Player p = e.getPlayer();
        Material iph = p.getInventory().getItemInMainHand().getType();
        ItemMeta iphm = p.getInventory().getItemInMainHand().getItemMeta();
        if(e.getBlock().getType().equals(Material.COPPER_ORE) || e.getBlock().getType().equals(Material.DEEPSLATE_COPPER_ORE)){
            if (iph.equals(Material.STONE_PICKAXE) || iph.equals(Material.IRON_PICKAXE) || iph.equals(Material.GOLDEN_PICKAXE) || iph.equals(Material.DIAMOND_PICKAXE)) {
                if(iphm.hasEnchant(Enchantment.SILK_TOUCH)){return;}
                if(iphm.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) && (iphm.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1)){
                    plugin.addcoins(p,10,true, false);
                } else if(iphm.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) && (iphm.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)==2)){
                    plugin.addcoins(p,15,true, false);
                } else if(iphm.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) && (iphm.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)==3)){
                    plugin.addcoins(p,20,true, false);
                } else {
                    plugin.addcoins(p,5,true, false);
                }
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                spawnParticle(e.getBlock());
            } else if(iph.equals(Material.NETHERITE_PICKAXE)){
                if(iphm.hasEnchant(Enchantment.SILK_TOUCH)){return;}
                if(iphm.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) && (iphm.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1)){
                    plugin.addcoins(p,20,true, false);
                } else if(iphm.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) && (iphm.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)==2)){
                    plugin.addcoins(p,30,true, false);
                } else if(iphm.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) && (iphm.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)==3)){
                    plugin.addcoins(p,40,true, false);
                } else {
                    plugin.addcoins(p,10, true, false);
                }
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                spawnParticle(e.getBlock());
            }
        }
    }

    public void spawnParticle(Block b){
        Location loc = b.getLocation();
        b.getWorld().spawnParticle(Particle.WAX_ON, loc.add(0.5,0.5,0.5), 100, 0.2,0.2,0.2, 0.00001);
    }




}
