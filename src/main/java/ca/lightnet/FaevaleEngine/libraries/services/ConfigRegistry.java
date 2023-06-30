package ca.lightnet.FaevaleEngine.libraries.services;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.utilities.DefaultConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ConfigRegistry {

    private final Map<String,FileConfiguration> configList;

    public ConfigRegistry() {
        this.configList = new HashMap<>();
    }

    public void addConfig(String componentName) {
        File configFile;
        FileConfiguration config;

        configFile = new File(FaevaleEngine.getInstance().getDataFolder(), componentName + ".yml");

        if (!configFile.exists()) {
            try { configFile.createNewFile(); }
            catch (IOException e) {
                FaevaleEngine.getInstance().logSevere("&4Unable to create " + configFile.getName() + ".yml. Check server storage permissions!", configFile.getName() + "Create");
                return;
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        config.addDefaults(DefaultConfig.getDefaults(componentName));
        config.options().copyDefaults(true);
        try { config.save(configFile); }
        catch(IOException e) {
            FaevaleEngine.getInstance().logSevere("&4Unable to save " + configFile.getName() + ".yml. ",configFile.getName() + "Save");
        }
        FaevaleEngine.getInstance().logInfo("Before store "+config.getName(),"[ConfigRegistry]");
        this.configList.put(componentName, config);
        FaevaleEngine.getInstance().logInfo("After store "+this.configList.get(componentName).getName(),"[ConfigRegistry]");
    }

    public FileConfiguration getConfig(String componentName) {

        this.configList.forEach((k, v) -> FaevaleEngine.getInstance().logInfo("key: "+k+" Value: "+v.getName(),"ConfigRegistry"));
        return this.configList.get(componentName);
    }

    public void reloadConfig(String componentName) { YamlConfiguration.loadConfiguration(new File(FaevaleEngine.getInstance().getDataFolder(), componentName + ".yml")); }

    public void saveConfig(String componentName) {
        try {
            this.configList.get(componentName).save(new File(FaevaleEngine.getInstance().getDataFolder(),componentName+".yml"));
        }catch(IOException e){
            FaevaleEngine.getInstance().logSevere("&4Unable to save BlockBreakComponent.yml. Check server storage permissions!",componentName+"Config");
        }
    }
}
