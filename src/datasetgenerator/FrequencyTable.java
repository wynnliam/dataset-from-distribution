package datasetgenerator;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

class FrequencyTable implements Iterable<Integer> {
	private Hashtable<Integer, Integer> frequencies;

	public FrequencyTable() {
		// Keys are the item, the value is its count
		this.frequencies = new Hashtable<Integer, Integer>();
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

	public void incrementCount(int item) {
		if(this.frequencies.containsKey(item))
			this.frequencies.put(item, this.frequencies.get(item) + 1);
	}

	public Iterator<Integer> iterator() {
		Set<Integer> set = this.frequencies.keySet();

		return set.iterator();
	}
}
