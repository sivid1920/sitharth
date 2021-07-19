package com.base.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
	
	static Properties prop=readPropertiesFile();
	public  static Properties readPropertiesFile() 
	{

		FileInputStream fis = null;
		try 
		{
			String filePath=System.getProperty("user.dir")+"\\resources\\data.properties";
			fis = new FileInputStream(filePath);
		}
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try 
		{
			prop.load(fis);
		} 
		catch (IOException e)
		{

			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getBrowser1()
	{
		return prop.getProperty("Browser1");
	}
	
	public static String getBrowser2()
	{
		return prop.getProperty("Browser2");
	}

	public static String getBrowser3()
	{
		return prop.getProperty("Browser3");
	}

}
