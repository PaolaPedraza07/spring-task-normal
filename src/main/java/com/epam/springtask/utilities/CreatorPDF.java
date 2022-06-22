package com.epam.springtask.utilities;

import com.epam.springtask.model.Ticket;
import java.io.*;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreatorPDF {
    Logger logger = LoggerFactory.getLogger(CreatorPDF.class);


    public ByteArrayInputStream ticketReportPDF(List<Ticket> tickets) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3,3,3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Event", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("User", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Category", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Place", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            for (Ticket ticket : tickets) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(String.valueOf(ticket.getId())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(ticket.getEventId())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(ticket.getUserId())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(ticket.getCategory())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(ticket.getPlace())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
