package com.wl.common.utils;

import java.math.BigDecimal;

public class NumberUtil {
	/** 
     * double 相加 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public static final double sum(Double d1,Double d2){ 
    	if(d1==null){
    		d1=0.0;
    	}
    	if(d2==null){
    		d2=0.0;
    	}
        BigDecimal bd1 = new BigDecimal(d1.toString()); 
        BigDecimal bd2 = new BigDecimal(d2.toString()); 
        return bd1.add(bd2).doubleValue(); 
    } 
    /** 
     * double 相减 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public static final double sub(Double d1,Double d2){ 
    	if(d1==null){
    		d1=0.0;
    	}
    	if(d2==null){
    		d2=0.0;
    	}
        BigDecimal bd1 = new BigDecimal(d1.toString()); 
        BigDecimal bd2 = new BigDecimal(d2.toString());  
        return bd1.subtract(bd2).doubleValue(); 
    } 
    /** 
     * double 乘法 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public static final double mul(Double d1,Double d2){ 
    	if(d1==null){
    		d1=0.0;
    	}
    	if(d2==null){
    		d2=0.0;
    	}
        BigDecimal bd1 = new BigDecimal(d1.toString()); 
        BigDecimal bd2 = new BigDecimal(d2.toString());  
        return bd1.multiply(bd2).doubleValue(); 
    } 
    /** 
     * double 除法 
     * @param d1 
     * @param d2 
     * @param scale 四舍五入 小数点位数 
     * @return 
     */ 
    public static final double div(Double d1,Double d2,int scale){ 
        //  当然在此之前，你要判断分母是否为0，   
        //  为0你可以根据实际需求做相应的处理 
    	if(d1==null){
    		d1=0.0;
    	}
    	if(d2==null){
    		d2=0.0;
    	}
        BigDecimal bd1 = new BigDecimal(d1.toString()); 
        BigDecimal bd2 = new BigDecimal(d2.toString());  
        return bd1.divide (bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    } 
    /**
     * 转换指定位数的小数点
     * @param val
     * @param precision
     * @return
     */
    public static Double round(Double val, int precision) {
    	if(val==null){
    		val=0.0;
    	}
        BigDecimal bd = new BigDecimal(val).setScale(precision, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    /**
     * 把double 类型转换为小数点后两位
     * @param val
     * @return
     */
    public static Double round(Double val) {
    	if(val==null){
    		val=0.0;
    	}
        BigDecimal bd = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
