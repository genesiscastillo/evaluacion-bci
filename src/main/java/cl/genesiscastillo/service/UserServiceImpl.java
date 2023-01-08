package cl.genesiscastillo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.NotFoundUserByEmailException;
import cl.genesiscastillo.repository.PhoneRepository;
import cl.genesiscastillo.repository.UserRepository;
import cl.genesiscastillo.vo.UserVO;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService  {
	
	@Setter
	@Autowired
	PhoneRepository phoneRepository;

	@Setter
	@Autowired
	UserRepository userRepsoitory;
	
	@Value("${app.security.jwt.expirationTime}")
	String expirationTime;
	
	@Value("${app.security.jwt.secretKey}")
	String secretKey;

	@Override
	public User saveUser(UserVO userVO) {
		
		String token = getToken(userVO.getName(), userVO.getEmail());
		
		List<Phone> phonesReg = new ArrayList<>();
		userVO.getPhones().forEach((phoneVO) -> {
			Phone phone =	phoneRepository.save( phoneVO.toPhone() );
			log.info("add phone {}", phone);
			phonesReg.add(phone);
		});
		User user = userVO.toUser(token , phonesReg);
		log.info("pre user {}", user);		
		user = userRepsoitory.save(user);
		log.info("add user {}", user);		
		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) throws NotFoundUserByEmailException {
		log.info("findByEmail: {}", email);		
		User user = userRepsoitory.findByEmail(email);
		log.info("user: {}", user);
		if(user == null )
			throw new NotFoundUserByEmailException(email);
		return Optional.of(user);
	}

	protected String getToken(@NotNull String username , @NotNull String email) {
		log.debug("getToken secretKey=  {}",secretKey);
		Instant now = Instant.now();		
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		Date dateExpired = Date.from(now.plus(Long.parseLong(expirationTime.trim()), ChronoUnit.SECONDS));

	    return JWT.create()
	            .withSubject("authentication")
	            .withClaim("name", username)
	            .withClaim("email", email )
	            .withExpiresAt(dateExpired )
	            .sign(algorithm);
	}


}
