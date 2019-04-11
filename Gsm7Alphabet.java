   /**
    * This program is useful if you are sending sms message via your sms message provider
    * checks if input text can be sent as plain text as sms message or needs to be encoded to utf-8
    * Gsm 7-bit (Gsm 03.38) is default character set which all GSM networks support
    * @link https://www.etsi.org/deliver/etsi_gts/03/0338/05.00.00_60/gsmts_0338v050000p.pdf
    *
    * Compile: javac Gsm7Alphabet.java
    * Run: java Gsm7Alphabet string-to-check
    */
public class Gsm7Alphabet {

    /**
     * GSM 7-bit Default Alphabet
     */
    private static final char[] BASIC_CHARS = {
            // Basic Character Set
            '@', '£', '$', '¥', 'è', 'é', 'ù', 'ì', 'ò', 'Ç', '\n', 'Ø', 'ø', '\r', 'Å', 'å',
            'Δ', '_', 'Φ', 'Γ', 'Λ', 'Ω', 'Π', 'Ψ', 'Σ', 'Θ', 'Ξ', 'Æ', 'æ', 'ß', 'É',
            ' ', '!', '"', '#', '¤', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?',
            '¡', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ö', 'Ñ', 'Ü', '§',
            '¿', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ö', 'ñ', 'ü', 'à',
            // Basic Character Set Extension
            '\f', '^', '{', '}', '\\', '[', '~', ']', '|', '€'
    };

    /**
     * Detects any non GSM 7-bit alphabet character
     *
     * @param str String
     * @return boolean
     */
    public static boolean isGsm7Encodable(String str) {
        char[] javaChars = str.toCharArray();
        for (char c : javaChars) {
            if (Gsm7Alphabet.isGsm7Encodable(c) == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * Matches input character to GSM 7-bit Default Alphabet
     *
     * @param javaChar chat
     * @return boolean
     */
    public static boolean isGsm7Encodable(char javaChar) {
        for (char basicChar : BASIC_CHARS) {
            if (basicChar == javaChar) {
                return true;
            }
        }
        return false;
    }

    /**
     * Main method
     */
    public static void main(String[] argv) throws Exception {

	String str;
	if (argv.length > 0) {
		str = String.join(" ", argv);

		if (Gsm7Alphabet.isGsm7Encodable(str) == false) {
		// we do unicoding the string and print out
      		byte[] arr = str.getBytes("UTF-8");
      		System.out.println("String is unicode UTF-8");
      		for(byte a: arr) {
         	System.out.print(a);
		System.out.print(" ");
      		}
		System.out.println();
	    }
		else {
      		System.out.println("String is GSM 7-bit");
		System.out.println(str);
	    }

	}
   }
}
