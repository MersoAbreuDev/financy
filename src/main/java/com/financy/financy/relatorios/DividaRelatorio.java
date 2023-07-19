package com.financy.financy.relatorios;

import com.financy.financy.entity.Divida;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DividaRelatorio {

    public void gerarRelatorioPDF(Divida divida) {
        String nomePasta = "Downloads";
        String diretorio = System.getProperty("user.home") + File.separator + nomePasta;
        File pastaDownload = new File(diretorio);
        pastaDownload.mkdirs(); // Cria a pasta se ela não existir

        String caminhoArquivo = diretorio + File.separator + "relatorio.pdf";
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Relatório de Divida");

            contentStream.newLineAtOffset(0, -20);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.showText("ID: " + divida.getId());
            contentStream.newLine();
            contentStream.showText("Nome: " + divida.getNome());
            contentStream.newLine();
            contentStream.showText("Status: " + divida.getStatus());
            contentStream.newLine();
            contentStream.showText("Valor: " + divida.getValor());
            contentStream.newLine();
            contentStream.showText("Quantidade de Parcelas: " + divida.getParcela());
            contentStream.newLine();
            contentStream.showText("Valor Parcela: " + divida.getValorParcelas());
            contentStream.newLine();
            contentStream.showText("Data de Vencimento: " + divida.getDataVencimento());
            contentStream.newLine();
            contentStream.showText("Data da Compra: " + divida.getDataCompra());
            contentStream.endText();
            contentStream.close();

            document.save(caminhoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
