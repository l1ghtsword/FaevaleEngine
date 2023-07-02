package ca.lightnet.FaevaleEngine.runnables;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class SerializeRegenTaskToDB extends BukkitRunnable {
    private final String id;
    private final Location loc;
    private final BlockData bd;

    public SerializeRegenTaskToDB(String id, Location location, BlockData blockdata) {
        this.id = id;
        this.loc = location;
        this.bd = blockdata;
    }

    @Override
    public void run() {
        FaevaleEngine.getInstance().logInfo("ID{"+id+"}\n"+"DATA {"+bd.getAsString()+"}\nLOCATION{"+loc.toString()+"}","SerialTask");
    }
}