package cl.genesiscastillo.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserVO {

	@JsonProperty(required = true)
	private String name;
	
	@JsonProperty(required = true)
	private String email;
	
	@JsonProperty(required = true)
	private String password;
	 
	@JsonProperty(required = true)
	private Collection<PhoneVO> phones = new ArrayList<PhoneVO>();
	
	public User toUser(String token , Collection<Phone> phones) {
		return new User(this.name, this.email,this.password , token , phones);
	}
	
}
