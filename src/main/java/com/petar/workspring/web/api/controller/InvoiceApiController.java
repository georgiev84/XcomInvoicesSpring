package com.petar.workspring.web.api.controller;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceBasicInfo;
import com.petar.workspring.domain.data.InvoiceForRest;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.service.InvoiceService;
import com.petar.workspring.web.api.model.InvoiceResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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

    @GetMapping("/api/invoices/{id}")
    public ArrayList<InvoiceProduct> details(@PathVariable(name = "id") String id, HttpSession session, ModelAndView modelAndView){
        String owner = invoiceService.checkInvoiceOwner(id);
        String sessionOwner = session.getAttribute("userId").toString();


        // security check if invoice owner is the same logged in
        if(session.getAttribute("username") == null || owner == null || !owner.equals(sessionOwner)){
           return null;
        } else {


            InvoiceBasicInfo invoiceBasicInfo = invoiceService.getInvoiceBasicInfoDetails(id);
            modelAndView.addObject("invoiceBasicInfo", invoiceBasicInfo);
            modelAndView.setViewName("invoiceBasicInfo");

            ArrayList<InvoiceProduct> productList = invoiceService.getInvoiceDetails(id);

//            InvoiceForRest testing = new InvoiceForRest(productList, invoiceBasicInfo);

            return productList;
        }

    }
}
