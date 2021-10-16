package rsa;

import java.math.BigInteger;
public class Coder {
	public BigInteger encode(String s){
		return new BigInteger(s.getBytes());
	}
	public String decode(BigInteger b) {
		return new String(b.toByteArray());
	}
}
