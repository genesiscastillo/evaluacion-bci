package cl.genesiscastillo.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;

public class UserTest {
	
	static final String USER_UUID = "67c26dfb-63ee-4ebc-b869-57bb3194af6e";
	static final String USER_NAME = "cesar castillo";
	static final String USER_EMAIL = "cepija6292@kaftee.com"; 	
	static final String USER_PASSWORD = "6oR9pSPj!Xg1";
	static final String USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRta";
	static final Collection<Phone> USER_PHONES = Collections.emptyList();
	
	@Test
	public void validateNotNullUserToString() {
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN ,USER_PHONES);
		//Assertions.assertNotNull(user.toString());
	}
	
	@Test
	public void testCountPhoneValidateUser() {
		Phone phone = new Phone("abc","123","XYZ");		
		Collection<Phone> phones = new LinkedList<>();
		phones.add(phone);
		
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN  , phones );
//		
//		Assertions.assertEquals(1 , user.getPhones().size());
	}
	
	@Test
	public void testUUIDValidateUser() {
//		User user = new User();
//		user.setId(USER_UUID);
//		user.setPhones(USER_PHONES);
//		user.setToken(USER_TOKEN);
//		Assertions.assertEquals(USER_UUID, user.getId());
//		Assertions.assertEquals(USER_PHONES, user.getPhones());
//		Assertions.assertEquals(USER_TOKEN, user.generateToken());
	}

	@Test
	public void testDataValidateUser() {
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN ,USER_PHONES);
//		
//		Assertions.assertEquals(USER_NAME, user.getName());
//		Assertions.assertEquals(USER_EMAIL, user.getEmail());
//		Assertions.assertEquals(USER_TOKEN, user.generateToken());
//		Assertions.assertEquals(USER_PASSWORD, user.getPassword());
//		Assertions.assertEquals(USER_PHONES, user.getPhones());
	}

}
