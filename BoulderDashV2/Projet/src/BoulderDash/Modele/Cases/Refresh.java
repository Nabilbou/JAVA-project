/**
 * 
 */
package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Niveau;

/**
 * 
 * function that allows the level's items to be updated
 * 
 */
public interface Refresh {

	public void refresh(Niveau N);

	public void refreshAnim();

	public boolean stayInUpTable();
}
