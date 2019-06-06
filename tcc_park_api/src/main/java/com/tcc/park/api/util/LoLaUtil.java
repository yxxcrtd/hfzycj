package com.tcc.park.api.util;

import java.math.BigDecimal;

public class LoLaUtil {

	/** 
	    * @param raidus 单位米 
	    * return minLat,maxLat,minLng,maxLng 
	    */  
	   public static double[] getAround(Double lat,Double lon,Integer raidus){  
	         
	       Double latitude = lat;  
	       Double longitude = lon;  
	         
	       Double degree = (24901*1609)/360.0;  
	       double raidusMile = raidus;  
	         
	       Double dpmLat = 1/degree;  
	       Double radiusLat = dpmLat*raidusMile;  
	       Double minLat = latitude - radiusLat;  
	       Double maxLat = latitude + radiusLat;  
	         
	       Double mpdLng = degree*Math.cos(latitude * (Math.PI/180));  
	       Double dpmLng = 1 / mpdLng;  
	       Double radiusLng = dpmLng*raidusMile;  
	       Double minLng = longitude - radiusLng;  
	       Double maxLng = longitude + radiusLng;  
//	       System.out.println("["+minLat+","+maxLat+","+minLng+","+maxLng+"]");  
	       return new double[]{minLat,maxLat,minLng,maxLng};  
	   }  
	
	public static void main(String[] args) {
		System.err.println(getDistatce(31.867126, 31.853386, 117.282052, 117.279321));
		System.err.println(getDistance(117.282052, 31.853386, 117.279321, 31.867126));
//		System.err.println(getAround(31.853632, 117.292401, 500));
		
	}
	
	public static  Double getDistatce(Double lat1, Double lat2, Double lon1,Double lon2) { 
     double R = 6371; 
     double distance = 0.0; 
     double dLat = (lat2 - lat1) * Math.PI / 180; 
     double dLon = (lon2 - lon1) * Math.PI / 180; 
     double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) 
             + Math.cos(lat1 * Math.PI / 180) 
             * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) 
             * Math.sin(dLon / 2); 
     distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R; 
     return distance*1000.00; 
 }
	
	
	/** 
	    * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米 
	    * @param lng1 
	    * @param lat1 
	    * @param lng2 
	    * @param lat2 
	    * @return 
	    */  
	   public static double getDistance(double lng1, double lat1, double lng2, double lat2)  
	   {  
		   double PI = 3.14159265;  
		   double EARTH_RADIUS = 6378137;  
		   double RAD = Math.PI / 180.0;  
	      double radLat1 = lat1*RAD;  
	      double radLat2 = lat2*RAD;  
	      double a = radLat1 - radLat2;  
	      double b = (lng1 - lng2)*RAD;  
	      double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +  
	       Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
	      s = s * EARTH_RADIUS;  
	      s = Math.round(s * 10000) / 10000;  
	      return s;  
	   }
	   
	   /**
		 * @param la
		 * @param lo
		 * @param distance
		 * @return
		 * params.put("sLa", sLa);
			params.put("lLa", lLa);纬度
			params.put("sLo", sLo);经度
			params.put("lLo", lLo);
		 */
		public static BigDecimal[] getPointByDistance(BigDecimal la,BigDecimal lo,int distance) {
			BigDecimal[] result = new BigDecimal[4];
			result[0]= new BigDecimal(31.853693);
			result[1]= new BigDecimal(31.867126);
			result[2]= new BigDecimal(117.282052);
			result[3]= new BigDecimal(117.295563);
			
//			define(EARTH_RADIUS, 6371);//地球半径，平均半径为6371km
//			 /**
//			 *计算某个经纬度的周围某段距离的正方形的四个点
//			 *
//			 *@param lng float 经度
//			 *@param lat float 纬度
//			 *@param distance float 该点所在圆的半径，该圆与此正方形内切，默认值为0.5千米
//			 *@return array 正方形的四个点的经纬度坐标
//			 */
//			 function returnSquarePoint($lng, $lat,$distance = 0.5){
//			 
//			    $dlng =  2 * asin(sin($distance / (2 * EARTH_RADIUS)) / cos(deg2rad($lat)));
//			    $dlng = rad2deg($dlng);
//			     
//			    $dlat = $distance/EARTH_RADIUS;
//			    $dlat = rad2deg($dlat);
//			     
//			    return array(
//			                'left-top'=>array('lat'=>$lat + $dlat,'lng'=>$lng-$dlng),
//			                'right-top'=>array('lat'=>$lat + $dlat, 'lng'=>$lng + $dlng),
//			                'left-bottom'=>array('lat'=>$lat - $dlat, 'lng'=>$lng - $dlng),
//			                'right-bottom'=>array('lat'=>$lat - $dlat, 'lng'=>$lng + $dlng)
//			                );
//			 }
//			//使用此函数计算得到结果后，带入sql查询。
//			$squares = returnSquarePoint($lng, $lat);
//			$info_sql = "select id,locateinfo,lat,lng from `lbs_info` where lat<>0 and lat>{$squares['right-bottom']['lat']} and lat<{$squares['left-top']['lat']} and lng>{$squares['left-top']['lng']} and lng<{$squares['right-bottom']['lng']} "; 
			return result;
		}
}
