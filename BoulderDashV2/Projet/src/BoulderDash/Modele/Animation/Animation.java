package BoulderDash.Modele.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Animation class, used to open up a table as sprite, can be opened or stopped
 * 
 * @see Sprite, Frame
 */
public class Animation {

	private int compteur;

	/**
	 * delay before the frame's change
	 */
	private int delaiFrame;

	/**
	 * current animation
	 */
	private int frameCourante;

	/**
	 * total number of frames for the animation
	 */
	private int totalFrames;

	/**
	 * indicates if the animation is stopped
	 */
	private boolean enPause;

	/**
	 * List of frames
	 */
	private List<Frame> frames = new ArrayList<>();

	/**
	 * Crée une animation en stockant les frames dans une liste et en
	 * initialisant le délai creates an animation while stacking the frames un
	 * a list and initializes the delay
	 * 
	 * @param frames
	 *            table of the images that could be used
	 * @param delaiFrame
	 *            frame's change delay
	 */
	public Animation(BufferedImage[] frames, int delaiFrame) {
		this.delaiFrame = delaiFrame;
		this.enPause = true;

		for (int i = 0; i < frames.length; i++) {
			addFrame(frames[i], delaiFrame);
		}

		this.compteur = 0;
		this.delaiFrame = delaiFrame;
		this.frameCourante = 0;
		this.totalFrames = this.frames.size();

	}

	/**
	 * starts the animation
	 */
	public void start() {
		if (!enPause) {
			return;
		}

		if (frames.size() == 0) {
			return;
		}

		enPause = false;
	}

	/**
	 * stops the animation
	 */
	public void stop() {
		if (frames.size() == 0) {
			return;
		}

		enPause = true;
	}

	/**
	 * Transforms the image to a frame due to its duration, then stocks it in a
	 * list
	 * 
	 * @param frame
	 *            une des images de l'animation
	 * @param duration
	 *            délai du changement de frame
	 */
	private void addFrame(BufferedImage frame, int duration) {
		if (duration <= 0) {
			System.err.println("Invalid duration: " + duration);
			throw new RuntimeException("Invalid duration: " + duration);
		}

		frames.add(new Frame(frame, duration));
		frameCourante = 0;
	}

	/**
	 * Recovers the current image
	 * 
	 * @return the current frame's image
	 */
	public BufferedImage getSprite() {
		return frames.get(frameCourante).getFrame();
	}

	/**
	 * recovers the static image of an animation
	 * 
	 * @return a static image
	 */
	public BufferedImage getSpriteImmobile() {
		return frames.get(0).getFrame();
	}

	/**
	 * updates the animation thanks to the timer which edits the current frame
	 * once the deadline has passed
	 */
	public void update() {
		if (!enPause) {
			compteur++;
			if (compteur > delaiFrame) {
				compteur = 0;
				frameCourante += 1;

				if (frameCourante > totalFrames - 1) {
					frameCourante = 0;
				} else if (frameCourante < 0) {
					frameCourante = totalFrames - 1;
				}
			}
		}

	}

}
