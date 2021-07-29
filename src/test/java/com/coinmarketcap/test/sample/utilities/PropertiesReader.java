
package com.coinmarketcap.test.sample.utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	
	private String fileName = System.getProperty("user.dir")+"/"+"config.properties";
	private Properties properties;

	public PropertiesReader(){
		properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key){
		return properties.getProperty(key).trim();
	}	
}