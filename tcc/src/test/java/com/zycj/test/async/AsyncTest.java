package com.zycj.test.async;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class AsyncTest {
	private static ThreadLocal<DateFormat> tl1 = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> tl2 = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> tl3 = new ThreadLocal<DateFormat>();
	public static DateFormat getDateFormatYMD() {
		DateFormat df = tl1.get();
		System.out.println("YMD:"+Thread.currentThread());
		if (df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			tl1.set(df);
		}
		return df;
	}
	public static DateFormat getDateFormatYMDHMS() {
		DateFormat df = tl2.get();
		System.out.println("YMDHMS:"+Thread.currentThread());
		if (df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tl2.set(df);
		}
		return df;
	}
	public static DateFormat getDateFormatHMS() {
		DateFormat df = tl3.get();
		System.out.println("HMS:"+Thread.currentThread());
		if (df == null) {
			df = new SimpleDateFormat("HH:mm:ss");
			tl3.set(df);
		}
		return df;
	}
	public static Date parse_HMS(Date d) throws ParseException{
		return getDateFormatHMS().parse((getDateFormatHMS().format(d)));
	}
	public static String test(Date d) throws ParseException{
		return getDateFormatYMDHMS().format(getDateFormatHMS().parse((getDateFormatHMS().format(d))));
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException, TimeoutException, ParseException {
		// ExecutorService pool=Executors.newFixedThreadPool(2);
		// FutureTask<String> future =
		// new FutureTask<String>(new Callable<String>() {
		// public String call() throws InterruptedException {
		// Thread.sleep(2000);
		// return "aaaaa";
		// }});
		// pool.execute(future);
		// System.out.println(future.get(3,TimeUnit.SECONDS));
		// pool.shutdownNow();
	}
}
