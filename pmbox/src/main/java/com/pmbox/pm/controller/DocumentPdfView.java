package com.pmbox.pm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class DocumentPdfView extends AbstractPdfView
{

	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document,
			PdfWriter arg2, HttpServletRequest arg3, HttpServletResponse arg4)
			throws Exception {
		String projectName = (String) map.get("projectName");
		String authorName = (String) map.get("authorName);");
		String message = (String) map.get("message");
		String docName = (String) map.get("docName");
		String docContent = (String) map.get("docContent");
		
		Paragraph p0 = new Paragraph("" + message);
		Paragraph p1 = new Paragraph("Project: " + projectName);
		Paragraph p2 = new Paragraph("Document Title: " + docName);
		Paragraph p3 = new Paragraph("Author: " + authorName);
		Paragraph p4 = new Paragraph("" + docContent);
		
		document.add(p0);
		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.add(p4);
		
		document.close();
	}

}
