package encripcion;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public class AES {

    public static SecretKeySpec getKeySpec() throws IOException, NoSuchAlgorithmException {
        byte[] bytes = new byte[16];
        File f = new File("sample_aes_key");
        SecretKey key = null;
        SecretKeySpec spec = null;
        if (f.exists()) {
            new FileInputStream(f).read(bytes);
        } else {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256);
            key = kgen.generateKey();
            bytes = key.getEncoded();
            new FileOutputStream(f).write(bytes);
        }
        spec = new SecretKeySpec(bytes,"AES");
        return spec;
    }
    public static void encrypt(String text) throws Exception {
        SecretKeySpec spec = getKeySpec();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, spec);
        BASE64Encoder enc = new BASE64Encoder();
        System.out.println(enc.encode(cipher.doFinal(text.getBytes())));
    }
    public static void decrypt(String text) throws Exception {
        SecretKeySpec spec = getKeySpec();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, spec);
        BASE64Decoder dec = new BASE64Decoder();
        System.out.println(new String(cipher.doFinal(dec.decodeBuffer(text))));
    }

}