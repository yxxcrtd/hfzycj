package com.tcc.park.api.util;

import java.util.Date;
import java.util.Random;

public class RandomUtil {
	public static String genRandomFixedLenth(int len) {
		StringBuffer llString = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			llString.append(random(0, 9));
		}
		return llString.toString();
	}
	
	public static String getSrialNoByDate(){
		StringBuffer num = new StringBuffer(20);
		num.append(DateUtil.getDateStringByFormat(new Date(), "yyyyMMddHHmmss"));
		num.append(RandomUtil.genRandomFixedLenth(6));
		return num.toString();
	}

	public static int random(int low, int heigh) {
		return low + (int) ((heigh - low) * new Random().nextDouble());
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.err.println(genRandomFixedLenth(6));
		}
	}
}
