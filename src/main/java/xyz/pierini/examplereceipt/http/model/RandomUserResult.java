package xyz.pierini.examplereceipt.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Usate per mappare la risposta di RandomNameService
 * @author lucapierini
 */
public class RandomUserResult {

	private RandomUserName name;

	public RandomUserName getName() {
		return name;
	}

	public void setName(RandomUserName name) {
		this.name = name;
	}
	
	public String getFirstName() {
		return this.name == null ? null : this.name.getFirst();
	}

}
