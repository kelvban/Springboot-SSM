package com.example.demo.util;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class RsaUtil {
    public static RSAPrivateKey LoadKey(){
        RSAPrivateKey rsaPrivateKey=null;
        InputStream inputStream=null;
        BufferedReader bufferedReader=null;
        BASE64Decoder base64Decoder=new BASE64Decoder();
        StringBuffer stringBuffer=new StringBuffer();
        String privateKey="rsa_private_key.pem";
        String read="";
        try {
            inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(privateKey);
            bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            while ((read=bufferedReader.readLine())!=null){
                if(read.charAt(0)=='-'){
                    continue;
                }
                stringBuffer.append(read+"\r");
            }
            byte [] keybyte=base64Decoder.decodeBuffer(stringBuffer.toString());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec= new PKCS8EncodedKeySpec(keybyte);
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            rsaPrivateKey=(RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rsaPrivateKey;
    }

    public static String decrypt(PrivateKey privateKey,String data){
        BASE64Decoder base64Decoder=new BASE64Decoder();
        byte [] result=null;
        try{
            byte [] bytes=base64Decoder.decodeBuffer(data);
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            result=cipher.doFinal(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new String(result);
    }
}
