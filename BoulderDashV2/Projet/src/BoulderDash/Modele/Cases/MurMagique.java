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
public class MurMagique extends Case {
	private boolean active;

	public MurMagique(int x, int y) {
		super(x, y);
		active = false;
	}

	/**
	 * 
	 * function used to activate a mur magique, will call every mur magique
	 * function
	 */
	public void activer(Niveau N) {
		active = true;
		if (N.getCase(getX() - 1, getY()) instanceof MurMagique
				&& !((MurMagique) N.getCase(getX() - 1, getY())).isActive()) {
			((MurMagique) N.getCase(getX() - 1, getY())).activer(N);
		}

		if (N.getCase(getX() + 1, getY()) instanceof MurMagique
				&& !((MurMagique) N.getCase(getX() + 1, getY())).isActive()) {
			((MurMagique) N.getCase(getX() + 1, getY())).activer(N);
		}
	}

	/**
	 * 
	 * function that allows to see whatever the wall is active or not
	 */
	public boolean isActive() {
		return active;
	}

	@Override
	public String ID() {

		return "X";

	}

	/**
	 * returns the wall's sprite if inactive
	 */
	@Override
	public Animation getAnimation() {
		if (active) {
			return TableAnimation.getMurMagique();
		} else {
			return TableAnimation.getMur();
		}
	}

	/**
	 * fonction d'interaction d'élément chutable : si un élément chutable
	 * arrive et est en état de chute, alors il active le mur et est
	 * transmuté, sinon le mur se comporte comme n'importe quel mur
	 * 
	 */
	@Override
	public EtatChutable chutableArrive(Niveau N) {
		if (((Chutable) N.getCase(getX(), getY() - 1)).chute()) {
			if (!active) {
				activer(N);
			}
			N.remUptable(N.getCase(getX(), getY() - 1));
			if (N.getCase(getX(), getY() + 1).isVide()) {
				if (N.getCase(getX(), getY() - 1) instanceof Diamant) {
					N.insereRocher(getX(), getY() + 1);
				} else {
					N.insereDiamant(getX(), getY() + 1);
				}
				((Chutable) N.getCase(getX(), getY() + 1)).setChute();
			}
			N.insereVide(getX(), getY() - 1);

			return EtatChutable.Instable;
		} else {
			return modeMur(N);
		}
	}

	/**
	 * 
	 * function that simulates a normal wall or a falling object whenever the
	 * mur magic is inactive
	 * 
	 * @see Chutable
	 */
	private EtatChutable modeMur(Niveau N) {
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
}
