package cl.genesiscastillo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table( name = "PHONE")
public class Phone {
	
	public Phone(String number, String cityCode, String countryCode) {
		this.number = number;
		this.citycode = cityCode;
		this.countrycode = countryCode; 
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long phoneId;
	
	@Column(name="NUMBER")
	private String number;
	
	@Column(name="CYTY_CODE")
	private String citycode;
	
	@Column(name="COUNTRY_CODE")
	private String countrycode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	@Override
	public String toString() {
		return "Phone [phoneId=" + phoneId + ", number=" + number + ", citycode=" + citycode + ", countrycode="
				+ countrycode + "]";
	}
	
}
