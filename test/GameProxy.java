package test;

import java.lang.reflect.Field;
import java.io.*;
import java.util.*;

import dominion.*;
import dominion.card.*;

public class GameProxy {
	/**
	 * Référence vers le dernier GameProxy créé
	 */
	public static GameProxy shared;

	/**
	 * 
	 */
	public Game game;
	public List<CardList> supplyStacks;
	public Player[] players;
	private Field scanner_f, currentPlayerIndex_f;
	private PipedOutputStream pipeOut;

	public GameProxy(Game g) {
		GameProxy.shared = this;
		this.game = g;
		try {
			Field supplyStacks_f = Game.class.getDeclaredField("supplyStacks");
			supplyStacks_f.setAccessible(true);
			this.supplyStacks = (List<CardList>) supplyStacks_f.get(this.game);
			Field players_f = Game.class.getDeclaredField("players");
			players_f.setAccessible(true);
			this.players = (Player[]) players_f.get(this.game);
			this.scanner_f = Game.class.getDeclaredField("scanner");
			scanner_f.setAccessible(true);
			this.currentPlayerIndex_f = Game.class.getDeclaredField("currentPlayerIndex");
			currentPlayerIndex_f.setAccessible(true);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public Player getPlayer(int index) {
		return this.game.getPlayer(index);
	}

	public int getCurrentPlayerIndex() {
		try {
			return this.currentPlayerIndex_f.getInt(this.game);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Renvoie la pile correspondant au nom de carte passé en argument.
	 * @param cardName: nom de la carte recherchée
	 * @return: la pile de réserve correspondant au nom, ou null si aucune.
	 */
	public CardList getSupplyStack(String cardName) {
		for (CardList stack: this.supplyStacks) {
			if (!stack.isEmpty() && stack.get(0).getName().equals(cardName)) {
				return stack;
			}
		}
		return null;
	}

	public void run() {
		PrintStream outs = System.out;
		try {
			System.setOut(Test.nullOut);
			this.game.run();
		} finally {
			System.setOut(outs);
		}
	}

	public void setInput(String inputString) {
		ByteArrayInputStream is = new ByteArrayInputStream(inputString.getBytes());
		try {
			this.scanner_f.set(this.game, new Scanner(is));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void setPipedInput() {
		try {
			PipedInputStream ins = new PipedInputStream();
			PipedOutputStream outs = new PipedOutputStream(ins);
			this.scanner_f.set(this.game, new Scanner(ins));
			this.pipeOut = outs;
		} catch (IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToPipedInput(String inputString) {
		try {
			this.pipeOut.write(inputString.getBytes(), 0, inputString.length());
			this.pipeOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}