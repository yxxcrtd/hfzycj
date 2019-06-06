package com.zycj.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class Test {
     
     
    public static void main(String[] args) throws Exception {
    	String str="{\"data\":{\"info\":[{\"carNo\":\"浙A11111\",\"inTime\":\"2014-10-30 10:10:03\",\"orderId\":67496,\"spaceNo\":\"220001\",\"spaceStatus\":1,\"statusCode\":3},{\"carNo\":\"浙A22222\",\"inTime\":\"2014-10-30 10:10:15\",\"orderId\":67497,\"spaceNo\":\"220002\",\"spaceStatus\":1,\"statusCode\":2},{\"carNo\":\"浙A33333\",\"inTime\":\"2014-10-30 10:10:26\",\"orderId\":67498,\"spaceNo\":\"220003\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A44444\",\"inTime\":\"2014-10-30 10:10:40\",\"orderId\":67499,\"spaceNo\":\"220004\",\"spaceStatus\":1,\"statusCode\":2},{\"carNo\":\"浙A55555\",\"inTime\":\"2014-10-30 10:10:51\",\"orderId\":67500,\"spaceNo\":\"220005\",\"spaceStatus\":1,\"statusCode\":2},{\"carNo\":\"浙A66666\",\"inTime\":\"2014-10-30 10:11:03\",\"orderId\":67501,\"spaceNo\":\"220006\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A77777\",\"inTime\":\"2014-10-30 10:11:16\",\"orderId\":67502,\"spaceNo\":\"220007\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A88888\",\"inTime\":\"2014-10-30 10:11:38\",\"orderId\":67503,\"spaceNo\":\"220008\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A99999\",\"inTime\":\"2014-10-30 10:11:53\",\"orderId\":67504,\"spaceNo\":\"220009\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A10101\",\"inTime\":\"2014-10-30 10:12:16\",\"orderId\":67505,\"spaceNo\":\"220010\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙B11111\",\"inTime\":\"2014-10-30 10:12:38\",\"orderId\":67506,\"spaceNo\":\"220011\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A12121\",\"inTime\":\"2014-10-30 10:20:59\",\"orderId\":67522,\"spaceNo\":\"220012\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A13131\",\"inTime\":\"2014-10-30 10:21:09\",\"orderId\":67523,\"spaceNo\":\"220013\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A14141\",\"inTime\":\"2014-10-30 10:21:20\",\"orderId\":67524,\"spaceNo\":\"220014\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A15151\",\"inTime\":\"2014-10-30 10:21:34\",\"orderId\":67525,\"spaceNo\":\"220015\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A16161\",\"inTime\":\"2014-10-30 10:23:11\",\"orderId\":67526,\"spaceNo\":\"220016\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A17171\",\"inTime\":\"2014-10-30 10:23:21\",\"orderId\":67527,\"spaceNo\":\"220017\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A18181\",\"inTime\":\"2014-10-30 10:23:34\",\"orderId\":67528,\"spaceNo\":\"220018\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A19191\",\"inTime\":\"2014-10-30 10:27:44\",\"orderId\":67529,\"spaceNo\":\"220019\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A20202\",\"inTime\":\"2014-10-30 10:27:55\",\"orderId\":67530,\"spaceNo\":\"220020\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A21212\",\"inTime\":\"2014-10-30 11:24:06\",\"orderId\":67535,\"spaceNo\":\"220021\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙B22222\",\"inTime\":\"2014-10-30 11:24:17\",\"orderId\":67536,\"spaceNo\":\"220022\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A23232\",\"inTime\":\"2014-10-30 11:24:29\",\"orderId\":67537,\"spaceNo\":\"220023\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A24242\",\"inTime\":\"2014-10-30 11:24:39\",\"orderId\":67538,\"spaceNo\":\"220024\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A25252\",\"inTime\":\"2014-10-30 11:24:48\",\"orderId\":67539,\"spaceNo\":\"220025\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A26262\",\"inTime\":\"2014-10-30 11:24:58\",\"orderId\":67540,\"spaceNo\":\"220026\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A27272\",\"inTime\":\"2014-10-30 11:25:14\",\"orderId\":67541,\"spaceNo\":\"220027\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A28282\",\"inTime\":\"2014-10-30 11:25:22\",\"orderId\":67542,\"spaceNo\":\"220028\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A29292\",\"inTime\":\"2014-10-30 11:25:33\",\"orderId\":67543,\"spaceNo\":\"220029\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A30303\",\"inTime\":\"2014-10-30 11:25:45\",\"orderId\":67544,\"spaceNo\":\"220030\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A31313\",\"inTime\":\"2014-10-30 11:25:55\",\"orderId\":67545,\"spaceNo\":\"220031\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A32323\",\"inTime\":\"2014-10-30 11:26:04\",\"orderId\":67546,\"spaceNo\":\"220032\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙B33333\",\"inTime\":\"2014-10-30 11:26:30\",\"orderId\":67547,\"spaceNo\":\"220033\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A34343\",\"inTime\":\"2014-10-30 11:26:48\",\"orderId\":67548,\"spaceNo\":\"220034\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A35353\",\"inTime\":\"2014-10-30 11:27:34\",\"orderId\":67549,\"spaceNo\":\"220035\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A36363\",\"inTime\":\"2014-10-30 11:27:44\",\"orderId\":67550,\"spaceNo\":\"220036\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A37373\",\"inTime\":\"2014-10-30 11:27:59\",\"orderId\":67551,\"spaceNo\":\"220037\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A38383\",\"inTime\":\"2014-10-30 11:28:11\",\"orderId\":67552,\"spaceNo\":\"220038\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A39393\",\"inTime\":\"2014-10-30 11:28:22\",\"orderId\":67553,\"spaceNo\":\"220039\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A40404\",\"inTime\":\"2014-10-30 11:28:35\",\"orderId\":67554,\"spaceNo\":\"220040\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A41414\",\"inTime\":\"2014-10-30 11:28:45\",\"orderId\":67555,\"spaceNo\":\"220041\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A42424\",\"inTime\":\"2014-10-30 11:28:54\",\"orderId\":67556,\"spaceNo\":\"220042\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A43434\",\"inTime\":\"2014-10-30 11:29:48\",\"orderId\":67557,\"spaceNo\":\"220043\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙B44444\",\"inTime\":\"2014-10-30 11:29:59\",\"orderId\":67558,\"spaceNo\":\"220044\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A45454\",\"inTime\":\"2014-10-30 11:30:10\",\"orderId\":67559,\"spaceNo\":\"220045\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A46464\",\"inTime\":\"2014-10-30 11:30:18\",\"orderId\":67560,\"spaceNo\":\"220046\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A47474\",\"inTime\":\"2014-10-30 11:30:26\",\"orderId\":67561,\"spaceNo\":\"220047\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A48484\",\"inTime\":\"2014-10-30 11:31:27\",\"orderId\":67562,\"spaceNo\":\"220048\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A49494\",\"inTime\":\"2014-10-30 11:31:48\",\"orderId\":67563,\"spaceNo\":\"220049\",\"spaceStatus\":1,\"statusCode\":1},{\"carNo\":\"浙A50505\",\"inTime\":\"2014-10-30 11:37:08\",\"orderId\":67564,\"spaceNo\":\"220050\",\"spaceStatus\":1,\"statusCode\":1}],\"pageSize\":6},\"resultCode\":\"8888\"}";
     // 字符串超过一定的长度  
        System.out.println("\n原始的字符串为------->" + str);  
        double len0=str.length();  
        System.out.println("原始的字符串长度为------->"+len0);  
  
        String ys = compress(str);  
        System.out.println("\n压缩后的字符串为----->" + ys);  
        double len1=ys.length();  
        System.out.println("压缩后的字符串长度为----->" + len1);  
  
        String jy = unCompress(ys);  
        System.out.println("\n解压缩后的字符串为--->" + jy);  
        System.out.println("解压缩后的字符串长度为--->"+jy.length());  
          
        System.out.println("\n压缩比例为"+len1/len0);  
          
        //判断  
        if(str.equals(jy)){  
            System.out.println("先压缩再解压以后字符串和原来的是一模一样的");  
        }  
        
    }
    
    /**  
     * 字符串的压缩  
     * @param str 待压缩的字符串  
     * @return 返回压缩后的字符串  
     * @throws IOException  
     */  
    public static String compress(String str) throws IOException {  
        if (null == str || str.length() <= 0) {  
            return str;  
        }  
        // 创建一个新的 byte 数组输出流  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        // 使用默认缓冲区大小创建新的输出流  
        GZIPOutputStream gzip = new GZIPOutputStream(out);  
        // 将 b.length 个字节写入此输出流  
        gzip.write(str.getBytes("UTF-8"));  
        gzip.close();  
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串  
        return out.toString("ISO-8859-1");  
    }  
      
    /**  
     * 字符串的解压  
     * @param str 对字符串解压  
     * @return 返回解压缩后的字符串  
     * @throws IOException  
     */  
    public static String unCompress(String str) throws IOException {  
        if (null == str || str.length() <= 0) {  
            return str;  
        }  
        // 创建一个新的 byte 数组输出流  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组  
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));  
        // 使用默认缓冲区大小创建新的输入流  
        GZIPInputStream gzip = new GZIPInputStream(in);  
        byte[] buffer = new byte[256];  
        int n = 0;  
        while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组  
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流  
            out.write(buffer, 0, n);  
        }  
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串  
        return out.toString("UTF-8");  
    }  
}
