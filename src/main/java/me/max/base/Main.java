package me.max.base;


import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.max.base.events.*;
import me.max.base.menus.SpawnerMenu;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static  Main instance;
    public static String prefix = "§5[§dSurvival +§5] ";
    public static String errorprefix = "§5[§cSurvival +§5] ";

    public static Main getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        new SpawnerMenu(this);
        Bukkit.getServer().getPluginManager().registerEvents(new SugarFurnaceEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MobPickupEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnerPlaceEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnerClickEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnSpawnerOre(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ConfigEvents(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CopperOreMine(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KillMobEvent(this), this);

        Bukkit.getServer().addRecipe(nrecipe(Material.EMERALD, "§9§lSoulstone"));


        }

    @Override
    public void onDisable() {
        saveConfig();
    }



    public Recipe nrecipe(Material mat, String name){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        ShapedRecipe emerald = new ShapedRecipe(item);
        emerald.shape("###","#@#", "###");
        emerald.setIngredient('#', Material.EGG);
        emerald.setIngredient('@', Material.LEAD);
        return emerald;
    };



    public static ItemStack newitem(Material mat, String name){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        return item;
    };

    public static ItemStack newSkull(String id, String name){
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack item = api.getItemHead(id);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        return item;
    }


    public static ItemStack newSkull(String id, String name, List<String> lore){
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack item = api.getItemHead(id);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        itemm.setLore(lore);
        item.setItemMeta(itemm);
        return item;
    }

    public void addcoins(Player p, Integer amount, Boolean actionbar, Boolean message){
        Integer total = getConfig().getInt(p.getName() + ".coins") + amount;
        getConfig().set(p.getName() + ".coins", getConfig().getInt(p.getName() + ".coins") + amount);
        saveConfig();
        if (message) {
            p.sendMessage(prefix + "§2Added coins: " + amount.toString() + " new total: " + total.toString());
        }
        if(actionbar){
            p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§dAdded coins: §5" + amount.toString() + " §dnew total: §5" + total.toString()));
        }
    }

    public void removecoins(Player p, Integer amount){
        Integer total = getConfig().getInt(p.getName() + ".coins") - amount;
        getConfig().set(p.getName() + ".coins", getConfig().getInt(p.getName() + ".coins") - amount);
        saveConfig();
        p.sendMessage(prefix + "§cRemoved coins: " + amount.toString() + " new total: " + total.toString());

    }

    public ItemStack newSkullMob(Player p,String hostileorepassive,String mob, String id, String name, Integer neededkills){
        Integer killedamount = getConfig().getInt(p.getName() + "." + hostileorepassive + "." + mob);
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        List<String> lore = new ArrayList<>();
        if(neededkills > killedamount){
            ItemStack item = api.getItemHead(String.valueOf(42078));
            ItemMeta itemm = item.getItemMeta();
            lore.add(mob);
            lore.add("Killed: " + getConfig().getInt(p.getName() + "." + hostileorepassive + "." + mob));
            lore.add("Amount Needed: " + neededkills);
            itemm.setDisplayName("§cLocked");
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            return item;
        } else if(neededkills <= killedamount){
            ItemStack item = api.getItemHead(id);
            ItemMeta itemm = item.getItemMeta();
            lore.add("Killed: " + getConfig().getInt(p.getName() + "." + hostileorepassive + "." + mob));
            lore.add("Amount Needed: " + neededkills);
            itemm.setDisplayName(name);
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            return item;
        }
        return null;
    }


    public void addmobkill(Player p,String mob, String hostileorpassive){
        getConfig().set(p.getName() + "." + hostileorpassive + "." + mob, getConfig().getInt(p.getName() + "." + hostileorpassive + "." + mob) + 1);
        saveConfig();
        Integer total = getConfig().getInt(p.getName() + "." + hostileorpassive + "." + mob);
        p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§dKilled §5" + mob + " §dnew total: §d" + total));
    }


    public void saveconfigwarps(String name,ItemStack item, Location loc){
        List<String> warpstrings = new ArrayList<>();
        if(getConfig().contains("warps")){
            warpstrings = getConfig().getStringList("warps");
            warpstrings.add(name+"#"+item.getType().toString()+"#"+loc.getWorld().getName()+"#"+loc.getX()+"#"+loc.getY()+"#"+loc.getZ());
            getConfig().set("warps", warpstrings);
            saveConfig();
            return;
        }
        warpstrings.add(name+"#"+item.getType().toString()+"#"+loc.getWorld().getName()+"#"+loc.getX()+"#"+loc.getY()+"#"+loc.getZ());
        getConfig().set("warps", warpstrings);
        saveConfig();
    }


    public ItemStack getwarpitem(Integer id){
        if (getConfig().contains("warps")) {
            List<String> warpitem = getConfig().getStringList("warps");
            String[] warpitemtext = warpitem.get(id).split("#");
            ItemStack item = new ItemStack(Material.valueOf(warpitemtext[1]));
            ItemMeta itemm = item.getItemMeta();
            itemm.setDisplayName(warpitemtext[0]);
            List<String> lore = new ArrayList<>();
            lore.add(warpitemtext[2]);
            lore.add(warpitemtext[3]);
            lore.add(warpitemtext[4]);
            lore.add(warpitemtext[5]);
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            return item;
        } else {
            return null;
        }
    }


}
