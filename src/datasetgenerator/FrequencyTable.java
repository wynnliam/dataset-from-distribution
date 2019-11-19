package datasetgenerator;

import java.util.Map;
import java.util.Hashtable;
import java.util.Iterator;

class FrequencyTable {
	Map frequencies;

	public FrequencyTable() {
		// Keys are the item, the value is its count
		this.frequencies = new Hashtable<Integer, Integer>();
	}
}
