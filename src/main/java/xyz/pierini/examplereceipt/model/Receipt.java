package xyz.pierini.examplereceipt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "receipts")
/**
 * Classe usata solo per il conteggio delle ricevute
 * Un domani si potranno persistere i dati delle stesse, se necessario
 */
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Receipt() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
