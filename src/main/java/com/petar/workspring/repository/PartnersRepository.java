package com.petar.workspring.repository;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PartnersRepository extends JpaRepository<Partner, Integer> {

    Optional<Partner> findByUsername(String email);

@Query(value = "SELECT documents.InvoiceNumber AS InvoiceAcct, " +
        "documents.InvoiceDate, documents.TaxDate,operations.OperType AS OperTypeInvisible, " +
        " operations.Acct AS Acct, " +
        "partners.ID AS PartnerID, " +
        "partners.Company AS Partner,  " +
        "SUM(operations.Qtty * operations.PriceOut)  AS SumWithoutVAT, " +
        "SUM(operations.Qtty * operations.VATOut) AS VAT1Only, " +
        "SUM(operations.Qtty * (operations.PriceOut+operations.VATOut)) AS InvoiceSum, " +
        "documents.Reason AS Reason, " +
        "documents.Description AS Description, " +
        "documents.Place AS Place, " +
        "documents.DocumentType AS DocType " +
        "FROM ((((((operations  " +
        "LEFT JOIN documents " +
        "ON (operations.Acct = documents.Acct AND  (operations.OperType = documents.OperType OR operations.OperType+100 = documents.OperType))) " +
        "LEFT JOIN goods ON operations.GoodID=goods.ID)  " +
        "LEFT JOIN partners ON operations.PartnerID=partners.ID)  " +
        "LEFT JOIN objects ON operations.ObjectID = objects.ID)  " +
        "LEFT JOIN users ON operations.OperatorID=users.ID)  " +
        "LEFT JOIN operationtype ON operations.OperType = operationtype.ID) " +
        "LEFT JOIN paymenttypes ON documents.PaymentType = paymenttypes.ID " +
        "WHERE partners.ID=:userId AND  documents.PaymentType = 2 AND (operations.OperType = 2 OR operations.OperType = 16) AND operations.PartnerID <> 0 " +
        "GROUP BY documents.InvoiceNumber, documents.InvoiceDate, " +
        "operations.OperType, documents.TaxDate, " +
        "operations.Acct, partners.ID, partners.Company, documents.Reason, " +
        "documents.Description, documents.Place, documents.DocumentType ", nativeQuery = true)
ArrayList<String> getInvoiceByPartnerId(@Param("userId") int userId);

    @Query(value = "SELECT documents.InvoiceNumber AS InvoiceAcct, " +
            "documents.InvoiceDate, documents.TaxDate,operations.OperType AS OperTypeInvisible, " +
            " operations.Acct AS Acct " +
            "FROM ((((((operations  " +
            "LEFT JOIN documents " +
            "ON (operations.Acct = documents.Acct AND  (operations.OperType = documents.OperType OR operations.OperType+100 = documents.OperType))) " +
            "LEFT JOIN goods ON operations.GoodID=goods.ID)  " +
            "LEFT JOIN partners ON operations.PartnerID=partners.ID)  " +
            "LEFT JOIN objects ON operations.ObjectID = objects.ID)  " +
            "LEFT JOIN users ON operations.OperatorID=users.ID)  " +
            "LEFT JOIN operationtype ON operations.OperType = operationtype.ID) " +
            "LEFT JOIN paymenttypes ON documents.PaymentType = paymenttypes.ID " +
            "WHERE partners.ID=:userId AND  documents.PaymentType = 2 AND (operations.OperType = 2 OR operations.OperType = 16) AND operations.PartnerID <> 0 " +
            "GROUP BY documents.InvoiceNumber, documents.InvoiceDate, " +
            "operations.OperType, documents.TaxDate, " +
            "operations.Acct, partners.ID, partners.Company, documents.Reason, " +
            "documents.Description, documents.Place, documents.DocumentType ", nativeQuery = true)
    ArrayList<Invoice> getInvoiceByPartnerId1(@Param("userId") int userId);

}
