package com.tcc.park.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


@Service
public class IpTask {
	private long period = 1800;
	
//	@PostConstruct
	public void startSinglePool() {
		ScheduledExecutorService executorService = Executors
				.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new IpCheck(), 10, period, TimeUnit.SECONDS);// 0多久后开始执行；5执行间隔时间；时间单位
	}

	
	class IpCheck implements Runnable {
		String oldIp = "";
		String[] mobiles = {"15256586585","15556991861","15105609556","18955186464","18516029990"};
		public void run() {
			try {
				String ip = getIp();
				System.err.println("ip="+ip+",old="+oldIp);
				if (!oldIp.equals(ip) ) {
					if (!oldIp.equals("")) {
//						Send.sendSms(mobiles,ip);
					}
					oldIp = ip;
				}
			} catch (Exception e) {
				System.err.println();
			}
		}
	}
	
	public String getIp() throws IOException {
		URL url = new URL("http://www.ip138.com/ips138.asp");
		URLConnection conn = url.openConnection();
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.15) Gecko/20110303 Firefox/3.6.15");
		conn.setRequestProperty("Content-Type", "text/html");
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		InputStream is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"GB2312"));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.contains("您的IP地址是")) {
				int start = line.indexOf('[') + 1;
				int end = line.indexOf(']');
				System.out.println(line.substring(start, end));
				return line.substring(start, end);
			}
		}
		br.close();
		return "";
	}

}
