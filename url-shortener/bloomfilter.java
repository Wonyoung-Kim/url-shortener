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

	public int[] runThroughHashFunctions(String s) {
		int[] bitsToSet = new int[kHashFunctions];
		int sumOfNumeric = getSumOfNumericValues(s); 
		Random r = new Random(sumOfNumeric);
		for (int i = 0; i < kHashFunctions; i++) {
			int randNum = r.nextInt(bloomFilterSize);
			bitsToSet[i] = randNum;
		}
		return bitsToSet;
	}

	public boolean insert(String s) {
		int[] randomIndices = runThroughHashFunctions(s);
		for (int i = 0; i < randomIndices.length; i++) {
			bloomFilterSet.set(randomIndices[i], true);
		}
		return true;
	}
}
	
	
