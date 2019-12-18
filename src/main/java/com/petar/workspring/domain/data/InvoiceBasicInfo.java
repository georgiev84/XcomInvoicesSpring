package com.petar.workspring.domain.data;

public interface InvoiceBasicInfo {
    String getInvoiceAcct();
    String getInvoicePartnerId();
    String getSumWithoutVat();
    String getVatOnly();
    String getInvoiceSum();
    String getReason();
    String getDescription();
    String getPlace();
    String getRecipient();
}
