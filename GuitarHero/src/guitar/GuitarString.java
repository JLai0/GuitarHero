package guitar;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
/*
 * Name: Jonathan Lai
 * Due Date: October 11th 2019
 * Class: Data Structures
 * Period: 02
 * Teacher: Ms. Maloney
 * LetterInventory
 */

public class GuitarString {
	// The ringBuffer's size based on the sampling rate divided by frequency to the
	// nearest int
	private Queue<Double> ringBuffer = new LinkedList<>();
	// ringBuffer that will store each frequency
	// pre:the frequency passed in will be a double. if not an
	// IllegalArgumentException error will be thrown
	// post: a GuitarString object will be constructed with the given frequency that
	// will dictate the capacity of the ringBuffer. The capacity will not be less
	// than 2.
	public GuitarString(double frequency) {
		int capacity;
		// Constructs a GuitarString object that will take in a given frequency and
		// create a ringBuffer with the given frequency
		if (frequency <= 0) {
			// if frequency is less than or equal to 0 an IllegalArgumentException will be
			// thrown
			throw new IllegalArgumentException("Invalid Frequency! Frequency Must Be More Than 0!");
			// prints out error upon receiving an IllegalArgumentException (Frequency is
			// less than or equal to 0).
		}
		capacity = (int) Math.round((((double) StdAudio.SAMPLE_RATE) / frequency));
		// if the frequency is not 0 or less then create a capacity value using the
		// frequency given and a constant called SAMPLE_RATE
		if (capacity < 2) {
			// if the created capacity is less than 2 then throw an IllgalArgumentException
			throw new IllegalArgumentException("Invalid Ring Buffer Size! Ring Buffer Must Be 2 or More!");
			// prints out error upon receiving an IllegalArgumentException (ringBuffer
			// capacity is less than 2).
		}
		for (int x = 0; x < capacity; x++) {
			// Auto instantiates ringBuffer given the capacity calculated. 0.0 will be a
			// placeholder value.
			ringBuffer.add(0.0);
		}
	}

	// pre: the passed in double array will have at least 3 values or more or else
	// an IllegalArgumentException will be thrown
	// post: ringBuffer will have added every single value from the passed in double
	// array
	public GuitarString(double[] init) {
		if (init.length < 2) {
			throw new IllegalArgumentException("Invalid Ring Buffer Size! Ring Buffer Must Be 2 or More!");
			// prints out error upon receiving an IllegalArgumentException (ringBuffer
			// capacity is less than 2).
		}
		for (int x = 0; x < init.length; x++) {
			// using the passed in given array it will pass in every frequency into the
			// ringBuffer
			ringBuffer.add(init[x]);
		}

	}

	// pre: None.
	// post: all ringBuffer values will be replaced by frequencies between -0.5 and
	// 0.5 inclusive
	public void pluck() {
		// replace all of the elements in the ringBuffer with random values between -0.5
		// and 0.5.
		Random randomFrequency = new Random();
		// instantiates a a random object
		for (int x = 0; x < ringBuffer.size(); x++) {
			// for to traverse the ringBuffer using the size of ringBuffer.
			ringBuffer.remove();
			// removes ringBuffer value at the beginning of the queue that will be replaced
			// with a random value between -0.5 and 0.5 inclusive
			ringBuffer.add(randomFrequency.nextDouble() - 0.5);
			// using the random object it will grab a value between -0.5 and 0.5 inclusive
			// and add it to the queue.
		}
	}

	// pre: ringBuffer values will not all be 0.0
	// post: changes ringBuffer values accordingly
	public void tic() {
		double decayFactor = 0.996;
		// constant decay rate multiplied by ringBuffer in order to derive the
		// karplusStrongUpdate in tic()
		double karplusStrongUpdate = ((ringBuffer.remove() + ringBuffer.peek())) / 2 * decayFactor;
		// uses the Karplus Strong formula in order to create a double value that will
		// be stored as karplusStrongUpdate
		ringBuffer.add(karplusStrongUpdate);
		// adds the calculated Karplus Strong value to the ringBuffer queue.
	}

	// pre: there will be a value in the ringBuffer as it will auto instantiates to
	// 0.
	// post returns the first value in the ringBuffer queue as a double
	public double sample() {
		// returns the first value in the ringBuffer
		return ringBuffer.peek();

	}

}
