package com.example.demo.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepositry;
import com.example.demo.model.FeedbackViewModel;
import com.example.demo.model.User;

@Service
public class FeedBackMailSender{
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private UserRepositry userRepositry;
	
    public void sendFeedback(FeedbackViewModel feedback) {
    	
    User user = userRepositry.findByEmail(feedback.getEmail());
    	
    SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("mohamed_ramadan7@hotmail.com");
        message.setSubject("New feedback from " + user.getUserName());
        message.setText(feedback.getFeedback());
        System.err.println(user.getEmail());
        message.setFrom(user.getEmail());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        
        this.mailSender.send(message);
    }
}

