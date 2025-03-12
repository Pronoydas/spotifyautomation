package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtility {
	
	private static final String filePath =System.getProperty("user.dir")+"\\src\\test\\resources\\ApplicationProperties\\Config.properties";
	
	
	public static String getPropertiyValue(String key) throws Exception {
		
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(new File(filePath));
		p.load(fis);
		return p.getProperty(key);
	}

}
