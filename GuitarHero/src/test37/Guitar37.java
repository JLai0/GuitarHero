package test37;
// skeleton version of the class

import test37.Guitar;
import test37.GuitarString;

public class Guitar37 implements Guitar {
	private final double CONCERT_A = 440.0;
	private final GuitarString[] GUITAR_STRING = new GuitarString[37];
	public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; // keyboard layout
	private int TIME;

	public Guitar37() {
		TIME = 0;
		for (int x = 0; x < 37; x++) {
			GUITAR_STRING[x] = new GuitarString(CONCERT_A * Math.pow(2, (((double) x - 24.0)) / 12.0));
		}
	}

	public void playNote(int pitch) {
		if (pitch >= -24 && pitch <= 12) {
			GUITAR_STRING[pitch + 24].pluck();
			System.out.println(pitch);
		}

	}

	public boolean hasString(char key) {
		if (KEYBOARD.indexOf(key) != -1) {
			return true;
		}
		return false;
	}

	public void pluck(char key) {
		int keyNumber = KEYBOARD.indexOf(key);
		if(hasString(key)) {
		GUITAR_STRING[keyNumber].pluck();
		}
	}

	public double sample() {
		int sampleTotal = 0;
		for (int x = 0; x < GUITAR_STRING.length; x++) {
			sampleTotal += GUITAR_STRING[x].sample();
		}
		return sampleTotal;
	}

	public void tic() {
		for (int x = 0; x < GUITAR_STRING.length; x++) {
			GUITAR_STRING[x].tic();
		}
		TIME++;
	}

	public int time() {
		return this.TIME;
	}

}