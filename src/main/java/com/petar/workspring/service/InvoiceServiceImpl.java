package com.petar.workspring.service;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.repository.PartnersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final PartnersRepository invoiceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceServiceImpl(PartnersRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<Invoice> getInvoicesForUser(int userId) {
        ArrayList<Invoice> invoices = invoiceRepository.getInvoiceByPartnerId(userId);

        return invoices;
    }


    @Override
    public ArrayList<InvoiceProduct> getInvoiceDetails(String invoiceId) {

        ArrayList<String> acctList = invoiceRepository.getInvoiceAcct(invoiceId);
        ArrayList<InvoiceProduct> products = new ArrayList<>();

            for (String acct: acctList) {

                List<InvoiceProduct> list = invoiceRepository.getInvoiceProducts(acct);
                BigDecimal test = list.get(0).getPrice();
                if(list.size() > 1){

                    for(InvoiceProduct item : list){
                        products.add(item);
                    }

                } else {

                    products.add(list.get(0));
                }

            }

        return products;
    }
}
