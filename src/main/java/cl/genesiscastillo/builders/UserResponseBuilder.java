package cl.genesiscastillo.builders;

import cl.genesiscastillo.vo.UserResponse;

public class UserResponseBuilder{
	private String id;
	private String created;
	private String modified;
	private String lastLogin;
	private String token;
	private Boolean isactive;
	
	public UserResponseBuilder setId(String id) {
		this.id = id;
		return this;
	}
	public UserResponseBuilder setCreated(String created) {
		this.created = created;
		return this;
	}
	public UserResponseBuilder setModified(String modified) {
		this.modified = modified;
		return this;
	}
	public UserResponseBuilder setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}
	public UserResponseBuilder setToken(String token) {
		this.token = token;
		return this;
	}
	public UserResponseBuilder setIsactive(Boolean isactive) {
		this.isactive = isactive;
		return this;
	}
	
	public UserResponse build() {
		return new UserResponse(this.id, this.created, this.modified,this.lastLogin,this.token,this.isactive);
	}

}
