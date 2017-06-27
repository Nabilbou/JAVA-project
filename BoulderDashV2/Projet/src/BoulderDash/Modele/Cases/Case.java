package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Niveau;
import BoulderDash.Modele.Animation.Animation;

public abstract class Case implements Interactions, Refresh {

	private int x;
	private int y;
	boolean vide;

	/**
	 * constructor, create a class that is not empty
	 * 
	 * @param x
	 * @param y
	 */
	public Case(int x, int y) {
		this(x, y, false);
	}

	/**
	 * constructor, creates a class that could be empty
	 * 
	 * @param x
	 * @param y
	 */
	public Case(int x, int y, boolean vide) {
		this.x = x;
		this.y = y;
		this.vide = vide;
	}

	/**
	 * Retourne l'identifiant de la case
	 */
	@SuppressWarnings("static-method")
	public String ID() {
		return "C";
	}

	/**
	 * recovery function of the case
	 */
	public abstract Animation getAnimation();

	/**
	 * function that verifies if the case is empty
	 */
	public boolean isVide() {
		return vide;
	}

	/**
	 * function that tells us if a case's priority is supperier than another,
	 * the lower cases in the table have the highest priority
	 */
	public boolean isSuperior(Case C) {
		return getY() > C.getY();
	}

	/**
	 * function that defines the interaction of a falling object, called when an
	 * item is falling towards a case, returns stable by default
	 */
	@Override
	public EtatChutable chutableArrive(Niveau N) {
		return EtatChutable.Stable;
	}

	/**
	 * function that defines the interaction with a character, called when a
	 * character arrives towards a case, returns false by default
	 */
	@Override
	public boolean PersonageArrive(Niveau N) {
		return false;
	}

	/**
	 * function that defines the interaction with an ennemy, is called when an
	 * ennemy arrives towards a case, returns false by default
	 */
	@Override
	public boolean EnemiArrive(Niveau N) {
		return false;
	}

	/**
	 * funciton that updates the case, does nothing by default
	 */
	@Override
	public void refresh(Niveau N) {
	}

	/**
	 * animation update function
	 */
	@Override
	public void refreshAnim() {
	}

	/**
	 * function that defines if an item should stay in the update table
	 */
	@Override
	public boolean stayInUpTable() {
		return false;
	}

	/**
	 * gets the current position x of the case
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the position x of the case
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * gets the current position y of the case
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the position y of the case
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * sets the position x and y of the case
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
