/**
 * 
 */
package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Niveau;

/**
 * interface that countains every kind of interaction
 * 
 */
public interface Interactions {
	/**
	 * 
	 * function called by the character when moving towards another case
	 */
	boolean PersonageArrive(Niveau N);

	/**
	 * 
	 * function called by a falling object whenever it is falling
	 */
	EtatChutable chutableArrive(Niveau N);

	/**
	 * function called by an ennemy while moving
	 * 
	 */
	boolean EnemiArrive(Niveau N);
}
