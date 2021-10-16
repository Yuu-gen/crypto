package rsa;

import java.math.BigInteger;

public class Crypter {
	public BigInteger encrypt(BigInteger plainInt, KeyPair keyPair) {
		return plainInt.modPow(keyPair.getE(), keyPair.getN()); // m^e mod n
	}
	public BigInteger decrypt(BigInteger chiffreInt, KeyPair keyPair) {
		return chiffreInt.modPow(keyPair.getD(), keyPair.getN()); // c^d mod n
	}

	public BigInteger decrypt(BigInteger chiffreInt, BigInteger d, BigInteger n) {
		return chiffreInt.modPow(d, n); // c^d mod n
	}
}
