package cl.genesiscastillo;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.service.UserService;
import cl.genesiscastillo.service.UserServiceImpl;
import cl.genesiscastillo.vo.PhoneVO;
import cl.genesiscastillo.vo.UserVO;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Registration API", version = "2.0", description = "Regsitro de Usuario - Generacion Token"))
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
	
	@Primary
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl();
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService) {
		
		return args -> {
			PhoneVO phone = new PhoneVO("963","CL08","741258963");
			
			Collection<PhoneVO> phones = new LinkedList<>();
			phones.add(phone);
			
			UserVO userVO = new UserVO();
			userVO.setEmail("abc@gmail.com");
			userVO.setName("cesar");
			userVO.setPassword("abc123");
			userVO.setPhones(phones);
			
			User user = 	userService.saveUser(userVO);
			
			System.out.println(user);
			
			
		};
		
	}
}
