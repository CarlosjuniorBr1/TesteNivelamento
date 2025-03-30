package com.web.scraping.service;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class PdfMergeService {

    public void mergePDFs(File file1, File file2, File outputFile) throws IOException {
        PDDocument document1 = PDDocument.load(file1);
        PDDocument document2 = PDDocument.load(file2);

        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        mergerUtility.appendDocument(document1, document2);
        document1.save(outputFile);
        document1.close();
        document2.close();
    }
        
}
