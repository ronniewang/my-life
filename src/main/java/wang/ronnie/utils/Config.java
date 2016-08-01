package wang.ronnie.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static Properties load(String file) {
		InputStream inputStream = Config.class.getResourceAsStream(file);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return p;
	}
}
