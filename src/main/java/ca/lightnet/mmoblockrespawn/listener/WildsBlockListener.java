package ca.lightnet.mmoblockrespawn.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;

public class WildsBlockListener implements Listener {
    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent event) {
        MmoBlockRespawn.LOGGER.info(
                "Event is cancelled? = " + event.isCancelled()+
                     " Wilgerness? = "+event.isInWilderness()+
                     " Player: "+event.getPlayer().getDisplayName()
        );
    }
}