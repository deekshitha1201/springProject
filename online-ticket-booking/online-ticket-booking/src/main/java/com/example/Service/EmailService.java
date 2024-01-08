package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	public void sendOtpEmail(String to,String otp) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setText("your OTP is:"+otp);
		javaMailSender.send(message);
	}

}
