package me.max.base.menus;

import me.max.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnerMenu {

    static Main plugin;

    public SpawnerMenu(Main instance) {
        plugin = instance;
    }

    public static void spawnermenu(Player p, Block block){
        CreatureSpawner cs = (CreatureSpawner) block.getState();
        int maxNearbyEntities = cs.getMaxNearbyEntities();

        Inventory inv = Bukkit.createInventory(null, 54, "§9Spawner Menu");
        for (int i = 0; i < inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.WHITE_STAINED_GLASS_PANE, " "));};

        inv.setItem(10, Main.newSkull("45277", "§6" + cs.getCreatureTypeName()));

        inv.setItem(43, Main.newSkull("50905", "§6Choose your mob"));
        inv.setItem(42, Main.newSkull("229", "§cRemove Spawner"));


        p.openInventory(inv);
    }


    public static void MainMenu(Player p, Block block){
        Inventory inv = Bukkit.createInventory(null, 27, "§9Main Menu");
        for (int i = 0; i < inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.WHITE_STAINED_GLASS_PANE, " "));};
        CreatureSpawner cs = (CreatureSpawner) block.getState();
        List<String> lore = new ArrayList<>();
        lore.add("§9Nearby Enemies §6§o" + cs.getMaxNearbyEntities());
        lore.add("§9Spawn Count §6§o" + cs.getSpawnCount());
        lore.add("§9Min Delay §6§o" + cs.getMinSpawnDelay()/20 + " Seconds");
        lore.add("§9Max Delay §6§o" + cs.getMaxSpawnDelay()/20 + " Seconds");
        //ROW2
        inv.setItem(11, Main.newSkull("34571","§b§lChoose Mob"));
        inv.setItem(13, Main.newSkull("762","§b§lUpgrade Menu", lore));
        inv.setItem(15, Main.newSkull("11558","§c§lRemove Spawner"));
        p.openInventory(inv);
    }


    public static void UpgradeMenu(Player p, Block block, Integer coins){
        Inventory inv = Bukkit.createInventory(null, 54, "§9Upgrade Menu");
        for (int i = 0; i < inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.WHITE_STAINED_GLASS_PANE, " "));}
        CreatureSpawner cs = (CreatureSpawner) block.getState();
        //ROW1
        inv.setItem(2, Main.newSkull("9226","§cBack"));
        List<String> lore = new ArrayList<>();
        lore.add("§6§l" + coins);
        inv.setItem(4, Main.newSkull("4133","§cMoney", lore));
        inv.setItem(6, Main.newSkull("1159","§cExit"));
        //ROW3
        lore.clear();
        lore.add("§5§oMaximum: 25");
        lore.add("§5§oMinimum: 1");
        lore.add("§5§oHigher is better");
        inv.setItem(19, Main.newSkull("46488","§bMax Nearby Entities", lore));
        lore.clear();
        lore.add("§5Price: §2§o+" + (cs.getMaxNearbyEntities()-1)*120);
        inv.setItem(21, Main.newSkull("9351","§c-1", lore));
        inv.setItem(22, Main.newSkull("307","§b" + cs.getMaxNearbyEntities()));
        lore.clear();
        lore.add("§5Price: §c§o-" + cs.getMaxNearbyEntities()*120);
        inv.setItem(23, Main.newSkull("10209","§2+1", lore));
        //ROW4
        lore.clear();
        lore.add("§5§oMaximum: 25");
        lore.add("§5§oMinimum: 1");
        lore.add("§5§oHigher is better");
        inv.setItem(28, Main.newSkull("46488","§bMax Spawn Count", lore));
        lore.clear();
        lore.add("§5Price: §2§o+" + (cs.getSpawnCount()-1)*160);
        inv.setItem(30, Main.newSkull("9351","§c-1", lore));
        inv.setItem(31, Main.newSkull("307","§b" + cs.getSpawnCount()));
        lore.clear();
        lore.add("§5Price: §c§o-" + cs.getSpawnCount()*160);
        inv.setItem(32, Main.newSkull("10209","§2+1", lore));
        //ROW5
        lore.clear();
        lore.add("§5§oMaximum: 25");
        lore.add("§5§oMinimum: 1");
        lore.add("§5§oLower is better");
        inv.setItem(37, Main.newSkull("46488","§bMin Delay", lore));
        lore.clear();
        lore.add("§5Price: §c§o-" + ((cs.getMinSpawnDelay()/20)-1)*150);
        inv.setItem(39, Main.newSkull("9351","§c-1", lore));
        inv.setItem(40, Main.newSkull("307","§b" + cs.getMinSpawnDelay()/20 + " Seconds"));
        lore.clear();
        lore.add("§5Price: §2§o+" + (cs.getMinSpawnDelay()/20)*150);
        inv.setItem(41, Main.newSkull("10209","§2+1", lore));
        //ROW6
        lore.clear();
        lore.add("§5§oMaximum: 40");
        lore.add("§5§oMinimum: 1");
        lore.add("§5§oLower is better");
        inv.setItem(46, Main.newSkull("46488","§bMax Delay", lore));
        lore.clear();
        lore.add("§5Price: §c§o-" + ((cs.getMaxSpawnDelay()/20)-1)*150);
        inv.setItem(48, Main.newSkull("9351","§c-1", lore));
        inv.setItem(49, Main.newSkull("307","§b" + cs.getMaxSpawnDelay()/20 + " Seconds"));
        lore.clear();
        lore.add("§5Price: §2§o+" + (cs.getMaxSpawnDelay()/20)*150);
        inv.setItem(50, Main.newSkull("10209","§2+1", lore));

        p.openInventory(inv);
    }



    public static void PassiveorHostile(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "§9Passive or Hostile");
        for(int i = 0; i < inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.WHITE_STAINED_GLASS_PANE, " "));};
        //ROW1
        inv.setItem(11, Main.newSkull("31294", "§6Passive"));
        inv.setItem(15, Main.newSkull("4187", "§6Hostile"));
        p.openInventory(inv);
    }


    public static void HostileMobs(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, "§9Choose Hostile Mob");
        for(int i = 0; i < inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.WHITE_STAINED_GLASS_PANE, " "));};
        //ROW1
        inv.setItem(4, Main.newSkull("9226", "§cBack"));
        //ROW3
        inv.setItem(19, plugin.newSkullMob(p,"hostile","zombie","4187", "§6Zombie", 100));
        inv.setItem(20, plugin.newSkullMob(p,"hostile","skeleton","8188", "§6Skeleton", 250));
        inv.setItem(21, plugin.newSkullMob(p,"hostile","drowned","21521", "§6Drowned", 250));
        inv.setItem(22, plugin.newSkullMob(p,"hostile","spider","26010", "§6Spider", 300));
        inv.setItem(23, plugin.newSkullMob(p,"hostile","ravenger","28196", "§6Ravenger", 50));
        inv.setItem(24, plugin.newSkullMob(p,"hostile","magma_cube","45421", "§6Magma Cube" , 500));
        inv.setItem(25, plugin.newSkullMob(p,"hostile","creeper","4169", "§6Creeper", 500));
        //ROW4
        inv.setItem(28, plugin.newSkullMob(p,"hostile","guardian","25292", "§6Guardian", 500));
        inv.setItem(29, plugin.newSkullMob(p,"hostile","slime","30399", "§6Slime", 500));
        inv.setItem(30, plugin.newSkullMob(p,"hostile","blaze","322", "§6Blaze", 750));
        inv.setItem(31, plugin.newSkullMob(p,"hostile","enderman","318", "§6Enderman",750));
        inv.setItem(32, plugin.newSkullMob(p,"hostile","witch","3864", "§6Witch", 150));
        inv.setItem(33, plugin.newSkullMob(p,"hostile","piglin","34885", "§6Piglin", 850));
        inv.setItem(34, plugin.newSkullMob(p,"hostile","shulker","38317", "§6Shulker", 850));
        //ROW5
        inv.setItem(37, plugin.newSkullMob(p,"hostile","pillager","25149", "§6Pillager", 1000));
        inv.setItem(38, plugin.newSkullMob(p,"hostile","wither_skeleton","22400", "§6Wither Skeleton", 1250));





        p.openInventory(inv);
    }


    public static void PassiveMobs(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, "§9Choose Passive Mob");
        for(int i = 0; i < inv.getSize(); i++){inv.setItem(i, Main.newitem(Material.WHITE_STAINED_GLASS_PANE, " "));};
        //ROW1
        inv.setItem(4, Main.newSkull("9226", "§cBack"));
        //ROW3
        inv.setItem(19, plugin.newSkullMob(p, "passive", "parrot" , "49663", "§6Parrot", 50));
        inv.setItem(20, plugin.newSkullMob(p, "passive", "squid" , "338", "§6Squid", 100));
        inv.setItem(21, plugin.newSkullMob(p, "passive", "glow_squid" , "40581", "§6Glow Squid", 150));
        inv.setItem(22, plugin.newSkullMob(p, "passive", "polarbear" , "18379", "§6Polarbear", 50));
        inv.setItem(23, plugin.newSkullMob(p, "passive", "turtle" , "17929", "§6Turtle", 100));
        inv.setItem(24, plugin.newSkullMob(p, "passive", "goat" , "42452", "§6Goat", 50));
        inv.setItem(25, plugin.newSkullMob(p, "passive", "wolf" , "49760", "§6Wolf", 100));
        //ROW4
        inv.setItem(28, plugin.newSkullMob(p,"passive","panda","39280", "§6Panda", 50));
        inv.setItem(29, plugin.newSkullMob(p,"passive","fox","26328", "§6Fox", 50));
        inv.setItem(30, plugin.newSkullMob(p,"passive","rabbit","49677", "§6Rabbit", 100));
        inv.setItem(31, plugin.newSkullMob(p,"passive","strider","35431", "§6Strider",750));
        inv.setItem(32, plugin.newSkullMob(p,"passive","chicken","35656", "§6Chicken", 250));
        inv.setItem(33, plugin.newSkullMob(p,"passive","sheep","49688", "§6Sheep", 250));
        inv.setItem(34, plugin.newSkullMob(p,"passive","pig","337", "§6Pig", 250));
        //ROW5
        inv.setItem(37, plugin.newSkullMob(p,"passive","cow","335", "§6Cow", 250));
        inv.setItem(38, plugin.newSkullMob(p,"passive","bee","35298", "§6Bee", 75));
        inv.setItem(39, plugin.newSkullMob(p,"passive","mushroom_cow","339", "§6Mushroom Cow", 300));
        p.openInventory(inv);
    }















}
