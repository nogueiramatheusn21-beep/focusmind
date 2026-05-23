package src.util;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.util.ArrayList;

public class GeradorPDF {

    public static void gerarPDF(

        ArrayList<String[]> tarefas

    ) {

        try {

            Document documento =
            new Document();

            PdfWriter.getInstance(

                documento,

                new FileOutputStream(
                    "tarefas.pdf"
                )
            );

            documento.open();

            Paragraph titulo =
            new Paragraph(
                "RELATÓRIO DE TAREFAS\n\n"
            );

            documento.add(titulo);

            PdfPTable tabela =
            new PdfPTable(5);

            PdfPCell c1 =
            new PdfPCell(
                new Phrase("ID")
            );

            tabela.addCell(c1);

            PdfPCell c2 =
            new PdfPCell(
                new Phrase("Título")
            );

            tabela.addCell(c2);

            PdfPCell c3 =
            new PdfPCell(
                new Phrase("Prazo")
            );

            tabela.addCell(c3);

            PdfPCell c4 =
            new PdfPCell(
                new Phrase("Status")
            );

            tabela.addCell(c4);

            PdfPCell c5 =
            new PdfPCell(
                new Phrase("Prioridade")
            );

            tabela.addCell(c5);

            for(String[] tarefa : tarefas) {

                tabela.addCell(
                    tarefa[0]
                );

                tabela.addCell(
                    tarefa[1]
                );

                tabela.addCell(
                    tarefa[2]
                );

                tabela.addCell(
                    tarefa[3]
                );

                tabela.addCell(
                    tarefa[4]
                );
            }

            documento.add(tabela);

            documento.close();

            System.out.println(
                "PDF gerado!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}