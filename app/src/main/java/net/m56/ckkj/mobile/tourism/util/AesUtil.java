package net.m56.ckkj.mobile.tourism.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.m56.ckkj.mobile.tourism.entity.DataCache;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * @version V1.0 <描述当前版本功能>.
 * @filename: net.m56.ckkj.mobile.tourism.util.AesUtil.java
 * @Author 兔兔  on 2017/09/25.
 * @Org 山西创客空间科技有限公司.
 * @Description: ${TODO} .
 * @Motto 大梦谁先觉, 贫僧我自知..
 */
public class AesUtil {
    private final static String HEX = "0123456789ABCDEF";
    private  static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private  static final String AES = "AES";//AES 加密
    private  static final String  SHA1PRNG="SHA1PRNG";//// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法

    /*
    * 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
    */
    public static String generateKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes_key = new byte[20];
            localSecureRandom.nextBytes(bytes_key);
            String str_key = toHex(bytes_key);
            return str_key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 对密钥进行处理
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        //for android
        SecureRandom sr = null;
        // 在4.2以上版本中，SecureRandom获取方式发生了改变
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
        } else {
            sr = SecureRandom.getInstance(SHA1PRNG);
        }
        // for Java
        // secureRandom = SecureRandom.getInstance(SHA1PRNG);
        sr.setSeed(seed);
        kgen.init(128, sr); //256 bits or 128 bits,192bits
        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    /*
         * 加密
         */
    public static String encrypt(String key, String cleartext) {
//        if (TextUtils.isEmpty(cleartext)) {
//            return cleartext;
//        }
        try {
            byte[] result = encrypt(key, cleartext.getBytes());

            return new BASE64Encoder().encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 加密
    */
    private static byte[] encrypt(String key, byte[] clear) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;

    }

    /*
        * 解密
        */
    public static String decrypt(String key, String encrypted) {
//        if (TextUtils.isEmpty(encrypted)) {
//            return encrypted;
//        }
        try {
            byte[] enc = new BASE64Decoder().decodeBuffer(encrypted);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 解密
     */
    private static byte[] decrypt(String key, byte[] encrypted) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }
    //二进制转字符
    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    public  static  void main(String args []){
        List<DataCache> personList = new ArrayList<>();
        int testMaxCount = 1000;//测试的最大数据条数
        //添加测试数据
        for (int i = 0; i < testMaxCount; i++) {
            DataCache person = new DataCache();
            person.set_id(i);
            person.setUsername(String.valueOf(i));
            personList.add(person);
        }
        //FastJson生成json数据
        Gson  gson  = new GsonBuilder().create();
        String jsonData  =  gson.toJson(personList);
        System.out.println("AES加密前json数据 ---->" + jsonData);
        System.out.println("AES加密前json数据长度 ---->" + jsonData.length());
//        Log.e("MainActivity", "AES加密前json数据 ---->" + jsonData);
//        Log.e("MainActivity", "AES加密前json数据长度 ---->" + jsonData.length());

        //生成一个动态key
        String secretKey = generateKey();
//        Log.e("MainActivity", "AES动态secretKey ---->" + secretKey);
        System.out.println("AES动态secretKey ---->" + secretKey);
        //AES加密
        long start = System.currentTimeMillis();
        String encryStr = encrypt(secretKey, jsonData);
        long end = System.currentTimeMillis();
        System.out.println( "AES加密耗时 cost time---->" + (end - start));
        System.out.println("AES加密后json数据 ---->" + encryStr);
        System.out.println("AES加密后json数据长度 ---->" + encryStr.length());
//        Log.e("MainActivity", "AES加密耗时 cost time---->" + (end - start));
//        Log.e("MainActivity", "AES加密后json数据 ---->" + encryStr);
//        Log.e("MainActivity", "AES加密后json数据长度 ---->" + encryStr.length());

        //AES解密
        start = System.currentTimeMillis();
        String decryStr = decrypt(secretKey, encryStr);
        end = System.currentTimeMillis();
        System.out.println( "AES解密耗时 cost time---->" + (end - start));
        System.out.println( "AES解密后json数据 ---->" + decryStr);
//        Log.e("MainActivity", "AES解密耗时 cost time---->" + (end - start));
//        Log.e("MainActivity", "AES解密后json数据 ---->" + decryStr);
    }
}
