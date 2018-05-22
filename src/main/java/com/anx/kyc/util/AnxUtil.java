package com.anx.kyc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
 	


	public static Map<String, String> getCountryList() {
		Map<String,String> countryList = new HashMap<String,String>();
		
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);

			countryList.put(obj.getCountry(), obj.getDisplayCountry());

		}
		return countryList;
	}
	
	public static Map<String,String> getIndustryList(){
		

		Map<String,String> industryList = new HashMap<String,String>();
		
		industryList.put("accounting_and_finance","Accounting and Finance");
		industryList.put("administrative","Administrative");
		industryList.put("architecture_and_engineering","Architecture and Engineering");
		industryList.put("arts_and_sports","Arts and Sports");
		industryList.put("construction","Construction");
		industryList.put("customer_service","Customer Service");
		industryList.put("education_and_training","Education and Training");
		industryList.put("general_service","General Service");
		industryList.put("health_and_medical","Health and Medical");
		industryList.put("hospitality_and_tourism","Hospitality and Tourism");
		industryList.put("household","Household (helper, driver)");
		industryList.put("human_resources","Human Resources");
		industryList.put("it_and_software","IT and Software");
		industryList.put("legal","Legal");
		industryList.put("management_and_consultancy","Management and Consultancy");
		industryList.put("manufacturing_and_production","Manufacturing and Production");
		industryList.put("media_and_creatives","Media and Creatives");
		industryList.put("public_service_and_ngos","Public Service and NGOs");
		industryList.put("safety_and_security","Safety and Security");
		industryList.put("sales_and_marketing","Sales and Marketing");
		industryList.put("sciences","Sciences");
		industryList.put("supply_chain","Supply Chain");
		industryList.put("writing_and_content","Writing and Content");
		
		return industryList;
	}
	
	public static Map<String,String> getSourceList(){
		

		Map<String,String> sourceList = new HashMap<String,String>();
		
		sourceList.put("allowance","Allowance");
		sourceList.put("government_subsidy","Government Subsidy");
		sourceList.put("other","Other");
		sourceList.put("pension","Pension");
		sourceList.put("remittances","Remittances");
		sourceList.put("scholarship_stipend","Scholarship/Stipend");
		sourceList.put("separation_pay","Separation Pay");
		
		return sourceList;
	}
	
	public static Map<String,String> getIdList(){
		

		Map<String,String> idList = new HashMap<String,String>();
		

	    idList.put("afp","Armed Forces of the Philippines (AFP) ID");
	    idList.put("drivers_license","Driver's License");
	    idList.put("gsis_ecard","Government Service Insurance System (GSIS) e-Card Plus2");
	    idList.put("nbi","National Bureau of Investigation (NBI) clearance");
	    idList.put("ncwdp","Certification from the National Council for the Welfare of Disabled Persons (NCWDP)");
	    idList.put("ofw","OFW E-Card");
	    idList.put("owwa","Overseas Workers Welfare Administration (OWWA) ID");
	    idList.put("passport","Passport");
	    idList.put("police","Police Clearance Certificate");
	    idList.put("postal","Postal ID");
	    idList.put("prc","Professional Regulation Commission (PRC) ID");
	    idList.put("seaman","Seaman's Book");
	    idList.put("ssn","Social Security System (SSS) Card");
	    idList.put("umid","Unified Multi-Purpose ID");
	    idList.put("voter","Voter's ID");
	    idList.put("alien","Alien Certificate of Registration");
	    idList.put("bureau_of_fire_protection","Bureau of Fire Protection ID");
	    idList.put("pnp","Philippine National Police ID");
	    idList.put("integrated_bar","Integrated Bar of the Philippines ID");
	    idList.put("philhealth","PhilHealth ID");
	    
		return idList;
	}
	

	
	public static Map<String,String> getDocTypeList(){
		

		Map<String,String> docTypeList = new HashMap<String,String>();
		
		docTypeList.put("bank_statement","Bank Statement (within the last 6 months)");
        docTypeList.put("credit_card","Credit Card Billing Statement (within the last 6 months)");
        docTypeList.put("utility_bill","Utility Bill (water, electricity, cable, phone - within the last 6 months)");
        docTypeList.put("mobile_phone","Mobile Phone Bill (within the last 6 months)");
        docTypeList.put("income_tax_return","Income Tax Return (Form 1700 or 1701)");
        docTypeList.put("mail","Mail from a University, Government Institution or Embassy");
        docTypeList.put("barangay_certification","Barangay Clearance / Barangay Certification of Residency");
        docTypeList.put("transcript_of_records","Transcript of Records (within the last 6 months)");
		
		return docTypeList;
	}
	
	
	public static Date parseFormatterdDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isNotNullOrEmpty(String value) {
		return value != null && !value.isEmpty();
	}
 	

}
