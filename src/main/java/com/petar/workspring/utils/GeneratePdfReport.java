package com.petar.workspring.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.petar.workspring.domain.data.InvoiceBasicInfo;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.data.Owner;
import com.petar.workspring.domain.entities.Partner;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    public static ByteArrayInputStream invoiceReport(List<InvoiceProduct> products, Partner partner, Owner owner, InvoiceBasicInfo invoiceBasicInfo) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final String FONT = "font/Helvetica.ttf";
        final String HEAD_FONT = "font/Verdana.ttf";

        try {

            Font commonFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10);
            Font headFont = FontFactory.getFont(HEAD_FONT, BaseFont.IDENTITY_H, true, 10, Font.BOLD);


            // TABLE OUTER
            PdfPTable mainTable = new PdfPTable(2);
            mainTable.getDefaultCell().setBorder(0);
            // TABLE BUYER
            PdfPTable tableByer = new PdfPTable(1);
            tableByer.setWidthPercentage(50);


            PdfPCell xCell;


            xCell = new PdfPCell(new Phrase("Получател", headFont));
//            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBackgroundColor(new BaseColor(242,242,240));
            xCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableByer.addCell(xCell);
            xCell = new PdfPCell(new Phrase(partner.toString(), headFont));
            tableByer.addCell(xCell);
            mainTable.addCell(tableByer);


            // TABLE SELLER
            PdfPTable tableSeller = new PdfPTable(1);
            tableByer.setWidthPercentage(50);

            xCell = new PdfPCell(new Phrase("Доставчик", headFont));
//            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBackgroundColor(new BaseColor(242,242,240));
            xCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableSeller.addCell(xCell);
            xCell = new PdfPCell(new Phrase(owner.getCompany() + " " + owner.getAddress(), headFont));
            tableSeller.addCell(xCell);
            mainTable.addCell(tableSeller);




            // TABLE PRODUCTS
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);


            table.setWidths(new float[] { 2, 4, 20, 5, 5 });



            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Ред", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Код", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Продукт", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Количество", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Цена", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            DecimalFormat df = new DecimalFormat("0.0000");
            df.setRoundingMode(RoundingMode.CEILING);

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

                cell = new PdfPCell(new Phrase(String.valueOf(df.format(product.getPrice())),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);


            }

            DecimalFormat dfTwo = new DecimalFormat("0.00");
            PdfPCell cellTest;

            PdfPTable tab1 = new PdfPTable(2);

            cellTest = new PdfPCell(new Phrase("Сума с ДДС: ",commonFont));
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tab1.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase(String.valueOf(dfTwo.format(invoiceBasicInfo.getInvoiceSum())),commonFont));
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tab1.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase("Описание: ",commonFont));
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tab1.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase(String.valueOf(invoiceBasicInfo.getDescription()),commonFont));
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tab1.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase("Срок на плащане: ",commonFont));
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tab1.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase(String.valueOf(invoiceBasicInfo.getReason()),commonFont));
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tab1.addCell(cellTest);


            // Invoice Information
            PdfPTable tableInvoiceInfo = new PdfPTable(2);
            PdfPCell cellInfo;

            cellInfo = new PdfPCell(new Phrase("Номер: ",commonFont));
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);

            cellInfo = new PdfPCell(new Phrase(String.valueOf(invoiceBasicInfo.getInvoiceAcct()),commonFont));
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);

            cellInfo = new PdfPCell(new Phrase("Дата: ",commonFont));
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy");
            cellInfo = new PdfPCell(new Phrase(String.valueOf(formatter.format(invoiceBasicInfo.getinvoiceDate())),commonFont));
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);
            // END

            Paragraph paragraph = new Paragraph(" ");

            PdfWriter.getInstance(document, out);
            document.open();


            document.add(mainTable);
            document.add(paragraph);
            document.add(tableInvoiceInfo);
            document.add(paragraph);


            document.add(table);
            document.add(paragraph);

            document.add(tab1);


            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}