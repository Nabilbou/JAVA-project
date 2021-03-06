package BoulderDash.Modele.Cases;

import BoulderDash.Modele.Animation.Animation;
import BoulderDash.Modele.Animation.TableAnimation;

public class MurIndestructible extends Case {

	public MurIndestructible(int x, int y) {
		super(x, y);
	}

	/**
	 * returns a mur inderstructible's sprite
	 */
	@Override
	public Animation getAnimation() {
		return TableAnimation.getMur();
	}

	@Override
	public String ID() {
		return "M";
	}

}
