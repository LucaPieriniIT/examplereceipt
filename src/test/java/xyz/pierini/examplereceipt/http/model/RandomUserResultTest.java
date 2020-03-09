package xyz.pierini.examplereceipt.http.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RandomUserResultTest {

	@Test
	public void shouldReturnFirstNameNull() {
		RandomUserResult result = new RandomUserResult();
		assertThat(result.getFirstName()).isNull();
	}

	@Test
	public void shouldReturnFirstName() {
		final String myName = "Luca";
		RandomUserName name = new RandomUserName();
		name.setFirst(myName);

		RandomUserResult result = new RandomUserResult();
		result.setName(name);

		assertThat(result.getFirstName()).isEqualTo(myName);
	}

}
