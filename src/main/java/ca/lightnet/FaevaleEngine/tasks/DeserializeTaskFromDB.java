package ca.lightnet.FaevaleEngine.tasks;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class DeserializeTaskFromDB extends BukkitRunnable {
    private final String id;
    private Location loc;
    private BlockData bd;

    public DeserializeTaskFromDB(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        FaevaleEngine.logInfo("GETTING ID{"+id+"} from database!","DeserialTask");
    }
}