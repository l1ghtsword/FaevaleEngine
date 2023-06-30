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
  private static FaevaleEngine instance;
  private ComponentRegistry componentRegistry;
  private CommandRegistry commandRegistry;
  private ConfigRegistry configRegistry;
  private FileConfiguration globalConfig;
  private Essentials essentials;
  private Logger logger;
  private String origin;

  public void onEnable() {
    //Initialization
    instance = this;
    origin = "Global";
    logger = Logger.getLogger("FaevaleEngine");
    essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
    if(essentials == null) { essentials = new Essentials(); } //Prevent crashing, loads defaults
    logInfo("starting up!", origin);

    //Load and set global config
    globalConfig = this.getConfig();
    globalConfig.options().copyDefaults(true);
    saveConfig();

    //Load configRegistry
    configRegistry = new ConfigRegistry();

    try {
      //load command registration service
      getCommand("faevale").setExecutor(new CommandRegistry());
      commandRegistry = (CommandRegistry) getCommand("faevale").getExecutor();
    } catch(NullPointerException e) {
      logSevere("Something has gone horribly wrong with the command registry",origin);
      e.getStackTrace();
      this.onDisable();
      return;
    }

    //Global Listeners
    Bukkit.getServer().getPluginManager().registerEvents(new CancelledEventListener(origin),this);

    commandRegistry.addCommand(new DebugCommand(origin));

    //////// Load Components ////////
    componentRegistry = new ComponentRegistry();
    componentRegistry.registerComponent(new BlockBreakComponent());

    /////////////////////////////////
    componentRegistry.load();

    logInfo("loaded and ready to go!", origin);
  }

  public void onDisable() {
    logInfo("Shutting down!", origin);
    if (componentRegistry != null) {
      componentRegistry.saveAll();
      componentRegistry.unload();
    }
    componentRegistry = null;
    commandRegistry = null;
    configRegistry = null;
  }

  public static FaevaleEngine getInstance() {
    return instance;
  }
  public FileConfiguration getConfigFile() { return this.globalConfig; }
  public Essentials getEssentials() { return this.essentials; }
  public CommandRegistry getCommandRegistry() { return this.commandRegistry; }
  public ConfigRegistry getConfigRegistry() { return this.configRegistry; }

  //Logging Methods
  public void logInfo (String msg, String origin) { logger.info("["+origin+"] "+msg); }
  public void logWarn (String msg, String origin) { logger.warning("["+origin+"] "+msg);}
  public void logSevere (String msg, String origin) { logger.severe("["+origin+"] "+msg);}
}
