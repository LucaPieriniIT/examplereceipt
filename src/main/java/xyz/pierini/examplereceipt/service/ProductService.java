package xyz.pierini.examplereceipt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.pierini.examplereceipt.model.Product;
import xyz.pierini.examplereceipt.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public List<Product> findAllById(List<Long> ids) {
		return productRepository.findAllById(ids);
	}

}
