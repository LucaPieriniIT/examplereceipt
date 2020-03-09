package xyz.pierini.examplereceipt.lib;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import xyz.pierini.examplereceipt.lib.RandomNameLib;

public class RandomNameLibTest {

	@Test
	public void shouldReturnRandomNameString() {
		assertThat(RandomNameLib.getRandomName()).isNotNull();
	}

}
