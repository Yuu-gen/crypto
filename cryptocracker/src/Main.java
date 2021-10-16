import java.math.BigInteger;

import rsa.Coder;
import rsa.Crypter;
import rsa.KeyPair;
//import rsa.PrimeAnalysis;

public class Main {
	private static Coder coder = new Coder();
	private static Crypter crypter = new Crypter();
	public static void main(String[] args) throws Exception {
// Generate new Key Pair 		
		KeyPair keyPair = new KeyPair(100, true);
// Simulate encryption and decryption of a plain Text		
		String plainText = "Das Leben ist schön";
		System.out.println("Klartext = " + plainText);
		
		BigInteger encoded = coder.encode(plainText);
		System.out.println("Encoded: " + encoded);
		
		BigInteger encrypted = crypter.encrypt(encoded, keyPair);
		System.out.println("Encrypted: " + encrypted);
// ============== Cipher as text ================== 		
		String chiffreText = coder.decode(encrypted);
		System.out.println("Cipher text = " + chiffreText);
// ================================================ 
		BigInteger decrypted = crypter.decrypt(encrypted, keyPair);
		System.out.println("Decrypted: " + decrypted);
		
		String decoded = coder.decode(decrypted);
		System.out.println("Decoded: " + decoded);
	}
}
