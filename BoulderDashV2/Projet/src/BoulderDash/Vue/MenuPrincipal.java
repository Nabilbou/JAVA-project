package BoulderDash.Vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BoulderDash.Controlleur.GestionBoutonsMenu;
import BoulderDash.Modele.Variables;

/**
 * Vue du menu principal
 * 
 * 
 */
@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel {

	/**
	 * 
	 * initializes the main menu
	 */
	public MenuPrincipal() {
		initMenuPrincipal();
		setFocusable(true);
	}

	/**
	 * initializes the main meni
	 */
	private void initMenuPrincipal() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(Variables.HAUTEUR_PANEL_SCORE,
				Variables.LARGEUR_PANEL_SCORE));
		setBackground(Color.darkGray);
		setDoubleBuffered(true);

		JLabel logo = new JLabel(new ImageIcon("./sprites/titre.png"));

		JButton lancerJeu = new JButton("Jeu");

		JButton quitter = new JButton("Quitter");

		logo.setAlignmentX(CENTER_ALIGNMENT);
		lancerJeu.setAlignmentX(CENTER_ALIGNMENT);

		quitter.setAlignmentX(CENTER_ALIGNMENT);

		GestionBoutonsMenu ctrl;
		ctrl = new GestionBoutonsMenu(lancerJeu, quitter);
		lancerJeu.addActionListener(ctrl);

		quitter.addActionListener(ctrl);

		add(logo);
		add(Box.createVerticalStrut(15));
		add(lancerJeu);
		add(Box.createVerticalStrut(15));
		add(Box.createVerticalStrut(15));
		add(quitter);
	}
}
