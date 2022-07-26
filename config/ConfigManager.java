package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import oregenerator.OreGenerator;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.representer.Representer;

public class ConfigManager {
	public static final String CONFIG = "config.yml";
	public Config config;
	private Yaml yaml;

	public void init() { initConfig(); }
  
	public void initConfig() {
		DumperOptions options = new DumperOptions();
		options.setExplicitStart(true);
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);
		this.yaml = new Yaml(new CustomClassLoaderConstructor(OreGenerator.class.getClassLoader()), new Representer(), options);
		
		this.config = createConfiguration(OreGenerator.PLUGIN.getDataFolder().getAbsolutePath() + File.separator + "config.yml", Config.class);
	}


  
	public Config createConfiguration(String configPath, Class<Config> clazz) { return createConfig(configPath, clazz); }


  
	private Config createConfig(String configPath, Class<Config> clazz) {
		if (!OreGenerator.PLUGIN.getDataFolder().exists()) {
			OreGenerator.PLUGIN.getDataFolder().mkdir();
		}
    
		Config config = null;
		File file = new File(configPath);
		if (file.exists()) {
			try {
				config = this.yaml.load(new InputStreamReader(new FileInputStream(configPath), StandardCharsets.UTF_8));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				config = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			try {
				this.yaml.dump(config, new OutputStreamWriter(Files.newOutputStream(Paths.get(configPath)), StandardCharsets.UTF_8));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} 
		return config;
  	}	
  
	public <T> void saveConfig(String configPath, T config) {
		try {
			this.yaml.dump(config, new FileWriter(configPath));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public Yaml getYaml() { return this.yaml; }
	public static void main(String... args) {
		ConfigManager configManager = new ConfigManager();
		configManager.init();
		configManager.createConfig("config.yml", Config.class);
	}
	public Config getConfig() { return this.config; }
}