package com.petar.workspring.service;


import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceBasicInfo;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.data.Owner;
import com.petar.workspring.domain.entities.Partner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface InvoiceService {
        ArrayList<Invoice> getInvoicesForUser(int userId);

        ArrayList<InvoiceProduct> getInvoiceDetails(String invoiceId);

        Partner getPartnerInfo(int userId);

        String checkInvoiceOwner(String invoiceId);

        Owner getOwnerInfo();

        InvoiceBasicInfo getInvoiceBasicInfoDetails(String invoiceId);
}
