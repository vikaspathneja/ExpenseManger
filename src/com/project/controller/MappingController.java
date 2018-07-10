package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.service.MappingServiceInterface;

@Controller
public class MappingController {
	@Autowired
	private MappingServiceInterface mappingServiceInterfaceRef;
	
	@RequestMapping(value="/")
	public String redirectindexpage()
	{
		mappingServiceInterfaceRef.checkIncomeExpenseCategory();
		return "redirect:/login";
	}
}
