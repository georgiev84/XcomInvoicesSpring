package com.petar.workspring.repository;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PartnersRepository extends JpaRepository<Partner, Integer> {

    Optional<Partner> findByUsername(String email);
    Partner findById(int partnerId);

    // NATIVE QUERIES

    //  TOO SLOW, better using cash table -> @Query(value = "SELECT Code, Name, Qtty, PriceOut AS Price FROM Operations LEFT JOIN Goods ON Goods.id=Operations.GoodId WHERE Acct=:acctId", nativeQuery = true)
    @Query(value = "SELECT Code, Name, Qtty, Price AS Price FROM test_invoice_details WHERE Acct=:acctId", nativeQuery = true)
    ArrayList<InvoiceProduct> getInvoiceProducts(@Param("acctId") String acctId);


    @Query(value = "SELECT Acct FROM Documents WHERE InvoiceNumber=:invoiceId", nativeQuery = true)
    ArrayList<String> getInvoiceAcct(@Param("invoiceId") String invoiceId);


    @Query(value = "SELECT * FROM test_invoice WHERE PartnerID=:partnerId", nativeQuery = true)
    ArrayList<Invoice> getInvoiceByPartnerId(@Param("partnerId") int userId);

    @Query(value = "SELECT PartnerID FROM test_invoice WHERE InvoiceAcct=:invoiceId", nativeQuery = true)
    String getInvoicePartner(@Param("invoiceId") String userId);


}
