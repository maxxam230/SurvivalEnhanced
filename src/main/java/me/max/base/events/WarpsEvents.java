package me.max.base.events;

import me.max.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WarpsEvents implements Listener {

    ArrayList<UUID> addingwarp = new ArrayList<UUID>();

    static Main plugin;

    public WarpsEvents(Main instance) {
        plugin = instance;
    }

    public void newInventory(Player p){
        if(plugin.getConfig().contains("warps")) {
            List<String> warps = plugin.getConfig().getStringList("warps");
            Inventory inv = Bukkit.createInventory(null, 54, "Warps");
            for(int i = 0; i < warps.size(); i++){
                inv.setItem(i, plugin.getwarpitem(i));
            }
            p.openInventory(inv);
        } else {
            p.sendMessage(Main.errorprefix + "§cNo warps created yet");
        }
    }


    @EventHandler
    public void itemdrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(e.getItemDrop().getItemStack().getType().equals(Material.BEACON)){
            addingwarp.add(p.getUniqueId());
            p.sendMessage("type name with item in your hand");
            e.setCancelled(true);
        } else if(e.getItemDrop().getItemStack().getType().equals(Material.COMPASS)){
            newInventory(p);
            e.setCancelled(true);
        } else if(e.getItemDrop().getItemStack().getType().equals(Material.LAVA_BUCKET)){
            plugin.getConfig().set("warps", null);
            plugin.saveConfig();
            p.sendMessage("§c§lReset warps");
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void messagesend(PlayerChatEvent e){
        Player p = e.getPlayer();
        if(addingwarp.contains(p.getUniqueId())){
            if(!p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                plugin.saveconfigwarps(e.getMessage().replaceAll("&","§"),p.getInventory().getItemInMainHand(), p.getLocation());
                p.sendMessage(e.getMessage());
                p.sendMessage(p.getInventory().getItemInMainHand().getType().toString());
                e.setCancelled(true);
                addingwarp.remove(p.getUniqueId());
            } else {
                p.sendMessage("You need to have an item in your hand");
                p.sendMessage("Try again");
                addingwarp.remove(p.getUniqueId());
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void invclick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equals("Warps")){
            if(e.getCurrentItem().getItemMeta().hasLore()){
                teleportfromitem(p, e.getCurrentItem());
            }
            e.setCancelled(true);
        }
    }



    public void teleportfromitem(Player p,ItemStack item){
        ItemMeta itemm = item.getItemMeta();
        List<String> lore = itemm.getLore();
        String world = lore.get(0).toString();
        double x = Double.parseDouble(lore.get(1));
        double y = Double.parseDouble(lore.get(2));
        double z = Double.parseDouble(lore.get(3));

        p.teleport(new Location(Bukkit.getWorld(world), x,y,z));
    }





}
