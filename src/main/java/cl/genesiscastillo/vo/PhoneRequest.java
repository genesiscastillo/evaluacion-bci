package cl.genesiscastillo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.genesiscastillo.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
	
	@JsonProperty(required = true)
	private String number;
	
	@JsonProperty(required = true)
	private String citycode;
	
	@JsonProperty(required = true)
	private String countrycode;
	
	public Phone toEntity() {
		return new Phone(this.number, this.citycode, this.countrycode);
	}
}
