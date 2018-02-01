package com.javens.security;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CrytoUtils {
	
	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  seed
	 * @return 
	 */  
	public static String encrypt(String content, String seed) {  
	        try {             
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                kgen.init(128, new SecureRandom(seed.getBytes()));  
	                SecretKey secretKey = kgen.generateKey();  
	                byte[] enCodeFormat = secretKey.getEncoded();  
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
	                Cipher cipher = Cipher.getInstance("AES");
	                byte[] byteContent = content.getBytes("utf-8");  
	                cipher.init(Cipher.ENCRYPT_MODE, key);
	                byte[] byteResult = cipher.doFinal(byteContent);
	                String result=java.util.Base64.getEncoder().encodeToString(byteResult);
	                return result; // 加密  
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
	}
	
	 /**
     * 解密AES加密过的字符串
     * 
     * @param content
     *            AES加密过过的内容
     * @param seed
     *            加密时的密码
     * @return 明文
     */
    public static String decrypt(String content, String seed) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(seed.getBytes()));
            SecretKey secretKey = kgen.generateKey();// seed相同，生成一个密钥就相同
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] str=java.util.Base64.getDecoder().decode(content.getBytes());
            byte[] byteResult = cipher.doFinal(str);  
            try {
				return new String(byteResult,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*public static void main(String[] args) {
        String content = "18953219501";
        String seed = "123";
        System.err.println("加密之前：" + content);

        // 加密
        String encrypt = CrytoUtils.encrypt(content, seed);
        System.out.println("加密后的内容：" + encrypt);
        
        // 解密
        String plain = CrytoUtils.decrypt(encrypt, seed);
        System.out.println("解密后的内容：" + plain);        
    }*/
}
