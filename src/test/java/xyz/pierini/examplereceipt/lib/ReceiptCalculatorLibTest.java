package xyz.pierini.examplereceipt.lib;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import xyz.pierini.examplereceipt.enumerator.ProductCategoryEnum;
import xyz.pierini.examplereceipt.model.Product;

public class ReceiptCalculatorLibTest {

	@Test
	public void calculateReceiptReturnNull() {
		assertThat(ReceiptCalculatorLib.calculateReceipt(null, 5)).isNull();
		assertThat(ReceiptCalculatorLib.calculateReceipt(new ArrayList<Product>(), 5)).isNull();
		assertThat(ReceiptCalculatorLib.calculateReceipt(new ArrayList<Product>(), -1)).isNull();
		List<Product> products = new ArrayList<>();
		Product p = new Product();
		products.add(p);
		assertThat(ReceiptCalculatorLib.calculateReceipt(products, -1)).isNull();
	}

	@Test
	public void shouldReturnRealReceipt() {
		List<Product> products = new ArrayList<>();
		Product p1 = new Product();
		p1.setCategory(ProductCategoryEnum.MUSIC);
		p1.setCountryOrigin("CH");
		p1.setName("Test music");
		p1.setNetPrice(new BigDecimal(30));

		Product p2 = new Product();
		p2.setCategory(ProductCategoryEnum.BOOK);
		p2.setCountryOrigin("IT");
		p2.setName("Test book");
		p2.setNetPrice(new BigDecimal(15.55));

		Product p3 = new Product();
		p3.setCategory(ProductCategoryEnum.PHARMACEUTICAL);
		p3.setCountryOrigin("EN");
		p3.setName("Test pharma");
		p3.setNetPrice(new BigDecimal(9.09));

		products.add(p1);
		products.add(p2);
		products.add(p3);

		String expectedReceipt = "Test music - Musica: 33.00\n" + "Test book - Libro: 16.34\n"
				+ "Test pharma - Farmaco: 9.55\n" + "TAXES: 4.24\n" + "TOTAL: 58.89";
		assertThat(ReceiptCalculatorLib.calculateReceipt(products, 0)).isEqualTo(expectedReceipt);
	}

	@Test
	public void shouldReturnRealReceiptWithBonusText() {
		List<Product> products = new ArrayList<>();
		Product p1 = new Product();
		p1.setCategory(ProductCategoryEnum.MUSIC);
		p1.setCountryOrigin("CH");
		p1.setName("Test music");
		p1.setNetPrice(new BigDecimal(30));

		products.add(p1);

		String expectedReceiptStartsWith = "Test music - Musica: 33.00\n" + 
				"TAXES: 3.00\n" + 
				"TOTAL: 33.00\n" + 
				"Se ti chiami ";
		String expectedReceiptEndsWith = " hai vinto un buono da 50 euro";
		String calculatedReceipt = ReceiptCalculatorLib.calculateReceipt(products, 10);
		assertThat(calculatedReceipt).isNotNull();
		assertThat(calculatedReceipt).startsWith(expectedReceiptStartsWith);
		assertThat(calculatedReceipt).endsWith(expectedReceiptEndsWith);
	}

}
