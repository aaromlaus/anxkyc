package com.anx.kyc.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.anx.kyc.helper.EmailHelper;
import com.anx.kyc.service.EmailService;
import com.anx.kyc.util.AnxUtil;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
    private EmailHelper emailHelper;
	
	@Override
	public void sendVerificationCodeEmail(String emailAddress) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailAddress);
            helper.setReplyTo("someone@localhost");
            helper.setFrom("someone@localhost");
            helper.setSubject("Reset your password");
            helper.setText(emailHelper.buildVerificationCodeEmail(AnxUtil.generateVerificationCode()),true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        javaMailSender.send(mail);
	}

}
