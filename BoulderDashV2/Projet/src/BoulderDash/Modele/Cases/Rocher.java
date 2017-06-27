/**
 * 
 */
package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Niveau;
import BoulderDash.Modele.Animation.Animation;
import BoulderDash.Modele.Animation.TableAnimation;

/**
 *
 * 
 */
public class Rocher extends Chutable {

	/**
	 * @param pos_x
	 * @param pos_y
	 */
	public Rocher(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}

	@Override
	public String ID() {
		return "R";
	}

	/**
	 * 
	 * interaction with the character, pushes the rocher left or right depending
	 * on the space
	 */
	@Override
	public boolean PersonageArrive(Niveau N) {
		if (N.getPerso().getDeplace() == Directions.Gauche) {
			if (N.getCase(getX() - 1, getY()).isVide()) {
				N.echangeCases(getX(), getY(), getX() - 1, getY());
				N.addUptable(getX(), getY());
				N.remplirUpTable(getX() + 1, getY());
			}
		} else if (N.getPerso().getDeplace() == Directions.Droite) {
			if (N.getCase(getX() + 1, getY()).isVide()) {
				N.echangeCases(getX(), getY(), getX() + 1, getY());
				N.addUptable(getX(), getY());
				N.remplirUpTable(getX() - 1, getY());
			}
		}
		return false;
	}

	/**
	 * 
	 * returns the rocher's sprite
	 */
	@Override
	public Animation getAnimation() {
		return TableAnimation.getRocher();
	}
}
