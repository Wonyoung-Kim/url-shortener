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
		// return a shortened URL to the user for the long URL, and save it
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
}
