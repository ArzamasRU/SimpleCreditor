package com.example.creditor.init;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.creditor.domain.CountryLimit;
import com.example.creditor.domain.Request;
import com.example.creditor.domain.User;
import com.example.creditor.repos.CountryLimitRepo;
import com.example.creditor.repos.RequestRepo;
import com.example.creditor.repos.UserRepo;

@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RequestRepo requestRepo;
	@Autowired
	private CountryLimitRepo countryLimitRepo;

	@Autowired
	public DataInit(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long userCount = userRepo.count();
		long requestCount = requestRepo.count();
		long countryLimitCount = countryLimitRepo.count();
		var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		User user1 = null;
		User user2 = null;
		User user3 = null;
		User user4 = null;

		if (userCount == 0) {
			user1 = new User("user1", "p1", "Agent", "Smith", "United States", false, false);
			user2 = new User("user2", "p2", "Paul", "Gray", "Japan", false, false);
			user3 = new User("user3", "p3", "Corey", "Taylor", "India", false, true);
			user4 = new User("user4", "p4", "Joey", "Jordison", "Japan", false, false);
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(user3);
			userRepo.save(user4);
			userRepo.save(new User("admin", "admin", "", "", "Russia", true, false));
		}
		if (requestCount == 0) {
			requestRepo.save(new Request(user1, dateFormat.parse("2021-12-20"), 1000000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user1, dateFormat.parse("2025-10-05"), 2000000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user1, dateFormat.parse("2022-01-08"), 5000000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user2, dateFormat.parse("2023-06-11"), 900000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user2, dateFormat.parse("2024-12-22"), 300000d,
					dateFormat.parse("2019-01-01")));
		}
		if (countryLimitCount == 0) {
			countryLimitRepo.save(new CountryLimit("Japan", 2));
			countryLimitRepo.save(new CountryLimit("China", 0));
		}
	}

}