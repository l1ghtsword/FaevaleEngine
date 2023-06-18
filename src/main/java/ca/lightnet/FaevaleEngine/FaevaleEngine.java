package ca.lightnet.FaevaleEngine;

import ca.lightnet.FaevaleEngine.libraries.services.CommandRegistry;
import ca.lightnet.FaevaleEngine.components.blockRegen;
import ca.lightnet.FaevaleEngine.libraries.services.ComponentRegistry;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FaevaleEngine extends JavaPlugin
{
  private static ComponentRegistry registry;
  private static FaevaleEngine instance;
  private static FileConfiguration config;
  private static Essentials essentials;
  private static CommandRegistry commandRegistry;
  private static Logger logger;
  private static String origin;

  public void onEnable() {
    //Initialization
    logger = Logger.getLogger("FaevaleEngine");
    origin = "Main";
    instance = this;
    config = this.getConfig();
    essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
    logInfo("starting up!", origin);

    //Load and set global config
    config.options().copyDefaults(true);
    saveConfig();

    //load command registration service
    getCommand("faevale").setExecutor(new CommandRegistry());
    commandRegistry = (CommandRegistry) getCommand("faevale").getExecutor();



    registry = new ComponentRegistry();
    //////// Load Components ////////
    registry.registerComponent(new blockRegen());


    /////////////////////////////////
    registry.load();
    logInfo("loaded and ready to go!", origin);
  }

  public void onDisable() {
    logInfo("Shutting down!", origin);
    registry.saveAll();
    registry.unload();
    logInfo("Plugin disabled", origin);
  }

  public static FaevaleEngine getInstance() {
    return instance;
  }
  public static FileConfiguration getConfigFile() { return config; }
  public static Essentials getEssentials() { return essentials; }
  public static CommandRegistry getCommandRegistry() { return commandRegistry;}

  //Logging Methods
  public static void logInfo (String msg, String origin) { logger.info(origin+": "+msg); }
  public static void logWarn (String msg, String origin) { logger.warning(origin+": "+msg);}
  public static void logSevere (String msg, String origin) { logger.severe(origin+": "+msg);}

}
