package xyz.pierini.examplereceipt.http.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RandomUserWrapperTest {

	@Test
	public void shouldReturnResultNull() {
		RandomUserWrapper wrapper = new RandomUserWrapper();

		assertThat(wrapper.getResults()).isNull();
		assertThat(wrapper.getFirstResult()).isNull();
		assertThat(wrapper.getFirstNameOfFirstResult()).isNull();
	}

	@Test
	public void shouldReturnResultNullForEmptyList() {
		RandomUserWrapper wrapper = new RandomUserWrapper();
		wrapper.setResults(new ArrayList<>());

		assertThat(wrapper.getFirstResult()).isNull();
		assertThat(wrapper.getFirstNameOfFirstResult()).isNull();
	}

	@Test
	public void shouldReturnFirstName() {
		final String myName = "Luca";
		RandomUserName name = new RandomUserName();
		name.setFirst(myName);

		RandomUserResult result = new RandomUserResult();
		result.setName(name);

		RandomUserWrapper wrapper = new RandomUserWrapper();
		List<RandomUserResult> results = new ArrayList<>();
		results.add(result);
		wrapper.setResults(results);

		assertThat(wrapper.getFirstResult()).isNotNull();
		assertThat(wrapper.getFirstNameOfFirstResult()).isEqualTo(myName);
	}

}
