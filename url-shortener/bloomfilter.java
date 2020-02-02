import java.util.BitSet;
import java.util.Random;

public class BloomFilter {
	int bloomFilterSize;
	int kHashFunctions;
	BitSet bloomFilterSet; 

	public BloomFilter(int size, int k) {
		bloomFilterSize = size;
		kHashFunctions = k;
		bloomFilterSet = new BitSet(bloomFilterSize);
	}

	int getSumOfNumericValues(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += Character.getNumericValue(s.charAt(i));
		}
		return sum;
	}
}