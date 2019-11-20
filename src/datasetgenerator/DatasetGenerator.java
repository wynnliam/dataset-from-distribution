package datasetgenerator;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class DatasetGenerator {
	public static void main(String[] args) throws IOException {
		FrequencyTable distribution = distribution();
		int sum = 0;
		int prev = 0;
		int curr;

		for(Integer item : distribution)
			sum += distribution.count(item);

		BufferedWriter outWriter = null;

		try {
			FileWriter fw = new FileWriter("test.output");
			outWriter = new BufferedWriter(fw);

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

			int prev, curr;

			prev = -1;

			String strInput;
			while((strInput = reader.readLine()) != null) {
				curr = Integer.parseInt(strInput);

				if(curr == prev) {
					System.out.println("ERROR: TWO CONSECUTIVE NUMBERS!");
					return;
				}

				testDist.incrementCount(curr);
				prev = curr;
			}

			for(Integer item : testDist) {
				if(testDist.count(item) != mainDist.count(item)) {
					System.out.println("ERROR: RESULTING DIST IS INCORRECT");
					return;
				}
			}

			System.out.println("System passes verification tests!");

		} catch(IOException e) {
			System.err.println("Failed to open test.output");
		}
	}

	private static FrequencyTable emptyTestTable() {
		FrequencyTable result = new FrequencyTable();

		for(int i = 1; i <= 20; i++)
			result.insertItem(i, 0);

		return result;
	}
}
