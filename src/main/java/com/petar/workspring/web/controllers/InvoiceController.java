package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.Invoice;
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
        ArrayList<Invoice> invoices;

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null || userId <=0){
            //kur tuka trqq varnesh neshto 4e ne eok da ne praish zaqvka naprazno
        } else {
            invoices = invoiceService.getInvoicesForUser(userId);
            modelAndView.addObject("company", session.getAttribute("company"));
            modelAndView.addObject("messages", invoices);
        }




        return modelAndView;
    }

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
