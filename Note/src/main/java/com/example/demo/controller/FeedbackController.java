package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FeedbackViewModel;
import com.example.demo.services.FeedBackMailSender;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {

	@Autowired
	private FeedBackMailSender feedbackSender;

    @CrossOrigin
    @PostMapping("/send")
    public void sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel){
    	System.err.println("I m here");
        this.feedbackSender.sendFeedback(feedbackViewModel);
    }
}
