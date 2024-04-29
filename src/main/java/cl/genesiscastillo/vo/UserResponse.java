package cl.genesiscastillo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {
	
//	public UserResponse() {
//		super();
//	}
	
	public UserResponse(String id, String created, String modified, String lastLogin, String token, Boolean isactive) {
		this.setId(id);
		this.setCreated(created);
		this.setModified(modified);
		this.setLastLogin(lastLogin);
		this.setToken( token);
		this.setIsactive( isactive);
	}
	
	private String id;
	private String created;
	private String modified;
	@JsonProperty(value = "last_login")
	private String lastLogin;
	private String token;
	private Boolean isactive;

}
