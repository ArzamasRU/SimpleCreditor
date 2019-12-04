package com.example.creditor.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.creditor.domain.CountryLimit;
import com.example.creditor.repos.CountryLimitRepo;

@Controller
@RequestMapping("/limits")
public class LimitController {
	@Autowired
	private CountryLimitRepo countryLimitRepo;

	@GetMapping()
	public String mapping(Model model) {
		List<CountryLimit> limits = countryLimitRepo.findAll();
		model.addAttribute("limits", limits);
		return "limits";
	}

	@PostMapping(params = "setLimit")
	public String setLimit(@RequestParam String country, @RequestParam Integer limit, Model model)
			throws IOException {

		if (!country.equals("not selected")) {
			var countryLimit = countryLimitRepo.findByCountry(country);
			if (countryLimit == null) {
				countryLimitRepo.save(new CountryLimit(country, limit));
				model.addAttribute("message", "Limit created");
			} else if (countryLimit.getLimit() != limit) {
				countryLimit.setLimit(limit);
				model.addAttribute("message", "Limit changed");
			}
		}
		List<CountryLimit> limits = countryLimitRepo.findAll();
		model.addAttribute("limits", limits);
		return "limits";
	}

	@PostMapping(params = "removeLimit")
	public String removeLimit(@RequestParam String country, Model model) throws IOException {
		countryLimitRepo.delete(countryLimitRepo.findByCountry(country));
		List<CountryLimit> limits = countryLimitRepo.findAll();
		model.addAttribute("limits", limits);
		model.addAttribute("message", "Limit removed");
		return "limits";
	}
}
