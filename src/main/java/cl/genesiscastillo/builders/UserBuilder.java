package cl.genesiscastillo.builders;

import java.util.ArrayList;
import java.util.Collection;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;

public class UserBuilder {
	private String email;
	private String name;
	private String password;
	private String token;
	private Collection<Phone> phones = new ArrayList<Phone>();

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder setToken(String token) {
		this.token = token;
		return this;
	}

	public UserBuilder setPhones(Collection<Phone> phones) {
		this.phones = phones;
		return this;
	}

	public User build() {
		return new User(this.email, this.name, this.password, this.phones , this.token);
	}

}
