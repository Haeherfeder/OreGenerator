package oregenerator.listeners;

import java.util.ArrayList;
import java.util.List;
import oregenerator.OreGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import utils.PlayerBreakBlock;

public class BlockBreakListener implements Listener
{
	public static List<PlayerBreakBlock> PLAYER_BREAK_BLOCK = new ArrayList<PlayerBreakBlock>();

  
	public BlockBreakListener() 
	{ Bukkit.getScheduler().runTaskTimer(OreGenerator.PLUGIN, new Runnable()
		{
			public void run() {
				List<PlayerBreakBlock> remove = null;
	            for (PlayerBreakBlock playerBreakBlock : BlockBreakListener.PLAYER_BREAK_BLOCK) 
	            {
	            	Integer cooldown = Integer.valueOf(playerBreakBlock.getCooldown().intValue() - 1);
	            	if (cooldown.intValue() <= 0) {
	            		if (remove == null) {
	            			remove = new ArrayList<PlayerBreakBlock>();
	            		}
	            		remove.add(playerBreakBlock);
	            	}
	            	playerBreakBlock.setCooldown(cooldown);
	            } 
	            if (remove != null) {
	            	for (PlayerBreakBlock playerBreakBlock : remove) {
	            		BlockBreakListener.PLAYER_BREAK_BLOCK.remove(playerBreakBlock);
	            	}
	            }
			}
	    },1L, 1L); 
	}

  
  @SuppressWarnings("deprecation")
  @EventHandler
  public void BlockBreakEvent(BlockBreakEvent event) {
	  //	System.out.println("\n\n\nTest");
	  Block cobblestone = event.getBlock();
//	  event.getPlayer().sendMessage("Block "+event.getBlock().getType()+"zerstört.");
	  if (cobblestone.getType().equals(Material.COBBLESTONE)||cobblestone.getType().equals(Material.STONE)) {
//		  event.getPlayer().sendMessage("Stone or Cobble");
		  Location location = cobblestone.getLocation();
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
		  if (lava && water && newLoc(location)) {
//			  event.getPlayer().sendMessage("Added to list");
			  PLAYER_BREAK_BLOCK.add(new PlayerBreakBlock(event.getPlayer(), event, event.getPlayer().getItemInHand(), Integer.valueOf(1000))); 
			//	System.out.println("\n\n\nTest2: lava"+lava+" Water "+water);
		  }else {
//			  event.getPlayer().sendMessage("Not added.");
		  }
	}
  	}


private boolean newLoc(Location location) {
	for(PlayerBreakBlock playerBreakBlock : BlockBreakListener.PLAYER_BREAK_BLOCK) {
		  if(playerBreakBlock.getEvent().getBlock().getLocation().equals(location)) {
			  return false;
		  }
	  }
	return true;
	}
}