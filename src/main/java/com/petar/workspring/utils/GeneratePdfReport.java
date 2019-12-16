package com.petar.workspring.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.entities.Partner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    public static ByteArrayInputStream invoiceReport(List<InvoiceProduct> products, Partner partner) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final String FONT = "font/Helvetica.ttf";
        final String HEAD_FONT = "font/Verdana.ttf";

        try {

            Font commonFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10);
            Font headFont = FontFactory.getFont(HEAD_FONT, BaseFont.IDENTITY_H, true, 10, Font.BOLD);


            // TABLE OUTER
            PdfPTable mainTable = new PdfPTable(2);

            // TABLE BUYER
            PdfPTable tableByer = new PdfPTable(1);
            tableByer.setWidthPercentage(50);


            PdfPCell xCell;

            xCell = new PdfPCell(new Phrase("Получател", headFont));
            xCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableByer.addCell(xCell);
            xCell = new PdfPCell(new Phrase(partner.toString(), headFont));
            tableByer.addCell(xCell);
            mainTable.addCell(tableByer);

            // TABLE SELLER
            PdfPTable tableSeller = new PdfPTable(1);
            tableByer.setWidthPercentage(50);

            xCell = new PdfPCell(new Phrase("Доставчик", headFont));
            xCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableSeller.addCell(xCell);
            mainTable.addCell(tableSeller);

            // TABLE PRODUCTS
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);


            table.setWidths(new float[] { 2, 4, 20, 5, 5 });



            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Ред", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Код", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Продукт", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Количество", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Цена", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            int counterRow =0;
            for (InvoiceProduct product : products) {

                PdfPCell cell;
                counterRow++;
                cell = new PdfPCell(new Phrase(String.valueOf(counterRow),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(product.getCode(),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(product.getName(),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(product.getQtty()),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(product.getPrice()),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            Paragraph paragraph = new Paragraph(" ");

            PdfWriter.getInstance(document, out);
            document.open();


            document.add(mainTable);
            document.add(paragraph);
            document.add(table);


            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}