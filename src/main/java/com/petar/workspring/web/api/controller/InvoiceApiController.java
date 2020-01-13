package com.petar.workspring.web.api.controller;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.service.InvoiceService;
import com.petar.workspring.web.api.model.InvoiceResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceApiController {
    private final InvoiceService invoiceService;


    public InvoiceApiController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/api/invoices")
    public ArrayList<Invoice> invoiceList(HttpSession session) throws InterruptedException {

        Thread.sleep(3000);
        if(session.getAttribute("username") != null){

            ArrayList<Invoice> invoices;


            Integer userid = (int) session.getAttribute("userId");

            if (userid == null){
                //kur tuka trqq varnesh neshto 4e ne eok da ne praish zaqvka naprazno
            } else {
                invoices = invoiceService.getInvoicesForUser(userid);

                return invoices;
            }
        } else {
            return null;
        }

        return null;

    }
}
