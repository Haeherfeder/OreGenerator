package oregenerator;

import config.ConfigManager;
import oregenerator.listeners.BlockBreakListener;
import oregenerator.listeners.BlockFromToListener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OreGenerator extends JavaPlugin
{
	public static OreGenerator PLUGIN;
	public static ConfigManager CONFIG_MANAGER;
	
	public void onEnable() {
		PLUGIN = this;

		saveDefaultConfig();
		
		CONFIG_MANAGER = new ConfigManager();
		CONFIG_MANAGER.init();
	
		initListener();
	}
	
	public void initListener() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new BlockFromToListener(), this);
	}
}