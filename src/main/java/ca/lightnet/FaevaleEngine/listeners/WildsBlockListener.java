package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.tasks.RespawnBlockTask;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;

import java.util.List;

public class WildsBlockListener implements Listener{
    private final FaevaleEngine plugin;
    private final FileConfiguration config;


    public WildsBlockListener() {
        this.plugin = FaevaleEngine.getInstance();
        this.config = FaevaleEngine.getConfigFile();
    }

    @EventHandler
    public void onWildsBlockBuild(TownyDestroyEvent e) {
        //Disqualifier checks
        if (   !config.getBoolean("enabled") ||
                e.isCancelled() ||
               !e.isInWilderness() ||
                e.getPlayer().isOp() ||
               !e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        //AFK cancel feature
        if (!config.getBoolean("allowAFK",false)) {
            if (FaevaleEngine.getEssentials().getUser(e.getPlayer()).isAfk()) {
                e.setCancelMessage("&4You cannot do that while AFK");
                e.setCancelled(true);
                return;
            }
        }

        //VARIABLE DECLARATIONS
        @SuppressWarnings("unchecked")
        List<String> regenBlocklist = (List<String>) config.getList("regen");
        boolean regenerate = false;
        long timer = config.getLong("defaultTimer",100L);
        Material placeholder = Material.getMaterial(config.getString("defaultPlaceholder","BEDROCK"));

        //block all break events if regen list is unset (Panic mode)
        if (regenBlocklist == null) {
            FaevaleEngine.LOGGER.warning("Regen list is null, preventing block events!");
            e.setCancelMessage("Regen list is null, preventing break events!");
            e.setCancelled(true);
            return;
        }

        // Look for match and validate config results into variables
        for (String obj : regenBlocklist) {
            if (obj.startsWith(e.getMaterial().toString()+",")) {
                String[] elements = obj.split(",");

                //Check result for valid material, ignores event if Material.getMaterial() returns null
                if (elements[0].isEmpty() || Material.getMaterial(elements[0]) == null) { return; }
                regenerate = true;

                //If second element isn't null, set timer instead of using default
                if (!elements[1].isEmpty()) {
                    try { timer = Long.parseLong(elements[1]); } catch (NumberFormatException ignored) { }
                }

                //if third element isn't null, set placeholder material instead of using default
                if (!elements[2].isEmpty() && Material.getMaterial(elements[2]) != null) {
                    placeholder = Material.getMaterial(elements[2]);
                }
            }
        }
        //If Material is not on the regen list, quit
        if(!regenerate) { return; }

        //TODO add blacklist


        //TODO Refactor to use Block instead for task serialization
        new RespawnBlockTask(placeholder,e.getLocation()).runTask(plugin);
        new RespawnBlockTask(e.getMaterial(),e.getLocation()).runTaskLater(plugin,timer);

        if (config.getBoolean("debug")) {
            FaevaleEngine.LOGGER.info(
                    e.getPlayer().getName()+" just broke "+e.getMaterial()+
                            " - Wilderness: "+e.isInWilderness()+
                            " - Op: "+e.getPlayer().isOp()+
                            " - Gamemode: "+e.getPlayer().getGameMode());
        }
    }
}