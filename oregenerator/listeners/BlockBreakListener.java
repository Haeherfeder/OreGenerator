/*    */ package oregenerator.listeners;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import oregenerator.OreGenerator;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import utils.PlayerBreakBlock;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBreakListener
/*    */   implements Listener
/*    */ {
/* 23 */   public static List<PlayerBreakBlock> PLAYER_BREAK_BLOCK = new ArrayList<PlayerBreakBlock>();
/*    */ 
/*    */   
/* 26 */   public BlockBreakListener() { Bukkit.getScheduler().runTaskTimer(OreGenerator.PLUGIN, new Runnable()
/*    */         {
/*    */           public void run() {
/* 29 */             List<PlayerBreakBlock> remove = null;
/* 30 */             for (PlayerBreakBlock playerBreakBlock : BlockBreakListener.PLAYER_BREAK_BLOCK) {
/* 31 */               Integer cooldown = Integer.valueOf(playerBreakBlock.getCooldown().intValue() - 1);
/* 32 */               if (cooldown.intValue() <= 0) {
/* 33 */                 if (remove == null) {
/* 34 */                   remove = new ArrayList<PlayerBreakBlock>();
/*    */                 }
/*    */                 
/* 37 */                 remove.add(playerBreakBlock);
/*    */               } 
/*    */               
/* 40 */               playerBreakBlock.setCooldown(cooldown);
/*    */             } 
/*    */             
/* 43 */             if (remove != null) {
/* 44 */               for (PlayerBreakBlock playerBreakBlock : remove) {
/* 45 */                 BlockBreakListener.PLAYER_BREAK_BLOCK.remove(playerBreakBlock);
/*    */               }
/*    */             }
/*    */           }
/*    */         },1L, 1L); }
/*    */ 
/*    */   
/*    */   @SuppressWarnings("deprecation")
@EventHandler
/*    */   public void BlockBreakEvent(BlockBreakEvent event) {
		//	System.out.println("\n\n\nTest");
/* 54 */     Block cobblestone = event.getBlock();
/* 55 */     if (cobblestone.getType().equals(Material.COBBLESTONE)) {
/* 56 */       Location location = cobblestone.getLocation();
/*    */       
/* 58 */       boolean lava = false;
/* 59 */       boolean water = false;
/* 60 */       for (int y = -1; y <= 1; y++) {
/* 61 */         for (int x = -1; x <= 1; x++) {
/* 62 */           for (int z = -1; z <= 1; z++) {
/* 63 */             Block lavaWater = location.getWorld().getBlockAt(x + location.getBlockX(), y + location.getBlockY(), z + location.getBlockZ());
/* 64 */             if (lavaWater.getType().equals(Material.LAVA) || lavaWater.getType().equals(Material.LEGACY_LAVA)) {
/* 65 */               lava = true;
/* 66 */             } else if (lavaWater.getType().equals(Material.WATER) || lavaWater.getType().equals(Material.LEGACY_WATER)) {
/* 67 */               water = true;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/* 72 */       if (lava && water)
/* 73 */         PLAYER_BREAK_BLOCK.add(new PlayerBreakBlock(event.getPlayer(), event, event.getPlayer().getItemInHand(), Integer.valueOf(100))); 
			//	System.out.println("\n\n\nTest2: lava"+lava+" Water "+water);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/oregenerator/listeners/BlockBreakListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */