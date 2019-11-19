/*
	Okay so the idea for writing all of the results is
	as follows:

	For each iteration:
		We choose an item such that:
			1. We did not choose it last iteraton
			2. We do not exceed the count of that item
*/

package datasetgenerator;

class DatasetGenerator {
	public static void main(String[] args) {
		// The distribtuion of values we want our dataset to look like
		FrequencyTable distribution = distribution();
		// Keeps track of the frequency of values we counted. Use this
		// to help us choose the next value from our distribution
		FrequencyTable currentCount = new FrequencyTable();
		// The total number of items from our distribution.
		int sum = sumOfItems(distribution);
		// Use this to choose the highest freqency item that is
		// not a previously chosen value
		int prev = 0;

		// Verify that the total number of items is 99740
		System.out.println("Total: " + sum);
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

	private static int sumOfItems(FrequencyTable distribution) {
		int sum = 0;

		for(Integer item : distribution) {
			sum += distribution.count(item);
		}

		return sum;
	}
}
