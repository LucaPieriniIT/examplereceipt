package xyz.pierini.examplereceipt.rest.model;

public class CalculatedReceipt {

	private String receiptText;

	public String getReceiptText() {
		return receiptText;
	}

	public void setReceiptText(String receiptText) {
		this.receiptText = receiptText;
	}

	public CalculatedReceipt(String receiptText) {
		this.receiptText = receiptText;
	}

}
