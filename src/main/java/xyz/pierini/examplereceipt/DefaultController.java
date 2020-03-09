package xyz.pierini.examplereceipt;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class DefaultController {

	@RequestMapping("/")
	public String index() {
		return "Welcome to Example Receipt";
	}

}