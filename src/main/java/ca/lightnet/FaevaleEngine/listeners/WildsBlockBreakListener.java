package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import ca.lightnet.FaevaleEngine.libraries.utilities.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import org.bukkit.event.EventPriority;

import java.util.Locale;

public class WildsBlockBreakListener extends Listener {

    private String propString = null;

    public WildsBlockBreakListener(String componentName) { super(componentName); }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTownyWildsBlockDestroy(TownyDestroyEvent e) {

        if(isIgnored(e)) { return; }

        e.setCancelled(true);

        if(isPlayerAFK(e) || isNotOnList(e) || isSupportBlock(e)){ return; }

        e.suppressMessage();

        Bukkit.getServer().getPluginManager().callEvent( new FaevaleDestroyEvent(
            e.getPlayer(),
            e.getLocation(),
            e.getBlock().getBlockData(),
            e.getMaterial(),
            e.getBlock().getDrops(),
            getPropString()
        ));
    }

    private boolean isSupportBlock(TownyDestroyEvent e) {
        Material above = LocationUtils.getAbove(e.getLocation()).getBlock().getType();
        if( !above.isSolid() && !above.equals(Material.AIR) && !above.equals(e.getMaterial()) ) {
            e.setCancelMessage("You cannot break supporting "+e.getMaterial().name().toLowerCase(Locale.ROOT));
            return true;
        }

        switch (e.getMaterial()) {
            case JUNGLE_LOG:
            case JUNGLE_WOOD:
            case STRIPPED_JUNGLE_LOG:
            case STRIPPED_JUNGLE_WOOD:
                for (Location loc : LocationUtils.getAllAdjacentBlocks(e.getLocation(), e.getMaterial())) {
                    if (loc.getBlock().getType() == Material.COCOA) {
                        e.setCancelMessage("You cannot break supporting " + e.getMaterial().name().toLowerCase(Locale.ROOT));
                        return true;
                    }
                }
            break;
        }

        return false;
    }

    private boolean isNotOnList(TownyDestroyEvent e) {
        for (String prop : getConfig().getStringList("properties")) {
            if (prop.startsWith(e.getMaterial().toString()+",")) {
                setPropString(prop);
                break; }
        }

        if(getPropString() == null) {
            if (FaevaleEngine.getInstance().getConfig().getBoolean("debug",false)) { sendDebuggingMessage(e); }
            return false;
        }

        e.setCancelMessage(e.getMaterial().name().toLowerCase(Locale.ROOT)+" cannot be broken in the wild.");
        return true;
    }

    private boolean isPlayerAFK(TownyDestroyEvent e){
        if (!getConfig().getBoolean("allowAFK", false)) {
            if (FaevaleEngine.getInstance().getEssentials().getUser(e.getPlayer()).isAfk()) {
                e.setCancelMessage("You cannot do that while AFK");
                return true;
            }
        }
        return false;
    }

    private boolean isIgnored(TownyDestroyEvent e) {
        if (e.isCancelled() || !e.isInWilderness() || e.getPlayer().isOp() || !e.getPlayer().getGameMode().equals(GameMode.SURVIVAL))
            { return true; }
        return false;
    }

    private void setPropString(String s) { propString = s; }
    private String getPropString() { return propString; }

    private void sendDebuggingMessage(TownyDestroyEvent e) {
        FaevaleEngine.getInstance().logInfo(
                e.getPlayer().getName()+" just broke "+e.getMaterial()+
                        " - Wilderness: "+e.isInWilderness()+
                        " - Op: "+e.getPlayer().isOp()+
                        " - Gamemode: "+e.getPlayer().getGameMode(),getComponentName());
    }
}