package cl.genesiscastillo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cl.genesiscastillo.entity.Phone;

@DataJpaTest
public class PhoneRepositoryTest {
	
	static final String PHONE_NUMBER = "99999999";
	static final String PHONE_CITY_CODE = "01";
	static final String PHONE_COUNTRY_CODE = "56";
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	PhoneRepository phoneRepository;
	
	@Test
	public void createNewPhone() {
		Phone phone = new Phone(PHONE_NUMBER, PHONE_CITY_CODE, PHONE_COUNTRY_CODE);
		
		phone = phoneRepository.save(phone);
		
		Assertions.assertNotNull(phone);
		Assertions.assertNotNull(phone.getNumber());
		Assertions.assertNotNull(phone.getCountrycode());
		Assertions.assertNotNull(phone.getCitycode());
	}

}
