package cl.genesiscastillo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.genesiscastillo.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneVO {
	
	@JsonProperty(required = true)
	private String number;
	
	@JsonProperty(required = true)
	private String citycode;
	
	@JsonProperty(required = true)
	private String countrycode;
	
	public Phone toPhone() {
		return new Phone(this.number, this.citycode, this.countrycode);
	}
}
