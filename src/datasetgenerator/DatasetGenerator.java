package datasetgenerator;

class DatasetGenerator {
	public static void main(String[] args) {
		FrequencyTable distribution = constructDistrubtion();

		for(int i = 0; i < distribution.count(); i++) {
			System.out.println(i + ": " + distribution.getFrequency(i));
		}
	}

	private static FrequencyTable constructDistrubtion() {
		FrequencyTable result = new FrequencyTable(21);

		result.setFrequency(20, 5);
		result.setFrequency(19, 10);
		result.setFrequency(18, 25);
		result.setFrequency(17, 50);
		result.setFrequency(16, 100);
		result.setFrequency(15, 250);
		result.setFrequency(14, 500);
		result.setFrequency(13, 1000);
		result.setFrequency(12, 83000);
		result.setFrequency(11, 83000);
		result.setFrequency(10, 83000);
		result.setFrequency(9, 83000);
		result.setFrequency(8, 83000);
		result.setFrequency(7, 83000);
		result.setFrequency(6, 83000);
		result.setFrequency(5, 83000);
		result.setFrequency(4, 83000);
		result.setFrequency(3, 83000);
		result.setFrequency(2, 83000);
		result.setFrequency(1, 83000);

		return result;
	}
}
