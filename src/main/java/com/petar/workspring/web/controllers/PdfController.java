package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.InvoiceBasicInfo;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.data.Owner;
import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.service.InvoiceService;
import com.petar.workspring.service.PartnerService;
import com.petar.workspring.utils.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class PdfController {
    @Autowired
    InvoiceService invoiceService;


    @RequestMapping(value = "/pdfreport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> invoiceReport(@PathVariable(name = "id") String id, HttpSession session) throws IOException {
        Partner partner = invoiceService.getPartnerInfo((int)session.getAttribute("userId"));
        Owner owner = invoiceService.getOwnerInfo();
        InvoiceBasicInfo invoiceBasicInfo = invoiceService.getInvoiceBasicInfoDetails(id);

        List<InvoiceProduct> products = (List<InvoiceProduct>) invoiceService.getInvoiceDetails(id);

        ByteArrayInputStream bis = GeneratePdfReport.invoiceReport(products, partner, owner, invoiceBasicInfo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoice.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}