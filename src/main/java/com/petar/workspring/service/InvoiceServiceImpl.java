package com.petar.workspring.service;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.entities.Partner;
import com.petar.workspring.repository.PartnersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final PartnersRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final PartnerService partnerService;


    @Autowired
    public InvoiceServiceImpl(PartnersRepository invoiceRepository, ModelMapper modelMapper, PartnerService partnerService) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
        this.partnerService = partnerService;
    }

    @Override
    public ArrayList<Invoice> getInvoicesForUser(int userId) {
        ArrayList<Invoice> invoices = invoiceRepository.getInvoiceByPartnerId(userId);

        return invoices;
    }



    @Override
    public ArrayList<InvoiceProduct> getInvoiceDetails(String invoiceId) {

        ArrayList<String> acctList = invoiceRepository.getInvoiceAcct(invoiceId);

        HashMap<String, InvoiceProduct> product = new HashMap<>();

            for (String acct: acctList) {

                List<InvoiceProduct> list = invoiceRepository.getInvoiceProducts(acct);

                for(InvoiceProduct item : list){


                    if(product.containsKey(item.getCode())){

                        int qtty = product.get(item.getCode()).getQtty();
                        int allQtty = qtty + item.getQtty();

                        BigDecimal oldPrice = product.get(item.getCode()).getPrice();
                        BigDecimal newPrice = item.getPrice();

                        BigDecimal avgPrice = oldPrice.add(newPrice).divide(BigDecimal.valueOf(2));


                        product.put(item.getCode(), new InvoiceProduct() {
                            @Override
                            public String getCode() {
                                return item.getCode();
                            }

                            @Override
                            public String getName() {
                                return item.getName();
                            }

                            @Override
                            public int getQtty() {
                                return allQtty;
                            }

                            @Override
                            public BigDecimal getPrice() {
                                return avgPrice;
                            }
                        });
                    } else {

                        product.put(item.getCode(), item);
                    }

                }


            }

            // convert to arrayList
        Collection<InvoiceProduct> values = product.values();
        ArrayList<InvoiceProduct> listOfValues = new ArrayList<>(values);
            
        return listOfValues;
    }

    @Override
    public Partner getPartnerInfo(int userId) {
        Partner partner = invoiceRepository.findById(userId);

        return partner;
    }

    @Override
    public String checkInvoiceOwner(String invoiceId) {

        return  invoiceRepository.getInvoicePartner(invoiceId);
    }
}
