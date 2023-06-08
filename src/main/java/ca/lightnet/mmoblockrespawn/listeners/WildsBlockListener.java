package ca.lightnet.mmoblockrespawn.listeners;

import ca.lightnet.mmoblockrespawn.tasks.RespawnBlockTask;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;

public class WildsBlockListener implements Listener{
    private final MmoBlockRespawn plugin;
    private final FileConfiguration config;
    private final Boolean debug;
    private final Boolean enabled;
    public WildsBlockListener() {
        this.plugin = MmoBlockRespawn.getInstance();
        this.config = plugin.getConfig();
        this.debug = config.getBoolean("debug");
        this.enabled = config.getBoolean("enabled");
    }
    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent e) {
        if(!enabled) {
            return;
        }
        if ( e.isCancelled() || !e.isInWilderness() || e.getPlayer().isOp() || !e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        if (debug) {
            MmoBlockRespawn.LOGGER.info(
            e.getPlayer().getName()+" just broke"+e.getMaterial()+
                 " Wilderness: "+e.isInWilderness()+
                 " IsOp: "+e.getPlayer().isOp()+
                 " Gamemode: "+e.getPlayer().getGameMode());
        }
        new RespawnBlockTask(plugin,Material.BEDROCK,e.getLocation()).runTask(plugin);
        new RespawnBlockTask(plugin,e.getMaterial(),e.getLocation()).runTaskLater(plugin,100L);
    }
}