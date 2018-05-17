package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Domaine (Estate)
 * 
 * 1 VP
 */
public class Estate extends VictoryCard {
	public Estate() { super("Estate", 2);	}

	@Override
	public void play(Player p) {

	}

	public int victoryValue(Player p) {
		return 1;
	}
}