package cl.genesiscastillo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table( name = "PHONE")
public class Phone {
	
	public Phone(String number, String cityCode, String countryCode) {
		this.setNumber(number);
		this.setCitycode(cityCode);
		this.setCountrycode(countryCode); 
	}
	
	@Id
	@Column(name="NUMBER")
	private String number;
	
	@Column(name="CYTY_CODE")
	private String citycode;
	
	@Column(name="COUNTRY_CODE")
	private String countrycode;

}
