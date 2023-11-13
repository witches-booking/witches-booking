package com.witches.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.witches.booking.service.CalendarService;

@Controller
public class CalendarController {

	@Autowired
	CalendarService calendarservice ;
	
	
	@RequestMapping("/")
	public String welcome() {	
		return "/Scheduler";
	}
	
	
	
	
	
	
	
}
