/*    */ package config;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import oregenerator.OreGenerator;
/*    */ import org.yaml.snakeyaml.DumperOptions;
/*    */ import org.yaml.snakeyaml.Yaml;
/*    */ import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
/*    */ import org.yaml.snakeyaml.representer.Representer;
/*    */ 
/*    */ public class ConfigManager {
/*    */   public static final String CONFIG = "config.yml";
/*    */   public Config config;
/*    */   private Yaml yaml;
/*    */   
/* 23 */   public void init() { initConfig(); }
/*    */ 
/*    */   
/*    */   public void initConfig() {
/* 27 */     DumperOptions options = new DumperOptions();
/* 28 */     options.setExplicitStart(true);
/* 29 */     options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
/* 30 */     options.setPrettyFlow(true);
/* 31 */     this.yaml = new Yaml(new CustomClassLoaderConstructor(OreGenerator.class.getClassLoader()), new Representer(), options);
/*    */     
/* 33 */     this.config = (Config)createConfiguration(OreGenerator.PLUGIN.getDataFolder().getAbsolutePath() + File.separator + "config.yml", Config.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public <T> T createConfiguration(String configPath, Class<T> clazz) { return (T)createConfig(configPath, clazz); }
/*    */ 
/*    */ 
/*    */   
/*    */   @SuppressWarnings("unchecked")
private <T> T createConfig(String configPath, Class<T> clazz) {
/* 43 */     if (!OreGenerator.PLUGIN.getDataFolder().exists()) {
/* 44 */       OreGenerator.PLUGIN.getDataFolder().mkdir();
/*    */     }
/*    */     
/* 47 */     T config = null;
/* 48 */     File file = new File(configPath);
/* 49 */     if (file.exists()) {
/*    */       try {
/* 51 */         config = (T)this.yaml.load(new InputStreamReader(new FileInputStream(configPath), "UTF-8"));
/* 52 */       } catch (FileNotFoundException e) {
/* 53 */         e.printStackTrace();
/* 54 */       } catch (UnsupportedEncodingException e) {
/* 55 */         e.printStackTrace();
/*    */       } 
/*    */     } else {
/*    */       try {
/* 59 */         config = (T)clazz.newInstance();
/* 60 */       } catch (InstantiationException e) {
/* 61 */         e.printStackTrace();
/* 62 */       } catch (IllegalAccessException e) {
/* 63 */         e.printStackTrace();
/*    */       } 
/*    */       try {
/* 66 */         this.yaml.dump(config, new OutputStreamWriter(new FileOutputStream(configPath), "UTF-8"));
/* 67 */       } catch (IOException e) {
/* 68 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     
/* 72 */     return config;
/*    */   }
/*    */   
/*    */   public <T> void saveConfig(String configPath, T config) {
/*    */     try {
/* 77 */       this.yaml.dump(config, new FileWriter(configPath));
/* 78 */     } catch (IOException e) {
/* 79 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 84 */   public Yaml getYaml() { return this.yaml; }
/*    */ 
/*    */   
/*    */   public static void main(String... args) {
/* 88 */     ConfigManager configManager = new ConfigManager();
/* 89 */     configManager.init();
/* 90 */     configManager.createConfig("config.yml", Config.class);
/*    */   }
/*    */   
/* 93 */   public Config getConfig() { return this.config; }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/config/ConfigManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */