package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.service.InvoiceService;
import com.petar.workspring.utils.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class PdfController {
    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {

        List<InvoiceProduct> cities = (List<InvoiceProduct>) invoiceService.getInvoiceDetails("0000064536");

        ByteArrayInputStream bis = GeneratePdfReport.invoiceReport(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=invoice.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}