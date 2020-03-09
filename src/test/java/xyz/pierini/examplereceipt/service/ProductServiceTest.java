package xyz.pierini.examplereceipt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.pierini.examplereceipt.enumerator.ProductCategoryEnum;
import xyz.pierini.examplereceipt.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Mock
	private ProductService productService;

	@BeforeEach
	public void setUp() {
		List<Product> products = new ArrayList<>();
		Product p1 = new Product();
		p1.setCategory(ProductCategoryEnum.PHARMACEUTICAL);
		p1.setCountryOrigin("CH");
		p1.setId(new Long(1));
		p1.setName("Test");
		p1.setNetPrice(new BigDecimal(100));

		Product p2 = new Product();
		p2.setCategory(ProductCategoryEnum.BOOK);
		p2.setCountryOrigin("IT");
		p2.setId(new Long(2));
		p2.setName("Test2");
		p2.setNetPrice(new BigDecimal(50));

		products.add(p1);
		products.add(p2);

		Mockito.when(productService.findAll()).thenReturn(products);
		List<Long> ids = new ArrayList<>();
		ids.add(new Long(1));
		ids.add(new Long(2));
		Mockito.when(productService.findAllById(ids)).thenReturn(products);
	}

	@Test
	public void shouldReturnAllProducts() {
		List<Product> products = productService.findAll();
		assertThat(products).isNotNull();
		assertThat(products).hasSize(2);
		assertThat(products.get(0).getName()).isEqualTo("Test");
	}

	@Test
	public void shouldReturnProductsByIds() {
		List<Long> ids = new ArrayList<>();
		ids.add(new Long(1));
		ids.add(new Long(2));
		List<Product> products = productService.findAllById(ids);
		assertThat(products).isNotNull();
		assertThat(products).hasSize(2);
		assertThat(products.get(0).getName()).isEqualTo("Test");
	}

}
