package xyz.pierini.examplereceipt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.pierini.examplereceipt.model.Product;
import xyz.pierini.examplereceipt.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product/all")
	/**
	 * @return Tutti i prodotti presenti nel database
	 */
	public List<Product> findAll() {
		return productService.findAll();
	}

}
