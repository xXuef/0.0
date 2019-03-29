package net.m56.ckkj.mobile.tourism.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by yue on 2017/08/10.
 */

public class RandomUtil {
    /**
     * 生成随机汉子
     * @return
     */
    public static char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }
}
