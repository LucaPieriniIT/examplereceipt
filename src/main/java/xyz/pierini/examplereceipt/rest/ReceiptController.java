package xyz.pierini.examplereceipt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.pierini.examplereceipt.rest.model.CalculatedReceipt;
import xyz.pierini.examplereceipt.rest.model.SelectedProducts;
import xyz.pierini.examplereceipt.service.ReceiptService;

@RestController
public class ReceiptController {

	@Autowired
	private ReceiptService receiptService;

	@PostMapping("/receipt/calculate")
	/**
	 * @param selectedProducts Prodotti selezionati (scelti tramite ID)
	 * @return Testo della ricevuta calcolata
	 */
	public CalculatedReceipt calculate(@RequestBody SelectedProducts selectedProducts) throws Exception {
		return receiptService.calculate(selectedProducts);
	}

}
