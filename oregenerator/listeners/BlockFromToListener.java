/*     */ package oregenerator.listeners;
/*     */ 
/*     */ import config.OreGeneratorToken;
/*     */ import java.util.Random;
/*     */ import oregenerator.OreGenerator;

/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockFromToEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import utils.BlockContent;
/*     */ import utils.PlayerBreakBlock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFromToListener
/*     */   implements Listener
/*     */ {
/*  24 */   private final Random random = new Random();
/*     */ 
/*     */ 
/*     */   
/*  28 */   private final BlockFace[] faces = { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
/*     */ 
@SuppressWarnings("deprecation")
/*     */   
/*     */   @EventHandler
/*     */   public void BlockFromToEvent(BlockFromToEvent event) {
	
//	System.out.println("\n\n\nBlockfromToListeners startet.");
/*  33 */     int id = event.getBlock().getType().getId();
//	System.out.println("\n\n\n"+id+" id: "+event.getToBlock().getType().getId());
/*  34 */     if (((id >= 8 && id <= 11)||(id == 8415)) && 
/*  35 */       (event.getToBlock().getType().getId()  == 0 || event.getToBlock().getType().getId() == 9648) && generatesCobble(id, event.getToBlock())) {
/*  36 *///       System.out.println("\n\n\ntrue");
	PlayerBreakBlock locationBlock = null;
/*  37 */       for (PlayerBreakBlock playerBreakBlock : BlockBreakListener.PLAYER_BREAK_BLOCK) {
/*  38 */         if (playerBreakBlock.getEvent().getBlock().getLocation().equals(event.getToBlock().getLocation())) {
/*  39 *///           System.out.println("\n\n\nplayerBreakBlock true.");
	locationBlock = playerBreakBlock;
/*     */           break;
/*     */         } 
/*     */       } 
/*  43 */       if (locationBlock != null || !OreGenerator.CONFIG_MANAGER.getConfig().isOnlyPlayerBreakGenerateOre()) {
/*  44 */         OreGeneratorToken token = OreGenerator.CONFIG_MANAGER.getConfig().getDefaultOreGeneratorToken(event.getBlock().getWorld().getName());
/*     */         
/*  46 */         if (locationBlock != null) {
/*  47 */           ItemStack breakItemInHand = locationBlock.getItemInHand();
//System.out.println("\n\n\nOKAY");
/*  48 */           for (OreGeneratorToken oreGeneratorToken : OreGenerator.CONFIG_MANAGER.getConfig().getOreGenerator()) {
/*  49 *///             System.out.println("\n\n\nItem in Hand: "+oreGeneratorToken.getItemInHand()+" Name "+oreGeneratorToken.getDisplayName());
	String[] itemInHand = oreGeneratorToken.getItemInHand().split(":");
/*  50 */             Material type = null;
						if(itemInHand != null && !itemInHand.equals(new String[] {""}) && !itemInHand.equals(null)) {
//							System.out.println("\n\n\nnot null: "+itemInHand[0]);
							try{type = Material.valueOf(itemInHand[0]);}catch(Exception e) {
//								System.out.println("\n\n\nError, set DiamondPick");
								type = Material.DIAMOND_PICKAXE;
							}
						}else {
//							System.out.println("\n\n\nnull");
							type = Material.DIAMOND_PICKAXE;
						}
/*  51 */             Integer durability = (itemInHand.length > 1) ? Integer.valueOf(itemInHand[1]) : null;
///*     */             System.out.println("\n\n\nDur: "+durability+"DispName "+oreGeneratorToken.getDisplayName()+" Disp2: "+breakItemInHand.getItemMeta().getDisplayName());
/*  53 */             if (type.equals(breakItemInHand.getType()) && (durability == null || breakItemInHand.getDurability() == durability.intValue()) && (
/*  54 */               oreGeneratorToken.getDisplayName().isEmpty() || (breakItemInHand.hasItemMeta() && breakItemInHand.getItemMeta().hasDisplayName() && oreGeneratorToken.getDisplayName().equals(breakItemInHand.getItemMeta().getDisplayName()))) && (
/*  55 */               token.getWorlds().isEmpty() || token.getWorlds().contains(event.getBlock().getLocation().getWorld().getName().toLowerCase()))) {
/*  56 */               token = oreGeneratorToken;
///*     */ 				System.out.println("\n\n\ntoken set "+token.getDisplayName());
/*     */               
/*     */               break;
/*     */             } else {
//						System.out.println("\n\n\nType: " +type+ " \"Item in Hand\": "+breakItemInHand.getType()+" equals "+type.equals(breakItemInHand.getType())+oreGeneratorToken.getDisplayName().isEmpty()+oreGeneratorToken.getDisplayName().equals(breakItemInHand.getItemMeta().getDisplayName()));
}
/*     */           } 
/*     */           
/*  63 */           if (token.getPermission() != null && !token.getPermission().isEmpty() && 
/*  64 */             !locationBlock.getPlayer().hasPermission(token.getPermission())) {
/*  65 */             token = OreGenerator.CONFIG_MANAGER.getConfig().getDefaultOreGeneratorToken(event.getBlock().getWorld().getName());
	//					System.out.println("\n\n\nset to def");
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/*  70 */         if (token != null) {
/*  71 */           int number = 0;
/*     */           
/*  73 */           int chance = this.random.nextInt(100000) + 1;
/*  74 */           for (BlockContent content : token.content) {
						number = (int)(number + content.getPercent() * 1000.0D);
/*  76 *///             	System.out.println("\n\n\ntest for: "+content.getMaterial()+" with "+number+" and "+chance);
/*  75 */             if (number >= chance) {
/*  77 */               event.getToBlock().setType(content.getMaterial());
						event.setCancelled(true);
///*  78 */               event.getToBlock().setData((byte)content.getDurability());
/*     */  //             System.out.println("\n\n\n"+content.getMaterial());
						break;
/*     */             } 
/*     */           }
			//		System.out.println("\n\n\nshould worked"+number+" "+chance);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SuppressWarnings("deprecation")
public boolean generatesCobble(int id, Block b) {
/*  89 */     int mirrorID1 = (id != 8 && id != 9) ? 8 : 10;
/*  90 */     int mirrorID2 = (id != 8 && id != 9) ? 9 : 11;
/*  91 */     BlockFace[] var8 = this.faces;
/*  92 */     int var7 = this.faces.length;
/*     */     
/*  94 */     for (int var6 = 0; var6 < var7; var6++) {
/*  95 */       BlockFace face = var8[var6];
/*  96 */       Block r = b.getRelative(face, 1);
/*  97 */       if (r.getType().getId() == mirrorID1 || r.getType().getId() == mirrorID2) {
/*  98 */         return true;
/*     */       }
/*     */     } 
/*     *///   System.out.println("\n\n\n");
/* 102 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/benjamin/backup/server/20191022214801/new/mc/plugins/OreGenerator.jar!/oregenerator/listeners/BlockFromToListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */