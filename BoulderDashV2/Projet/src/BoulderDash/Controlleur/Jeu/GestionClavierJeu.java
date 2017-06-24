/**
 * 
 */
package BoulderDash.Controlleur.Jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import BoulderDash.Modele.Niveau;
import BoulderDash.Modele.Cases.Directions;

public class GestionClavierJeu implements KeyListener {
	/**
	 * map of the pressed keys, every key is false by default
	 */
	private Map<Integer, Boolean> keys;
	private Niveau niveau;

	public GestionClavierJeu(Niveau niveau) {
		this.niveau = niveau;
		keys = new HashMap<>();
		keys.put(KeyEvent.VK_RIGHT, false);
		keys.put(KeyEvent.VK_LEFT, false);
		keys.put(KeyEvent.VK_UP, false);
		keys.put(KeyEvent.VK_DOWN, false);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	/**
	 * changes to false the pressed key's variable if another keys is pressed,
	 * it would take the lead
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keys.put(KeyEvent.VK_UP, false);
			break;
		case KeyEvent.VK_LEFT:
			keys.put(KeyEvent.VK_LEFT, false);
			break;
		case KeyEvent.VK_DOWN:
			keys.put(KeyEvent.VK_DOWN, false);
			break;
		case KeyEvent.VK_RIGHT:
			keys.put(KeyEvent.VK_RIGHT, false);
			break;
		default:
			break;
		}
		if (keys.get(KeyEvent.VK_RIGHT)) {
			droite();
		} else if (keys.get(KeyEvent.VK_LEFT)) {
			gauche();
		} else if (keys.get(KeyEvent.VK_UP)) {
			haut();
		} else if (keys.get(KeyEvent.VK_DOWN)) {
			bas();
		} else {
			niveau.getPerso().setDeplace(Directions.Null);
		}
	}

	/**
	 * starts the function associated to the key
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			haut();
			break;
		case KeyEvent.VK_LEFT:
			gauche();
			break;
		case KeyEvent.VK_DOWN:
			bas();
			break;
		case KeyEvent.VK_RIGHT:
			droite();
			break;
		default:
			break;
		}
	}

	/**
	 * processing right key press
	 */
	private void droite() {
		keys.put(KeyEvent.VK_RIGHT, true);
		niveau.getPerso().setDeplace(Directions.Droite);
	}

	/**
	 * processing down key press
	 */
	private void bas() {
		keys.put(KeyEvent.VK_DOWN, true);
		niveau.getPerso().setDeplace(Directions.Bas);
	}

	/**
	 * processing left key press
	 */
	private void gauche() {
		keys.put(KeyEvent.VK_LEFT, true);
		niveau.getPerso().setDeplace(Directions.Gauche);
	}

	/**
	 * processing upper key press
	 */
	private void haut() {
		keys.put(KeyEvent.VK_UP, true);
		niveau.getPerso().setDeplace(Directions.Haut);
	}

}
