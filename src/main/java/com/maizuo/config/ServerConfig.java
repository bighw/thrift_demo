package com.maizuo.config;

import java.util.*;

public class ServerConfig 
{
	private static Map<String,String> configMap;
	static
	{
		try {
			Locale locale = Locale.getDefault();
			ResourceBundle bundle = ResourceBundle.getBundle("config", locale);
			Set<String> keySet = bundle.keySet();
			configMap = new HashMap<String,String>();
			Iterator<String> it = keySet.iterator();
			while(it.hasNext())
			{
				String key = it.next();
				configMap.put(key, bundle.getString(key));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	private ServerConfig()
	{
		
	}
	
	public static String getProperty(String key)
	{
		 return configMap.get(key);
	}
	
	
	

}
