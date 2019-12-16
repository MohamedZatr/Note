package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.MessageResponse;
import com.example.demo.services.ColorsService;
import com.example.demo.view.ColorViewModel;

@RestController
@RequestMapping("api/color")
@CrossOrigin
public class ColorsController {

	@Autowired
	ColorsService colorService;
	
	@GetMapping("/all")
	public List<ColorViewModel> getAllColers(){
		return this.colorService.getAllColors();
	}
	
	@GetMapping("/byName/{name}")
	public ColorViewModel getColoByName(@PathVariable("name") String name){
		return this.colorService.getColorByName(name);
	}
	
	@PostMapping("/add")
	public ColorViewModel saveOrUpdatecolors(@RequestBody ColorViewModel model){
		return this.colorService.SaveOrUpdate(model);
	}
	
	@DeleteMapping("deleteByName/{name}")
	public MessageResponse deleteColorByName(@PathVariable("name")String name) {
		return this.deleteColorByName(name);
	}
}
