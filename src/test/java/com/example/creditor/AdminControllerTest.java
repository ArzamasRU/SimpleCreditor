package com.example.creditor;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.example.creditor.controller.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "admin")
public class AdminControllerTest {
	@Autowired
	private MainController controller;

	@Autowired
	private MockMvc mockMvc;

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
				.andExpect(xpath("//*[@id='requestsRow']").nodeCount(5));
		this.mockMvc.perform(get("/requests"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='requestsList']/tr").nodeCount(5));
	}

	@Test
	public void filterMessageTest() throws Exception {
		this.mockMvc.perform(get("/requests"))
				.andDo(print())
				.andExpect(authenticated())
				.andExpect(xpath("//*[@id='requestlist']/tr[@data-id='6']").exists())
				.andExpect(xpath("//*[@id='requestlist']/tr[@data-id='10']").exists());
	}
}
