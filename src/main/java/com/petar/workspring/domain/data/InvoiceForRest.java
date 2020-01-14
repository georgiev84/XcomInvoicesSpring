package com.petar.workspring.domain.data;

import java.util.ArrayList;

public class InvoiceForRest {
    private ArrayList<InvoiceProduct> productsInInvoice;
    private InvoiceBasicInfo invoiceBasicInfo;

    public InvoiceForRest(ArrayList<InvoiceProduct> productsInInvoice, InvoiceBasicInfo invoiceBasicInfo) {
        this.productsInInvoice = productsInInvoice;
        this.invoiceBasicInfo = invoiceBasicInfo;
    }

    public ArrayList<InvoiceProduct> getProductsInInvoice() {
        return productsInInvoice;
    }

    public InvoiceBasicInfo getInvoiceBasicInfo() {
        return invoiceBasicInfo;
    }
}
