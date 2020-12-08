package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
	public static String getValueForKey(String key) throws Throwable
	{
		Properties confingProperties=new Properties();
		confingProperties.load(new FileInputStream("E:\\10'Oclock OJT\\ERP_Maven\\Propertyfile\\Environment.properties"));
		return confingProperties.getProperty(key);
	}

}
