package com.example.creditor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.creditor.repos.CountryLimitRepo;
import com.example.creditor.repos.RequestRepo;
import com.example.creditor.repos.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "admin")
public class AdminActivityTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RequestRepo requestRepo;

	@Autowired
	private CountryLimitRepo countryLimitRepo;

	@Test
	public void mainPageTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='navbarSupportedContent']/div").string("admin"));
	}

	@Test
	public void requestsListTest() throws Exception {
		this.mockMvc.perform(get("/requests"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='requestRow']").nodeCount(requestRepo.findAll().size()))
				.andExpect(xpath("//*[@id='requestsList']/tr[@data-id='1']").exists())
				.andExpect(xpath("//*[@id='requestsList']/tr[@data-id='"
						+ requestRepo.findAll().size() + "']").exists());

		this.mockMvc
				.perform(get("/requests").param("filter", "username").param("filterValue", "user2"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='requestRow']")
						.nodeCount(requestRepo.findByUsername("user2").size()));
	}

	@Test
	public void blacklistTest() throws Exception {
		this.mockMvc.perform(get("/blacklist"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(
						xpath("//*[@id='userRow']").nodeCount(userRepo.findByAdmin(false).size()))
				.andExpect(xpath("//*[@id='usersList']/tr[@data-id='3']/td[@id='inBlacklist']")
						.string("true"))
				.andExpect(xpath("//*[@id='usersList']/tr[@data-id='4']/td[@id='inBlacklist']")
						.string("false"));

		this.mockMvc.perform(multipart("/blacklist").param("username", "user3")
				.param("inBlacklist", "true")
				.with(csrf()));
		this.mockMvc.perform(multipart("/blacklist").param("username", "user4")
				.param("inBlacklist", "false")
				.with(csrf()));

		this.mockMvc.perform(get("/blacklist"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='usersList']/tr[@data-id='3']/td[@id='inBlacklist']")
						.string("false"))
				.andExpect(xpath("//*[@id='usersList']/tr[@data-id='4']/td[@id='inBlacklist']")
						.string("true"));
	}

	@Test
	public void limitsTest() throws Exception {
		int initLimitsQty = countryLimitRepo.findAll().size();
		this.mockMvc.perform(get("/limits"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='limitRow']").nodeCount(initLimitsQty));

		this.mockMvc.perform(multipart("/limits").param("country", "Germany")
				.param("limit", "5")
				.param("setLimit", "setLimit")
				.with(csrf()));
		initLimitsQty++;

		this.mockMvc.perform(get("/limits"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='limitRow']").nodeCount(initLimitsQty))
				.andExpect(xpath("//*[@id='limitsList']/tr[@data-id='" + initLimitsQty
						+ "']/td[@id='country']").string("Germany"))
				.andExpect(xpath("//*[@id='limitsList']/tr[@data-id='" + initLimitsQty
						+ "']/td[@id='limit']").string("5"));

		this.mockMvc.perform(multipart("/limits").param("country", "China")
				.param("removeLimit", "removeLimit")
				.with(csrf()));
		initLimitsQty--;

		this.mockMvc.perform(get("/limits"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='limitRow']").nodeCount(initLimitsQty));
	}
}
