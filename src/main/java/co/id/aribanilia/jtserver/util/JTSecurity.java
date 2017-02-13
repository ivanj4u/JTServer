package co.id.aribanilia.jtserver.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * Created by ivan_j4u on 2/13/2017.
 */
public class JTSecurity {

    private static Cipher cipherEncrypt;
    private static Cipher cipherDecrypt;

    private final static Logger logger = LoggerFactory.getLogger(JTSecurity.class);

    /**
     * Method for setting encrypt configuration
     */
    public static void install() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        /**
         * Store Cipher in memory
         */
        logger.info("Installing Cipher...");
        cipherEncrypt = install(Cipher.ENCRYPT_MODE);
        cipherDecrypt = install(Cipher.DECRYPT_MODE);
    }

    /**
     * Method for setting configuration
     */
    private static Cipher install(int mode) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        SecretKeySpec keyS = new SecretKeySpec(decode(JTConstants.SECURITY.KEY), JTConstants.SECURITY.AES);
        IvParameterSpec ivS = new IvParameterSpec(decode(JTConstants.SECURITY.IV));
        Cipher cipher = createCipher(JTConstants.SECURITY.AES_CBC_PKCS5PAD, mode, keyS, ivS);
        return cipher;
    }

    /**
     * Method todo Encrypt
     */
    public static byte[] encrypt(String text) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidParameterSpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] encrypt = cipherEncrypt.doFinal(encode(text));
        return encrypt;
    }

    /**
     * Method todo EncryptString
     */
    public static String encryptToString(String text) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidParameterSpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] b = encrypt(text);
        return new String(encode(b));
    }

    /**
     * Method todo Decrypt
     */
    public static byte[] decrypt(String text) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidParameterSpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] decrypt = cipherDecrypt.doFinal(decode(text));
        return decrypt;
    }

    /**
     * Method todo DecryptString
     */
    public static String decryptToString(String text) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidParameterSpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] decrypt = decrypt(text);
        return new String(decode(decrypt));
    }


    /**
     * Simple keyGenerator only using Algorithm
     * Key = 128 bit
     */
    private static SecretKey getSecretKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
        keygen.init(128);
        return keygen.generateKey();
    }

    private static Cipher createCipher(String algorithmCipher, int mode, SecretKey key, IvParameterSpec iv) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(algorithmCipher);
        cipher.init(mode, key, iv);
        return cipher;
    }

    public static byte[] encode(byte[] b) {
        return Base64.encodeBase64(b);
    }
    public static byte[] encode(String s) {
        return Base64.encodeBase64(s.getBytes());
    }
    public static byte[] decode(String s) {
        return Base64.decodeBase64(s);
    }
    public static byte[] decode(byte[] b) {
        return Base64.decodeBase64(b);
    }
}
