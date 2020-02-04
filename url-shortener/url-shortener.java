import java.util.HashMap;
import java.util.Random;

public class URLShortener {
	int sizeOfUrl;
	HashMap<String, String> map;
	BloomFilter bf;

	public URLShortener(int sizeOfUrl) {
		this.sizeOfUrl = sizeOfUrl;
		map = new HashMap<String, String>();
		bf = new BloomFilter(1000, 4);

	}

	public String createShortenedURL(String longUrl, String forceShort) { 
		if (map.containsValue(longUrl)) {
			for (String str : map.keySet()) {
				if (map.get(str).equals(longUrl)) {
					return str;
				}
			}
		}

		String shortUrl = "";
		boolean foundValidShortURL = false;
		while (!foundValidShortURL) {
			// 1) generate candidae URL
			shortUrl = generateCandidateURL(longUrl);
			if (forceShort != null) {
				shortUrl = forceShort;
			}
			// check in bloom filter and possibly map to see if its valid
			if (!bf.query(shortUrl)) {
				// were sure that the short URL is valid
				foundValidShortURL = true;
			} else {
				// according to the bloom filter, shortUrl exists. but we might have false
				// positives
				if (map.containsKey(shortUrl)) {
					foundValidShortURL = false;
				} else {
					sysout foundValidShortURL = true;
				}
			}
		}
		bf.insert(shortUrl);
		storeURL(shortUrl, longUrl);
		return shortUrl;
	}
	private void storeURL(String shortUrl, String longUrl) {
		map.put(shortUrl, longUrl);
	}

	public String shortenedUrlExists(String longUrl) {
		if (map.containsValue(longUrl)) {
			for (String str : map.keySet()) {
				if (map.get(str).equals(longUrl)) {
					return str;
				}
			}
		}
		return null;
	}

	public String getLongUrl(String shortUrl) {
		if (map.containsKey(shortUrl)) {
			return map.get(shortUrl);
		}
		return null;
	}

	private String generateCandidateURL(String longUrl) {
		String candidateOfAll = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
		String candidateUrl = "https://bit.ly/";
		int numForLongUrl = bf.getSumOfNumericValues(longUrl);
		Random rand = new Random(numForLongUrl);
		for (int i = 0; i < sizeOfUrl; i++) {
			candidateUrl += Character.toString(candidateOfAll.charAt(rand.nextInt(candidateOfAll.length())));
		}
		return candidateUrl;
	}
}

