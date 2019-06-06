package com.zycj.tcc.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
/**
* 生成验证码图片
* Title: IdentifyingCodez.java
* Description: 城泊二期
* Company: zycj
* @author wangy
* @date 2015年3月26日
 */
public class IdentifyingCode {
	private String identifyingCodeStr;
	
	public void createIdentifyingCode(OutputStream o) throws Exception{
		int width=78, height=18;  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
          
        Graphics g = image.getGraphics();  
        Random random = new Random();  
        g.setColor(getRandColor(200,250));  
        g.fillRect(0, 0, width-1, height-1);  
        g.setColor(new Color(102,102,102));  
        g.drawRect(0, 0, width-1, height-1);  
        g.setFont(mFont);  
  
        g.setColor(getRandColor(160,200));  
  
        //鐢婚殢鏈虹嚎  
        for (int i=0;i<155;i++)  
        {  
            int x = random.nextInt(width - 1);  
            int y = random.nextInt(height - 1);  
            int xl = random.nextInt(6) + 1;  
            int yl = random.nextInt(12) + 1;  
            g.drawLine(x,y,x + xl,y + yl);  
        }  
  
        //浠庡彟涓�柟鍚戠敾闅忔満绾� 
        for (int i = 0;i < 70;i++)  
        {  
            int x = random.nextInt(width - 1);  
            int y = random.nextInt(height - 1);  
            int xl = random.nextInt(12) + 1;  
            int yl = random.nextInt(6) + 1;  
            g.drawLine(x,y,x - xl,y - yl);  
        }  
  
        //鐢熸垚闅忔満鏁�骞跺皢闅忔満鏁板瓧杞崲涓哄瓧姣� 
        String sRand="";  
        for (int i=0;i<4;i++)  
        {  
            int itmp = random.nextInt(26) + 65;  
            char ctmp = (char)itmp;  
            sRand += String.valueOf(ctmp);  
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));  
            g.drawString(String.valueOf(ctmp),15*i+10,16);  
        }
        this.identifyingCodeStr = sRand;
        g.dispose();  
        ImageIO.write(image, "JPEG", o);
	}
	
	private Font mFont = new Font("Times New Roman", Font.PLAIN, 17);  
    Color getRandColor(int fc,int bc)  
    {  
        Random random = new Random();  
        if(fc>255) fc=255;  
        if(bc>255) bc=255;  
        int r=fc+random.nextInt(bc-fc);  
        int g=fc+random.nextInt(bc-fc);  
        int b=fc+random.nextInt(bc-fc);  
        return new Color(r,g,b);  
    }
    
	public String getIdentifyingCodeStr() {
		return identifyingCodeStr;
	}

	public void setIdentifyingCodeStr(String identifyingCodeStr) {
		this.identifyingCodeStr = identifyingCodeStr;
	}
	
}
