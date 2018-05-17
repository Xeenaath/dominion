package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Malédiction (Curse)
 * 
 * -1 VP
 */
public class Curse extends CurseCard {
	public Curse() { super("Curse", 0);	}

	@Override
	public void play(Player p) {

	}

	public int victoryValue(Player p) {
		return -1;
	}
}