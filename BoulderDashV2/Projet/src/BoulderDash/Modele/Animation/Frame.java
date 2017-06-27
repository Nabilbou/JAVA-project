package BoulderDash.Modele.Animation;

import java.awt.image.BufferedImage;

/**
 * Classe d'animation, permet d'animer un tableau de sprites Peut être lancé
 * ou stoppé
 */
public class Frame {

	/**
	 * Image of the frame
	 */
	private BufferedImage frame;

	/**
	 * duration of the frame
	 */
	private int duree;

	/**
	 * Creation of the frame
	 * 
	 * @param frame
	 *            image de la frame
	 * @param duree
	 *            temps durant lequel la frame reste affichée
	 */
	public Frame(BufferedImage frame, int duree) {
		this.frame = frame;
		this.duree = duree;
	}

	/**
	 * Recovers the image of the frame
	 * 
	 * @return l'image de la frame
	 */
	public BufferedImage getFrame() {
		return frame;
	}

	/**
	 * allows to modify the image of the frame
	 * 
	 * @param frame
	 *            la nouvelle image de la frame
	 */
	public void setFrame(BufferedImage frame) {
		this.frame = frame;
	}

	/**
	 * allows the recovery of the duration of the frame
	 * 
	 * @return la durée de la frame
	 */
	public int getduree() {
		return duree;
	}

	/**
	 * modifies the frame's duration
	 * 
	 * @param la
	 *            nouvelle durée de la frame
	 */
	public void setduree(int duree) {
		this.duree = duree;
	}

}
