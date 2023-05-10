package ca.lightnet.mmoblockrespawn;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * mmoblockrespawn java plugin
 */
public class Plugin extends JavaPlugin
{
  public static final Logger LOGGER=Logger.getLogger("mmoblockrespawn");

  public void onEnable()
  {
    LOGGER.info("mmoblockrespawn enabled");
  }

  public void onDisable()
  {
    LOGGER.info("mmoblockrespawn disabled");
  }
}
