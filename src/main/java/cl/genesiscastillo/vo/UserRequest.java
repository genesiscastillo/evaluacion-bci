package cl.genesiscastillo.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	
	@JsonProperty(required = true)
	private String name;
	
	@JsonProperty(required = true)
	private String email;
	
	@JsonProperty(required = true)
	private String password;
	 
	@JsonProperty(required = true)
	private Collection<PhoneRequest> phones = new ArrayList<PhoneRequest>();
	
}
