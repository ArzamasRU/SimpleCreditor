package com.example.creditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String greeting(Model model) {
		return "greeting";
	}

	@PostMapping("/signIn")
	public String signIn(Model model) {
		return "login";
	}
}
