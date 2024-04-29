package cl.genesiscastillo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cl.genesiscastillo.entity.Phone;

public class PhoneTest {
	
	static final Long PHONE_ID = 963l;
	static final String NUMBER = "9951817XX";
	static final String CITY_CODE = "RM";
	static final String COUNTRY_CODE = "CL"; 
	
	@Test
	public void validateNotNullPhoneToString() {
		Phone phone = new Phone(NUMBER , CITY_CODE , COUNTRY_CODE);
		Assertions.assertNotNull(phone.toString());
	}

	@Test
	public void createNewPhone() {
		Phone phone = new Phone(NUMBER , CITY_CODE , COUNTRY_CODE);
		Assertions.assertNotNull(phone);
		Assertions.assertNotNull(phone.getCitycode());
		Assertions.assertNotNull(phone.getCountrycode());
		Assertions.assertNotNull(phone.getNumber());
		
		//phone.setPhoneId(963l);
		Assertions.assertNotNull(phone);
		//Assertions.assertEquals(PHONE_ID, phone.getPhoneId());
	}
	
	@Test
	public void createNewOtherPhone() {
//		Phone phone = new Phone();
//		//phone.setPhoneId(PHONE_ID);
//		phone.setNumber(NUMBER);
//		phone.setCountrycode(COUNTRY_CODE);
//		phone.setCitycode(CITY_CODE);
//		
//		Assertions.assertNotNull(phone);
	}
}
