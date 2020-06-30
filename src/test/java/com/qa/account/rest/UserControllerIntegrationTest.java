package com.qa.account.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	@Autowired // will insert the obj in the class
	private MockMvc mockMVC; // 3rd anatation will create this obj

	private User user;

	private User savedUser;

	@Autowired
	private ObjectMapper mapper; // used to convert java classes to and from json

	@Autowired
	private UserRepo repo;

	private long userId;

	@Before
	public void init() {
		this.repo.deleteAll();

		this.user = new User("Malle", "999999");
		this.savedUser = this.repo.save(this.user);
		this.userId = this.savedUser.getUserId() + 1;
	}

	@Test
	public void testCreate() throws Exception {
		this.savedUser.setUserId(userId);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/create");
		builder.contentType(MediaType.APPLICATION_JSON); // accepts Json and sends Json back
		builder.accept(MediaType.APPLICATION_JSON);
		builder.content(this.mapper.writeValueAsString(user));

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedUser));

		this.mockMVC.perform(builder).andExpect(matchStatus).andExpect(matchContent);
	}

//	@Test // same as above
//	public void testCreateBuilder() throws Exception {
//		this.mockMVC.perform(post("/user/create")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(user))).andExpect(status().isCreated())
//		.andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedUser)));
//	}

	@Test
	public void testReadOneSuccess() throws Exception {
		this.mockMVC
				.perform(get("/user/read/" + this.savedUser.getUserId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedUser)));
	}

	@Test
	public void testReadOneFailure() throws Exception {
		this.mockMVC
				.perform(get("/user/read/999999").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(user)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testRead() {

	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testDelete() {

	}
}
