package ca.lightnet.mmoblockrespawn.listeners;

import ca.lightnet.mmoblockrespawn.tasks.RespawnBlockTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;
import org.bukkit.scheduler.BukkitTask;

public class WildsBlockListener implements Listener{
    private final Boolean debug;
    private final MmoBlockRespawn plugin;
    public WildsBlockListener(MmoBlockRespawn plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        debug = (Boolean) config.get("debug");
    }
    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent e) {
        if (e.isCancelled()) { return; }
        if (!e.isInWilderness()) { return; }
        if(e.getPlayer().isOp()) { return; }

        if (debug) {
            MmoBlockRespawn.LOGGER.info("**Event is in wilderness**");
            MmoBlockRespawn.LOGGER.info(e.getPlayer().getName() + " just broke " + e.getMaterial());
        }

        Location blockLoc = e.getLocation();
        Material blockMaterial = e.getMaterial();

        blockLoc.getBlock().setType(Material.BEDROCK);
        BukkitTask task = new RespawnBlockTask(plugin,blockMaterial,blockLoc).runTaskLaterAsynchronously(plugin,100L);
    }
}