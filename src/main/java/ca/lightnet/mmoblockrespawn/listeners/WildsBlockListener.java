package ca.lightnet.mmoblockrespawn.listeners;

import ca.lightnet.mmoblockrespawn.tasks.RespawnBlockTask;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.actions.TownyDestroyEvent;
import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;

import java.util.List;
import java.util.Objects;

public class WildsBlockListener implements Listener{
    private final MmoBlockRespawn plugin;
    private final FileConfiguration config;


    public WildsBlockListener() {
        this.plugin = MmoBlockRespawn.getInstance();
        this.config = MmoBlockRespawn.getConfigFile();
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
        if (config.getBoolean("allowAFK")) {
            if (MmoBlockRespawn.getEssentials().getUser(e.getPlayer()).isAfk()) {
                e.setCancelMessage("&4You cannot do that while AFK");
                e.setCancelled(true);
                return;
            }
        }

        //VARIABLE DECLARATIONS
        @SuppressWarnings("unchecked")
        List<String> regenBlocklist = (List<String>) config.getList("regen");
        boolean regenerate = false;
        long timer = config.getLong("defaultTimer");
        Material placeholder = Material.getMaterial(Objects.requireNonNullElse(config.getString("defaultPlaceholder"),"BEDROCK"));

        MmoBlockRespawn.LOGGER.info("timer = "+timer+"\nplaceholder = "+placeholder);
        //block all break events if regen list is unset (Panic mode)
        if (regenBlocklist == null) {
            MmoBlockRespawn.LOGGER.warning("Regen list is null, preventing block events!");
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
        MmoBlockRespawn.LOGGER.info("timer = "+timer+"\nplaceholder = "+placeholder+"\nRegen = "+regenerate);
        //If Material is not on the regen list, quit
        if(!regenerate) { return; }

        //TODO add blacklist


        //TODO Refactor to use Block instead for task serialization
        new RespawnBlockTask(placeholder,e.getLocation()).runTask(plugin);
        new RespawnBlockTask(e.getMaterial(),e.getLocation()).runTaskLater(plugin,timer);
        MmoBlockRespawn.LOGGER.info("Tasks sent");

        if (config.getBoolean("debug")) {
            MmoBlockRespawn.LOGGER.info(
                    e.getPlayer().getName()+" just broke "+e.getMaterial()+
                            " - Wilderness: "+e.isInWilderness()+
                            " - Op: "+e.getPlayer().isOp()+
                            " - Gamemode: "+e.getPlayer().getGameMode());
        }
    }
}