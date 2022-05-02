package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class MobPickupEvent implements Listener {

    @EventHandler
    public void mobpickup(PlayerInteractAtEntityEvent e){
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType().equals(Material.EMERALD) &&
                p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§9§lSoulstone")){
            if(!e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                e.getRightClicked().remove();
                ItemStack i = p.getInventory().getItemInMainHand();
                ItemMeta im = i.getItemMeta();
                im.addEnchant(Enchantment.DURABILITY,1,true);
                im.setDisplayName("§9§l" + e.getRightClicked().getType().toString().toLowerCase().replaceAll("_", " "));
                im.setLore(Arrays.asList(e.getRightClicked().getType().toString()));
                i.setItemMeta(im);
                p.getInventory().setItemInMainHand(i);
                p.sendMessage(Main.prefix + "§byou picked up a §9§o" + e.getRightClicked().getType().toString().toLowerCase());
                e.getRightClicked().getLocation().getWorld().spawnParticle(Particle.TOTEM,e.getRightClicked().getLocation(), 100, 0.2,1,0.2,0.000001);
            }
        }
    }



    @EventHandler
    public void mobplace(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().getItemInMainHand().getType().equals(Material.EMERALD) && p.getInventory().getItemInMainHand().getItemMeta().hasEnchants() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && p.isSneaking()){
            ItemMeta item = p.getInventory().getItemInMainHand().getItemMeta();
            String stringtype = item.getLore().get(0);
            EntityType type = EntityType.valueOf(stringtype);
            e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5,1,0.5), type);
            p.getInventory().setItemInMainHand(newItem("§9§lSoulstone", Material.EMERALD));
        }
    }




    public ItemStack newItem(String name, Material mat){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        return item;
    }




}
