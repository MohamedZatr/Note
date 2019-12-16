package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan({"com.example.mail","com.example.demo.controller","com.example.demo"})
public class NoteApplication  {
	
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(NoteApplication.class, args);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
		System.err.println(date);
	}

}
