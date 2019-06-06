/**
 * 
 */
package com.zycj.tcc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 资讯信息相关的配置
 * Title: PathConfig.java
 * Description: 城泊二期
 * Company: zycj
 * @author wangy
 * @date 2015年4月9日
 */
public class NewsConfig {
	/**
	 * 分隔符
	 */
	public static final char SEPARATE_CHAR = '/';
	/**
	 * html 文件后缀名
	 */
	public static final String HTML_FILE_SUFFIX=".html";
	/**
	 * 测试时根路径
	 */
	public static final String ROOT_PATH;
	/**
	 * 测试时,图片保存路径
	 */
	public static final String IMAGE_PATH;
	/**
	 * 测试时,静态保存路径
	 */
	public static final String HTML_PATH;
	/**
	 * 测试时，css路径
	 */
	public static final String CSS_PATH;
	/**
	 * 模板html相对路径
	 */
	public static final String HTML_MODEL_PATH;
	/**
	 * 模板html文件名
	 */
	public static final String HTML_MODEL_FILE_NAME;
	/**
	 * html静态页面模板
	 */
	public static String HTML_MODEL_STR;
	
	/**
	 * html页面 html title
	 */
	public static final String HTML_TITLE;
	/**
	 * html页面 html title 名
	 */
	public static final String HTML_TITLE_NAME;
	
	/**
	 * html静态页面 ，资讯标题
	 */
	public static final String NEWS_TITLE;
	/**
	 * 资讯发布时间
	 */
	public static final String NEWS_UP_TIME;
	
	/**
	 * 资讯来源
	 */
	public static final String NEWS_FROM;
	/**
	 * 资讯内容
	 */
	public static final String NEWS_CONTENT;
	/**
	 * 图片样式  字符串
	 */
	public static final String IMAGE_CLASS;
	/**
	 * 标题样式  字符串
	 */
	public static final String H_CLASS;
	/**
	 * 模板html访问路径
	 */
	public static final String HTML_SRC;
	static {
		Locale locale = new Locale("zh", "CN");
		ResourceBundle bundle = ResourceBundle.getBundle("newsConfig", locale);
		ROOT_PATH = bundle.getString("root_path");
		IMAGE_PATH = bundle.getString("image_path");
		HTML_PATH = bundle.getString("html_path");
		CSS_PATH = bundle.getString("css_path");
		HTML_MODEL_PATH =  bundle.getString("html_model_path");
		HTML_TITLE = bundle.getString("html_title");
		HTML_TITLE_NAME = bundle.getString("html_title_name");
		NEWS_TITLE = bundle.getString("news_title");
		NEWS_UP_TIME = bundle.getString("news_up_time");
		NEWS_FROM = bundle.getString("news_from");
		NEWS_CONTENT = bundle.getString("news_content");
		HTML_MODEL_FILE_NAME = bundle.getString("html_model_file_name");
		IMAGE_CLASS = bundle.getString("image_class");
		H_CLASS = bundle.getString("h_class");
		HTML_SRC = bundle.getString("html_src");
        File htmlFile=new File(ROOT_PATH+HTML_MODEL_PATH+SEPARATE_CHAR+HTML_MODEL_FILE_NAME);
        if(!htmlFile.exists())
        { 
        	HTML_MODEL_STR="";
        }
        StringBuffer htmlModel = new StringBuffer();
        try 
        {	
        	String str;
            BufferedReader in = new BufferedReader(new FileReader(htmlFile));
            while ((str = in.readLine()) != null) 
            {
                  htmlModel.append(str);
            }
            in.close();
            HTML_MODEL_STR = htmlModel.toString();
        } 
        catch (Exception e) 
        {
        	HTML_MODEL_STR="";
        }
	}
}
