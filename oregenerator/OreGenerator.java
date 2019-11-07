/*    */ package oregenerator;
/*    */ 
/*    */ import config.ConfigManager;
/*    */ import oregenerator.listeners.BlockBreakListener;
/*    */ import oregenerator.listeners.BlockFromToListener;

/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OreGenerator
/*    */   extends JavaPlugin
/*    */ {
/*    */   public static OreGenerator PLUGIN;
/*    */   public static ConfigManager CONFIG_MANAGER;
/*    */   
/*    */   public void onEnable() {
/* 24 */     PLUGIN = this;
/*    */     
/* 26 */     CONFIG_MANAGER = new ConfigManager();
/* 27 */     CONFIG_MANAGER.init();
/*    */     
/* 29 */     initListener();
/*    */   }
/*    */   
/*    */   public void initListener() {
/* 33 */     PluginManager pm = getServer().getPluginManager();
/*    */     
/* 35 */     pm.registerEvents(new BlockBreakListener(), this);
/* 36 */     pm.registerEvents(new BlockFromToListener(), this);
/*    */   }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/oregenerator/OreGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */