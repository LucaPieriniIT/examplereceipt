package xyz.pierini.examplereceipt.http.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Wrapper principale della risposta di RandomNameService
 * @author lucapierini
 */
public class RandomUserWrapper {

	private List<RandomUserResult> results;

	public List<RandomUserResult> getResults() {
		return results;
	}

	public void setResults(List<RandomUserResult> results) {
		this.results = results;
	}

	public RandomUserResult getFirstResult() {
		return this.results == null || this.results.isEmpty() ? null : this.results.get(0);
	}

	public String getFirstNameOfFirstResult() {
		RandomUserResult result = getFirstResult();
		if (result == null) {
			return null;
		}
		return result.getFirstName();
	}

}
