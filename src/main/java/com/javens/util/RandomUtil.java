package com.javens.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
	public static String getRandom(int c) {
		Random r = new Random();
		SimpleDateFormat format = new SimpleDateFormat("ssSSS");
		Date d = new Date();
		StringBuffer sb = new StringBuffer();
		// for(int i = 0; i < c; i ++){
		sb.append(format.format(d));
		sb.append(r.nextInt(10));
		// }
		//// System.out.println(sb.toString());
		// return sb.toString().substring(1,7);
		return sb.toString();
	}
    /**
     * java中如何获取某个范围内的随机数
     *
     * @param a
     * @param b
     * @return 返回返回a<=返回值<b 或者a>返回值>=b
     */
    public static int generateRandom(int a, int b) {
        int temp = 0;
        try {
            if (a > b) {
                temp = new Random().nextInt(a - b);
                return temp + b;
            } else {
                temp = new Random().nextInt(b - a);
                return temp + a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp + a;
    }

	public static String getSMSCODE() {
		int x = (int) (Math.random() * 9000 + 1000);
		return String.valueOf(x);
	}

	public static void main(String args[]) {
		System.out.println(RandomUtil.getRandom(6));
		// System.out.println(RandomUtil.genMobile());
	}

	public static String genMobile() {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		SimpleDateFormat format = new SimpleDateFormat("DDssSSS");
		Date d = new Date();
		sb.append("1");
		sb.append("3");
		sb.append(r.nextInt(10));
		sb.append(format.format(d));
		return sb.toString();
	}

    public static String generateUUID() {
        return StringUtils.replace(java.util.UUID.randomUUID().toString(), "-", "").toUpperCase();
    }

	public static double nextDouble(double min, double max) {
		if (max < min) {
			double temp = max;
			max = min;
			min = temp;
		}
		if (min == max) {
			return min;
		}
		return min + ((max - min) * new Random().nextDouble());
	}
}