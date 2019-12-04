package com.example.creditor;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.string(containsString("This is a simple service of providing credits")));
	}

	@Test
	public void correctLoginTest() throws Exception {
		this.mockMvc.perform(formLogin().user("admin").password("admin"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
		this.mockMvc.perform(formLogin().user("user1").password("p1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
	}

	@Test
	public void badCredentials() throws Exception {
		this.mockMvc.perform(post("/login").param("username", "Test"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}
