package com.example.creditor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.creditor.controller.ApplyController;
import com.example.creditor.domain.CountryLimit;
import com.example.creditor.domain.Request;
import com.example.creditor.domain.User;
import com.example.creditor.repos.CountryLimitRepo;
import com.example.creditor.repos.RequestRepo;
import com.example.creditor.repos.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyControllerTest {
	@Autowired
	private ApplyController applyController;

	@MockBean
	private RequestRepo requestRepo;

	@MockBean
	private UserRepo userRepo;

	@MockBean
	private CountryLimitRepo countryLimitRepo;

	@Test
	public void getDifferenceDaysTest() throws ParseException {
		var simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date date1 = simpleDateFormat.parse("2020-01-01");
		Date date2 = simpleDateFormat.parse("2020-01-03");
		assertTrue(applyController.getDifferenceDays(date1, date2) == 2);
	}

	@Test
	public void checkTermsTest() {
		assertTrue(applyController.checkTerms(365, Double.valueOf(1000)).isEmpty());
		assertFalse(applyController.checkTerms(365, Double.valueOf(2_000_000_000)).isEmpty());
		assertFalse(applyController.checkTerms(-1, Double.valueOf(2000)).isEmpty());
		assertFalse(applyController.checkTerms(365 * 31, Double.valueOf(2000)).isEmpty());
	}

	@Test
	public void checkLimitTest() {
		var user = new User("user5", "p5", "Chris", "Fehn", "Italy", false, false);
		var date = new Date();
		assertTrue(applyController.checkLimit(user, date).isEmpty());

		doReturn(List.of(new Request(), new Request())).when(requestRepo)
				.findByCountryAndDateBetween("Italy",
						new Date(date.getTime() - ApplyController.ONE_MINUTE_IN_MILLIS), date);
		doReturn(new CountryLimit("Italy", 2)).when(countryLimitRepo).findByCountry("Italy");
		assertFalse(applyController.checkLimit(user, date).isEmpty());

		doReturn(new CountryLimit("Italy", 3)).when(countryLimitRepo).findByCountry("Italy");
		assertTrue(applyController.checkLimit(user, date).isEmpty());
	}

	@Test
	public void checkBlacklistTest() {
		User user1 = new User("user5", "p5", "Chris", "Fehn", "Italy", false, false);
		User user2 = new User("user5", "p5", "Chris", "Fehn", "Italy", false, false);
		assertTrue(applyController.checkBlacklist(user1).isEmpty());
		assertTrue(applyController.checkBlacklist(user2).isEmpty());
	}

	//	@Test
	//	public void addUser() {
	//		User user = new User("user5", "p5", "Chris", "Fehn", "Italy", false, false);
	//		CountryLimit countryLimit = new CountryLimit("Italy", 3);
	//
	//		assertFalse(user.isAdmin());
	//		assertFalse(user.isInBlacklist());
	//		assertEquals(user.getUsername(), "user5");
	//
	//		//		userRepo.save(user);
	//		//		verify(userRepo, times(1)).save(user);
	//
	//		//		assertTrue(doReturn(user).when(userRepo).findByUsername("user1") == null);
	//		//		assertTrue(when(userRepo.findByUsername("user1")).thenReturn(user) == null);
	//		assertTrue(when(countryLimitRepo.findByCountry("Italy"))
	//				.thenReturn(new CountryLimit()) == null);
	//		//		when(userRepo.findByUsername("user1")).thenReturn(user);
	//		//		boolean isUserCreated = userRepo.save(user);
	//
	//		//		Mockito.verify(mailSender, Mockito.times(1))
	//		//				.send(ArgumentMatchers.eq(user.getEmail()), ArgumentMatchers.anyString(),
	//		//						ArgumentMatchers.anyString());
	//	}
}
