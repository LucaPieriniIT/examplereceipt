package xyz.pierini.examplereceipt.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import xyz.pierini.examplereceipt.ExamplereceiptApplication;
import xyz.pierini.examplereceipt.http.model.RandomUserWrapper;

public class RandomNameLib {

	private static final Logger log = LoggerFactory.getLogger(ExamplereceiptApplication.class);

	// TODO recuperare da un JSON di configurazione, magari da DB o dal Cloud
	private static final String RANDOM_NAME_URL = "https://randomuser.me/api";

	public static String getRandomName() {
		try {
			RandomUserWrapper result = callUrlForRandomName();
			return parseRandomNameResult(result);
		} catch (Exception ex) {
			log.error("Error getting randomuser data");
			return null;
		}
	}

	private static RandomUserWrapper callUrlForRandomName() {
		RestTemplate restTemplate = new RestTemplate();
		RandomUserWrapper result = restTemplate.getForObject(RANDOM_NAME_URL, RandomUserWrapper.class);
		return result;
	}

	private static String parseRandomNameResult(RandomUserWrapper result) {
		if (result == null) {
			log.error("Error mapping randomuser data");
			return null;
		}
		String randomName = result.getFirstNameOfFirstResult();
		if (randomName == null) {
			log.error("Error reading randomuser data");
		}
		return randomName;
	}

}
