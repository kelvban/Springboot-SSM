package com.example.demo.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author luosen
 * @date 8/14/2019 4:43 PM
 */
public class CryptionUtil {
    public static Key DEFAULT_KEY = null;
    //1qaz2wsx3edc$RFV%TGB^YHN&UJM
    private static final String DEFAULT_SECRET_KEY = "1qaz2wsx3edc$RFV%TGB^YHN&UJM";
    //不能写成DES,如果写成DES加密时会自动填充成64位，在解密时没有去除尾部就会发生问题。
    private static final String DES_Content = "DES/ECB/NoPadding";
    private static final String DES_Key= "DES";
    static {
        DEFAULT_KEY = obtainKey(DEFAULT_SECRET_KEY);
    }

    /**
     * 获得key
     **/
    private static Key obtainKey(String key) {
        if (key == null) {
            return DEFAULT_KEY;
        }
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(DES_Key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.init(new SecureRandom(key.getBytes()));
        Key key1 = generator.generateKey();
        generator = null;
        return key1;
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    public static String encode(String str) {
        return encode(null, str);
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    public static String encode(String key, String str) {
        //将str补齐8字节的整数倍
        byte[] bytes=formatData(str);
        return Base64.encodeBase64URLSafeString(obtainEncode(key,bytes));
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    public static String decode(String str) {
        return decode(null, str);
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    public static String decode(String key, String str) {
        //通过DES算法解密得到正确的bytes[]
        byte[] bytes=obtainDecode(key, Base64.decodeBase64(str));
        //将bytes[]末尾补齐的空白去掉返回。
        return formatByte(bytes);
    }

    /**
     * 加密<br>
     * 以byte[]明文输入,byte[]密文输出
     */
    private static byte[] obtainEncode(String key, byte[] str) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES_Content);
            cipher.init(Cipher.ENCRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 解密<br>
     * 以byte[]密文输入,以byte[]明文输出
     */
    private static byte[] obtainDecode(String key, byte[] str) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES_Content);
            cipher.init(Cipher.DECRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    //将str转为8的整数倍的byte[]
    public static byte[] formatData(String str){
        try{
            byte[] data=str.getBytes("UTF-8");
            int size=8-(data.length%8);
            byte[] result=new byte[data.length+size];
            for(int i=0;i<data.length;i++){
                result[i]=data[i];
            }
            return  result;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //将byte[]去掉空行转为Str
    public static String formatByte(byte[] bytes){
        int i=0;
        for(;i<bytes.length;i++){
            if(bytes[i]==0){
                break;
            }
        }
        byte[] result=new byte[i];
        for(int j=0;j<i;j++){
            result[j]=bytes[j];
        }
        try{
            return  new String(result,"UTF-8");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }


    public static void main(String[] args) {
        //String 中一个英文字符占一个字节，中文根据编码不同所占字节数不同。UFT-8占3个字节，GBK占两个字节。
//        String a = "admin1";
//        //encode的str必须是8字节的整数倍，key没做要求
//        String m = encode(a);
//        System.out.println(m);
        String n = decode("7DoWKFohKNUqtfzOgZp4rg");
        System.out.println(n);
//        String str="abcdefg";
//        byte[] bytes=formatData(str);
//        String result=formatByte(bytes);
//        System.out.println(result);
    }
}
