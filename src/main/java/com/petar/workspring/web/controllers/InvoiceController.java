package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class InvoiceController {
    private final InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;

    }

    @GetMapping("/invoices")
    public ModelAndView invoices(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("username") != null){

            modelAndView.setViewName("invoices");
        } else {
            modelAndView.setViewName("redirect:/");
        }
        ArrayList<Invoice> invoices;


        Integer userid = (int) session.getAttribute("userId");

        if (userid == null){
            //kur tuka trqq varnesh neshto 4e ne eok da ne praish zaqvka naprazno
        } else {
            invoices = invoiceService.getInvoicesForUser(userid);
            modelAndView.addObject("company", session.getAttribute("company"));
            modelAndView.addObject("messages", invoices);
        }




        return modelAndView;
    }

    @GetMapping("/invoices/{id}")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("username") == null){
            modelAndView.setViewName("redirect:/login");
        } else {

            ArrayList<InvoiceProduct> productList = invoiceService.getInvoiceDetails(id);

            modelAndView.addObject("invoiceDetails", productList);

            modelAndView.setViewName("invoice_details");
        }

        return modelAndView;

    }

}
