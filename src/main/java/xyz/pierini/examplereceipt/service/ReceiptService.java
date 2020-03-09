package xyz.pierini.examplereceipt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.pierini.examplereceipt.lib.ReceiptCalculatorLib;
import xyz.pierini.examplereceipt.model.Product;
import xyz.pierini.examplereceipt.model.Receipt;
import xyz.pierini.examplereceipt.repository.ReceiptRepository;
import xyz.pierini.examplereceipt.rest.model.CalculatedReceipt;
import xyz.pierini.examplereceipt.rest.model.SelectedProducts;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;

	@Autowired
	private ProductService productService;

	public CalculatedReceipt calculate(SelectedProducts selectedProducts) {
		List<Product> foundProducts = productService.findAllById(selectedProducts.getProductIds());
		String receiptText = calculateAndPersistReceipt(foundProducts);
		return new CalculatedReceipt(receiptText);
	}

	private String calculateAndPersistReceipt(List<Product> selectedProducts) {
		/*
		 * il +1 è dovuto al fatto che la count() viene fatta prima della save(). Si
		 * potrebbe invertire l'ordine delle funzioni ma poi salverei una Receipt anche
		 * se il calcolo va in errore
		 */
		String receiptText = ReceiptCalculatorLib.calculateReceipt(selectedProducts, receiptRepository.count() + 1);
		persistNewReceipt();
		return receiptText;
	}

	private void persistNewReceipt() {
		// persisto per tenere il conteggio delle ricevute calcolate
		// così da stampare la frase bonus ogni RECEIPT_COUNTER_BONUS_TEXT
		Receipt receipt = new Receipt();
		receiptRepository.save(receipt);
	}

}
