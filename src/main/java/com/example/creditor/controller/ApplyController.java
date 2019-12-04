package com.example.creditor.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.creditor.domain.CountryLimit;
import com.example.creditor.domain.Request;
import com.example.creditor.domain.User;
import com.example.creditor.repos.CountryLimitRepo;
import com.example.creditor.repos.RequestRepo;

@Controller
@RequestMapping("/apply")
public class ApplyController {
	static final long ONE_MINUTE_IN_MILLIS = 60000; //millisecs

	@Autowired
	private RequestRepo requestRepo;
	@Autowired
	private CountryLimitRepo countryLimitRepo;

	@GetMapping()
	public String mapping(Model model) {
		return "apply";
	}

	@PostMapping()
	public synchronized String applyForCredit(@AuthenticationPrincipal User user,
			@RequestParam String term, @RequestParam Double summ, Model model)
			throws IOException, ParseException {

		Date currDate = new Date();
		Date dterm = new SimpleDateFormat("yyyy-MM-dd").parse(term);
		String message = checkLimit(user, currDate);
		if (message.isEmpty())
			message = checkBlacklist(user);
		if (message.isEmpty()) {
			long diffDays = getDifferenceDays(new Date(), dterm);
			message = checkTerms(diffDays, summ);
		}
		if (message.isEmpty()) {
			requestRepo.save(new Request(user, dterm, summ, currDate));
			model.addAttribute("message", "Requests accepted!");
		} else
			model.addAttribute("message", message);
		return "apply";
	}

	private long getDifferenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	private String checkTerms(long days, Double summ) {
		if (days < 0 || summ > 1_000_000_000) {
			return "Invalid conditions!";
		} else if (days > 365 * 30) {
			return "Unprofitable conditions!";
		} else
			return "";
	}

	//	private String checkLimit(User user) {
	//		String country = user.getCountry();
	//		CountryLimit countryLimit = countryLimitRepo.findByCountry(country);
	//		if (countryLimit != null
	//				&& requestRepo.findByCountry(country).size() >= countryLimit.getLimit())
	//			return "limit exceeded!";
	//		else
	//			return "";
	//	}

	private String checkLimit(User user, Date date) {
		String country = user.getCountry();
		CountryLimit countryLimit = countryLimitRepo.findByCountry(country);
		Date limitStartingPoint = new Date(date.getTime() - ONE_MINUTE_IN_MILLIS);
		if (countryLimit != null
				&& requestRepo.findByCountryAndDateBetween(country, limitStartingPoint, date)
						.size() >= countryLimit.getLimit())
			return "Limit exceeded!";
		else
			return "";
	}

	private String checkBlacklist(User user) {
		if (user.isInBlacklist())
			return "User in blacklist!";
		else
			return "";
	}
}
