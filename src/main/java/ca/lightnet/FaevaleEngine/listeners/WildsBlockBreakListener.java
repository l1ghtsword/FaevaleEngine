package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import org.bukkit.event.EventPriority;

import java.util.Locale;

public class WildsBlockBreakListener extends Listener {

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

        //This point on the event will always cancel
        e.setCancelled(true);

        //is user AFK?
        if (!getConfig().getBoolean("allowAFK", false)) {
            if (FaevaleEngine.getInstance().getEssentials().getUser(e.getPlayer()).isAfk()) {
                e.setCancelMessage("You cannot do that while AFK");
                return;
            }
        }

        //is block on the prop list?
        for (String prop : getConfig().getStringList("properties")) {
            if (prop.startsWith(e.getMaterial().toString()+",")) {
                e.suppressMessage();
                Bukkit.getServer().getPluginManager().callEvent(new FaevaleDestroyEvent(e.getPlayer(),e.getLocation(),e.getBlock().getBlockData(),e.getMaterial(),e.getBlock().getDrops()));
                //Debugging message
                if (FaevaleEngine.getInstance().getConfig().getBoolean("debug",false)) { debuggingMessage(e); }
                return;
            }
        }

        //Always cancel destroy event if none of the above apply
        e.setCancelMessage(e.getMaterial().name().toLowerCase(Locale.ROOT)+" cannot be broken in the wild.");
    }
    private void debuggingMessage(TownyDestroyEvent e) {
        FaevaleEngine.getInstance().logInfo(
                e.getPlayer().getName()+" just broke "+e.getMaterial()+
                        " - Wilderness: "+e.isInWilderness()+
                        " - Op: "+e.getPlayer().isOp()+
                        " - Gamemode: "+e.getPlayer().getGameMode(),getComponentName());
    }
}