package cl.genesiscastillo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cl.genesiscastillo.builders.UserBuilder;
import cl.genesiscastillo.builders.UserResponseBuilder;
import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.ErrorBussinesException;
import cl.genesiscastillo.repository.PhoneRepository;
import cl.genesiscastillo.repository.UserRepository;
import cl.genesiscastillo.vo.UserRequest;
import cl.genesiscastillo.vo.UserResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl {

	final String emailAddressExpReg= "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

	@Setter
	@Autowired
	PhoneRepository phoneRepository;

	@Setter
	@Autowired
	UserRepository userRepsoitory;

	String secretKey = "mySecretKey";
	
	@Setter
	@Value("${app.expreg.password.user}")
	String passwordExpReg;

	@Transactional
	public UserResponse createUser(UserRequest userRequest) throws ErrorBussinesException {
		
		validateFormatEmail(userRequest.getEmail());

		validateExistEmail( userRequest.getEmail());
		
		validatePassword( userRequest.getPassword());
			
		List<Phone> phonesReg = new ArrayList<>();
		userRequest.getPhones().forEach((phoneVO) -> {
			Phone phone = phoneRepository.saveAndFlush(phoneVO.toEntity());
			log.info("add phone {}", phone);
			phonesReg.add(phone);
		});
		User user = new UserBuilder().setEmail(userRequest.getEmail()).setName(userRequest.getName())
				.setPassword(userRequest.getPassword()).setToken(generateToken(userRequest)).setPhones(phonesReg)
				.build();

		user = userRepsoitory.save(user);
		
		log.info("add user {}", user);

		UserResponse userResponse = new UserResponseBuilder().setId(user.getId())
				.setCreated(user.getCreated().toString()).setModified(user.getModified().toString())
				.setLastLogin(user.getLastLogin().toString()).setToken(user.getToken()).setIsactive(user.getIsactive())
				.build();

		return userResponse;
	}

	public List<User> findAllUser() {
		return userRepsoitory.findAll();
	}

	public UserResponse findById(String id) throws ErrorBussinesException {
		User user = userRepsoitory.findById(id)
				.orElseThrow(() -> new ErrorBussinesException("Usuario no encontrado"));

		UserResponse userResponse = new UserResponseBuilder().setId(user.getId())
				.setCreated(user.getCreated().toString()).setModified(user.getModified().toString())
				.setLastLogin(user.getLastLogin().toString()).setToken(user.getToken()).setIsactive(user.getIsactive())
				.build();
		return userResponse;
	}
	
	public UserResponse updateUser(String id , UserRequest userRequest) throws ErrorBussinesException {
		
		List<Phone> phonesReg = new ArrayList<>();
		userRequest.getPhones().forEach((phoneVO) -> {
			Phone phone = phoneRepository.saveAndFlush(phoneVO.toEntity());
			phonesReg.add(phone);
		});
		
		User user = userRepsoitory.findById(id)
				.map( userId -> {
					userId.setPassword(userRequest.getPassword());
					userId.setToken(generateToken(userRequest));
					userId.setPhones(phonesReg);
					userId.setModified(LocalDateTime.now());
					return userRepsoitory.save(userId);
				})
				.orElseThrow(() -> new ErrorBussinesException("Usuario ID no encontrado"));
		
		UserResponse userResponse = new UserResponseBuilder().setId(user.getId())
				.setCreated(user.getCreated().toString()).setModified(user.getModified().toString())
				.setLastLogin(user.getLastLogin().toString()).setToken(user.getToken()).setIsactive(user.getIsactive())
				.build();
		
		return userResponse;
	}
	
	public void deleteUserById(String id) throws ErrorBussinesException {
		User user = userRepsoitory.findById(id)
		.orElseThrow(() -> new ErrorBussinesException("Usuario ID no encontrado"));
		userRepsoitory.delete(user);
	}

	protected String generateToken(UserRequest userRequest) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		return JWT.create().withSubject("authentication").withClaim("name", userRequest.getName())
				.withClaim("email", userRequest.getEmail()).sign(algorithm);
	}

	protected void validateExistEmail(String email) throws ErrorBussinesException {
		if(userRepsoitory.existsById(email)) {
			throw new ErrorBussinesException("Correo ya registrado");
		}
	}
	
	protected void validateFormatEmail(String email) throws ErrorBussinesException {
		Pattern patternEmailAddres = Pattern.compile(emailAddressExpReg ,Pattern.CASE_INSENSITIVE);		
	    if(patternEmailAddres.matcher(email).matches() == false) {
	    	throw new ErrorBussinesException(String.format("Correo invalido %s", email));
	    }
	}
	
	protected void validatePassword(String password) throws ErrorBussinesException {
		Pattern patternPassword = Pattern.compile(passwordExpReg,Pattern.CASE_INSENSITIVE);		
	    if(patternPassword.matcher(password).matches() == false) {
	    	throw new ErrorBussinesException("Password Invalido no cumple con las condiciones");
	    }
	}

}
