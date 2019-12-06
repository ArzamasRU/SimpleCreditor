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
	public String search(@AuthenticationPrincipal User user,
			@RequestParam(required = false, defaultValue = "") String filter,
			@RequestParam(required = false, defaultValue = "") String filterValue, Model model) {

		List<Request> requests = null;
		if (!user.isAdmin()) {
			filter = "username";
			filterValue = user.getUsername();
		}
		if (filterValue != null && !filterValue.isEmpty()) {
			if (filter.equals("username")) {
				requests = requestRepo.findByUsername(filterValue);
				model.addAttribute("filter", filterValue);
			}
		} else
			requests = requestRepo.findAll();
		model.addAttribute("requests", requests);
		return "requests";
	}
}
