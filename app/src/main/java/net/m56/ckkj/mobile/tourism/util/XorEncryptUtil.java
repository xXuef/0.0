package net.m56.ckkj.mobile.tourism.util;

/**
 * @version V1.0 < 异或运算中，如果某个字符（或数值）x 与 一个数值m 进行异或运
 * 算得到y，则再用y 与 m 进行异或运算就可以还原为 x ，因此应用这个原理可以实现数据的加密解密功能>.
 * @filename: net.m56.ckkj.mobile.tourism.util.XorEncryptUtil.java
 * @Author 兔兔  on 2017/09/25.
 * @Org 山西创客空间科技有限公司.
 * @Description: ${两个变量的互换（不借助第三个变量）or,数据的简单加密解密} .
 * @Motto 大梦谁先觉, 贫僧我自知..
 */
public class XorEncryptUtil  {

    public static byte[] encrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] ^= key;
        }
        return bytes;
    }

    public static byte[] dyanmic_encrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (bytes[i] ^ key);
            key = bytes[i];
        }
        return bytes;
    }
    public static byte[] dyanmic_decrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = len - 1; i > 0; i--) {
            bytes[i] = (byte) (bytes[i] ^ bytes[i - 1]);
        }
        bytes[0] = (byte) (bytes[0] ^ key);
        return bytes;
    }
    public  static void main(String args[]){
        byte[] bytes = encrypt("abcdefg".getBytes());//加密
        System.out.println("加密后:"+bytes.toString());
        String str1 = new String(encrypt(bytes));//解密
        System.out.println("解密后:"+str1);

        byte[] byte_day = dyanmic_encrypt("abcdefg".getBytes());//加密
        String str1_day = new String(dyanmic_decrypt(byte_day));//解密
        System.out.println("dyanmic加密后:"+byte_day.toString());
        System.out.println("dyanmic解密后:"+str1_day);
    }

}
