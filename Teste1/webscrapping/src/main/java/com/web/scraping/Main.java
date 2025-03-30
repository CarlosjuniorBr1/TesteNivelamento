package com.web.scraping;

import java.io.IOException;

import com.web.scraping.service.PdfService;

public class Main {
    public static void main(String[] args) {
        
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

        PdfService pdfService = new PdfService();

        try{
            pdfService.dowloadAndMergeFiles(url);
            System.out.println("Anexos baixados e unidos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao processar os anexos: " + e.getMessage());
        }
    }
}