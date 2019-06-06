package com.zycj.tcc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * properties帮助类
 * @author 洪捃能
 *
 */
public class PropertiesHelper {
	boolean isSearchSystemProperty = false;
	Properties p;

	public PropertiesHelper(Properties p) {
		this.p = p;
	}

	public PropertiesHelper(Properties p,boolean isSearchSystemProperty) {
		this.p = p;
		this.isSearchSystemProperty = isSearchSystemProperty;
	}
	
	public Properties getProperties() {
		return p;
	}
	
	public String getProperty(String key, String defaultValue) {
		String value = null;
		if(isSearchSystemProperty) {
			value = System.getProperty(key);
		}
		if(value == null || "".equals(value.trim())) {
			value = getProperties().getProperty(key);
		}
		return value == null || "".equals(value.trim()) ? defaultValue : value;
	}
	
	public String getProperty(String key) {
		return getProperty(key,null);
	}
	
	public String getRequiredProperty(String key) {
		String value = getProperty(key);
		if(value == null || "".equals(value.trim())) {
			throw new IllegalStateException("required property is blank by key="+key);
		}
		return value;
	}
	
	public Integer getInt(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Integer.parseInt(getRequiredProperty(key));
	}
	
	public int getInt(String key,int defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Integer.parseInt(getRequiredProperty(key));
	}
	
	public int getRequiredInt(String key) {
		return Integer.parseInt(getRequiredProperty(key));
	}
	
	public Boolean getBoolean(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Boolean.parseBoolean(getRequiredProperty(key));
	}
	
	public boolean getBoolean(String key,boolean defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Boolean.parseBoolean(getRequiredProperty(key));
	}
	
	public boolean getRequiredBoolean(String key) {
		return Boolean.parseBoolean(getRequiredProperty(key));
	}
	
	public String getNullIfBlank(String key) {
		String value = getProperty(key);
		if(value == null || "".equals(value.trim())) {
			return null;
		}
		return value;
	}
	
	public PropertiesHelper setProperty(String key,String value) {
		p.setProperty(key, value);
		return this;
	}

	public void clear() {
		p.clear();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return p.entrySet();
	}

	public Enumeration<?> propertyNames() {
		return p.propertyNames();
	}
	
	
	public void loadPropertiesFromClassLoader(String resourceName) throws IOException {
		InputStream input = PropertiesHelper.class.getClassLoader().getResourceAsStream(resourceName);
		try {
			if (resourceName.endsWith(".xml")) {
				p.loadFromXML(input);
			} else {
				p.load(input);
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
}
