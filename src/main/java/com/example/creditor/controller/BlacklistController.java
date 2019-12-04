package com.example.creditor.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.creditor.domain.User;
import com.example.creditor.repos.UserRepo;

@Controller
@RequestMapping("/blacklist")
public class BlacklistController {
	@Autowired
	private UserRepo userRepo;

	@GetMapping()
	public String mapping(Model model) {
		List<User> users = userRepo.findByAdmin(false);
		model.addAttribute("users", users);
		return "blacklist";
	}

	@PostMapping()
	public String RemoveAndAdd(@RequestParam String username, @RequestParam boolean inBlacklist,
			Model model) throws IOException, ParseException {

		User user = userRepo.findByUsername(username);
		user.setInBlacklist(!inBlacklist);
		userRepo.save(user);
		List<User> users = userRepo.findByAdmin(false);
		model.addAttribute("users", users);
		if (inBlacklist)
			model.addAttribute("message", "User " + username + " removed from blacklist");
		else
			model.addAttribute("message", "User " + username + " added in blacklist");
		return "blacklist";
	}
}
