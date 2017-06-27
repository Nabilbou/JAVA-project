package BoulderDash.Vue.Jeu;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import BoulderDash.BoulderDash;
import BoulderDash.Controlleur.Jeu.GestionClavierJeu;
import BoulderDash.Controlleur.Jeu.GestionFinJeu;

/**
 * Panel du jeu, englobe les vues du jeu
 * 
 * @see AireInfoJeu, AirePlateauJeu
 * @author Yiserot
 */
@SuppressWarnings("serial")
public class PanelJeu extends JPanel implements Observer {

	/**
	 * Terrain du jeu
	 */
	private AirePlateauJeu aireJeu;

	/**
	 * Zone d'information du jeu
	 */
	private AireInfoJeu aireInfo;

	/**
	 * 
	 * manager of the interactions with the keyboard
	 */
	private GestionClavierJeu listen;

	/**
	 * 
	 * panel's installation
	 */
	public PanelJeu() {
		setBackground(Color.lightGray);
		aireJeu = new AirePlateauJeu(BoulderDash.getJeu().getNiveau());
		add(aireJeu);
		aireInfo = new AireInfoJeu();
		add(aireInfo);

		listen = new GestionClavierJeu(BoulderDash.getJeu().getNiveau());
		addKeyListener(listen);
		BoulderDash.getJeu().addObserver(this);
	}

	/**
	 * 
	 * updates the view
	 */
	@Override
	public void update(Observable o, Object arg) {
		aireJeu.repaint();
		aireInfo.majinfos();
		if (BoulderDash.getJeu().isFini()) {
			GestionFinJeu.finJeu();
		}
	}
}
