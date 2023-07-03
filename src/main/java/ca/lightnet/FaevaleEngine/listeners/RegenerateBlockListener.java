package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import ca.lightnet.FaevaleEngine.libraries.utilities.LocationUtils;
import ca.lightnet.FaevaleEngine.runnables.ClearRegenTaskFromDB;
import ca.lightnet.FaevaleEngine.runnables.RespawnBlockTask;
import ca.lightnet.FaevaleEngine.runnables.SerializeRegenTaskToDB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.PatternSyntaxException;

public class RegenerateBlockListener extends Listener {

    private List<Location> locations;
    private Long timer;
    private Material placeholder;

    public RegenerateBlockListener(String componentName) {
        super(componentName);
        locations = new ArrayList<>();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFaevaleBlockDestroy(FaevaleDestroyEvent e) {

        if (e.isCancelled()) { return; }

        setProperties(e.getPropString());
        setLocations(e.getLocation(), e.getMaterial());
        sendRegenerationTasks(e);
    }

    private Long getTimer() { return this.timer; }
    private Material getPlaceholder() { return this.placeholder; }
    private List<Location> getLocations() { return this.locations; }

    private void setLocations(Location origin, Material material) {
        this.locations = LocationUtils.getAllLinearBlocks(origin,material);
    }

    private void setProperties(String propsString){
        String[] props;
        try {
            props = propsString.split(",");
        } catch(PatternSyntaxException err) {
            err.getStackTrace();
            props = new String[]{};
        }

        if (props[1].isEmpty()){
            this.timer = getConfig().getLong("defaultTimer",100L);
        } else {
            try {
                this.timer = Long.parseLong(props[1]);
            } catch (NumberFormatException err) {
                timer = 100L;
            }
        }

        if (props[2].isEmpty() && Material.getMaterial(props[2]) == null) {
            this.placeholder = Material.getMaterial(getConfig().getString("defaultPlaceholder","BEDROCK"));
        } else {
            this.placeholder = Material.getMaterial(props[2]);
        }
    }

    private void sendRegenerationTasks(FaevaleDestroyEvent e) {
        String taskID;
        Location supportBlock = null;
        List<BukkitRunnable> saveToDB = new ArrayList<>();
        List<BukkitRunnable> clearFromDB = new ArrayList<>();
        List<BukkitRunnable> setPlaceholder = new ArrayList<>();
        List<BukkitRunnable> restoreBlockLater = new ArrayList<>();

        //Condition only used when job requires support block first
        if(!e.getMaterial().isSolid() || e.getMaterial().equals(Material.BAMBOO)) {
            if(getLocations().get(0).getBlockY() <= getLocations().get(getLocations().size()-1).getBlockY()) {
                supportBlock = LocationUtils.getBelow(getLocations().get(0));
            }
        }

        for(Location loc : getLocations()) {
            if(FaevaleEngine.getInstance().getConfig().getBoolean("debug")) {
                FaevaleEngine.getInstance().logInfo(loc.toString(), getComponentName());
            }

            taskID = UUID.randomUUID().toString();

            if(supportBlock != null) {
                String supportTaskID = UUID.randomUUID().toString();
                new RespawnBlockTask(supportBlock,supportBlock.getBlock().getBlockData())
                        .runTaskLater(FaevaleEngine.getInstance(), getTimer()-1);
                new SerializeRegenTaskToDB(supportTaskID,supportBlock,supportBlock.getBlock().getBlockData())
                        .runTaskAsynchronously(FaevaleEngine.getInstance());
                new ClearRegenTaskFromDB(supportTaskID)
                        .runTaskLaterAsynchronously(FaevaleEngine.getInstance(), getTimer()-1);
                supportBlock = null;
            }

            setPlaceholder.add(new RespawnBlockTask(loc, Bukkit.createBlockData(placeholder)));
            restoreBlockLater.add(new RespawnBlockTask(loc,loc.getBlock().getBlockData()));
            saveToDB.add( new SerializeRegenTaskToDB(taskID,loc,loc.getBlock().getBlockData()));
            clearFromDB.add( new ClearRegenTaskFromDB(taskID));
        }
        setPlaceholder.forEach( (task) -> task.runTask(FaevaleEngine.getInstance()));
        restoreBlockLater.forEach( (task) -> task.runTaskLater(FaevaleEngine.getInstance(), getTimer()));
        saveToDB.forEach( (task) -> task.runTaskAsynchronously(FaevaleEngine.getInstance()));
        clearFromDB.forEach( (task) -> task.runTaskLaterAsynchronously(FaevaleEngine.getInstance(), getTimer()));
    }
}