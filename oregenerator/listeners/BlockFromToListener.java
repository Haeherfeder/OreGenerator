 package oregenerator.listeners;
 
import config.OreGeneratorToken;
import java.util.Random;
import oregenerator.OreGenerator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.inventory.ItemStack;
import utils.BlockContent;
import utils.PlayerBreakBlock;
 
 
 
 
 
 
 public class BlockFromToListener implements Listener
 {
	 private final Random random = new Random();
	 private final BlockFace[] faces = { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
 
	 @SuppressWarnings("deprecation")
	 @EventHandler
	 public void BlockFromToEvent(BlockFromToEvent event) {
//	System.out.println("\n\n\nBlockfromToListeners startet.");
		 Material mat = event.getBlock().getType();
//		 Bukkit.broadcastMessage("BlockFromToEvent startet"+event.getBlock().getType()+event.getToBlock().getType());
		 if (((mat.equals(Material.WATER)||mat.equals(Material.LEGACY_WATER)||mat.equals(Material.LEGACY_STATIONARY_WATER)||mat.equals(Material.AIR)||mat.equals(Material.LAVA))) && (event.getToBlock().getType().equals(Material.AIR) || event.getToBlock().getType().equals(Material.COBBLESTONE)||event.getToBlock().getType().equals(Material.STONE))) 
		 {
//			 Bukkit.broadcastMessage("BlockFromToEvent Water ");
			 PlayerBreakBlock locationBlock = null;
			 for (PlayerBreakBlock playerBreakBlock : BlockBreakListener.PLAYER_BREAK_BLOCK) {
				 if (playerBreakBlock.getEvent().getBlock().getLocation().equals(event.getToBlock().getLocation())) {
					 locationBlock = playerBreakBlock;
					 break;
				 } 
			 } 
			 if (locationBlock != null || !OreGenerator.CONFIG_MANAGER.getConfig().isOnlyPlayerBreakGenerateOre()) {
				 OreGeneratorToken token = OreGenerator.CONFIG_MANAGER.getConfig().getDefaultOreGeneratorToken(event.getBlock().getWorld().getName());
				 if (locationBlock != null) {
					 ItemStack breakItemInHand = locationBlock.getItemInHand();
					 for (OreGeneratorToken oreGeneratorToken : OreGenerator.CONFIG_MANAGER.getConfig().getOreGenerator()) {
//						 Bukkit.broadcastMessage("orgen : Name: "+oreGeneratorToken.getDisplayName()+"\n Item: "+oreGeneratorToken.getItemInHand()+"\n Lore"+oreGeneratorToken.getLore()+"\n Permission"+oreGeneratorToken.getPermission());
						 String[] itemInHand = oreGeneratorToken.getItemInHand().split(":");
						 Material type = null;
						 if(itemInHand != null && !itemInHand.equals(new String[] {""}) && !itemInHand.equals(null)) {
//							System.out.println("\n\n\nnot null: "+itemInHand[0]);
							 try{type = Material.valueOf(itemInHand[0]);}catch(Exception e) 
							 {
//								System.out.println("\n\n\nError, set DiamondPick");
								 type = Material.DIAMOND_PICKAXE;
							}
						 }else {
//							System.out.println("\n\n\nnull");
							 type = Material.DIAMOND_PICKAXE;
						 }
						 Integer durability = (itemInHand.length > 1) ? Integer.valueOf(itemInHand[1]) : null;
//             System.out.println("\n\n\nDur: "+durability+"DispName "+oreGeneratorToken.getDisplayName()+" Disp2: "+breakItemInHand.getItemMeta().getDisplayName());
						 if (type.equals(breakItemInHand.getType()) && (durability == null || breakItemInHand.getDurability() == durability.intValue()) && (oreGeneratorToken.getDisplayName().isEmpty() || (breakItemInHand.hasItemMeta() && breakItemInHand.getItemMeta().hasDisplayName() && oreGeneratorToken.getDisplayName().equals(breakItemInHand.getItemMeta().getDisplayName()))) && (token.getWorlds().isEmpty() || token.getWorlds().contains(event.getBlock().getLocation().getWorld().getName().toLowerCase()))) {
							 if(oreGeneratorToken.getLore().equals(null)|| oreGeneratorToken.getLore().isEmpty()|| oreGeneratorToken.getLore().equals("")||(locationBlock.getItemInHand().hasItemMeta() && locationBlock.getItemInHand().getItemMeta().hasLore() && locationBlock.getItemInHand().getItemMeta().getLore().contains(oreGeneratorToken.getLore()))) {
								 if (!(token.getPermission() != null && !token.getPermission().isEmpty() && !locationBlock.getPlayer().hasPermission(token.getPermission()))) {
//								 Bukkit.broadcastMessage("Lore:" + oreGeneratorToken.getLore()+"is"+locationBlock.getItemInHand().getItemMeta().getLore());
								 token = oreGeneratorToken; 
								 break;
								 }
							 }
// 				System.out.println("\n\n\ntoken set "+token.getDisplayName());
						 } else {
//						System.out.println("\n\n\nType: " +type+ " \"Item in Hand\": "+breakItemInHand.getType()+" equals "+type.equals(breakItemInHand.getType())+oreGeneratorToken.getDisplayName().isEmpty()+oreGeneratorToken.getDisplayName().equals(breakItemInHand.getItemMeta().getDisplayName()));
						 }
					 } 
					 if (!(token != null)) {
						token = OreGenerator.CONFIG_MANAGER.getConfig().getDefaultOreGeneratorToken(event.getBlock().getWorld().getName());
					 }
				 } 
				 if (token != null) {
					 int number = 0;
           
					 int chance = this.random.nextInt(100000) + 1;
					 for (BlockContent content : token.content) {
						 number = (int)(number + content.getPercent() * 1000.0D);
//             	System.out.println("\n\n\ntest for: "+content.getMaterial()+" with "+number+" and "+chance);
						 if (number >= chance) {
							 event.getToBlock().setType(content.getMaterial());
							 event.setCancelled(true);
//							 locationBlock.getPlayer().sendMessage("Should set to "+content.getMaterial());
//               event.getToBlock().setData((byte)content.getDurability());
							 break;
						 } 
					 }
//					System.out.println("\n\n\nshould worked"+number+" "+chance);
				 } 
			 } 
		 } 
	 }
	 @SuppressWarnings("deprecation")
	 public boolean generatesCobble(Material mat, Block b) {
		 int id = mat.getId();
		 int mirrorID1 = (id != 8 && id != 9) ? 8 : 10;
		 int mirrorID2 = (id != 8 && id != 9) ? 9 : 11;
		 BlockFace[] var8 = this.faces;
		 int var7 = this.faces.length;
		 
		 for (int var6 = 0; var6 < var7; var6++) {
			 BlockFace face = var8[var6];
			 Block r = b.getRelative(face, 1);
			 if (r.getType().getId() == mirrorID1 || r.getType().getId() == mirrorID2) {
				 return true;
			 }
		 } 
		 return true;
	 }
 }