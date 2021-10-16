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
		BigInteger p = new BigInteger("3051471971");
		BigInteger q = new BigInteger("3965590999");
		calcD(p,q);

		BigInteger d = new BigInteger("864349269634380433");
		BigInteger n = new BigInteger("12100889781898389029");


		BigInteger c1 = new BigInteger("8936241241181094235");
		BigInteger c2 = new BigInteger("6931651972065967907");
		BigInteger c3 = new BigInteger("272334545465344201");
		BigInteger c4 = new BigInteger("10594345573964628437");
		BigInteger c5 = new BigInteger("6466887967539712414");

		System.out.println("---------Decryption------------");
		BigInteger decrypted = crypter.decrypt(c1, d, n);
		String decoded1 =  coder.decode(decrypted);


		decrypted = crypter.decrypt(c2, d, n);
		String decoded2 =  coder.decode(decrypted);


		decrypted = crypter.decrypt(c3, d, n);
		String decoded3 =  coder.decode(decrypted);


		decrypted = crypter.decrypt(c4, d, n);
		String decoded4 =  coder.decode(decrypted);


		decrypted = crypter.decrypt(c5, d, n);
		String decoded5 =  coder.decode(decrypted);

		System.out.println("Plaintext: " + decoded1 + " " + decoded2 + " " + decoded3 + " " + decoded4 + decoded5);

		//Wissen macht uns verantwortlich - Che Guevara
	}

	public static void calculatePQ(BigInteger n) {
		BigInteger product = n;
		BigInteger rootN = n.sqrt();
		BigInteger p = BigInteger.ZERO;
		BigInteger q = rootN.subtract(BigInteger.ONE); //n =  12100889781898389029 ist 64-Bit ergo => 2 32-Bitzahlen
		BigInteger counter = BigInteger.ZERO;


		while (!n.mod(q).equals(BigInteger.ZERO)) {
			//System.out.println(q);
			counter = counter.add(BigInteger.ONE);
			//System.out.println(counter);

			q = q.subtract(BigInteger.valueOf(2));
//			if (counter.equals(new BigInteger("10000"))) {
//				System.out.println(q);
//				counter = BigInteger.ZERO;
//			}
			if (q.compareTo(rootN) == 1) {
				System.out.println("nothing found");
				break;
			}


		}
		p = n.divide(q);

		System.out.println("PQ für n:"+ n +" wurden gefunden:");
		System.out.println("p: " + p);
		System.out.println("q: " + q);

	}
	public static void calcD(BigInteger q, BigInteger p){
		BigInteger one = BigInteger.ONE;
		BigInteger pM1qM1 = p.subtract(one).multiply(q.subtract(one));
		BigInteger e = new BigInteger("6050444887440663037");
		BigInteger d = e.modInverse(pM1qM1);
		System.out.println("D wurde berechnet: ");
		System.out.println("d: " + d);
	}

}