package cl.genesiscastillo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class User {

	@Column( name = "ID")
	private String id;

	@Id
	@Column( name = "EMAIL" , unique = true)
	private String email;
	
	@Column( name = "NAME")
	private String name;
	
	@Column( name = "PASSWORD")
	private String password;
	
	@Column( name = "DATE_CREATED")
	private LocalDateTime created;
	
	@Column( name = "DATE_MODIFIED")
	private LocalDateTime modified;

	@Column( name = "DATE_LAST_LOGIN")
	private LocalDateTime lastLogin;
	
	@Column(name = "ACTIVE")
	private Boolean isactive = Boolean.TRUE;
	
	@OneToMany( targetEntity = Phone.class)
	private Collection<Phone> phones = new ArrayList<Phone>();
	
	@Column(name = "TOKEN")
	private String token;
	
	@PrePersist
	public void setDataCreated() {
		this.setId(UUID.randomUUID().toString());
		this.setCreated(LocalDateTime.now());
		this.setModified(LocalDateTime.now());
		this.setLastLogin(LocalDateTime.now());
		this.setIsactive(Boolean.TRUE);
	}
	
	public User( String _email , String _name, String _password, Collection<Phone> _phones , String _token) {
		this.setEmail(_email);
		this.setName(_name);
		this.setPassword(_password);
		this.setPhones(_phones);
		this.setToken(_token);
	}

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getName() {
//		return name;
//	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getPassword() {
//		return password;
//	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

//	public Collection<Phone> getPhones() {
//		return phones;
//	}

	public void setPhones(Collection<Phone> phones) {
		this.phones = phones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
