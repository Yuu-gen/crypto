import java.math.BigInteger;
import java.util.Random;

import rsa.Coder;
import rsa.Crypter;
import rsa.KeyPair;
//import rsa.PrimeAnalysis;

public class Main {
	private static Coder coder = new Coder();
	private static Crypter crypter = new Crypter();
	public static void main(String[] args) throws Exception {
// Generate new Key Pair 		
//		KeyPair keyPair = new KeyPair(100, true);
//// Simulate encryption and decryption of a plain Text
//		String plainText = "Das Leben ist schön";
//		System.out.println("Klartext = " + plainText);
//
//		BigInteger encoded = coder.encode(plainText);
//		System.out.println("Encoded: " + encoded);
//
//		BigInteger encrypted = crypter.encrypt(encoded, keyPair);
//		System.out.println("Encrypted: " + encrypted);
//// ============== Cipher as text ==================
//		String chiffreText = coder.decode(encrypted);
//		System.out.println("Cipher text = " + chiffreText);
//// ================================================
//		BigInteger decrypted = crypter.decrypt(encrypted, keyPair);
//		System.out.println("Decrypted: " + decrypted);
//
//		String decoded = coder.decode(decrypted);
//		System.out.println("Decoded: " + decoded);
		calculatePQ( new BigInteger("12100889781898389029"));
	}

	public static void calculatePQ(BigInteger n){
		BigInteger product = n;
		BigInteger rootN = n.sqrt();
		BigInteger p;
		BigInteger q = BigInteger.TWO; //n =  12100889781898389029 ist 64-Bit ergo => 2 32-Bitzahlen
		BigInteger counter = BigInteger.ZERO;
		while (!n.mod(q).equals(BigInteger.ZERO)) {
			//System.out.println(q);
			counter.add(BigInteger.ONE);

			q = q.nextProbablePrime();
			if (counter.equals(new BigInteger("1000000000"))) {
				System.out.println(q);
				counter = BigInteger.ZERO;
			}
			if(q.compareTo(rootN)==1){
				System.out.println("nothing found");
				break;
			}

		}

		p = n.divide(q);
		System.out.println("q: " + q.toString());
		System.out.println("p: " + p.toString());
	}


}
