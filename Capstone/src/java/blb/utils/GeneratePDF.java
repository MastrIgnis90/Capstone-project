/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.utils;

import blb.domain.orders.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Wild
 */
public class GeneratePDF {//unfinished
    public static ByteArrayOutputStream getpdfFile(ArrayList<Order> orders){
        
        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream baus = new ByteArrayOutputStream();
        
        try {
            PdfPTable table = new PdfPTable(3);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            PdfPCell hcell;
            
            hcell = new PdfPCell(new Phrase("Order Number", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell= new PdfPCell(new Phrase("Order", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Note", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            for (Order order: orders) {
                PdfPCell cell;
                
                cell = new PdfPCell(new Phrase(order.getOrderNum()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(order.getProduct()));
                cell.setVerticalAlignment(Element.ALIGN_LEFT);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(order.getNotes()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
            }
            
            PdfWriter.getInstance(doc, baus);
            doc.open();
            doc.add(table);
            
            doc.close();
            
        } catch (DocumentException ex) {
            Logger.getLogger(GeneratePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return baus;
    }
    
}
