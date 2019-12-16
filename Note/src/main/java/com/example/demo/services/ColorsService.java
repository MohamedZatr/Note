package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MyMapper;
import com.example.demo.Repository.ColorRepository;
import com.example.demo.Response.MessageResponse;
import com.example.demo.exception.ExceptionFiledRequired;
import com.example.demo.exception.NotfoundResult;
import com.example.demo.model.Colors;
import com.example.demo.view.ColorViewModel;

@Service
public class ColorsService {

	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private MyMapper mapper;
	
	public List<ColorViewModel> getAllColors(){
		List<Colors> list = colorRepository.findAll();
		return list.stream().map(color->mapper.convertToColorsViewModel(color)).
				collect(Collectors.toList());
	}
	
	public ColorViewModel getColorByName(String colorName)
	{
		Colors color = colorRepository.findByColor(colorName);
 		if(color == null) 
 			new NotfoundResult("Cannot find Color with given Name") ;
		return mapper.convertToColorsViewModel(color);
		
	}
	
	public ColorViewModel SaveOrUpdate(ColorViewModel model)
	{
		if(model.getName() == null || model.getName().isEmpty()) 
			throw new ExceptionFiledRequired("Color name is required");
		Colors colors = mapper.convertToColorsEntity(model);
		colorRepository.save(colors);
		return mapper.convertToColorsViewModel(colors);
	}
	
	public MessageResponse deleteColorByName(String colorName) {
		if(colorRepository.existsByColor(colorName))
		{
			colorRepository.deleteByColor(colorName);
		}
		throw new NotfoundResult("Cannot Delete Color with given Name");
	}
	
}















