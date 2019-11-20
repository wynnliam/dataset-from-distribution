package datasetgenerator;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class DatasetGenerator {
	public static void main(String[] args) throws IOException {
		FrequencyTable distribution = distribution();
		// Sum is the count of items in distribution. Also tells us the number of lines
		// test.output will have, as well as the number of sampling steps we do.
		int sum = 0;
		// Use this to keep the system from sampling a value we already grabbed
		// in the last iteration.
		int prev = 0;
		int curr;

		for(Integer item : distribution)
			sum += distribution.count(item);

		BufferedWriter outWriter = null;

		try {
			outWriter = new BufferedWriter(new FileWriter("test.output"));

			for(int i = 0; i < sum; i++) {
				curr = distribution.getUniqueSampleWithoutReplacement(prev);
				outWriter.write(curr + "\n");

				if(curr == 20)
					System.out.println((i + 1) + ": " + 20);

				prev = curr;
			}
			
		} catch(IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if(outWriter != null)
				outWriter.close();
		}

		verifyGeneratedDataset();
	}

	private static FrequencyTable distribution() {
		FrequencyTable result = new FrequencyTable();

		result.insertItem(20, 5);
		result.insertItem(19, 10);
		result.insertItem(18, 25);
		result.insertItem(17, 50);
		result.insertItem(16, 100);
		result.insertItem(15, 250);
		result.insertItem(14, 500);
		result.insertItem(13, 1000);
		result.insertItem(12, 83000);
		result.insertItem(11, 83000);
		result.insertItem(10, 83000);
		result.insertItem(9, 83000);
		result.insertItem(8, 83000);
		result.insertItem(7, 83000);
		result.insertItem(6, 83000);
		result.insertItem(5, 83000);
		result.insertItem(4, 83000);
		result.insertItem(3, 83000);
		result.insertItem(2, 83000);
		result.insertItem(1, 83000);

		return result;
	}

	// A quick and dirty way of verifying the resulting dataset.
	// We want to do two things: 1) ensure that in our file, no two
	// consecutive lines are the same and 2) the test.output's frequency
	// is the same as the specified distribution.
	private static void verifyGeneratedDataset() {
		FrequencyTable testDist = emptyTestTable();
		FrequencyTable mainDist = distribution();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("test.output"));
			// The value from the last line. Set it to -1 so that we make sure the
			// value is not something we could have possibly read in.
			int prev = -1;
			int curr;

			String strInput;
			while((strInput = reader.readLine()) != null) {
				curr = Integer.parseInt(strInput);

				// Oops! Our sampling system had two consecutive values in the generated dataset.
				if(curr == prev)
					throw(new Exception("TWO CONSECUTIVE NUMBERS IN test.output."));

				testDist.incrementCount(curr);
				prev = curr;
			}

			// Oops! Somehow the generated dataset's distribtuion doesn't match the one we should have.
			if(!testDist.equals(mainDist))
				throw(new Exception("DISTRIBUTION OF test.output IS INCORRECT"));

			System.out.println("System passes verification tests!");

		} catch(Exception e) {
			// I figured it'd be more clean to use exception handling for the cases of failed
			// tests. It reads more like a proper unit test which, if I had the time, I'd have added.
			System.err.println("ERROR: " + e);
		}
	}

	private static FrequencyTable emptyTestTable() {
		FrequencyTable result = new FrequencyTable();

		for(int i = 1; i <= 20; i++)
			result.insertItem(i, 0);

		return result;
	}
}
