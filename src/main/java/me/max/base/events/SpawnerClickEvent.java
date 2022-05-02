package me.max.base.events;


import me.max.base.Main;
import me.max.base.menus.SpawnerMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import javax.swing.text.EditorKit;
import java.util.*;

public class SpawnerClickEvent implements Listener {

    public HashMap<UUID, Block> editing = new HashMap<>();
    public HashMap<Block, Player> editingblock = new HashMap<>();
    public String notunlocked = Main.errorprefix + "§cYou need to unlock this mob first";

    static Main plugin;

    public SpawnerClickEvent(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onspawnerclick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.SPAWNER) && p.isSneaking() && e.getHand().equals(EquipmentSlot.HAND)) {
            if (editingblock.containsKey(e.getClickedBlock())) {
                if (editingblock.get(e.getClickedBlock()).equals(p)) {
                    if (editing.containsKey(p.getUniqueId())) {
                        editing.remove(p.getUniqueId());
                    }
                    editing.put(p.getUniqueId(), e.getClickedBlock());
                    SpawnerMenu.MainMenu(p, e.getClickedBlock());
                } else {
                    if (!(editingblock.get(e.getClickedBlock()).getOpenInventory().getTitle().equals("§9Main Menu") || editingblock.get(e.getClickedBlock()).getOpenInventory().getTitle().equals("§9Upgrade Menu") ||
                            editingblock.get(e.getClickedBlock()).getOpenInventory().getTitle().equals("§9Passive or Hostile") || editingblock.get(e.getClickedBlock()).getOpenInventory().getTitle().equals("§9Choose Hostile Mob") ||
                            editingblock.get(e.getClickedBlock()).getOpenInventory().getTitle().equals("§9Choose Passive Mob"))) {
                        editing.put(p.getUniqueId(), e.getClickedBlock());
                        editingblock.put(e.getClickedBlock(), p);
                        SpawnerMenu.MainMenu(p, e.getClickedBlock());
                    } else {
                        p.sendMessage(Main.errorprefix + "§cYou cannot edit at the same time");
                    }
                }
            } else {
                editingblock.put(e.getClickedBlock(), p);
                editing.put(p.getUniqueId(), e.getClickedBlock());
                SpawnerMenu.MainMenu(p, e.getClickedBlock());
            }
        }
    }


    @EventHandler
    public void menuspawnerclick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String invname = e.getView().getTitle();

        try {
            if (editing.get(p.getUniqueId()).getType().equals(Material.AIR) && (invname.equals("§9Main Menu") ||
                    invname.equalsIgnoreCase("§9Choose Hostile Mob") || invname.equalsIgnoreCase("§9Choose Passive Mob") ||
                    invname.equalsIgnoreCase("§9Passive or Hostile") || invname.equalsIgnoreCase("§9Upgrade Menu"))) {
                p.closeInventory();
                p.sendMessage(Main.errorprefix + "§cBlock is removed");
                e.setCancelled(true);
                return;
            }
            ;
        } catch (NullPointerException ex) {
        }


        if (invname.equals("§9Main Menu")) {
            Block b = editing.get(p.getUniqueId());
            CreatureSpawner cs = (CreatureSpawner) b.getState();
            if (e.getSlot() == 11) {
                SpawnerMenu.PassiveorHostile(p);
            } else if (e.getSlot() == 13) {
                SpawnerMenu.UpgradeMenu(p, b, plugin.getConfig().getInt(p.getName() + ".coins"));
            } else if (e.getSlot() == 15) {
                p.sendMessage(Main.prefix + "§2Removed spawner");
                p.getInventory().addItem(newSpawnerItem(b));
                p.closeInventory();
                b.setType(Material.AIR);
            }
            e.setCancelled(true);
        } else if (invname.equals("§9Choose Hostile Mob")) {
            Block b = editing.get(p.getUniqueId());
            CreatureSpawner cs = (CreatureSpawner) b.getState();
            if (b.getType().equals(Material.AIR)) {
                p.closeInventory();
                p.sendMessage(Main.errorprefix + "§cBlock is removed");
                e.setCancelled(true);
                return;
            }
            ;
            //ROW1
            if (e.getSlot() == 4) {
                SpawnerMenu.MainMenu(p, b);
                //ROW3
            } else if (e.getSlot() == 19) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.ZOMBIE, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 20) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.SKELETON, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 21) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.DROWNED, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 22) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.SPIDER, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 23) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.RAVAGER, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 24) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.MAGMA_CUBE, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 25) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.CREEPER, b);
                SpawnerMenu.MainMenu(p, b);
                //ROW4
            } else if (e.getSlot() == 28) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.GUARDIAN, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 29) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.SLIME, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 30) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.BLAZE, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 31) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.ENDERMAN, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 32) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.WITCH, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 33) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.PIGLIN, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 34) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.SHULKER, b);
                SpawnerMenu.MainMenu(p, b);
                //ROW5
            } else if (e.getSlot() == 37) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.PILLAGER, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 38) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.WITHER_SKELETON, b);
                SpawnerMenu.MainMenu(p, b);
            }
            e.setCancelled(true);
        } else if (invname.equals("§9Choose Passive Mob")) {
            Block b = editing.get(p.getUniqueId());
            CreatureSpawner cs = (CreatureSpawner) b.getState();
            if (e.getSlot() == 4) {
                SpawnerMenu.MainMenu(p, b);
                //ROW3
            } else if (e.getSlot() == 19) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.PARROT, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 20) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.SQUID, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 21) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.GLOW_SQUID, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 22) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.POLAR_BEAR, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 23) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.TURTLE, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 24) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.GOAT, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 25) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.WOLF, b);
                SpawnerMenu.MainMenu(p, b);
                //ROW4
            } else if (e.getSlot() == 28) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.PANDA, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 29) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.FOX, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 30) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.RABBIT, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 31) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.STRIDER, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 32) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.CHICKEN, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 33) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                };
                setEntity(p,EntityType.SHEEP, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 34) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.PIG, b);
                SpawnerMenu.MainMenu(p, b);
                //ROW5
            } else if (e.getSlot() == 37) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.COW, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 38) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.BEE, b);
                SpawnerMenu.MainMenu(p, b);
            } else if (e.getSlot() == 39) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLocked")) {
                    p.sendMessage(notunlocked);
                    e.setCancelled(true);
                    return;
                }
                ;
                setEntity(p,EntityType.MUSHROOM_COW, b);
                SpawnerMenu.MainMenu(p, b);
            }


            e.setCancelled(true);
        } else if (invname.equals("lol")) {
            p.sendMessage(e.getCurrentItem().getItemMeta().getDisplayNameComponent());
            p.closeInventory();
            e.setCancelled(true);
        } else if (invname.equals("§9Upgrade Menu")) {
            Block b = editing.get(p.getUniqueId());
            CreatureSpawner cs = (CreatureSpawner) b.getState();
            if (b.getType().equals(Material.AIR)) {
                p.closeInventory();
                p.sendMessage("§cBlock is removed");
                e.setCancelled(true);
                return;
            }
            ;
            if (e.getSlot() == 2) {
                SpawnerMenu.MainMenu(p, b);
            } else if(e.getSlot() == 6){
                editingblock.remove(editing.get(p.getUniqueId()));
                editing.remove(p.getUniqueId());
                p.closeInventory();
            } else if (e.getSlot() == 21) {
                if (cs.getMaxNearbyEntities() > 1) {
                    plugin.addcoins(p, (cs.getMaxNearbyEntities() - 1) * 120, false, true);
                    upgrade(p, b, -1, 0, 0, 0);
                } else {
                    p.sendMessage(Main.errorprefix + "§cCannot be lower than 1");
                }
            } else if (e.getSlot() == 23) {
                if (plugin.getConfig().getInt(p.getName() + ".coins") >= cs.getMaxNearbyEntities() * 120) {
                    if (cs.getMaxNearbyEntities() < 25) {
                        plugin.removecoins(p, cs.getMaxNearbyEntities() * 120);
                        upgrade(p, b, 1, 0, 0, 0);
                    } else {
                        p.sendMessage(Main.errorprefix + "§cCannot be higher than 25");
                    }
                } else {
                    p.sendMessage(Main.errorprefix + "§cYou do not have enough coins");
                }
            } else if (e.getSlot() == 30) {
                if (cs.getSpawnCount() > 1) {
                    plugin.addcoins(p, (cs.getSpawnCount() - 1) * 160, false, true);
                    upgrade(p, b, 0, -1, 0, 0);
                } else {
                    p.sendMessage(Main.errorprefix + "§cCannot be lower than 1");
                }
            } else if (e.getSlot() == 32) {
                if (plugin.getConfig().getInt(p.getName() + ".coins") >= cs.getSpawnCount() * 160) {
                    if (cs.getSpawnCount() < 25) {
                        plugin.removecoins(p, cs.getSpawnCount() * 160);
                        upgrade(p, b, 0, 1, 0, 0);
                    } else {
                        p.sendMessage(Main.errorprefix + "§cCannot be higher than 25");
                    }
                } else {
                    p.sendMessage(Main.errorprefix + "§cYou do not have enough coins");
                }
            } else if (e.getSlot() == 39) {
                if (plugin.getConfig().getInt(p.getName() + ".coins") >= ((cs.getMinSpawnDelay()-1)/20) * 150) {
                    if (cs.getMinSpawnDelay() > 20) {
                        plugin.removecoins(p, ((cs.getMinSpawnDelay()-1)/20) * 150);
                        upgrade(p, b, 0, 0, -20, 0);
                    } else {
                        p.sendMessage(Main.errorprefix + "§cCannot be lower than 1 second");
                    }
                } else {
                    p.sendMessage(Main.errorprefix + "§cYou do not have enough coins");
                }
            } else if (e.getSlot() == 41) {
                if (cs.getMinSpawnDelay() < 500) {
                    if (cs.getMaxSpawnDelay() == cs.getMinSpawnDelay()) {
                        p.sendMessage(Main.errorprefix + "§cMin Delay cannot be higher than max delay");
                        e.setCancelled(true);
                        return;
                    }
                    plugin.addcoins(p, (cs.getMinSpawnDelay() / 20) * 150, false, true);
                    upgrade(p, b, 0, 0, 20, 0);
                } else {
                    p.sendMessage(Main.errorprefix + "§cCannot be higher than 25 second");
                }
            } else if (e.getSlot() == 48) {
                if(plugin.getConfig().getInt(p.getName() + ".coins") >= ((cs.getMaxSpawnDelay()-1)/20)*150) {
                    if (cs.getMaxSpawnDelay() > 20) {
                        if (cs.getMinSpawnDelay() == cs.getMaxSpawnDelay()) {
                            p.sendMessage(Main.errorprefix + "§cMax delay cannot be lower than min delay");
                            e.setCancelled(true);
                            return;
                        }
                        plugin.removecoins(p, ((cs.getMaxSpawnDelay()-1)/20) * 150);
                        upgrade(p, b, 0, 0, 0, -20);
                    } else {
                        p.sendMessage(Main.errorprefix + "§cCannot be lower than 1 second");
                    }
                } else {
                    p.sendMessage(Main.errorprefix + "§cYou do not have enough coins");
                }
            } else if (e.getSlot() == 50) {
                if (cs.getMaxSpawnDelay() < 800) {
                    plugin.addcoins(p, (cs.getMinSpawnDelay()/20) * 150, false, true);
                    upgrade(p, b, 0, 0, 0, 20);
                } else {
                    p.sendMessage(Main.errorprefix + "§cCannot be higher than 40 seconds");
                }
            }
            e.setCancelled(true);
        } else if (invname.equals("§9Passive or Hostile")) {

            Block b = editing.get(p.getUniqueId());
            CreatureSpawner cs = (CreatureSpawner) b.getState();
            if (b.getType().equals(Material.AIR)) {
                p.closeInventory();
                p.sendMessage(Main.errorprefix + "§cBlock is removed");
                e.setCancelled(true);
                return;
            }
            ;
            if (e.getSlot() == 11) {
                SpawnerMenu.PassiveMobs(p);
            } else if (e.getSlot() == 15) {
                SpawnerMenu.HostileMobs(p);
            }
            e.setCancelled(true);
        }
    }




    public ItemStack newSpawnerItem(Block block){
        CreatureSpawner cs = (CreatureSpawner) block.getState();
        ItemStack spawner = new ItemStack(block.getType(), 1);
        BlockStateMeta blockMeta = (BlockStateMeta) spawner.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("§9Nearby Enemies §6§o" + cs.getMaxNearbyEntities());
        lore.add("§9Spawn Count §6§o" + cs.getSpawnCount());
        lore.add("§9Min Delay §6§o" + cs.getMinSpawnDelay()/20 + " Seconds");
        lore.add("§9Max Delay §6§o" + cs.getMaxSpawnDelay()/20 + " Seconds");
        blockMeta.setLore(lore);
        blockMeta.setDisplayName(cs.getCreatureTypeName().toLowerCase());
        blockMeta.setBlockState(cs);
        spawner.setItemMeta(blockMeta);
        return spawner;
    }



    public void setEntity(Player p,EntityType type, Block block){
        CreatureSpawner cs = (CreatureSpawner) block.getState();
        cs.setSpawnedType(type);
        cs.update(true);
        p.sendMessage(Main.prefix + "§2Set entity to: §b§l" + type.toString().toLowerCase());
    }

    public void upgrade(Player p,Block block, int maxnearbyentities, int spawncount, int mindelay, int maxdelay){
        CreatureSpawner cs = (CreatureSpawner) block.getState();

        if(!(maxnearbyentities == 0)){
            cs.setMaxNearbyEntities(cs.getMaxNearbyEntities() + maxnearbyentities);
        }
        if(!(spawncount == 0)){
            cs.setSpawnCount(cs.getSpawnCount() + spawncount);
        }
        if(!(mindelay == 0)){
            cs.setMinSpawnDelay(cs.getMinSpawnDelay() + mindelay);
        }
        if(!(maxdelay == 0)){
            cs.setMaxSpawnDelay(cs.getMaxSpawnDelay() + maxdelay);
        }
        cs.update(true);
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10,1);
        SpawnerMenu.UpgradeMenu(p, editing.get(p.getUniqueId()), plugin.getConfig().getInt(p.getName() + ".coins"));
    }




}
