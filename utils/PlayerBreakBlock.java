/*    */ package utils;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerBreakBlock
/*    */ {
/*    */   private Player player;
/*    */   private BlockBreakEvent event;
/*    */   private ItemStack itemInHand;
/*    */   private Integer cooldown;
/*    */   
/*    */   public PlayerBreakBlock(Player player, BlockBreakEvent event, ItemStack itemInHand, Integer cooldown) {
/* 19 */     this.player = player;
/* 20 */     this.event = event;
/* 21 */     this.itemInHand = itemInHand;
/* 22 */     this.cooldown = cooldown;
/*    */   }
/*    */ 
/*    */   
/* 26 */   public Player getPlayer() { return this.player; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void setPlayer(Player player) { this.player = player; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public BlockBreakEvent getEvent() { return this.event; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setEvent(BlockBreakEvent event) { this.event = event; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public ItemStack getItemInHand() { return this.itemInHand; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void setItemInHand(ItemStack itemInHand) { this.itemInHand = itemInHand; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public Integer getCooldown() { return this.cooldown; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setCooldown(Integer cooldown) { this.cooldown = cooldown; }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/utils/PlayerBreakBlock.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */