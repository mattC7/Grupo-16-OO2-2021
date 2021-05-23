package com.trabajo.Grupo16OO22021;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.trabajo.Grupo16OO22021.entities.User;
import com.trabajo.Grupo16OO22021.entities.UserRole;

public class ProfilePDFExporter {
	 private List<UserRole> listUserRole;
     
	    public ProfilePDFExporter(List<UserRole> listUserRole) {
	        this.listUserRole = listUserRole;
	    }
		private void writeTableHeader(PdfPTable table) {
		    PdfPCell cell = new PdfPCell();
		    cell.setBackgroundColor(Color.BLUE);
		    cell.setPadding(3);
		     
		    Font font = FontFactory.getFont(FontFactory.HELVETICA);
		    font.setColor(Color.WHITE);
		     
		     
		    cell.setPhrase(new Phrase("Role", font));
		    table.addCell(cell);
		    
		    cell.setPhrase(new Phrase("Description", font));
		    table.addCell(cell);
		     
		}
		 
		private void writeTableData(PdfPTable table) {
		    for (UserRole UserRole : listUserRole) {
		        table.addCell(UserRole.getName());
		        table.addCell(UserRole.getDescription());      
		    }
		}
		 
		public void export(HttpServletResponse response) throws DocumentException, IOException {
		    Document document = new Document(PageSize.A4);
		    PdfWriter.getInstance(document, response.getOutputStream());
		     
		    document.open();
		    Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		    font.setSize(18);
		    font.setColor(Color.BLUE);
		     
		    Paragraph p = new Paragraph("List of Profiles", font);
		    p.setAlignment(Paragraph.ALIGN_CENTER);
		     
		    document.add(p);
		     
		    PdfPTable table = new PdfPTable(2);
		    table.setWidthPercentage(100f);
		    
		    table.setSpacingBefore(10);
		     
		    writeTableHeader(table);
		    writeTableData(table);
		     
		    document.add(table);
		     
		    document.close();
		     
		}
}
