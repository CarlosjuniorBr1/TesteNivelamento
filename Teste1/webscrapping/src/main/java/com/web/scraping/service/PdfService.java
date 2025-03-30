package com.web.scraping.service;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PdfService {

    private final FileService fileService = new FileService();
    private final PdfMergeService pdfMergeService = new PdfMergeService();

    public void dowloadAndMergeFiles(String url)  throws IOException{
        Document doc = Jsoup.connect(url).get();

        String file1Url = null; 
        String file2Url = null; 
        Elements links = doc.select("a[href]");
        
        for(Element link : links){
            String text = link.text().toLowerCase();
            String href = link.absUrl("href");

            if (text.contains("anexo i") && href.contains("Anexo_I_Rol") && href.endsWith(".pdf")) file1Url = href;
            if (text.contains("anexo ii") && href.contains("Anexo_II") && href.endsWith(".pdf")) file2Url = href;
            
        }
        
        if(file1Url == null || file2Url == null){
            throw new IOException(" Links for pdf's not found!");
        }

        File dir = fileService.createDir("downloads");

        File file1 = new File(dir, "AnexoI.pdf");
        File file2 = new File(dir, "AnexoII.pdf");
        
        fileService.downloadFile(file1Url, file1);
        fileService.downloadFile(file2Url, file2);

        File mergedFile = new File(dir, "Anexos_Unidos.pdf");
        pdfMergeService.mergePDFs(file1, file2, mergedFile);

        System.out.println("PDFs mergeds in: " + mergedFile.getAbsolutePath());

    }

}
