package com.anx.kyc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.anx.kyc.model.AnxUser;

public class AnxUtil {

	public static int generateVerificationCode() {
		return generateCode();
	}
	
	public synchronized static String generateId(String prefix, int id) {
		return prefix + generateCode() + id;
	}
	
	private static int generateCode() {
		int code = 0;
		try {
		     SecureRandom number = SecureRandom.getInstanceStrong();
		     while(code < 100000) {
		    	 code = number.nextInt(999999);
		     }
		   } catch (NoSuchAlgorithmException nsae) {
			   
		   }
		
		return code;
	}
	
	public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        return(mat.matches());
	}
	
	public static String getRequestPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}
	
 	public static String encodeBase64(String imagePath) {
		String base64Image = "";
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) { 
			
		} catch (IOException ioe) { 
			
		}
		return base64Image;
	}
 	
 	public static String getAnxUserFullName(AnxUser anxUser) {
 		return anxUser.getFirstName().concat(" ").concat(anxUser.getMiddleName().concat(" ").concat(anxUser.getLastName()));
 	}
 	
}
