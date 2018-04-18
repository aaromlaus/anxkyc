package com.anx.kyc.helper;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EmailHelper {
	
	public String buildVerificationCodeEmail(int code) {
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append(getHeaderHtml())
			.append("<p>We received a request to <b>reset your KYC password.</b></span>" )
			.append("</br></br></br>") 
			.append("<span style=\"font-family: Arial, Helvetica, sans-serif; font-size:16px; color:#757575;\">" ) 
			.append("If you made this request, please use the following " )
			.append("<b>Verification Code:</b></p> </span>") 
			.append("<span style=\"font-family: Arial, Helvetica, sans-serif; font-size:32px; color:#3399cc;\">" ) 
			.append("<p><b>").append(code).append("</b></p> </span>" ) 
			.append("<hr>" )
			.append("<span style=\"font-family: Arial, Helvetica, sans-serif; font-size:11px; color:#757575;\">" ) 
			.append("<p>If you didn't request for a new password, please contact us at <a href=\"#\">help@anx.kyc.com</a></p>") 
			.append(getFooterHtml());
		
		return sb.toString();
	}
	
	public String buildUserSignupVerificationEmailContent(Map<String, String> param) {
		StringBuilder sb = new StringBuilder("");
		String verificationCode = param.get("verificationCode");
		
		sb.append(getHeaderHtml())
			.append("<p>Welcome to ANX! Before you get started, click the <b>link</b> below to complete your registration</span>" )
			.append("</br></br></br>") 
			.append("<span style=\"font-family: Arial, Helvetica, sans-serif; font-size:32px; color:#3399cc;\">" ) 
			.append("<p><b>")
			.append("<a href=\"")
			.append(verificationCode)
			.append("\">")
			.append("Confirm my email address")
			.append("</a>")
			.append("</b></p> </span>" ) 
			.append("<hr>" )
			.append("<span style=\"font-family: Arial, Helvetica, sans-serif; font-size:11px; color:#757575;\">" ) 
			.append("<p>If you didn't use this email for registration, please contact us at <a href=\"#\">help@anx.kyc.com</a></p>") 
			.append(getFooterHtml());
		return sb.toString();
	}
	
	private static String getHeaderHtml() {
		StringBuilder sb = new StringBuilder("");
		sb.append("<html>").append("<head>").append("</head>")
				.append("<body style=\"background-color: #567;  padding: 2%;\" >")
				.append("<div style=\"width: 400px; margin: auto; border: 1px #CCC solid; ")
				.append("padding: 30px; background-color: #f0f0f0; ").append("color: #FFF; text-align: center;\">")
				.append("<span style=\"font-family: Arial, Helvetica, sans-serif; font-size:16px; color:#757575;\">");
		return sb.toString();
	}

	private static String getFooterHtml() {
		StringBuilder sb = new StringBuilder("");
		sb.append("</span>").append("</div>").append("</body>").append("</html>");
		return sb.toString();
	}
}
