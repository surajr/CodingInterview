public class Codec {

    private Map<Integer, String> map = new HashMap<>();
    private String host = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int hashKey = longUrl.hashCode();
        map.put(hashKey, longUrl);
        return host+hashKey;
        
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        
        int hashKey = Integer.parseInt(shortUrl.replace(host,""));
        return map.get(hashKey);
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
