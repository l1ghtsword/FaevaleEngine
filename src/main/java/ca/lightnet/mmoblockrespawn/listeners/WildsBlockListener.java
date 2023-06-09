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

    public WildsBlockListener() {
        this.plugin = MmoBlockRespawn.getInstance();
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent e) {
        if (    !config.getBoolean("enabled") ||
                e.isCancelled() ||
                !e.isInWilderness() ||
                e.getPlayer().isOp() ||
                !e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        //Check config stuff here


        new RespawnBlockTask(plugin,Material.BEDROCK,e.getLocation()).runTask(plugin);
        new RespawnBlockTask(plugin,e.getMaterial(),e.getLocation()).runTaskLater(plugin,100L);

        if (config.getBoolean("debug")) {
            MmoBlockRespawn.LOGGER.info(
                    e.getPlayer().getName()+" just broke "+e.getMaterial()+
                            " - Wilderness: "+e.isInWilderness()+
                            " - Op: "+e.getPlayer().isOp()+
                            " - Gamemode: "+e.getPlayer().getGameMode());
        }
    }
}