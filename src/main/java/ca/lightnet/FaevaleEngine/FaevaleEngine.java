package ca.lightnet.FaevaleEngine;

import ca.lightnet.FaevaleEngine.commands.CommandManager;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockListener;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import java.util.Objects;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FaevaleEngine extends JavaPlugin
{
  private static FaevaleEngine instance;
  private static FileConfiguration config;
  private static Essentials essentials;
  public static Logger LOGGER;

  public void onEnable() {
    //Startup logic
    LOGGER = Logger.getLogger("mmoblockrespawn");
    LOGGER.info("mmoblockrespawn enabled");
    instance = this;
    config = this.getConfig();
    config.options().copyDefaults(true);
    saveConfig();
    essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

    //Events
    Bukkit.getServer().getPluginManager().registerEvents(new WildsBlockListener(), this);

    //Commands
    Objects.requireNonNull(this.getCommand("regen")).setExecutor(new CommandManager());
  }

  public void onDisable() {
    LOGGER.info("FaevaleEngine disabled");
  }

  public static FaevaleEngine getInstance() {
    return instance;
  }
  public static FileConfiguration getConfigFile() { return config; }
  public static Essentials getEssentials() { return essentials; }
}
