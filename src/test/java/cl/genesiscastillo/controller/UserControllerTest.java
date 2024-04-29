package cl.genesiscastillo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.genesiscastillo.builders.UserBuilder;
import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.service.UserServiceImpl;
import cl.genesiscastillo.vo.PhoneRequest;
import cl.genesiscastillo.vo.UserRequest;
import cl.genesiscastillo.vo.UserResponse;

@WebMvcTest(UserController.class)
class UserControllerTest {

	static final String USER_UUID = "67c26dfb-63ee-4ebc-b869-57bb3194af6e";
	static final String USER_NAME = "cesar castillo";
	static final String USER_EMAIL = "cepija6292@kaftee.com"; 	
	static final String USER_PASSWORD = "6oR9pSPj!Xg1";
	static final String USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRta";
	static final Collection<Phone> USER_PHONES = Collections.emptyList();
	
	static final String PHONE_NUMBER = "99999999";
	static final String PHONE_CITY_CODE = "01";
	static final String PHONE_COUNTRY_CODE = "56";
	
	UserRequest userRequest = new UserRequest();
	Collection<PhoneRequest> phoneVOs = new ArrayList<PhoneRequest>();
	User user = new User();
	UserResponse userResponse = new UserResponse(USER_UUID, PHONE_NUMBER, PHONE_COUNTRY_CODE, PHONE_CITY_CODE, USER_TOKEN, null);
	List<User> userList = new ArrayList<User>();
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;	
	
	@MockBean
	UserServiceImpl userServiceMocked;
	
	@BeforeEach
	public void init() {

		PhoneRequest phoneVO = new PhoneRequest(PHONE_NUMBER, PHONE_CITY_CODE, PHONE_COUNTRY_CODE);
		Assertions.assertNotNull(phoneVO);
		Assertions.assertNotNull(phoneVO.getCitycode());
		Assertions.assertNotNull(phoneVO.getCountrycode());
		Assertions.assertNotNull(phoneVO.getNumber());
		phoneVOs.add(phoneVO);		

		userRequest.setEmail(USER_EMAIL);
		userRequest.setName(USER_NAME);
		userRequest.setPassword(USER_PASSWORD);
		userRequest.setPhones( phoneVOs );
		Assertions.assertNotNull(userRequest.getPassword());
		
		user = new UserBuilder().setEmail(userRequest.getEmail()).setName(userRequest.getName())
				.setPassword(userRequest.getPassword()).setToken("123456789").setPhones(new ArrayList<Phone>())
				.build();
		user.setId("8aba4128-287f-41b7-823d-a76d9253f765");
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		
		userList.add(user);

	}

	@Test
	void testPostUser() throws Exception {
		List<Phone> phonesReg = new ArrayList<>();
		userRequest.getPhones().forEach((phoneVO2) -> {
			Phone phone = new Phone();
			phonesReg.add(phone);
		});
		
		given(userServiceMocked.createUser(userRequest)).willReturn(userResponse);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(userRequest));
		
		this.mockMvc.perform( request )
			.andExpect(status().isOk());
	}

	@Test
	void testGetUser() throws Exception {
		given(userServiceMocked.findById("8aba4128-287f-41b7-823d-a76d9253f765")).willReturn(userResponse );
		this.mockMvc.perform(get("/api/users/8aba4128-287f-41b7-823d-a76d9253f765"))
		.andExpect(status().isOk());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	void testGetAllUser() throws Exception {
		given(userServiceMocked.findAllUser()).willReturn(new ArrayList());
		this.mockMvc.perform(get("/api/users"))
		.andExpect(status().isNoContent());
	}

	@Test
	void testGetAllUser2() throws Exception {
		given(userServiceMocked.findAllUser()).willReturn(userList);
		this.mockMvc.perform(get("/api/users"))
		.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateUSer() throws Exception {
		 RequestBuilder request = MockMvcRequestBuilders
			        .put("/api/users/8aba4128-287f-41b7-823d-a76d9253f765")
			        .contentType(MediaType.APPLICATION_JSON)
			        .content(objectMapper.writeValueAsBytes(userRequest));
		 
		 given(userServiceMocked.updateUser("8aba4128-287f-41b7-823d-a76d9253f765", userRequest)).willReturn(userResponse);
		 
		this.mockMvc.perform(request)
		.andExpect(status().isOk());
	}

	
	@Test
	void testDeleteUser() throws Exception {
		 RequestBuilder request = MockMvcRequestBuilders
			        .delete("/api/users/8aba4128-287f-41b7-823d-a76d9253f765");
		 
		this.mockMvc.perform(request)
		.andExpect(status().isOk());
	}

}
