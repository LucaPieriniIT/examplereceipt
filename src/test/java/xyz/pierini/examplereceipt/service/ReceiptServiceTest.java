package xyz.pierini.examplereceipt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.pierini.examplereceipt.enumerator.ProductCategoryEnum;
import xyz.pierini.examplereceipt.model.Product;
import xyz.pierini.examplereceipt.repository.ReceiptRepository;
import xyz.pierini.examplereceipt.rest.model.CalculatedReceipt;
import xyz.pierini.examplereceipt.rest.model.SelectedProducts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptServiceTest {

	@Mock
	private ProductService productService;

	@Mock
	private ReceiptRepository receiptRepository;

	@InjectMocks
	private ReceiptService receiptService;

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

		List<Long> ids = new ArrayList<>();
		ids.add(new Long(1));
		ids.add(new Long(2));
		Mockito.when(productService.findAllById(ids)).thenReturn(products);

		Mockito.when(receiptRepository.count()).thenReturn(new Long(3));
	}

	@Test
	public void shouldReturnCalculatedReceipt() throws Exception {
		List<Long> ids = new ArrayList<>();
		ids.add(new Long(1));
		ids.add(new Long(2));

		SelectedProducts selectedProducts = new SelectedProducts();
		selectedProducts.setProductIds(ids);

		CalculatedReceipt calculatedReceipt = receiptService.calculate(selectedProducts);
		assertThat(calculatedReceipt).isNotNull();
		assertThat(calculatedReceipt.getReceiptText()).isNotBlank();

		String expectedReceiptText = "Test - Farmaco: 100.00\n" + "Test2 - Libro: 52.50\n" + "TAXES: 2.50\n"
				+ "TOTAL: 152.50";
		assertThat(calculatedReceipt.getReceiptText()).isEqualTo(expectedReceiptText);
	}

}
