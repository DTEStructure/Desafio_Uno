package com.previred.restGDD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.restGDD.models.GddRequest;
import com.previred.restGDD.models.GddResponse;
import com.previred.restGDD.service.GddService;

@RestController
public class GddController {
	
	@Autowired
	GddService gddService;
	
	@RequestMapping(value = "/GDD", method = RequestMethod.POST)
	public GddResponse GDD(@RequestBody GddRequest request) {
		return gddService.getPeriodos(request);
	}
	
}
