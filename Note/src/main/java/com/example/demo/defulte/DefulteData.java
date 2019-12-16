package com.example.demo.defulte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Repository.ColorRepository;
import com.example.demo.model.Colors;

@Component
public class DefulteData implements ApplicationRunner {
	@Autowired
	private ColorRepository repository;
	String  colors[] = {"bg-primary","bg-secondary","bg-success","bg-danger","bg-warning","bg-info","bg-dark"};
		
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(repository.count() <= 0) {
		for(String color : colors) {
		    Colors entity = new Colors();
			entity.setColor(color);
			repository.save(entity);
		
		}
		}
	}
}
