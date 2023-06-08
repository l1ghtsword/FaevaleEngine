package ca.lightnet.mmoblockrespawn;

import ca.lightnet.mmoblockrespawn.commands.CommandManager;
import ca.lightnet.mmoblockrespawn.listeners.WildsBlockListener;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public final class MmoBlockRespawn extends JavaPlugin
{
  private static MmoBlockRespawn instance;
  public static final Logger LOGGER=Logger.getLogger("mmoblockrespawn");

  public void onEnable() {
    //Startup logic
    LOGGER.info("mmoblockrespawn enabled");
    saveDefaultConfig();
    instance = this;

    //Events
    Bukkit.getServer().getPluginManager().registerEvents(new WildsBlockListener(), this);

    //Commands
    Objects.requireNonNull(this.getCommand("regen")).setExecutor(new CommandManager());
  }

  public void onDisable() {
    saveConfig();
    LOGGER.info("MmoBlockRespawn disabled");
  }

  public static MmoBlockRespawn getInstance() {
    return instance;
  }
}
