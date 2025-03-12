package utility;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerUtility {
	
	
	public static String generateName() {
		
		Faker f = new Faker(new Locale("en-IND"));
		return f.regexify("^[A-Za-z0-9_-]{5,17}");
	}

}
