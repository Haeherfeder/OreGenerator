package config;
 
import java.util.ArrayList;
import java.util.List;
 
 
 
 
 
 
 
 
public class Config
{
	private boolean onlyPlayerBreakGenerateOre = true;
	private List<OreGeneratorToken> oreGenerator = new ArrayList<OreGeneratorToken>();
 
   
	public OreGeneratorToken getDefaultOreGeneratorToken(String world) {
		for (OreGeneratorToken token : this.oreGenerator) {
			if ((token.getItemInHand().equals("*") || token.getItemInHand().isEmpty()) && (
				token.getWorlds().isEmpty() || token.getWorlds().contains(world))) {
			return token;
			}
		} 
    	return null;
	}
	public boolean isOnlyPlayerBreakGenerateOre() { return this.onlyPlayerBreakGenerateOre; }
	public void setOnlyPlayerBreakGenerateOre(boolean onlyPlayerBreakGenerateOre) { this.onlyPlayerBreakGenerateOre = onlyPlayerBreakGenerateOre; }
	public List<OreGeneratorToken> getOreGenerator() { return this.oreGenerator; }
	public void setOreGenerator(List<OreGeneratorToken> oreGenerator) { this.oreGenerator = oreGenerator; }
}