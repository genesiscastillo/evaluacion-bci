package cl.genesiscastillo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="EMPLOYEE")
public class User {
	
	public User(String name , String email, String password, String token,Collection<Phone> phones) {
		this.name = name;
		this.email = email;
		this.password =password;
		this.phones = phones;
		this.token = token;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column( name = "NAME")
	private String name;
	
	@Column( name = "EMAIL" , unique = true)
	private String email;
	
	@Column( name = "PASSWORD")
	private String password;
	
	@Column( name = "DATE_CREATED")
	private LocalDateTime created;
	
	@Column( name = "DATE_MODIFIED")
	private LocalDateTime modified;

	@Column( name = "DATE_LAST_LOGIN")
	private LocalDateTime last_login;
	
	@Column(name = "ACTIVE")
	private Boolean isactive = Boolean.TRUE;
	
	@OneToMany( targetEntity = Phone.class)
	private Collection<Phone> phones = new ArrayList<Phone>();
	
	@Column(name = "TOKEN")
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Phone> getPhones() {
		return phones;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	@PrePersist
	public void setCreated() {
		LocalDateTime localDateTime = LocalDateTime.now();
		this.created = localDateTime;
		setLast_login(localDateTime);
	}

	public LocalDateTime getModified() {
		return modified;
	}

	@PreUpdate
	public void setModified() {
		this.modified = LocalDateTime.now();
	}

	public void setPhones(Collection<Phone> phones) {
		this.phones = phones;
	}

	public LocalDateTime getLast_login() {
		return last_login;
	}

	public void setLast_login(LocalDateTime last_login) {
		this.last_login = last_login;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive ) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", created="
				+ created + ", modified=" + modified + ", last_login=" + last_login + ", isactive=" + isactive
				+ ", phones=" + phones + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
