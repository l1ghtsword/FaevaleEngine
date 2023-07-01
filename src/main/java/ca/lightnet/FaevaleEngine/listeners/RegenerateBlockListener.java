package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import ca.lightnet.FaevaleEngine.runnables.DeserializeTaskFromDB;
import ca.lightnet.FaevaleEngine.runnables.RespawnBlockTask;
import ca.lightnet.FaevaleEngine.runnables.SerializeTaskToDB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Bisected;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.util.Vector;

import java.util.Locale;
import java.util.UUID;

public class RegenerateBlockListener extends Listener {

    public RegenerateBlockListener(String componentName) { super(componentName); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onFaevaleBlockDestroy(FaevaleDestroyEvent e) {

        if(e.isCancelled()) { return; }
        Location checkN;
        Location checkE;
        Location checkS;
        Location checkW;


        if(e.getMaterial() == Material.BAMBOO) {
            checkN = e.getLocation().clone().subtract(0,0,1);
            checkE = e.getLocation().clone().add(new Vector().setX(1).setY(0).setZ(0));
            checkS = e.getLocation().clone().add(0,0,1);
            checkW = e.getLocation().clone().subtract(1,0,0);
        }


        /*
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
                        e.setCancelledMessage("&4Cannot break supporting block!");
                        return;
                    }
                } else if (!checkUP.getBlock().getType().isSolid() ||
                           checkUP.getBlock().getType() == Material.BAMBOO) {
                    e.setCancelled(true);
                    e.setCancelledMessage("&4Cannot break supporting block!");
                    return;
                }
            }
        }

        boolean regenerate = false;
        Long timer = getConfig().getLong("defaultTimer",100L);
        Material placeholder = Material.getMaterial(getConfig().getString("defaultPlaceholder","BEDROCK"));

        // Look for match and validate config results into variables
        for (String obj : getConfig().getStringList("properties")) {
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
            e.setCancelledMessage(e.getMaterial().name().toLowerCase(Locale.ROOT)+" cannot be broken in the wild.");
            return;
        }

        //Database serialization
        String taskID = UUID.randomUUID().toString();
        new SerializeTaskToDB(taskID,e.getLocation(),e.getBlockData()).runTask(FaevaleEngine.getInstance());
        new DeserializeTaskFromDB(taskID).runTaskLater(FaevaleEngine.getInstance(), timer);

        if (e.getBlockData() instanceof Bisected) {
            Bisected block = (Bisected) e.getBlockData();
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
            new RespawnBlockTask(e.getLocation(),e.getBlockData()).runTaskLater(FaevaleEngine.getInstance(), timer);
            new RespawnBlockTask(halfLoc,halfLoc.getBlock().getBlockData()).runTaskLater(FaevaleEngine.getInstance(), timer);


            //Set placeholder block for both half's
            new RespawnBlockTask(e.getLocation(),Bukkit.createBlockData(placeholder)).runTask(FaevaleEngine.getInstance());
            new RespawnBlockTask(halfLoc,Bukkit.createBlockData(placeholder)).runTask(FaevaleEngine.getInstance());
        } else {
            //Set the 1 block broken in the event (default)
            new RespawnBlockTask(e.getLocation(),e.getBlockData()).runTaskLater(FaevaleEngine.getInstance(), timer);
            new RespawnBlockTask(e.getLocation(),Bukkit.createBlockData(placeholder)).runTask(FaevaleEngine.getInstance());
        }

         */
    }

}