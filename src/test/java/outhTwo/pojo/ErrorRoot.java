package outhTwo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRoot {
	
	
	@JsonProperty("error")
	private Error error ;
	
	
	@JsonProperty("error")
	public Error getError() {
		return error;
	}
    
	
	
	

}
