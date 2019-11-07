package utils;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBreakBlock
{
	private Player player;
	private BlockBreakEvent event;
	private ItemStack itemInHand;
	private Integer cooldown;
  
	public PlayerBreakBlock(Player player, BlockBreakEvent event, ItemStack itemInHand, Integer cooldown) 
	{
		this.player = player;
		this.event = event;
		this.itemInHand = itemInHand;
		this.cooldown = cooldown;
	}

	public Player getPlayer() { return this.player; }
  
	public void setPlayer(Player player) { this.player = player; }
  
	public BlockBreakEvent getEvent() { return this.event; }
  
	public void setEvent(BlockBreakEvent event) { this.event = event; }
  
	public ItemStack getItemInHand() { return this.itemInHand; }
  
	public void setItemInHand(ItemStack itemInHand) { this.itemInHand = itemInHand; }
  
	public Integer getCooldown() { return this.cooldown; }
  
	public void setCooldown(Integer cooldown) { this.cooldown = cooldown; }
}