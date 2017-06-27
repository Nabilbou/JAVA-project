package BoulderDash.Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import BoulderDash.BoulderDash;
import BoulderDash.Vue.Vues;

public class GestionBoutonsMenu implements ActionListener {

	private JButton bouton1;
	private JButton bouton2;

	/**
	 * constructeur du gestionaire de menu
	 * 
	 * @param b1
	 *            Play button
	 * @param b2
	 *            Exit button
	 */
	public GestionBoutonsMenu(JButton b1, JButton b2) {
		this.bouton1 = b1;
		this.bouton2 = b2;

	}

	/**
	 * triggers the right actions associated to the pressed keys
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.bouton1) {
			BoulderDash.getFen().changerVue(Vues.MENUCHOIXNIVEAU);
			BoulderDash.getJeu().resetScore();

		} else if (arg0.getSource() == this.bouton2) {
			System.exit(0);
		}
	}
}