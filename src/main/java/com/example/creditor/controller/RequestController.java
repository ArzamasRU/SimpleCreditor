package com.example.creditor.controller;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public String filter(@AuthenticationPrincipal User user,
			@RequestParam(required = false, defaultValue = "") String filter,
			@RequestParam(required = false, defaultValue = "") String filterValue, Model model)
			throws IllegalArgumentException, IllegalAccessException {

		List<Request> requests = null;
		if (!user.isAdmin())
			requests = requestRepo.findByUsername(user.getUsername());
		else
			requests = requestRepo.findAll();
		if (!filter.isEmpty() && !filterValue.isEmpty() && requests.size() != 0) {
			requests = filterByField(requests, filter, filterValue);
		}
		model.addAttribute("requests", requests);
		return "requests";
	}

	private List<Request> filterByField(List<Request> list, String filter, String filterValue)
			throws IllegalArgumentException, IllegalAccessException {

		var filteredList = new ArrayList<Request>();
		var sdf = new SimpleDateFormat("yyyy-MM-dd");
		Field[] allFields = list.get(0).getClass().getDeclaredFields();
		for (Request request : list) {
			for (Field field : allFields) {
				if (field.getName().equals(filter)) {
					field.setAccessible(true);
					if (filter.equals("term") || filter.equals("date")) {
						boolean equivalDates = false;
						try {
							equivalDates = sdf.format(field.get(request))
									.equals(sdf.format(sdf.parse(filterValue)));
						} catch (ParseException e) {
						}
						if (equivalDates)
							filteredList.add(request);
					} else if (filter.equals("summ")
							&& field.get(request).equals(Double.valueOf(filterValue))) {
						filteredList.add(request);
					} else if (field.get(request).equals(filterValue)) {
						filteredList.add(request);
					}
				}
			}
		}
		return filteredList;
	}
}
