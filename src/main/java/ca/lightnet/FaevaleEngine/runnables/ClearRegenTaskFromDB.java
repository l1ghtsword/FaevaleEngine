package ca.lightnet.FaevaleEngine.runnables;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class ClearRegenTaskFromDB extends BukkitRunnable {
    private final String id;
    private Location loc;
    private BlockData bd;

    public ClearRegenTaskFromDB(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        FaevaleEngine.getInstance().logInfo("GETTING ID{"+id+"} from database!","DeserialTask");
    }
}