package com.nexmo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hangman")
public class HangmanViewCtrl {
	
	@RequestMapping("")
	public String index() {
		return "forward:/index.html";
	}
	
	@RequestMapping("/management")
	public String manager() {
		return "forward:/manager.html";
	}
	
}









