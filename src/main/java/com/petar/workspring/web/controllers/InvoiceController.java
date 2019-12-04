package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final EntityManager em;

    public InvoiceController(InvoiceService invoiceService, EntityManager em) {
        this.invoiceService = invoiceService;
        this.em = em;
    }

    @GetMapping("/invoices")
    public ModelAndView invoices(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("username") != null){

            modelAndView.setViewName("invoices");
        } else {
            modelAndView.setViewName("redirect:/");
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null || userId <=0){
            //kur tuka trqq varnesh neshto 4e ne eok da ne praish zaqvka naprazno
        }

        ArrayList<String> invoices = invoiceService.getInvoicesForUser(userId);


  /*  //        Query q = em.createNativeQuery("SELECT * FROM partners");
    //SELECT documents.InvoiceNumber AS InvoiceAcct, documents.InvoiceDate, documents.TaxDate,operations.OperType AS OperTypeInvisible,  operations.Acct AS Acct, partners.ID AS PartnerID, partners.Company AS Partner,  SUM(operations.Qtty * operations.PriceOut)  AS SumWithoutVAT, SUM(operations.Qtty * operations.VATOut) AS VAT1Only, SUM(operations.Qtty * (operations.PriceOut+operations.VATOut)) AS InvoiceSum,documents.Reason AS Reason, documents.Description AS Description, documents.Place AS Place, documents.DocumentType AS DocType FROM ((((((operations  LEFT JOIN documents ON (operations.Acct = documents.Acct AND  (operations.OperType = documents.OperType OR operations.OperType+100 = documents.OperType))) LEFT JOIN goods ON operations.GoodID=goods.ID)  LEFT JOIN partners ON operations.PartnerID=partners.ID)  LEFT JOIN objects ON operations.ObjectID = objects.ID)  LEFT JOIN users ON operations.OperatorID=users.ID)  LEFT JOIN operationtype ON operations.OperType = operationtype.ID) LEFT JOIN paymenttypes ON documents.PaymentType = paymenttypes.ID WHERE partners.ID=13069 AND  documents.PaymentType = 2 AND (operations.OperType = 2 OR operations.OperType = 16) AND operations.PartnerID <> 0 GROUP BY documents.InvoiceNumber, documents.InvoiceDate, operations.OperType, documents.TaxDate, operations.Acct, partners.ID, partners.Company, documents.Reason, documents.Description, documents.Place, documents.DocumentType ORDER BY 1, 3
       // tova raboti
        String myQuery = "SELECT documents.InvoiceNumber AS InvoiceAcct, documents.InvoiceDate, documents.TaxDate,operations.OperType AS OperTypeInvisible,  operations.Acct AS Acct, partners.ID AS PartnerID, partners.Company AS Partner,  SUM(operations.Qtty * operations.PriceOut)  AS SumWithoutVAT, SUM(operations.Qtty * operations.VATOut) AS VAT1Only, SUM(operations.Qtty * (operations.PriceOut+operations.VATOut)) AS InvoiceSum,documents.Reason AS Reason, documents.Description AS Description, documents.Place AS Place, documents.DocumentType AS DocType FROM ((((((operations  LEFT JOIN documents ON (operations.Acct = documents.Acct AND  (operations.OperType = documents.OperType OR operations.OperType+100 = documents.OperType))) LEFT JOIN goods ON operations.GoodID=goods.ID)  LEFT JOIN partners ON operations.PartnerID=partners.ID)  LEFT JOIN objects ON operations.ObjectID = objects.ID)  LEFT JOIN users ON operations.OperatorID=users.ID)  LEFT JOIN operationtype ON operations.OperType = operationtype.ID) LEFT JOIN paymenttypes ON documents.PaymentType = paymenttypes.ID WHERE partners.ID=:parameterId AND  documents.PaymentType = 2 AND (operations.OperType = 2 OR operations.OperType = 16) AND operations.PartnerID <> 0 GROUP BY documents.InvoiceNumber, documents.InvoiceDate, operations.OperType, documents.TaxDate, operations.Acct, partners.ID, partners.Company, documents.Reason, documents.Description, documents.Place, documents.DocumentType ";
        Query q = em.createNativeQuery(myQuery);
        q.setParameter("parameterId", session.getAttribute("userId"));
// tuka vzimam sessionId i go vkarvam v zaiavkata ok
        List<String> invoiceList= q.getResultList();*/

        return modelAndView;
    }
}
