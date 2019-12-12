package utils;

import org.bukkit.Material;




public class BlockContent
{
	private double percent;
	private Material material;
	private int durability;
	private String lore;
	
	public BlockContent(double percent, Material material, int durability, String lore) 
	{
		this.percent = percent;
	    this.material = material;
	    this.durability = durability;
	    this.lore = lore;
	}
	public String getLore() {return lore;}
	
	public void setLore(String lore) {this.lore = lore;}
	
	public double getPercent() { return this.percent; }
	  
	public void setPercent(double percent) { this.percent = percent; }
	  
	public Material getMaterial() { return this.material; }
	 
	public void setMaterial(Material material) { this.material = material; }
	  
	public int getDurability() { return this.durability; }
	  
	public void setDurability(int durability) { this.durability = durability; }
}
