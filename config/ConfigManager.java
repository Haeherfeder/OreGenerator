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
    
		this.config = (Config)createConfiguration(OreGenerator.PLUGIN.getDataFolder().getAbsolutePath() + File.separator + "config.yml", Config.class);
	}


  
	public <T> T createConfiguration(String configPath, Class<T> clazz) { return (T)createConfig(configPath, clazz); }


  
  @SuppressWarnings("unchecked")
  private <T> T createConfig(String configPath, Class<T> clazz) {
	  if (!OreGenerator.PLUGIN.getDataFolder().exists()) {
		  OreGenerator.PLUGIN.getDataFolder().mkdir();
	  }
    
	  T config = null;
	  File file = new File(configPath);
	  if (file.exists()) {
		  try {
			  config = (T)this.yaml.load(new InputStreamReader(new FileInputStream(configPath), "UTF-8"));
		  } catch (FileNotFoundException e) {
			  e.printStackTrace();
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
      } 
	  } else {
		  try {
			  config = (T)clazz.newInstance();
		  } catch (InstantiationException e) {
			  e.printStackTrace();
		  } catch (IllegalAccessException e) {
			  e.printStackTrace();
		  } 
		  try {
			  this.yaml.dump(config, new OutputStreamWriter(new FileOutputStream(configPath), "UTF-8"));
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