package datasetgenerator;

import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;

class FrequencyTable implements Iterable<Integer> {
	// Why did I pick a Hashtable? It allows me to keep track of
	// both items, their counts, and enforces uniqueness of keys.
	// That way, I don't have to focus too much on more trivial things
	// (like making sure there are no repeats in this table.
	// It also allows me to implment an Iterable so I can have
	// the fancy for-each loops that Java offers. This makes
	// DatasetGenerator read more nicely in my opinion.
	private Hashtable<Integer, Integer> frequencies;

	public FrequencyTable() {
		// Keys are the item, the value is its count
		this.frequencies = new Hashtable<Integer, Integer>();
	}

	// We check for equality by making sure the size of the set of
	// keys is equal and that the count corresponding to each key is equal.
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
		// If item is not in the frequencies,
		// create an KV pair where they key is item
		// and the value is the result of computing a trivial
		// lambda expression that results in count.
		this.frequencies.computeIfAbsent(item, k -> count);
	}

	public int count(int item) {
		if(this.frequencies.containsKey(item))
			return this.frequencies.get(item);
		else
			return -1;
	}

	public int getUniqueSampleWithoutReplacement(int prev) {
		// Originally I experimented with a random number generator,
		// which neccesitated the ArrayList. When this didn't work,
		// I checked for highest frequency. sampleFrom is a list
		// of keys that actually have a value greater than 0 corresponding
		// to it in frequencies.
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
			// If current candidate item isn't prev,
			// and its frequency is higher than our current highest frequency,
			// make that our best candidate.
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
