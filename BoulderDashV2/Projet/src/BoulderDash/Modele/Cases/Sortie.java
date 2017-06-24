/**
 * 
 */
package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Niveau;
import BoulderDash.Modele.Animation.Animation;
import BoulderDash.Modele.Animation.TableAnimation;

/**
 * @author 4r3
 * 
 */
public class Sortie extends Case {
	private boolean ouverte;
	private boolean persoSortit;

	// private Animation animation;

	/**
	 * 
	 * creates a closed exit at x y
	 */
	public Sortie(int x, int y) {
		super(x, y);
		Fermer();
		persoSortit = false;
	}

	@Override
	public String ID() {
		return "S";
	}

	/**
	 * 
	 * verifies if the exit is open
	 */
	public boolean isOuverte() {
		return ouverte;
	}

	/**
	 * 
	 * opens the exit
	 */
	public void Ouvrir() {
		this.ouverte = true;
	}

	/**
	 * 
	 * closes the exit
	 */
	public void Fermer() {
		this.ouverte = false;
	}

	/**
	 * 
	 * allows the arrival if the exit is open
	 */
	@Override
	public boolean PersonageArrive(Niveau N) {
		if (ouverte) {
			N.echangeCases(N.getPerso().getX(), N.getPerso().getY(), getX(),
					getY());
			N.setFini();
			persoSortit = true;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * interaction function of a falling object, behaves as a wall or a falling
	 * object
	 * 
	 * @see Chutable
	 */
	@Override
	public EtatChutable chutableArrive(Niveau N) {
		if ((N.getCase(getX() + 1, getY() - 1).isVide())
				&& (N.getCase(getX() + 1, getY()).isVide())) {
			if (((Chutable) N.getCase(getX(), getY() - 1)).instable()) {
				N.echangeCases(getX() + 1, getY(), getX(), getY() - 1);
				N.remplirUpTable(getX(), getY() - 1);
				return EtatChutable.Chute;
			} else {
				return EtatChutable.Instable;
			}
		} else if (N.getCase(getX() - 1, getY()).isVide()
				&& N.getCase(getX() - 1, getY() - 1).isVide()) {
			if (((Chutable) N.getCase(getX(), getY() - 1)).instable()) {
				N.echangeCases(getX(), getY() - 1, getX() - 1, getY());
				N.remplirUpTable(getX(), getY() - 1);
				return EtatChutable.Chute;
			} else {
				return EtatChutable.Instable;
			}
		} else {
			return EtatChutable.Stable;
		}
	}

	/**
	 * affiche de la boue si le personnage est sorti, le sprite de sortie si la
	 * sortie est ouverte, un mur sinon
	 * 
	 */
	@Override
	public Animation getAnimation() {
		if (persoSortit) {
			return TableAnimation.getVide();
		} else if (ouverte) {
			return TableAnimation.getSortie();
		} else {
			return TableAnimation.getMur();
		}
	}
}
