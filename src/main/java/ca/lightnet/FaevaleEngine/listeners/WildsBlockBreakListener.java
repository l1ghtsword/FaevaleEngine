package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import org.bukkit.event.EventPriority;

public class WildsBlockBreakListener extends Listener {

    private final FileConfiguration config = FaevaleEngine.getConfigRegistry().getConfig(getComponentName());

    public WildsBlockBreakListener(String componentName) { super(componentName); }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTownyWildsBlockDestroy(TownyDestroyEvent e) {

        //Ignore event? (bypass component entirely)
        if (e.isCancelled() ||
            !e.isInWilderness() ||
            e.getPlayer().isOp() ||
            !e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        //is user AFK?
        if (!config.getBoolean("allowAFK", false)) {
            if (FaevaleEngine.getEssentials().getUser(e.getPlayer()).isAfk()) {
                e.setCancelMessage("&4You cannot do that while AFK");
                e.setCancelled(true);
                return;
            }
        }

        //is block on the prop list?
        for (String prop : config.getStringList("properties")) {
            if (prop.startsWith(e.getMaterial().toString()+",")) {
                e.setCancelMessage("");
                Bukkit.getServer().getPluginManager().callEvent(new FaevaleDestroyEvent(e.getPlayer(),e.getMaterial(),e.getLocation(),e.getBlock().getBlockData(), e.getBlock().getDrops()));
        //Debugging message
                if (config.getBoolean("debug",false)) {
                    FaevaleEngine.logInfo(
                            e.getPlayer().getName()+" just broke "+e.getMaterial()+
                                    " - Wilderness: "+e.isInWilderness()+
                                    " - Op: "+e.getPlayer().isOp()+
                                    " - Gamemode: "+e.getPlayer().getGameMode(),getComponentName());
                }
                return;
            }
        }

        //Always cancel destroy event if none of the above apply
        e.setCancelMessage("&4"+e.getMaterial().name()+" cannot be broken in the wild.");
        e.setCancelled(true);
    }
}