package com.tcc.park.api.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class FeeConfig {
	
	public static Map<String, String> feeMap = new HashMap<String, String>();
	public static String FEE_SEPECIAL = "";
	
	static {
		Locale locale = new Locale("zh", "CN");
		ResourceBundle bundle = ResourceBundle.getBundle("fee", locale);
		Set<String> keySets = bundle.keySet();
		for (String key : keySets) {
			if (key.startsWith("fee_")) {
				feeMap.put(key, bundle.getString(key));
			}
			if (key.equals("sfee_special")) {
				FEE_SEPECIAL = bundle.getString(key);
			}
		}
		
	}

}
