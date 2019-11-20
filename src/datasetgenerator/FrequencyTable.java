package datasetgenerator;

import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;

class FrequencyTable implements Iterable<Integer> {
	private Hashtable<Integer, Integer> frequencies;

	public FrequencyTable() {
		// Keys are the item, the value is its count
		this.frequencies = new Hashtable<Integer, Integer>();
	}

	public boolean equals(FrequencyTable compare) {
		if(this.frequencies.keySet().size() != compare.frequencies.keySet().size())
			return false;

		boolean result = true;

		for(Integer item : this) {
			if(this.count(item) != compare.count(item)) {
				result = false;
				break;
			}
		}

		return result;
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
		int result;

		for(Integer item : this) {
			if(this.frequencies.get(item) > 0)
				sampleFrom.add(item);
		}

		result = chooseNonRepeatByFrequency(prev, sampleFrom);
		decrementCount(result);

		return result;
	}

	private int chooseNonRepeatByFrequency(int prev, ArrayList<Integer> candidates) {
		if(candidates.size() < 1)
			return -1;

		int best = candidates.get(0);
		int freq = this.count(best);

		for(int i = 0; i < candidates.size(); i++) {
			if(candidates.get(i) != prev && this.count(candidates.get(i)) > freq) {
				best = candidates.get(i);
				freq = this.count(best);
			}
		}

		return best;
	}

	private void decrementCount(int item) {
		if(this.frequencies.containsKey(item))
			this.frequencies.put(item, this.frequencies.get(item) - 1);
	}

	public void incrementCount(int item) {
		if(this.frequencies.containsKey(item))
			this.frequencies.put(item, this.frequencies.get(item) + 1);
	}

	public Iterator<Integer> iterator() {
		Set<Integer> set = this.frequencies.keySet();

		return set.iterator();
	}
}
