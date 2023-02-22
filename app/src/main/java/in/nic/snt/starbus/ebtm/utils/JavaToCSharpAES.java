package in.nic.snt.starbus.ebtm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class JavaToCSharpAES {


    public  static  String  key = "";



    public String Decryptt(String text, String key) throws Exception{


        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);

        // BASE64Decoder decoder = new BASE64Decoder();
        byte [] results ;//= cipher.doFinal(decoder.decodeBuffer(text));
        results = cipher.doFinal(Base64.decode(text, Base64.DEFAULT));
        return new String(results,"UTF-8");


    }


    public String Encryptt() throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= "8ffc7e56311679f12b6fc91aa77a5eb".getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

        byte[] results = cipher.doFinal("9999999999".getBytes("UTF-8"));

        return Base64.encodeToString(results, Base64.DEFAULT);


    }

}

