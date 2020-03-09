package xyz.pierini.examplereceipt.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Usate per mappare la risposta di RandomNameService
 * @author lucapierini
 */
public class RandomUserName {

	private String first;

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}
}
