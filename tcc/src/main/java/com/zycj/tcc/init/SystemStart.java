package com.zycj.tcc.init;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zycj.mqtt.listener.DataListener;

public class SystemStart {
	private final static Logger log = Logger.getLogger(SystemStart.class); 
	public static void main(String[] args) {
		Options options = new Options();

		options.addOption("m", true, "start:表示启动服务端");
		options.addOption("h", false, "帮助");
		// 启动消息推送数据接收监听功能
		DataListener.init();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try{
			cmd = parser.parse(options, args);
			String mode = null;
			if (cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();  
				formatter.printHelp("安徽保安停车场经营服务有限公司路边停车场", options);
				return;
			}
			if (cmd.hasOption("m")) {  
	            mode = cmd.getOptionValue("m");  
	        }
			if(StringUtils.isBlank(mode)){
				HelpFormatter formatter = new HelpFormatter();  
				formatter.printHelp("安徽保安停车场经营服务有限公司路边停车场", options);
				return;
			}
			if (mode.equals("start")) {
				ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
						"applicationContext.xml");
			}else{
				return ;
			}
		}catch (Exception e) {
			log.warn("解析参数异常");
			e.printStackTrace();
		}  
	}
}
