package cn.nj.storm.common.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class AESSecretUtil {
	
	private static String encryption="AES";
	private static String scheme="CBC";
	private static String complementWay="PKCS5Padding";

    private static String CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";
	
    public static String Encrypt(String content,String pwdKey,String iv) throws Exception {   
        byte[] raw = pwdKey.getBytes();   
        SecretKeySpec skeySpec = new SecretKeySpec(raw,encryption);   
        Cipher cipher = Cipher.getInstance(encryption+"/"+scheme+"/"+complementWay); 
        IvParameterSpec iv2 = new IvParameterSpec(iv.getBytes()); 
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv2);   
        byte[] encrypted = cipher.doFinal(content.getBytes()); 
        return Base64.encodeBase64String(encrypted);
    }   
   
    public static String Decrypt(String content,String pwdKey,String iv){   
        try {   
            byte[] raw = pwdKey.getBytes("ASCII");   
            SecretKeySpec skeySpec = new SecretKeySpec(raw, encryption);   
            Cipher cipher = Cipher.getInstance(encryption+"/"+scheme+"/"+complementWay);   
            IvParameterSpec iv2 = new IvParameterSpec(iv.getBytes());   
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv2);   
            byte[] encrypted1 = Base64.decodeBase64(content);
            byte[] original = cipher.doFinal(encrypted1);   
            String originalString = new String(original);   
            return originalString; 
        } catch (Exception ex) {   
            ex.printStackTrace();
            return null;   
        }   
    }


    /**
     * <pre>
     * 密钥生成器
     * </pre>
     *
     * @return 16进制密钥
     * @throws NoSuchAlgorithmException
     */
    public static String initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(encryption);
        kg.init(128);
        SecretKey secretKey = kg.generateKey();
        byte[] key = secretKey.getEncoded();
        return new String(Hex.encodeHexString(key));
    }

    /**
     * <pre>
     * 生成安全密钥，中间方法
     * </pre>
     *
     * @param encrypt_key
     *            initKey()生成的密钥
     * @return Key
     * @throws DecoderException
     */
    public static Key toKey(String encrypt_key) throws DecoderException {
        byte[] key_byte = Hex.decodeHex(encrypt_key.toCharArray());
        SecretKey secretKey = new SecretKeySpec(key_byte, encryption);
        return secretKey;
    }

    /**
     * <pre>
     * 加密方法
     * </pre>
     *
     * @param inputStr
     *            需要加密的串
     * @param key
     *            initKey()生成的密钥
     * @return 加密后的串（被封装成16进制,考虑便于页面传输）
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws DecoderException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String encrypt(String inputStr, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, DecoderException, IllegalBlockSizeException, BadPaddingException {
        byte[] inputStr_byte = inputStr.getBytes();
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, toKey(key));
        byte[] inStr = cipher.doFinal(inputStr_byte);
        return new String(Hex.encodeHex(inStr));
    }

    /**
     * <pre>
     * 解密方法
     * </pre>
     *
     * @param inputStr
     *            需要解密的串（必须是被封封装成16进制的）
     * @param key
     *            initKey()生成的密钥
     * @return 解密后的串
     * @throws DecoderException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String inputStr, String key) throws DecoderException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] inputStr_byte = Hex.decodeHex(inputStr.toCharArray());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, toKey(key));
        byte[] outStr = cipher.doFinal(inputStr_byte);
        return new String(outStr);
    }

    public static void main(String[] args) throws Exception {
//        String content = "{\"data\":[{\"userId\":\"70\"},{\"userId\":\"71\"},{\"userId\":\"72\"}]}";
//        String res = AESSecretUtil.Encrypt(content, "qckcmdbbv4u2hwxq", "0987654321abcdef");
//        System.out.println(res);
//        String res2 = AESSecretUtil.Decrypt(res, "qckcmdbbv4u2hwxq", "0987654321abcdef");
//		System.out.println(res2);
        
    }
}
