package datasetgenerator;

import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class FrequencyTable implements Iterable<Integer> {
	private Hashtable<Integer, Integer> frequencies;
	private Random random;

	public FrequencyTable() {
		// Keys are the item, the value is its count
		this.frequencies = new Hashtable<Integer, Integer>();

		this.random = new Random();
	}

	public void insertItem(int item, int count) {
		this.frequencies.computeIfAbsent(item, k -> count);
	}

	public int count(int item) {
		if(this.frequencies.containsKey(item))
			return this.frequencies.get(item);
		else
			return -1;
	}

	public int getUniqueSampleWithoutReplacement(int prev) {
		ArrayList<Integer> sampleFrom = new ArrayList<Integer>();
		int randIndex;
		int result;

		for(Integer item : this) {
			if(this.frequencies.get(item) > 0)
				sampleFrom.add(item);
		}

		do {
			randIndex = this.random.nextInt(sampleFrom.size());
			result = sampleFrom.get(randIndex);
		} while(result == prev);

		decrementCount(result);

		return result;
	}

	private void decrementCount(int item) {
		if(this.frequencies.containsKey(item))
			this.frequencies.put(item, this.frequencies.get(item) - 1);
	}

	public Iterator<Integer> iterator() {
		Set<Integer> set = this.frequencies.keySet();

		return set.iterator();
	}
}
