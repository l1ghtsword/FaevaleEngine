package ca.lightnet.mmoblockrespawn.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import ca.lightnet.mmoblockrespawn.Plugin;

public class WildsBlockListener implements Listener {
    public WildsBlockListener(Plugin Plugin) {
        Bukkit.getPluginManager().registerEvents(this, Plugin);
    }


    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent event) {
        Plugin.LOGGER.info(
                "Event is cancelled? = " + event.isCancelled()+
                     " Wilgerness? = "+event.isInWilderness()+
                     " Player: "+event.getPlayer().getDisplayName()
        );
    }
}