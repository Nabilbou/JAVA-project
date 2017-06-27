package BoulderDash;

import BoulderDash.Modele.EtatApplication;
import BoulderDash.Modele.Jeu;
import BoulderDash.Vue.Fenetre;

public class BoulderDash {

	private static Jeu jeu;
	private static Fenetre fen;
	private static EtatApplication state;

	/**
	 * installation of the game's functions
	 */
	private static void init() {
		state = EtatApplication.MenuPrincipal;
		jeu = new Jeu();

		fen = new Fenetre();
		fen.setVisible(true);

	}

	/**
	 * installation of the game's function
	 */
	private static void Application() {
		while (true) {
			switch (state) {
			case Jeu:
				jeu.gestion();
				break;
			// $CASES-OMITTED$
			default:
				break;
			}

		}
	}

	/**
	 * program's main, initnializes the program and launches it.
	 * 
	 */
	public static void main(String[] args) {
		BoulderDash.init();
		BoulderDash.Application();
	}

	/**
	 * allow the recovery of the game
	 * 
	 * @return
	 */
	public static Jeu getJeu() {
		return jeu;
	}

	/**
	 * allows the recovery of the game's window
	 * 
	 * @return
	 */
	public static Fenetre getFen() {
		return fen;
	}

	/**
	 * allows the recovery of the game's status
	 * 
	 */
	public static void setState(EtatApplication state) {
		BoulderDash.state = state;
	}
}
