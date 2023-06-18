package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import ca.lightnet.FaevaleEngine.tasks.DeserializeTaskFromDB;
import ca.lightnet.FaevaleEngine.tasks.RespawnBlockTask;
import ca.lightnet.FaevaleEngine.tasks.SerializeTaskToDB;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Bisected;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;

import java.util.List;
import java.util.UUID;

public class WildsBlockBreakListener extends Listener {

    private final FileConfiguration config = FaevaleEngine.getConfigFile();

    public WildsBlockBreakListener(Component component) {
        super(component);
    }

    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent e) {
        //Disqualifier checks
        if (   !config.getBoolean("enabled") ||
                e.isCancelled() ||
               !e.isInWilderness() ||
                e.getPlayer().isOp() ||
               !e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        //AFK cancel feature
        if (!config.getBoolean("allowAFK",false)) {
            if (FaevaleEngine.getEssentials().getUser(e.getPlayer()).isAfk()) {
                e.setCancelMessage("&4You cannot do that while AFK");
                e.setCancelled(true);
                return;
            }
        }

        //VARIABLE DECLARATIONS
        @SuppressWarnings("unchecked")
        List<String> regenBlocklist = (List<String>) config.getList("regen");
        boolean regenerate = false;
        long timer = config.getLong("defaultTimer",100L);
        Material placeholder = Material.getMaterial(config.getString("defaultPlaceholder","BEDROCK"));


        //block all break events if regen list is unset (Panic mode)
        if (regenBlocklist == null) {
            FaevaleEngine.logWarn("Regen list is null, preventing block events!",getComponentName());
            e.setCancelMessage("Regen list is null, preventing break events!");
            e.setCancelled(true);
            return;
        }

        //Prevent breaking supporting block under plant, etc
        if (e.getMaterial().isSolid()) {
            Location checkUP = e.getLocation().clone().add(0,1,0);
            Location checkN = e.getLocation().clone().subtract(0,0,1);
            Location checkE = e.getLocation().clone().add(1,0,0);
            Location checkS = e.getLocation().clone().add(0,0,1);
            Location checkW = e.getLocation().clone().subtract(1,0,0);

            if(e.getMaterial() != Material.BAMBOO) {
                if(     e.getMaterial() == Material.STRIPPED_JUNGLE_WOOD ||
                        e.getMaterial() == Material.JUNGLE_WOOD ||
                        e.getMaterial() == Material.STRIPPED_JUNGLE_LOG ||
                        e.getMaterial() == Material.JUNGLE_LOG) {
                    if (    checkN.getBlock().getType() == Material.COCOA ||
                            checkE.getBlock().getType() == Material.COCOA ||
                            checkS.getBlock().getType() == Material.COCOA ||
                            checkW.getBlock().getType() == Material.COCOA){
                        e.setCancelled(true);
                        e.setCancelMessage("&4Cannot break supporting block!");
                        return;
                    }
                } else if (!checkUP.getBlock().getType().isSolid() ||
                           checkUP.getBlock().getType() == Material.BAMBOO) {
                    e.setCancelled(true);
                    e.setCancelMessage("&4Cannot break supporting block!");
                    return;
                }
            }
        }


        // Look for match and validate config results into variables
        for (String obj : regenBlocklist) {
            if (obj.startsWith(e.getMaterial().toString()+",")) {
                String[] elements = obj.split(",");

                //Check result for valid material, ignores event if Material.getMaterial() returns null
                if (elements[0].isEmpty() || Material.getMaterial(elements[0]) == null) { return; }
                regenerate = true;

                //If second element isn't null, set timer instead of using default
                if (!elements[1].isEmpty()) {
                    try { timer = Long.parseLong(elements[1]); } catch (NumberFormatException ignored) { }
                }

                //if third element isn't null, set placeholder material instead of using default
                if (!elements[2].isEmpty() && Material.getMaterial(elements[2]) != null) {
                    placeholder = Material.getMaterial(elements[2]);
                }
            }
        }
        //If Material is not on the regen list, prevent block break
        if(!regenerate) {
            e.setCancelled(true);
            e.setCancelMessage("&4"+e.getMaterial().name()+" cannot be broken in the wild.");
            return;
        }

        //Database serialization
        String taskID = UUID.randomUUID().toString();
        new SerializeTaskToDB(taskID,e.getLocation(),e.getBlock().getBlockData()).runTask(getPlugin());
        new DeserializeTaskFromDB(taskID).runTaskLater(getPlugin(), timer);

        if (e.getBlock().getBlockData() instanceof Bisected) {
            Bisected block = (Bisected) e.getBlock().getBlockData();
            Location halfLoc = e.getLocation().clone();
            switch (block.getHalf()) {
                case BOTTOM:
                    halfLoc.add(0,1,0);
                    break;
                case TOP:
                    halfLoc.subtract(0,1,0);
                    break;
            }


            //Set both half's back after delay
            new RespawnBlockTask(e.getLocation(),e.getBlock().getBlockData()).runTaskLater(getPlugin(), timer);
            new RespawnBlockTask(halfLoc,halfLoc.getBlock().getBlockData()).runTaskLater(getPlugin(), timer);


            //Set placeholder block for both half's
            new RespawnBlockTask(e.getLocation(),Bukkit.createBlockData(placeholder)).runTask(getPlugin());
            new RespawnBlockTask(halfLoc,Bukkit.createBlockData(placeholder)).runTask(getPlugin());
        } else {
            //Set the 1 block broken in the event (default)
            new RespawnBlockTask(e.getLocation(),e.getBlock().getBlockData()).runTaskLater(getPlugin(), timer);
            new RespawnBlockTask(e.getLocation(),Bukkit.createBlockData(placeholder)).runTask(getPlugin());
        }


        if (config.getBoolean("debug")) {
            FaevaleEngine.logInfo(
                    e.getPlayer().getName()+" just broke "+e.getMaterial()+
                            " - Wilderness: "+e.isInWilderness()+
                            " - Op: "+e.getPlayer().isOp()+
                            " - Gamemode: "+e.getPlayer().getGameMode(),getComponentName());
        }
    }
}