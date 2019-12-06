package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProductList;
import com.petar.workspring.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

            ArrayList<InvoiceProductList> productList = invoiceService.getInvoiceDetails(id);

            modelAndView.addObject("invoiceDetails", productList);
            int b=5;
            modelAndView.setViewName("invoice_details");
        }


        return modelAndView;

    }

// native query - SELECT Code, Name, Qtty FROM Operations LEFT JOIN Goods ON Goods.id=Operations.GoodId WHERE Acct=40010788
//    @GetMapping("/invoices/{id}")
//    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView, HttpSession session){
//        if(session.getAttribute("username") == null){
//            modelAndView.setViewName("redirect:/login");
//        } else {
//            DocumentServiceModel documentServiceModel = this.documentService.findDocumentById(id);
//
//            if(documentServiceModel == null){
//                throw new IllegalArgumentException("Document not found");
//            }
//
//            modelAndView.setViewName("details");
//            modelAndView.addObject("model",
//                    this.modelMapper.map(documentServiceModel, DocumentDetailsViewModel.class));
//        }
//
//
//        return modelAndView;
//
//    }
}
