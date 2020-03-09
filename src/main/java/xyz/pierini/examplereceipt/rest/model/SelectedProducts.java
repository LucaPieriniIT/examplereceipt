package xyz.pierini.examplereceipt.rest.model;

import java.util.List;

public class SelectedProducts {

	private List<Long> productIds;

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

}
