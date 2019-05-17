
/**
 * For testing purposes
 * Download json-simple-1.1.1.jar
 * Do the following: export CLASSPATH=.:$CLASSPATH:./json-simple-1.1.1.jar
 * Compile: javac Jsonsize.java
 * argv[0] is url encoded string
 * Run: java Jsonsize %5B%7B%22number%22%3A447442542524%2C%22first_name%22%3A%22Customer1%22%2C%22last_name%22%3A%22Customer1%22%2C%22customer_id%22%3A%223454646454%22%2C%22order_id%22%3A%2254576575633%22%7D%2C%7B%22number%22%3A447442542524%2C%22first_name%22%3A%22Customer2%22%2C%22last_name%22%3A%22Customer2%22%2C%22customer_id%22%3A%223454646454%22%2C%22order_id%22%3A%2254576575633%22%7D%5D
 * Checking Utf8 characters encoding
 */
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Jsonsize {
  public static void main(String[] argv) throws Exception {

		String contacts = URLDecoder.decode(argv[0], "UTF-8");

		System.out.println(contacts);

		JSONParser parser = new JSONParser();
		JSONArray array = (JSONArray)parser.parse(contacts);
		System.out.println(array.size());
    }
}
