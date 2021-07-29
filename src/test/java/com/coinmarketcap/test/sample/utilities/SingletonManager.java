/**
 * 
 */
package com.coinmarketcap.test.sample.utilities;

public class SingletonManager {

	private static PropertiesReader propertiesReader;

	public static PropertiesReader getPropertiesReader() {

		if(propertiesReader == null){
			propertiesReader = new PropertiesReader();
		} 
		return propertiesReader;
	}








}
