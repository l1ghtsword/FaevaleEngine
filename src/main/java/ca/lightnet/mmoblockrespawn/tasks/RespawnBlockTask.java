package ca.lightnet.mmoblockrespawn.tasks;

import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnBlockTask extends BukkitRunnable {

    MmoBlockRespawn plugin;
    Material material;
    Block block;
    public RespawnBlockTask(MmoBlockRespawn plugin, Material material, Block block) {
        this.plugin = plugin;
        this.material = material;
        this.block = block;
    }
    @Override
    public void run() {
        block.setType(material);
    }
}
