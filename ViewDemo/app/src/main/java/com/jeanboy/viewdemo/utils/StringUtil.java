package com.jeanboy.viewdemo.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by yule on 2016/1/4.
 */
public class StringUtil {


    public static String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
