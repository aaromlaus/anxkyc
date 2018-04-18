package com.anx.kyc.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class AnxUtil {

	public static int generateVerificationCode() {
		int code = 0;
		try {
		     SecureRandom number = SecureRandom.getInstanceStrong();
		     code = number.nextInt(999999);
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
}
