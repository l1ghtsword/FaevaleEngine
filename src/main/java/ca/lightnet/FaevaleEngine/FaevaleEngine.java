package ca.lightnet.FaevaleEngine;

import ca.lightnet.FaevaleEngine.commands.DebugCommand;
import ca.lightnet.FaevaleEngine.components.BlockBreakComponent;
import ca.lightnet.FaevaleEngine.libraries.services.CommandRegistry;
import ca.lightnet.FaevaleEngine.libraries.services.ComponentRegistry;
import ca.lightnet.FaevaleEngine.libraries.services.ConfigRegistry;
import ca.lightnet.FaevaleEngine.listeners.CancelledEventListener;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FaevaleEngine extends JavaPlugin
{
  private static ComponentRegistry componentRegistry;
  private static CommandRegistry commandRegistry;
  private static ConfigRegistry configRegistry;
  private static FaevaleEngine instance;
  private static FileConfiguration globalConfig;
  private static Essentials essentials;
  private static Logger logger;
  private static String origin;

  public void onEnable() {
    //Initialization
    logger = Logger.getLogger("FaevaleEngine");
    origin = "Main";
    instance = this;
    globalConfig = this.getConfig();
    essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
    logInfo("starting up!", origin);

    //Load and set global config
    globalConfig.options().copyDefaults(true);
    saveConfig();

    //Load configRegistry
    configRegistry = new ConfigRegistry();

    //Global Listeners
    Bukkit.getServer().getPluginManager().registerEvents(new CancelledEventListener("Global"),this);

    //load command registration service
    try {
      getCommand("faevale").setExecutor(new CommandRegistry());
      commandRegistry = (CommandRegistry) getCommand("faevale").getExecutor();
    } catch(NullPointerException e) {
      logSevere("Something has gone horribly wrong with the command registry","Global");
      e.getStackTrace();
      this.onDisable();
      return;
    }
    commandRegistry.addCommand(new DebugCommand("Global"));

    //////// Load Components ////////
    componentRegistry = new ComponentRegistry();
    componentRegistry.registerComponent(new BlockBreakComponent());

    /////////////////////////////////
    componentRegistry.load();

    logInfo("loaded and ready to go!", origin);
  }

  public void onDisable() {
    logInfo("Shutting down!", origin);
    componentRegistry.saveAll();
    componentRegistry.unload();
    logInfo("Plugin disabled", origin);
  }

  public static FaevaleEngine getInstance() {
    return instance;
  }
  public static FileConfiguration getConfigFile() { return globalConfig; }
  public static Essentials getEssentials() { return essentials; }
  public static CommandRegistry getCommandRegistry() { return commandRegistry; }
  public static ConfigRegistry getConfigRegistry() { return configRegistry; }

  //Logging Methods
  public static void logInfo (String msg, String origin) { logger.info(origin+": "+msg); }
  public static void logWarn (String msg, String origin) { logger.warning(origin+": "+msg);}
  public static void logSevere (String msg, String origin) { logger.severe(origin+": "+msg);}
}
