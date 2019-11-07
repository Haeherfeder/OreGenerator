
package config;

import java.util.ArrayList;
import java.util.List;
import oregenerator.OreGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import utils.BlockContent;



public class OreGeneratorToken
{
	private String itemInHand;
	private String displayName;
	private String permission;
	private List<String> chances;
	private List<String> worlds;
	public List<BlockContent> content;

	public OreGeneratorToken() {
		this.content = new ArrayList<BlockContent>();


		this.itemInHand = "*";
		this.displayName = "";
		this.permission = "";
		this.chances = new ArrayList<String>();
		this.worlds = new ArrayList<String>();
		initContent();
	}


	public void initContent() { Bukkit.getScheduler().runTaskLater(OreGenerator.PLUGIN, new Runnable()
		{
			public void run() {
				for (String chance : OreGeneratorToken.this.chances) {
					try {
						Double percent = Double.valueOf(chance.split(", ")[0]);
						String[] material = chance.split(", ")[1].split(":");
						Material type = Material.valueOf(material[0]);
						int durability = (material.length > 1) ? Integer.valueOf(material[1]).intValue() : 0;
						if (type != null && percent != null && type.isBlock() && percent.doubleValue() > 0.0D) {
							OreGeneratorToken.this.content.add(new BlockContent(percent.doubleValue(), type, durability));
						}
					}
					catch (Exception e) {
						OreGenerator.PLUGIN.getLogger().warning("Error while decoding string " + chance + " in string-list chances! (" + OreGeneratorToken.this.itemInHand + ")");
					} 
				} 
			}
	},1L); 
	}



	public String getItemInHand() { return this.itemInHand; }
	public void setItemInHand(String itemInHand) { this.itemInHand = itemInHand; }
	
	
	
	public String getDisplayName() { return this.displayName; }
	
	
	public void setDisplayName(String displayName) { this.displayName = displayName; }
	
	
	
	public String getPermission() { return this.permission; }
		
		
	public void setPermission(String permission) { this.permission = permission; }
	
	
	
	public List<String> getChances() { return this.chances; }
	
	
	public void setChances(List<String> chances) { this.chances = chances; }
	
	
	
	public List<String> getWorlds() { return this.worlds; }
	
	
	
	public void setWorlds(List<String> worlds) { this.worlds = worlds; }
	}