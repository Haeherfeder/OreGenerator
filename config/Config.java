/*    */ package config;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Config
/*    */ {
/*    */   private boolean onlyPlayerBreakGenerateOre = true;
/* 16 */   private List<OreGeneratorToken> oreGenerator = new ArrayList<OreGeneratorToken>();
/*    */ 
/*    */   
/*    */   public OreGeneratorToken getDefaultOreGeneratorToken(String world) {
/* 20 */     for (OreGeneratorToken token : this.oreGenerator) {
/* 21 */       if ((token.getItemInHand().equals("*") || token.getItemInHand().isEmpty()) && (
/* 22 */         token.getWorlds().isEmpty() || token.getWorlds().contains(world))) {
/* 23 */         return token;
/*    */       }
/*    */     } 
/*    */     
/* 27 */     return null;
/*    */   }
/*    */ 
/*    */   
/* 31 */   public boolean isOnlyPlayerBreakGenerateOre() { return this.onlyPlayerBreakGenerateOre; }
/*    */ 
/*    */   
/* 34 */   public void setOnlyPlayerBreakGenerateOre(boolean onlyPlayerBreakGenerateOre) { this.onlyPlayerBreakGenerateOre = onlyPlayerBreakGenerateOre; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public List<OreGeneratorToken> getOreGenerator() { return this.oreGenerator; }
/*    */ 
/*    */   
/* 41 */   public void setOreGenerator(List<OreGeneratorToken> oreGenerator) { this.oreGenerator = oreGenerator; }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/config/Config.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */