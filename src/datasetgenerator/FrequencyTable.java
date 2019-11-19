package datasetgenerator;

class FrequencyTable {
	private int[] frequencies;

	public FrequencyTable(int count) {
		if(count <= 0)
			count = 1;

		this.frequencies = new int[count];
	}

	public int count() { return this.frequencies.length; }

	public int getFrequency(int index) {
		if(index < 0 || index >= this.frequencies.length)
			return -1;

		return this.frequencies[index];
	}

	public void setFrequency(int index, int val) {
		if(index < 0 || index >= this.frequencies.length)
			return;

		this.frequencies[index] = val;
	}
}
