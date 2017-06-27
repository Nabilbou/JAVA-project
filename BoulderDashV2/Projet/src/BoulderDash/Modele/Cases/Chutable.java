/**
 * 
 */
package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Niveau;

/**
 * @author 4r3
 * 
 */
public abstract class Chutable extends Case {
	private EtatChutable etat;

	/**
	 * creation of a falling object
	 */
	public Chutable(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		etat = EtatChutable.Stable;
	}

	/**
	 * updates the falling object
	 */
	@Override
	public void refresh(Niveau N) {
		Case C = N.getCase(getX(), getY() + 1);
		etat = C.chutableArrive(N);
	}

	/**
	 * met l'objet en état de chute
	 */
	public void setChute() {
		etat = EtatChutable.Chute;
	}

	/**
	 * verifies if the object is in falling state
	 * 
	 */
	public boolean chute() {
		return etat == EtatChutable.Chute;
	}

	/**
	 * sets the items to an instable state
	 */
	public void setInstable() {
		etat = EtatChutable.Instable;
	}

	/**
	 * checks if the object is stable
	 */
	public boolean instable() {
		return etat != EtatChutable.Stable;
	}

	/**
	 * sets an object to a stable state
	 */
	public void setStable() {
		etat = EtatChutable.Stable;
	}

	/**
	 * verifies if the object is stable
	 */
	public boolean stable() {
		return etat == EtatChutable.Stable;
	}

	/**
	 * fonction d'interaction avec un objet chutable, on vérifie que les cases
	 * à gauche au niveau de l'objet arrivant et l'objet d'arrivée sont vides,
	 * si oui alors l'objet est échangé avec cette dernière ; on effectue la
	 * même chose à droite
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
}
