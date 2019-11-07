/*    */ package config;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import oregenerator.OreGenerator;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import utils.BlockContent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OreGeneratorToken
/*    */ {
/*    */   private String itemInHand;
/*    */   private String displayName;
/*    */   private String permission;
/*    */   private List<String> chances;
/*    */   private List<String> worlds;
/*    */   public List<BlockContent> content;
/*    */   
/*    */   public OreGeneratorToken() {
/* 22 */     this.content = new ArrayList<BlockContent>();
/*    */ 
/*    */     
/* 25 */     this.itemInHand = "*";
/* 26 */     this.displayName = "";
/* 27 */     this.permission = "";
/* 28 */     this.chances = new ArrayList<String>();
/* 29 */     this.worlds = new ArrayList<String>();
/* 30 */     initContent();
/*    */   }
/*    */ 
/*    */   
/* 34 */   public void initContent() { Bukkit.getScheduler().runTaskLater(OreGenerator.PLUGIN, new Runnable()
/*    */         {
/*    */           public void run() {
/* 37 */             for (String chance : OreGeneratorToken.this.chances) {
/*    */               try {
/* 39 */                 Double percent = Double.valueOf(chance.split(", ")[0]);
/* 40 */                 String[] material = chance.split(", ")[1].split(":");
/* 41 */                 Material type = Material.valueOf(material[0]);
/* 42 */                 int durability = (material.length > 1) ? Integer.valueOf(material[1]).intValue() : 0;
/*    */                 
/* 44 */                 if (type != null && percent != null && 
/* 45 */                   type.isBlock() && percent.doubleValue() > 0.0D) {
/* 46 */                   OreGeneratorToken.this.content.add(new BlockContent(percent.doubleValue(), type, durability));
/*    */                 }
/*    */               }
/* 49 */               catch (Exception e) {
/* 50 */                 OreGenerator.PLUGIN.getLogger().warning("Error while decoding string " + chance + " in string-list chances! (" + OreGeneratorToken.this.itemInHand + ")");
/*    */               } 
/*    */             } 
/*    */           }
/*    */         },1L); }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public String getItemInHand() { return this.itemInHand; }
/*    */ 
/*    */   
/* 61 */   public void setItemInHand(String itemInHand) { this.itemInHand = itemInHand; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public String getDisplayName() { return this.displayName; }
/*    */ 
/*    */   
/* 68 */   public void setDisplayName(String displayName) { this.displayName = displayName; }
/*    */ 
/*    */ 
/*    */   
/* 72 */   public String getPermission() { return this.permission; }
/*    */ 
/*    */   
/* 75 */   public void setPermission(String permission) { this.permission = permission; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public List<String> getChances() { return this.chances; }
/*    */ 
/*    */   
/* 82 */   public void setChances(List<String> chances) { this.chances = chances; }
/*    */ 
/*    */ 
/*    */   
/* 86 */   public List<String> getWorlds() { return this.worlds; }
/*    */ 
/*    */ 
/*    */   
/* 90 */   public void setWorlds(List<String> worlds) { this.worlds = worlds; }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/config/OreGeneratorToken.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */