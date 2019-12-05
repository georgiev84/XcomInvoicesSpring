package com.petar.workspring.service;


import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProductList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface InvoiceService {
        ArrayList<Invoice> getInvoicesForUser(String userId);

        ArrayList<InvoiceProductList> getInvoiceDetails(String invoiceId);
}
