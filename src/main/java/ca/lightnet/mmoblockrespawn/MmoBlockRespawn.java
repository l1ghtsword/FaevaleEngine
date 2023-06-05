package ca.lightnet.mmoblockrespawn;

import ca.lightnet.mmoblockrespawn.listener.WildsBlockListener;
import org.bukkit.Bukkit;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public final class MmoBlockRespawn extends JavaPlugin
{
  public static final Logger LOGGER=Logger.getLogger("mmoblockrespawn");

  public void onEnable()
  {
    //Startup logic
    LOGGER.info("mmoblockrespawn enabled");
    //Events
    Bukkit.getServer().getPluginManager().registerEvents(new WildsBlockListener(), this);
  }

  public void onDisable()
  {
    LOGGER.info("mmoblockrespawn disabled");
  }
}
