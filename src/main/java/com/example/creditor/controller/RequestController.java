package com.example.creditor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.creditor.domain.Request;
import com.example.creditor.domain.User;
import com.example.creditor.repos.RequestRepo;

@Controller
@RequestMapping("/requests")
public class RequestController {
	@Autowired
	private RequestRepo requestRepo;

	@GetMapping()
	public String sort(@AuthenticationPrincipal User user,
			@RequestParam(required = false, defaultValue = "") String typeOfSort,
			@RequestParam(required = false, defaultValue = "") String filter, Model model) {

		List<Request> requests = null;
		if (!user.isAdmin()) {
			typeOfSort = "username";
			filter = user.getUsername();
		}
		if (filter != null && !filter.isEmpty()) {
			if (typeOfSort.equals("username")) {
				requests = requestRepo.findByUsername(filter);
				model.addAttribute("filter", filter);
			}
		} else
			requests = requestRepo.findAll();
		model.addAttribute("requests", requests);
		return "requests";
	}
}
