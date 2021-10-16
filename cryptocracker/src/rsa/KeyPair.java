package rsa;
import java.math.BigInteger;
import java.util.Random;

public class KeyPair {
	private static final BigInteger one = new BigInteger("1");
	private static final BigInteger two = new BigInteger("2");
	private BigInteger n; 
	private BigInteger e; // Öffentl. Schlüssel (n,e)
	private boolean traceOn; // Wenn true werden bei Instanzierung die Bestandteile des Schlüsselpaars auf der Konsole ausgegeben
	private BigInteger d; // Privater Schlüssel
	public KeyPair(int bitlength, boolean traceOn) {
		this.traceOn = traceOn;
		BigInteger p = BigInteger.probablePrime(bitlength, new Random());
		BigInteger q = BigInteger.probablePrime(bitlength, new Random());
		n = p.multiply(q);
		BigInteger pM1qM1 = p.subtract(one).multiply(q.subtract(one));
		e = this.publicKeyCorrect(pM1qM1);   
		// e = this.publicKey(pM1qM1);   // (*)
		d = e.modInverse(pM1qM1);
		if(traceOn) {
			System.out.println("p = " + p);
			System.out.println("q = " + q);
			System.out.println("n = " + n);
			System.out.println("e = " + e);		
			System.out.println("Ergibt d = " + d);
		}
		
	};
	public BigInteger getN() {
		return this.n;
	}
	public BigInteger getE() {
		return this.e;
	}
	public BigInteger getD() {
		return this.d;
	}	
	
/**
 * Determination of e 
 */
	private BigInteger publicKeyCorrect(BigInteger pM1qM1) {
// Start at (p-1)(q-1)/k with k a random number between 2 and 10 
		Integer k = Double.valueOf(2 + Math.random()*10).intValue();
		if(traceOn) System.out.println("Benutze " + k + " für Location von e");
		BigInteger e = pM1qM1.divide(new BigInteger(k.toString()));
		e = e.add(two); // If one just takes e + 1, we might have a problem, if k = 2, see the method below
		int counter = 0;
		while(true) {
			e = e.add(one);
			counter++;
			if(e.gcd(pM1qM1).equals(one)) {
				if(traceOn) System.out.println("Benötigte " + counter + " Versuche zum Finden von e");
				return e;
			}
		}
	}
	
/** A simpler way of finding e */	
	private BigInteger publicKey(BigInteger pM1qM1) {
		BigInteger e = pM1qM1.divide(two); // This has always common divisors with (p-1)(q-1), 
											// e.g. n = 15: (p-1)(q-1) = 2*4 = 8, 8/2 = 4 has common divisor with 8
		
		// But adding 1 avoids common divisors, e.g. 5 and 8 have no common divisors: 
		return e.add(one);
		// In general for any number x, which is a multiple of 4: x/2 + 1 and x have no common divisors!
		// Try out this choice of e instead of choosing e according to the following method, see (*) above
	}

}
