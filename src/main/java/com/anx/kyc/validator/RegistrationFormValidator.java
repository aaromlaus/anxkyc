package com.anx.kyc.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.anx.kyc.common.RoleType;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserService;

@Component
public class RegistrationFormValidator implements Validator {

	@Autowired
	private UserService userSvc;

	@Override
	public boolean supports(Class<?> paramClass) {
		return AnxUser.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AnxUser anxUser = (AnxUser) obj;

		// Password validation
		if (!anxUser.getPassword().equals(anxUser.getConfirmPassword())) {
			errors.rejectValue("password", "user.password.notMatch");	
		}

		List<AnxUser> users = userSvc.getUsersByRoleName(RoleType.USER);
		for (AnxUser user : users) {
			
			if (null != anxUser.getEmailAddress() && !anxUser.getEmailAddress().isEmpty()
					&& user.getEmailAddress().equals(anxUser.getEmailAddress())) {
				errors.rejectValue("emailAddress", "user.username.exist");
				break;
			}
			if(null != anxUser.getPhoneNumber() && !anxUser.getPhoneNumber().isEmpty() 
					&& user.getPhoneNumber().equals(anxUser.getPhoneNumber())){
				errors.rejectValue("phoneNumber", "user.username.exist");
				break;
			}
		}
	}

}
