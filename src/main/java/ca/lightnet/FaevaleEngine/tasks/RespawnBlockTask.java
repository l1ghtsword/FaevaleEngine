package ca.lightnet.FaevaleEngine.tasks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnBlockTask extends BukkitRunnable {
    Material material;
    Location location;
    public RespawnBlockTask(Material material, Location location) {
        this.material = material;
        this.location = location;
    }
    @Override
    public void run() {
        location.getBlock().setType(material);
    }
}