package xyz.pierini.examplereceipt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.pierini.examplereceipt.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
