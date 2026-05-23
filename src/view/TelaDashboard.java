package src.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.DefaultPieDataset;

import src.dao.TarefaDAO;
import src.util.Tema;

public class TelaDashboard extends JFrame {

    JLabel titulo;

    public TelaDashboard() {

        setTitle("Dashboard");

        setSize(1000, 650);

        setLayout(null);

        getContentPane().setBackground(
            Tema.fundo
        );

        // TÍTULO
        titulo =
        new JLabel(
            "DASHBOARD"
        );

        titulo.setBounds(
            380,
            20,
            300,
            40
        );

        titulo.setForeground(
            Color.WHITE
        );

        titulo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                30
            )
        );

        add(titulo);

        // DAO
        TarefaDAO dao =
        new TarefaDAO();

        int total =
        dao.contarTarefas();

        int pendentes =
        dao.contarPendentes();

        int concluidas =
        dao.contarConcluidas();

        int urgentes =
        dao.contarUrgentes();

        // CARDS
        add(
            criarCard(
                "TOTAL",
                String.valueOf(total),
                60
            )
        );

        add(
            criarCard(
                "PENDENTES",
                String.valueOf(pendentes),
                290
            )
        );

        add(
            criarCard(
                "CONCLUÍDAS",
                String.valueOf(concluidas),
                520
            )
        );

        add(
            criarCard(
                "URGENTES",
                String.valueOf(urgentes),
                750
            )
        );

        // DADOS GRÁFICO
        DefaultPieDataset dados =
        new DefaultPieDataset();

        dados.setValue(
            "Pendentes",
            pendentes
        );

        dados.setValue(
            "Concluídas",
            concluidas
        );

        dados.setValue(
            "Urgentes",
            urgentes
        );

        // GRÁFICO
        JFreeChart grafico =
        ChartFactory.createPieChart(

            "Resumo das Tarefas",

            dados,

            true,

            true,

            false
        );

        ChartPanel painel =
        new ChartPanel(grafico);

        painel.setBounds(
            220,
            220,
            550,
            320
        );

        add(painel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // MÉTODO CARD
    public JPanel criarCard(
        String titulo,
        String valor,
        int x
    ) {

        JPanel card =
        new JPanel();

        card.setLayout(null);

        card.setBounds(
            x,
            90,
            180,
            100
        );

        card.setBackground(
            Tema.painel
        );

        JLabel textoTitulo =
        new JLabel(titulo);

        textoTitulo.setBounds(
            20,
            15,
            150,
            30
        );

        textoTitulo.setForeground(
            Color.LIGHT_GRAY
        );

        textoTitulo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        card.add(textoTitulo);

        JLabel textoValor =
        new JLabel(valor);

        textoValor.setBounds(
            20,
            45,
            150,
            40
        );

        textoValor.setForeground(
            Color.WHITE
        );

        textoValor.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                32
            )
        );

        card.add(textoValor);

        return card;
    }
}