package ca.lightnet.mmoblockrespawn.tasks;

import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnBlockTask extends BukkitRunnable {
    MmoBlockRespawn plugin;
    Material material;
    Location location;
    public RespawnBlockTask(MmoBlockRespawn plugin, Material material, Location location) {
        this.plugin = plugin;
        this.material = material;
        this.location = location;
    }
    @Override
    public void run() {
        location.getBlock().setType(material);
    }
}