package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class RightWithChest implements Listener {

    @EventHandler
    public void rightclick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getHand().equals(EquipmentSlot.HAND) && e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(p.getInventory().getItemInMainHand().getType().equals(Material.PAPER)){
                hulp(p);
            }
        }
    }



    public void hulp(Player p){
        Inventory inv = Bukkit.createInventory(null,54, "lol");
        for(int i = 0; i<inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.OAK_SIGN, Integer.toString(i)));};
        p.openInventory(inv);
    }



}
