/*    */ package utils;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockContent
/*    */ {
/*    */   private double percent;
/*    */   private Material material;
/*    */   private int durability;
/*    */   
/*    */   public BlockContent(double percent, Material material, int durability) {
/* 15 */     this.percent = percent;
/* 16 */     this.material = material;
/* 17 */     this.durability = durability;
/*    */   }
/*    */ 
/*    */   
/* 21 */   public double getPercent() { return this.percent; }
/*    */ 
/*    */   
/* 24 */   public void setPercent(double percent) { this.percent = percent; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public Material getMaterial() { return this.material; }
/*    */ 
/*    */   
/* 31 */   public void setMaterial(Material material) { this.material = material; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int getDurability() { return this.durability; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setDurability(int durability) { this.durability = durability; }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/utils/BlockContent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */