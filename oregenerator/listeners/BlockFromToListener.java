 package oregenerator.listeners;
 
import config.OreGeneratorToken;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import oregenerator.OreGenerator;

import org.bukkit.Location;
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
 
	 @EventHandler
	 public void BlockFromToEvent(BlockFromToEvent event) {
//	System.out.println("\n\n\nBlockfromToListeners startet.");
		 Material from_mat = event.getBlock().getType();
//		 Bukkit.broadcastMessage("BlockFromToEvent startet"+event.getBlock().getType()+event.getToBlock().getType());
		 Material to_mat = event.getToBlock().getType();
		 if (((from_mat.equals(Material.WATER)||from_mat.equals(Material.LEGACY_WATER)||from_mat.equals(Material.LEGACY_STATIONARY_WATER)||from_mat.equals(Material.AIR)||from_mat.equals(Material.LAVA))) &&
//				 (to_mat.equals(Material.AIR) || to_mat.equals(Material.COBBLESTONE)|| to_mat.equals(Material.STONE)))
				 (to_mat.equals(Material.AIR) || to_mat.equals(Material.COBBLESTONE)|| to_mat.equals(Material.STONE)) && can_gen_cobble(event.getToBlock().getLocation()))
//				 (to_mat.equals(Material.COBBLESTONE)|| to_mat.equals(Material.STONE)))
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
						 String[] itemsInHand = oreGeneratorToken.getItemInHand().split(";");
						 String[][] itemInHand = new String[itemsInHand.length][];
						 for(int i = 0; i < itemsInHand.length; i++){
							 itemInHand[i] = itemsInHand[i].split(":");
						 }
						 Material[] needed_type_arr;
						 if(!itemInHand[0][0].equals("")) {
//							System.out.println("\n\n\nnot null: "+itemInHand[0]);
							 needed_type_arr = new Material[itemsInHand.length];
							 for(int i = 0; i< itemsInHand.length; i++){
								 try{needed_type_arr[i] = Material.valueOf(itemInHand[0][0]);}catch(Exception e)
								 {
//								System.out.println("\n\n\nError, set DiamondPick");
									 needed_type_arr[i] = Material.DIAMOND_PICKAXE;
								 }
							 }
						 } else {
//							System.out.println("\n\n\nnull");
							 needed_type_arr = new Material[0];
						 }
						 List<Material> needed_type = Arrays.asList(needed_type_arr);
						 int durability = itemInHand[0].length > 1 ? Integer.parseInt(itemInHand[0][1]) : -1;
//             System.out.println("\n\n\nDur: "+durability+"DispName "+oreGeneratorToken.getDisplayName()+" Disp2: "+breakItemInHand.getItemMeta().getDisplayName());
						 if (needed_type.contains(breakItemInHand.getType()) && (durability == -1 || breakItemInHand.getDurability() == durability) && (oreGeneratorToken.getDisplayName().isEmpty() || (breakItemInHand.hasItemMeta() && breakItemInHand.getItemMeta().hasDisplayName() && oreGeneratorToken.getDisplayName().equals(breakItemInHand.getItemMeta().getDisplayName()))) && (token.getWorlds().isEmpty() || token.getWorlds().contains(event.getBlock().getLocation().getWorld().getName().toLowerCase()))) {
							 if(oreGeneratorToken.getLore() == null || oreGeneratorToken.getLore().isEmpty()|| oreGeneratorToken.getLore().equals("")||(locationBlock.getItemInHand().hasItemMeta() && locationBlock.getItemInHand().getItemMeta().hasLore() && locationBlock.getItemInHand().getItemMeta().getLore().contains(oreGeneratorToken.getLore()))) {
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
					 if (token == null) {
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

	 private boolean can_gen_cobble(Location location) {
		 boolean lava = false;
		 boolean water = false;
		 for (int y = -1; y <= 1; y++) {
			 for (int x = -1; x <= 1; x++) {
				 for (int z = -1; z <= 1; z++) {
					 Block lavaWater = location.getWorld().getBlockAt(x + location.getBlockX(), y + location.getBlockY(), z + location.getBlockZ());
					 if (lavaWater.getType().equals(Material.LAVA) || lavaWater.getType().equals(Material.LEGACY_LAVA)) {
						 lava = true;
					 } else if (lavaWater.getType().equals(Material.WATER) || lavaWater.getType().equals(Material.LEGACY_WATER)) {
						 water = true;
					 }
				 }
			 }
		 }
		 return water && lava;
	 }

	 @SuppressWarnings("deprecation")
	 public boolean generatesCobble(Material mat, Block b) {
		 int id = mat.getId();
		 int mirrorID1 = (id != 8 && id != 9) ? 8 : 10;
		 int mirrorID2 = (id != 8 && id != 9) ? 9 : 11;
//		 BlockFace[] var8 = this.faces;

		 for (BlockFace face : this.faces) {
			 Block r = b.getRelative(face, 1);
			 if (r.getType().getId() == mirrorID1 || r.getType().getId() == mirrorID2) {
				 return true;
			 }
		 } 
		 return true;
	 }
 }