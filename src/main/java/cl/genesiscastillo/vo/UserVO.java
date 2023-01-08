package cl.genesiscastillo.vo;

import java.util.ArrayList;
import java.util.Collection;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserVO {

	@NotEmpty(message = "The name is required.")
	private String name;
	
	@NotEmpty(message = "The email is required.")
	private String email;
	
	@NotEmpty(message = "The password is required.")
	private String password;
	 
	private Collection<PhoneVO> phones = new ArrayList<PhoneVO>();
	
	public User toUser(String token , Collection<Phone> phones) {
		return new User(this.name, this.email,this.password , token , phones);
	}
	
}
