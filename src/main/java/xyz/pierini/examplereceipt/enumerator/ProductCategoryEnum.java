package xyz.pierini.examplereceipt.enumerator;

import java.util.HashMap;
import java.util.Map;

public enum ProductCategoryEnum {
	BOOK("Libro"), MUSIC("Musica"), FOOD("Cibo"), PHARMACEUTICAL("Farmaco"), OTHER("Altro");

	private String name;

	ProductCategoryEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private static final Map<String, ProductCategoryEnum> lookup = new HashMap<>();

	static {
		for (ProductCategoryEnum prod : ProductCategoryEnum.values()) {
			lookup.put(prod.getName(), prod);
		}
	}

	public static ProductCategoryEnum get(String name) {
		return lookup.get(name);
	}
}