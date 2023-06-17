package ca.lightnet.FaevaleEngine.tasks;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnBlockTask extends BukkitRunnable {
    private final Location loc;
    private final BlockData bd;

    public RespawnBlockTask(Location location, BlockData blockdata) {
        this.loc = location;
        this.bd = blockdata;
    }

    @Override
    public void run() { loc.getBlock().setBlockData(bd); }
}