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

import com.example.creditor.repos.RequestRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "user1")
public class UserActivityTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RequestRepo requestRepo;

	@Test
	public void mainPageTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='navbarSupportedContent']/div").string("user1"));
	}

	@Test
	public void requestsListTest() throws Exception {
		int userInitRequestsQty = requestRepo.findByUsername("user1").size();
		this.mockMvc.perform(get("/requests"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='requestRow']").nodeCount(userInitRequestsQty))
				.andExpect(
						xpath("//*[@id='requestsList']/tr[@data-id='" + userInitRequestsQty + "']")
								.exists());

		this.mockMvc.perform(
				multipart("/apply").param("term", "2020-01-01").param("summ", "1000").with(csrf()));
		userInitRequestsQty++;

		this.mockMvc.perform(get("/requests"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='requestRow']").nodeCount(userInitRequestsQty))
				.andExpect(xpath("//*[@id='requestsList']/tr[@data-id='" + userInitRequestsQty
						+ "']/td[@id='term']").string("Jan 1, 2020"))
				.andExpect(xpath("//*[@id='requestsList']/tr[@data-id='" + userInitRequestsQty
						+ "']/td[@id='summ']").string("1,000"));
	}
}
