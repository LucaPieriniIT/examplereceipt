package xyz.pierini.examplereceipt.lib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import xyz.pierini.examplereceipt.enumerator.ProductCategoryEnum;
import xyz.pierini.examplereceipt.model.Product;

public class ReceiptCalculatorLib {

	// TODO recuperare da un JSON di configurazione, magari da DB o dal Cloud
	private static final String DEFAULT_COUNTRY = "CH";
	private static final int DEFAULT_TAX_PERCENTAGE = 10;
	private static final int FOREIGN_COUNTRY_TAX_PERCENTAGE = 5;
	private static final int RECEIPT_COUNTER_BONUS_TEXT = 10;
	private static final List<ProductCategoryEnum> CATEGORY_WITHOUT_DEFAULT_TAX = Arrays
			.asList(ProductCategoryEnum.BOOK, ProductCategoryEnum.FOOD, ProductCategoryEnum.PHARMACEUTICAL);

	public static String calculateReceipt(List<Product> selectedProducts, long receiptCounter) {
		/*
		 * Per ogni prodotto: - calcolo totale tasse in percentuale; - calcolo totale
		 * tasse su prezzo netto; - aggiorno i totali di tasse e prezzo lordo; - calcolo
		 * la dicitura da stampare per il prodotto;
		 * 
		 * Aggiungo le diciture per tasse totali e prezzo totale Eventualmente, aggiungo
		 * una frase bonus
		 */
		if (selectedProducts == null || selectedProducts.isEmpty() || receiptCounter < 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		BigDecimal totalTaxes = new BigDecimal(0.00);
		BigDecimal totalPrice = new BigDecimal(0.00);
		for (Product p : selectedProducts) {
			int taxPercentage = calculateTaxPercentage(p);
			BigDecimal productTax = calculateTax(p.getNetPrice(), taxPercentage);
			totalTaxes = totalTaxes.add(productTax);
			BigDecimal productPriceWithTax = p.getNetPrice().add(productTax);
			totalPrice = totalPrice.add(productPriceWithTax);
			sb.append(getProductReceiptText(p, productPriceWithTax));
		}
		sb.append(getTotalTaxText(totalTaxes));
		sb.append(getTotalPriceText(totalPrice));
		if (checkIfAddBonusText(receiptCounter)) {
			sb.append(getBonusText());
		}
		return sb.toString();
	}

	private static int calculateTaxPercentage(Product p) {
		int taxPercentage = 0;
		// aggiungo DEFAULT_TAX_PERCENTAGE se non fa parte delle categorie escluse
		if (!CATEGORY_WITHOUT_DEFAULT_TAX.contains(p.getCategory())) {
			taxPercentage += DEFAULT_TAX_PERCENTAGE;
		}
		// aggiungo FOREIGN_COUNTRY_TAX_PERCENTAGE se estero
		if (!p.getCountryOrigin().equals(DEFAULT_COUNTRY)) {
			taxPercentage += FOREIGN_COUNTRY_TAX_PERCENTAGE;
		}
		return taxPercentage;
	}

	private static BigDecimal calculateTax(BigDecimal netPrice, int taxPercentage) {
		// netPrice / 100 * taxPercentage
		// 2 decimali
		return (netPrice.divide(new BigDecimal(100)).multiply(new BigDecimal(taxPercentage))).setScale(2,
				RoundingMode.CEILING);
	}

	private static String getProductReceiptText(Product product, BigDecimal productPriceWithTax) {
		// ad es. Il signore degli anelli - Libro: 13.12\n
		return product.getName() + " - " + product.getCategory().getName() + ": "
				+ productPriceWithTax.setScale(2, RoundingMode.CEILING) + System.lineSeparator();
	}

	private static String getTotalTaxText(BigDecimal totalTax) {
		return "TAXES: " + totalTax.setScale(2, RoundingMode.CEILING) + System.lineSeparator();
	}

	private static String getTotalPriceText(BigDecimal totalPrice) {
		return "TOTAL: " + totalPrice.setScale(2, RoundingMode.CEILING);
	}

	private static boolean checkIfAddBonusText(long receiptCounter) {
		// Ogni RECEIPT_COUNTER_BONUS_TEXT ricevute, stampo una frase bonus
		return receiptCounter != 0 && (receiptCounter % RECEIPT_COUNTER_BONUS_TEXT) == 0;
	}

	private static String getBonusText() {
		String randomName = RandomNameLib.getRandomName();
		if (randomName == null) {
			return null;
		}
		return System.lineSeparator() + "Se ti chiami " + randomName + " hai vinto un buono da 50 euro";
	}

}
