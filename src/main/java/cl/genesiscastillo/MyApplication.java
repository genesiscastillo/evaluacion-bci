package cl.genesiscastillo;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import cl.genesiscastillo.service.UserServiceImpl;
import cl.genesiscastillo.vo.PhoneRequest;
import cl.genesiscastillo.vo.UserRequest;
import cl.genesiscastillo.vo.UserResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Registration API", version = "2.0", description = "Regsitro de Usuario - Generacion Token"))
@Log4j2
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
	
	@Primary
	@Bean
	public UserServiceImpl getUserService() {
		return new UserServiceImpl();
	}
	
	@Bean
	CommandLineRunner commandLineRunner(UserServiceImpl userService) {
		
		return args -> {
			PhoneRequest phone = new PhoneRequest("963","CL08","741258963");
			
			Collection<PhoneRequest> phones = new LinkedList<>();
			phones.add(phone);
			
			UserRequest userVO = new UserRequest();
			userVO.setEmail("abc@gmail.com");
			userVO.setName("cesar");
			userVO.setPassword("Ae#12305");
			userVO.setPhones(phones);
			
			UserResponse userResponse = getUserService().createUser(userVO);
			
			log.info("Se Insertó un usuario a la bd {}", userResponse);		

		};
		
	}
}
