package com.wl.common.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	 /* 必需的信息 */
    private static final String SMTP_MAIL_HOST = "mail.kongzhong.com"; // 此邮件服务器地址，自行去所属邮件查询
    private static final String EMAIL_USERNAME = "miaojing@kongzhong.com";
    private static final String EMAIL_PASSWORD = "DACQsw4q=";
    //String TO_EMAIL_ADDRESS_1 = "402776277@qq.com";
    
    
    public static void sendCheckMail(String to_email,String id,String nickname) throws MessagingException{
    	 /* 服务器信息 */
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_MAIL_HOST);
        props.put("mail.smtp.auth", "true");
        /* 创建Session */
        Session session = Session.getDefaultInstance(props, new SimpleAuthenticator(EMAIL_USERNAME, EMAIL_PASSWORD));

        /* 发件人 */
        Address[] senderArray = new Address[1];
        senderArray[0] = new InternetAddress(EMAIL_USERNAME);
        
        /* 邮件信息 */
        MimeMessage message = new MimeMessage(session);
        message.addFrom(senderArray);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
        message.setSubject("空中网邮件验证");
        
        /* 正文 */
        MimeBodyPart bodyPart = new MimeBodyPart();
        long time=new Date().getTime();
        String check_url=SystemConfigUtil.getValue("email.confirm.url")+"?id="+id+"&time="+time+"&email="+to_email;
        StringBuilder sb=new StringBuilder();
        sb.append(nickname+",您好：<p>");
        
        sb.append("您于 "+DateUtil.getCurrDateTimeStr()+" 提交了验证邮箱申请<p>");
        sb.append("点击一下链接，验证邮箱<p>");
        sb.append("<a href='check_url'>"+check_url+"</a>");
        sb.append("(如果您无法点击这个链接，请将此链接复制到浏览器地址栏后访问，此链接在48小时内有效，48小时后需重新提交)");
        bodyPart.setContent(sb.toString(), "text/html;charset=gb2312");
        
        /* 封装邮件各部分信息 */
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);

        // 发送
        Transport.send(message);
    }
    /**
     * 找回密码
     * @param to_email
     * @param code
     * @param nickname
     * @throws MessagingException
     */
    public static void sendFindPasswd(String to_email,String code,String nickname) throws MessagingException{
   	 /* 服务器信息 */
       Properties props = new Properties();
       props.put("mail.smtp.host", SMTP_MAIL_HOST);
       props.put("mail.smtp.auth", "true");
       /* 创建Session */
       Session session = Session.getDefaultInstance(props, new SimpleAuthenticator(EMAIL_USERNAME, EMAIL_PASSWORD));

       /* 发件人 */
       Address[] senderArray = new Address[1];
       senderArray[0] = new InternetAddress(EMAIL_USERNAME);
       
       /* 邮件信息 */
       MimeMessage message = new MimeMessage(session);
       message.addFrom(senderArray);
       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
       message.setSubject("空中网邮件验证");
       
       /* 正文 */
       MimeBodyPart bodyPart = new MimeBodyPart();
       StringBuilder sb=new StringBuilder();
       sb.append(nickname+",您好：<p>");
       
       sb.append("您于 "+DateUtil.getCurrDateTimeStr()+" 提交了找回密码邮箱验证申请<p>");
       sb.append("您的验证码为："+code+" ,请完成验证。<p>");
       sb.append("(此验证码只在本次找回密码操作期间有效)");
       bodyPart.setContent(sb.toString(), "text/html;charset=gb2312");
       
       /* 封装邮件各部分信息 */
       Multipart multipart = new MimeMultipart();
       multipart.addBodyPart(bodyPart);
       message.setContent(multipart);

       // 发送
       Transport.send(message);
   }
    private static class SimpleAuthenticator extends Authenticator{
    	private String username;
        private String password;
        
        public SimpleAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
    
}
