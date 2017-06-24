package BoulderDash.Controlleur.Jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import BoulderDash.BoulderDash;
import BoulderDash.Modele.EtatApplication;
import BoulderDash.Vue.Vues;

public class GestionMenuJeu implements ActionListener {

	JButton b1;

	public GestionMenuJeu(JButton b1) {

		this.b1 = b1;
	}

	/**
	 * pauses the game triggers the actions associated to different buttons,
	 * resume the game.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		BoulderDash.getJeu().pauseOn();
		if (arg0.getSource() == b1) {
			menu();
		}

		BoulderDash.getJeu().pauseOff();
		BoulderDash.getFen().refocus();
	}

	/**
	 * lorsque l'on clique sur le bouton menu, on affiche une popup avec les
	 * différentes actions possibles opens a pop up with the possible actions
	 * whenever the user clicks the menu button
	 * 
	 */
	private static void menu() {
		Object[] options = { "Menu principal", "Recommencer le niveau",
				"Changer de niveau", "Retour au jeu" };
		int n = JOptionPane.showOptionDialog(BoulderDash.getFen(),
				"" + "Menu pause", "", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
		switch (n) {
		case 0:
			BoulderDash.getFen().changerVue(Vues.MENUPRINCIPAL);
			BoulderDash.getJeu().interompre();
			break;
		case 1:
			BoulderDash.getJeu().restartLevel();
			BoulderDash.setState(EtatApplication.Jeu);
			BoulderDash.getFen().changerVue(Vues.TABLEAUJEU);
			break;
		case 2:
			BoulderDash.getJeu().interompre();
			BoulderDash.getFen().changerVue(Vues.MENUCHOIXNIVEAU);
			break;
		default:
			break;
		}
	}

}
