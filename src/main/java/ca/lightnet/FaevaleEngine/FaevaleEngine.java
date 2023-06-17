package ca.lightnet.FaevaleEngine;

import ca.lightnet.FaevaleEngine.commands.CommandManager;
import ca.lightnet.FaevaleEngine.components.blockRegen;
import ca.lightnet.FaevaleEngine.libs.Models.Objects.ComponentRegistry;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import java.util.Objects;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FaevaleEngine extends JavaPlugin
{
  private static ComponentRegistry registry;
  private static FaevaleEngine instance;
  private static FileConfiguration config;
  private static Essentials essentials;
  private static Logger logger;

  public void onEnable() {

    registry = new ComponentRegistry();
    logger = Logger.getLogger("FaevaleEngine");
    instance = this;
    config = this.getConfig();
    essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

    logInfo("FaevaleEngine starting");
    config.options().copyDefaults(true);
    saveConfig();

    //Component registry (Add config checks)
    registry.registerComponent(new blockRegen());

    //Commands (To be abstracted)
    Objects.requireNonNull(this.getCommand("regen")).setExecutor(new CommandManager());

    registry.load();
  }

  public void onDisable() {
    registry.saveAll();
    registry.unload();
    logInfo("FaevaleEngine shut down!");
  }

  public static FaevaleEngine getInstance() {
    return instance;
  }
  public static FileConfiguration getConfigFile() { return config; }
  public static Essentials getEssentials() { return essentials; }

  //Logging Methods
  public static void logInfo (String msg) { logger.info("[FaevaleEngine] "+msg); }
  public static void logInfo (String msg, String origin) { logger.info("["+origin+"] "+msg); }
  public static void logWarn (String msg) { logger.warning("[FaevaleEngine] "+msg); }
  public static void logWarn (String msg, String origin) { logger.warning("["+origin+"] "+msg);}
  public static void logSevere (String msg) { logger.severe("[FaevaleEngine] "+msg); }
  public static void logSevere (String msg, String origin) { logger.severe("["+origin+"] "+msg);}

}
