package ca.lightnet.mmoblockrespawn;

import ca.lightnet.mmoblockrespawn.commands.CommandManager;
import ca.lightnet.mmoblockrespawn.listeners.WildsBlockListener;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import java.util.Objects;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.html.Option;

public final class MmoBlockRespawn extends JavaPlugin
{
  private static MmoBlockRespawn instance;
  private static FileConfiguration configFile;
  private static final Essentials essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

  public static final Logger LOGGER=Logger.getLogger("mmoblockrespawn");

  public void onEnable() {
    //Startup logic
    LOGGER.info("mmoblockrespawn enabled");
    instance = this;
    configFile = this.getConfig();
    configFile.options().copyDefaults(true);
    saveConfig();
    //Events
    Bukkit.getServer().getPluginManager().registerEvents(new WildsBlockListener(), this);
    //Commands
    Objects.requireNonNull(this.getCommand("regen")).setExecutor(new CommandManager());
  }

  public void onDisable() {
    LOGGER.info("MmoBlockRespawn disabled");
  }

  public static MmoBlockRespawn getInstance() {
    return instance;
  }
  public static FileConfiguration getConfigFile() { return configFile; }
  public static Essentials getEssentials() { return essentials; }
}
