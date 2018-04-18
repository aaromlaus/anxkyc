package com.anx.kyc.service.impl;

import java.util.List;

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
	
	public void sendEmail(List<String> emailToList, String subject, String content) {
		sendEmail(emailToList, null, null, subject, content);
	}
	
	public void sendEmail(List<String> emailToList, List<String> emailCcList, String subject, String content) {
		sendEmail(emailToList, emailCcList, null, subject, content);
	}
	
	public void sendEmail(List<String> emailToList, List<String> emailCcList, List<String> emailBccList, String subject, String content) {
		MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            
            helper.setTo(emailToList==null ? new String[0] : emailToList.toArray(new String[emailToList.size()]));
            helper.setCc(emailCcList== null ? new String[0] : emailCcList.toArray(new String[emailCcList.size()]));
            helper.setBcc(emailBccList==null ? new String[0] : emailBccList.toArray(new String[emailBccList.size()]));
            helper.setReplyTo("someone@localhost");
            helper.setFrom("aaromlaus.mvp@gmail.com");
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally { }
        
        javaMailSender.send(mail);
	}

}
