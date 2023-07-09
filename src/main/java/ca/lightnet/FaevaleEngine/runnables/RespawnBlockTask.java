package ca.lightnet.FaevaleEngine.runnables;

import org.bukkit.Bukkit;
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

    public RespawnBlockTask(String world, double x, double y, double z, float pitch, float yaw, BlockData blockdata){
        this.loc = new Location(Bukkit.getServer().getWorld(world),x,y,z,pitch,yaw);
        this.bd = blockdata;
    }

    @Override
    public void run() { loc.getBlock().setBlockData(bd); }
}