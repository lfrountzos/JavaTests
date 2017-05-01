package com.myproject.elliptic;
// The following code is from http://www.academicpub.org/PaperInfo.aspx?PaperID=14496 .
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.*;

public class ECCKeyGeneration {
  public static void main(String[] args) throws Exception {
    KeyPairGenerator kpg;
    kpg = KeyPairGenerator.getInstance("EC","SunEC");
    ECGenParameterSpec ecsp;
    ecsp = new ECGenParameterSpec("secp192r1");
    kpg.initialize(ecsp);

    KeyPair kp = kpg.genKeyPair();
    PrivateKey privKey = kp.getPrivate();
    PublicKey pubKey = kp.getPublic();

    System.out.println(privKey.toString());
    System.out.println(pubKey.toString());
    byte[] key = pubKey.getEncoded();
    FileOutputStream keyfos = new FileOutputStream("suepk");
    keyfos.write(key);
    keyfos.close();
  }
  private static PublicKey readPublicKey(){
		// read key bytes
		FileInputStream in;
		PublicKey publicKey = null;
		try {
			in = new FileInputStream("suepk");
			byte[] keyBytes = new byte[in.available()];
			in.read(keyBytes);
			in.close();
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("ECDH");
			publicKey = keyFactory.generatePublic(spec);
//			String pubKey = new String(keyBytes, "UTF-8");
//			pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//
//		// don't use this for real projects!
//		BASE64Decoder decoder = new BASE64Decoder();
//		keyBytes = decoder.decodeBuffer(pubKey);

		// generate public key
		
		return publicKey;
  }
}