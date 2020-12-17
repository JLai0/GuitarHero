package guitar;

public class Guitar37 implements Guitar {
	// Guitar37 is responsible for the creation, implementation, and usage of the
	// GuitarString class by converting keyboard inputs into a sound output that
	// corresponds to a certain pitch stored.
	private final GuitarString[] GUITAR_STRING = new GuitarString[37];
	public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; // keyboard layout
	private int TIME;

	// pre: N/A
	// post: An GuitarString array with the length of 37 will be created which will
	// store 37 different chords. Time will be set to 0.
	public Guitar37() {
		double standardFrequency = 440.0;
		TIME = 0;
		for (int x = 0; x < 37; x++) {
			GUITAR_STRING[x] = new GuitarString(standardFrequency * Math.pow(2, ((x - 24.0)) / 12.0));
			// utilizes the frequency formula in order to calculate 37 different frequencies
			// used to creatd 37 GuitarString objects for the GUITAR_STRING array
		}
	}

	// pre: the inputed pitch will range from -24 to 12 inclusive. An int will be
	// passed in.
	// post: the GuitarString pluck method will be used on GuitarString object
	// corresponding to the pitch
	public void playNote(int pitch) {
		if (pitch >= -24 && pitch <= 12) {
			GUITAR_STRING[pitch + 24].pluck();
		}

	}

	// pre: A char will be passed in
	// post: true will be returned if the char that has been passed in is in the
	// KEYBOARD constant. false will be returned if the char is not in the KEYBOARD
	// constant
	public boolean hasString(char key) {
		if (KEYBOARD.indexOf(key) != -1) {
			return true;
		}
		return false;
	}

	// pre: a char will be passed in and the corresponding index of the char's
	// appearance in the KEYBOARD constant will be stored
	// post: the GuitarString pluck method will be used on GuitarString object
	// corresponding to the pitch
	public void pluck(char key) {
		int keyNumber = KEYBOARD.indexOf(key);
		if (hasString(key)) {
			GUITAR_STRING[keyNumber].pluck();
		}
	}

	// pre: GUITAR_STRING is an existing array with a valid length
	// post: the double returned will be the collective sum of each GUITAR_STRING's
	// object first ringBuffer value
	public double sample() {
		int sampleTotal = 0;
		for (int x = 0; x < GUITAR_STRING.length; x++) {
			sampleTotal += GUITAR_STRING[x].sample();
		}
		return sampleTotal;
	}

	// pre: GUITAR_STRING is an exisiting array with a valid length
	// post: the GuitarString's tic method will be used on every chord in
	// GUITAR_STRING. time will be incremented once.
	public void tic() {
		for (int x = 0; x < GUITAR_STRING.length; x++) {
			GUITAR_STRING[x].tic();
		}
		TIME++;
	}

	// pre: N/A
	// post: the value of TIME will be returned as an int
	public int time() {
		return this.TIME;
	}

}