package cl.genesiscastillo.vo;

import cl.genesiscastillo.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneVO {
	private String number;
	private String citycode;
	private String countrycode;
	
	public Phone toPhone() {
		return new Phone(this.number, this.citycode, this.countrycode);
	}
}
