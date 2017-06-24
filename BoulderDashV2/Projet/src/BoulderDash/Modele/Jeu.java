package BoulderDash.Modele;

import java.io.File;
import java.util.Observable;

import javax.swing.JOptionPane;

import BoulderDash.BoulderDash;
import BoulderDash.Vue.Vues;

public class Jeu extends Observable {

	private Niveau level;
	private String levelPath;
	private int score;
	private boolean pause;
	private boolean interuption;
	private boolean niveauFini;

	/**
	 * crée un nouveau jeu, avec son score à 0 et ses informations de niveau
	 * à null creates a new game session, with a score of 0 and its level's
	 * informations at null
	 */
	public Jeu() {
		score = 0;
		levelPath = null;
		level = null;
	}

	/**
	 * charge un niveau situé au chemin path, initialise les données niveau du
	 * jeu
	 * 
	 * 
	 * chemin vers le niveau
	 * 
	 * loads a level situated in path, initializes the data level of the game
	 * 
	 * 
	 * 
	 */
	public void chargerNiveau(String path) {
		deleteObservers();
		niveauFini = false;
		interuption = false;
		levelPath = path;
		level = new Niveau(path);
	}

	/**
	 * permet de récupérer le niveau du jeu
	 * 
	 * @return niveau dans le jeu
	 */
	public Niveau getNiveau() {
		return level;
	}

	/**
	 * récupère la liste des niveaux recovers the levels' list
	 */
	public static String[] getListeNiveaux() {
		File f = new File("./niveaux");
		File[] paths;

		String[] S = null;

		paths = f.listFiles();

		S = new String[paths.length];

		for (int i = 0; i < paths.length; i++) {
			S[i] = paths[i].toString();
		}
		return S;
	}

	/**
	 * game's loop, handles the game
	 */
	public void gestion() {
		int i = 0;
		long time = System.currentTimeMillis();

		if (level == null) {
			return;
		}

		while (!level.isFini() && !interuption) {

			// measures the execution time of the last cycle, and calculates the
			// awaiting time
			// for this cycle
			time = Variables.FRAME + System.currentTimeMillis() - time;
			if (time <= 0) {
				time = 1;
			}

			// awaiting time of a cycle
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time = System.currentTimeMillis();
			// manages pauses
			if (!pause) {

				// manages the updates' cycle, executed every x
				// cycle
				//
				if (i == 0) {
					try {
						level.refresh();
					} catch (NullPointerException e) {
						corruptedLevel();
						return;
					}
				}

				// refreshes the game's animations
				level.refreshAnim();

				i = (i + 1) % Variables.CYCLES;

				// notification des changements à la fenêtre
				// notification of the changes
				setChanged();
				notifyObservers();
			}
		}
		// end game's manager
		BoulderDash.setState(EtatApplication.MenuPrincipal);
		// checks if the game is interrupted
		if (!interuption) {
			// if the game hasn't been stopped, incrementes the score.
			try {
				Thread.sleep(Variables.FRAME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			level.refreshAnim();
			niveauFini = true;
			int nScore = level.getTmax() + level.getScore();
			setChanged();
			notifyObservers();
			if (level.getPerso().isVivant()) {
				score += nScore;
			} else {
				score = 0;
			}
		}
	}

	/**
	 * pauses the game
	 */
	public void pauseOn() {
		pause = true;
	}

	/**
	 * stops the pause
	 */
	public void pauseOff() {
		pause = false;
	}

	/**
	 * error message when a crash occurs
	 */
	private static void corruptedLevel() {
		JOptionPane.showMessageDialog(BoulderDash.getFen(), "Niveau corompu",
				"ERREUR", JOptionPane.ERROR_MESSAGE);
		BoulderDash.getFen().changerVue(Vues.MENUPRINCIPAL);
		BoulderDash.setState(EtatApplication.MenuPrincipal);
	}

	/**
	 * reboots the actual level, loses the score acheived during the session
	 */
	public void restartLevel() {
		chargerNiveau(levelPath);
	}

	/**
	 * returns the score to 0
	 */
	public void resetScore() {
		score = 0;
	}

	/**
	 * recovers the score's level plus the game's level
	 */
	public int getScore() {
		return score + level.getScore();
	}

	/**
	 * stopps the game (called by the menu whenever a reboot or exit occurs)
	 */
	public void interompre() {
		interuption = true;
	}

	/**
	 * used if the level is finished
	 */
	public boolean isFini() {
		return niveauFini;
	}
}