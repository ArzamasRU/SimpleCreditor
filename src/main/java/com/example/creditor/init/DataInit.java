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
	public static int InitUsersQty;
	public static int InitAdminsQty;
	public static int InitRequestsQty;
	public static int InitLimitsQty;
	public static int UserInitRequestsQty;

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

		if (userCount == 0 && requestCount == 0 && countryLimitCount == 0) {
			User user1 = new User("user1", "p1", "Agent", "Smith", "United States", false, false);
			User user2 = new User("user2", "p2", "Paul", "Gray", "Japan", false, false);
			User user3 = new User("user3", "p3", "Corey", "Taylor", "India", false, true);
			User user4 = new User("user4", "p4", "Joey", "Jordison", "Japan", false, false);
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(user3);
			userRepo.save(user4);
			InitUsersQty = (int) userRepo.findByAdmin(false).size();

			userRepo.save(new User("admin", "admin", "", "", "Russia", true, false));
			InitAdminsQty = (int) userRepo.findByAdmin(true).size();

			requestRepo.save(new Request(user1, dateFormat.parse("2021-12-20"), 1000000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user1, dateFormat.parse("2025-10-05"), 2000000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user1, dateFormat.parse("2022-01-08"), 5000000d,
					dateFormat.parse("2019-01-01")));
			UserInitRequestsQty = (int) requestRepo.findByUsername(user1.getUsername()).size();
			requestRepo.save(new Request(user2, dateFormat.parse("2023-06-11"), 900000d,
					dateFormat.parse("2019-01-01")));
			requestRepo.save(new Request(user2, dateFormat.parse("2024-12-22"), 300000d,
					dateFormat.parse("2019-01-01")));
			InitRequestsQty = (int) requestRepo.count();

			countryLimitRepo.save(new CountryLimit("Japan", 2));
			countryLimitRepo.save(new CountryLimit("China", 0));
			InitLimitsQty = (int) countryLimitRepo.count();
		}

	}

}