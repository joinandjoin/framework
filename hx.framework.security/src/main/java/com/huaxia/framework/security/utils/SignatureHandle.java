package com.huaxia.framework.security.utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;


/**
 * 鎶ユ枃绛惧悕澶勭悊
 * @author Administrator
 *
 */
public class SignatureHandle {
	
	/**
	 * MD5鍔犲瘑
	 * @param encryptString
	 * @return
	 */
	public static String encryptMD5(String encryptString) 
	{		
		java.security.MessageDigest digest;
		try {
			digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(encryptString.getBytes("utf-8"));
			return bytes2Hex(digest.digest());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 浜岃繘鍒惰浆16杩涘埗瀛楃涓�
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {

		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		des = des.toUpperCase(); // 转锟缴达拷写
		return des;
	}
	
	/**
	 * 鍒涘缓RSA绉橀挜瀵�
	 * 鏁扮粍0 绉侀挜
	 * 鏁扮粍1 鍏挜
	 * @return
	 */
	public static String[] generateRSAKeys()
	{
		String[] keys=new String[2];
		try
	    {
	      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");	      
	      keyPairGen.initialize(1024, new SecureRandom());
	      KeyPair keyPair = keyPairGen.generateKeyPair();
	      keys[0] = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
	      keys[1] = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
	    } catch (Exception e) {
	    	e.printStackTrace();	     
	    }
		return keys;
	}
	
	 /** 
	 * 鍏挜鍔犲瘑 
	 *  
	 * @param data 
	 * @param publicKey 
	 * @return 
	 * @throws Exception 
	 */  
    public static String encryptByPublicKey(String data, String publicKey)  
            throws Exception {  
    	
    	byte[] pubKeyArray=Base64.getDecoder().decode(publicKey);
    	X509EncodedKeySpec x509ek =new X509EncodedKeySpec(pubKeyArray);  
    	KeyFactory keyFactory = KeyFactory.getInstance("RSA"); 
        PublicKey  pubKey = keyFactory.generatePublic(x509ek);
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
        byte[] resultBytes = cipher.doFinal(data.getBytes("UTF-8"));  
        return Base64.getEncoder().encodeToString(resultBytes);
    } 
  
	/** 
	 * 绉侀挜瑙ｅ瘑 
	 *  
	 * @param data 
	 * @param privateKey 
	 * @return 
	 * @throws Exception 
	 */  
	public static String decryptByPrivateKey(String encryptString, String privateKey)  
	        throws Exception {  
		byte[] prvKeyArray=Base64.getDecoder().decode(privateKey);
		byte[] targetData=Base64.getDecoder().decode(encryptString);
		PKCS8EncodedKeySpec s8ek  = new PKCS8EncodedKeySpec(prvKeyArray);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA"); 
		PrivateKey  prvKey = keyFactory.generatePrivate(s8ek);	 
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, prvKey);
		byte[] resultBytes = cipher.doFinal(targetData);		
		return new String(resultBytes);  
	}  
		
	public static boolean VerifySignature(String content, String hashMac, String privKey) throws Exception {
		String calculationContent =SignatureHandle.encryptMD5(content);
		String decryptMac;
		decryptMac = decryptByPrivateKey(calculationContent, privKey);
		if(!calculationContent.equals(decryptMac)) {
    		return false;		
    	}
		return true;
	}
	
	public static String signature(String content,  String publicKey) throws Exception {
		return encryptByPublicKey(encryptMD5(content),publicKey);
	}
	
	public static void main(String[] args) throws Exception {
	 
//		String[] keys=generateRSAKeys();
//		System.out.println(keys[0]);
//		System.out.println(keys[1]);
//		keys[0] = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJoBYalcSlzSdq+7YgScfurVhywsZwd4pRtTV9pOhuLkAOK/on4ajWxb0JTH5FXXqIxR8xg8nMqrl1ZXcIHEjnZ/GfOG5vGksoKm85pbm3CuBL5Vvat06khh1+b+97F7vzO/AdxQlbzWv+vJVmUXSeeHRKtjuBixs4KtIzh3svCZAgMBAAECgYBLnhAI63Lr3r8+DCDVqM28mrmzW93+0iMnpqWFYHdkopU4wOWVS45TPUXON9QKvkhZFcfqB6Do9pXpZCeu4Aceqc3NfNfeASytRJZS/w57Azx7k1vqnl0ekdOjFCPw+VMxY2K+oApOl+nzpXmoMzG3Bi687qfjrWUO5HGR+7N4gQJBAPjr3nhmYibCz3I/xbrRtaCySsyJF2vQd8Rpu/KWye4Pfvx8Pvd8MdvRLtYSxhrj7Z9j1LdJLwVEdLZuDMIZhPECQQCeYofRSi2NLHPgxGnETC0rAAagXi2AutGeJxQdSrEcoyumcifIjITH9x5kzXfzETVn4E38rIBpf5Npx8JkbwYpAkEArcY8cWFIdDbcC65po5pzadQOU+wA5cuLURtfkmOiZQd8Z1d1veB+Di9iuyAwHOWyJevTvIDiypKXyCPb1EVsgQJBAJJ8JItP5MNN2rqGTiAMKDY95uMJV2JY5qg0Emlo/Fe1HhjqOZtFQ0VCa+zlD8xSHSe+j8JitL1eDbPm2gZTnMkCQAWqZylyhLNENRA2WxkYHwjYKHl7t2xRA0A0b7BRIRya977QwANuvJlcz/dciuSixtfCnvGJCfRQUYwIHiMrsrE=";
//		keys[1] = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaAWGpXEpc0navu2IEnH7q1YcsLGcHeKUbU1faTobi5ADiv6J+Go1sW9CUx+RV16iMUfMYPJzKq5dWV3CBxI52fxnzhubxpLKCpvOaW5twrgS+Vb2rdOpIYdfm/vexe78zvwHcUJW81r/ryVZlF0nnh0SrY7gYsbOCrSM4d7LwmQIDAQAB";
//		
//		String targetString="XXDAFASFSA1231231FASFASS";		
//		System.out.println(targetString);
//		String encryptString=encryptByPublicKey(targetString,keys[1]);
//		System.out.println(encryptString);
//		System.out.println(decryptByPrivateKey(encryptString,keys[0]));
		
	}
}
