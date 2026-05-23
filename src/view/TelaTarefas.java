package src.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import src.dao.TarefaDAO;
import src.util.GeradorPDF;
import src.util.Tema;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import java.util.ArrayList;

public class TelaTarefas extends JFrame {

    JLabel titulo;

    JTextField campoPesquisa;

    JComboBox<String> comboFiltro;

    JButton botaoPesquisar;
    JButton botaoConcluir;
    JButton botaoExcluir;
    JButton botaoAtualizar;
    JButton botaoPDF;

    JTable tabela;

    DefaultTableModel modelo;

    JScrollPane scroll;

    public TelaTarefas() {

        setTitle("Tarefas");

        setSize(1100, 650);

        setLayout(null);

        getContentPane().setBackground(
            Tema.fundo
        );

        // TÍTULO
        titulo =
        new JLabel(
            "GERENCIAR TAREFAS"
        );

        titulo.setBounds(
            330,
            20,
            450,
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

        // PESQUISA
        campoPesquisa =
        new JTextField();

        campoPesquisa.setBounds(
            40,
            90,
            250,
            40
        );

        campoPesquisa.setBackground(
            Tema.painel
        );

        campoPesquisa.setForeground(
            Color.WHITE
        );

        campoPesquisa.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        add(campoPesquisa);

        // FILTRO
        comboFiltro =
        new JComboBox<>();

        comboFiltro.addItem(
            "TODAS"
        );

        comboFiltro.addItem(
            "URGENTE"
        );

        comboFiltro.addItem(
            "MÉDIA"
        );

        comboFiltro.addItem(
            "BAIXA"
        );

        comboFiltro.setBounds(
            310,
            90,
            170,
            40
        );

        comboFiltro.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        add(comboFiltro);

        // BOTÃO PESQUISAR
        botaoPesquisar =
        criarBotao(
            "Pesquisar",
            500
        );

        add(botaoPesquisar);

        // BOTÃO CONCLUIR
        botaoConcluir =
        criarBotao(
            "Concluir",
            670
        );

        add(botaoConcluir);

        // BOTÃO EXCLUIR
        botaoExcluir =
        criarBotao(
            "Excluir",
            840
        );

        add(botaoExcluir);

        // TABELA
        modelo =
        new DefaultTableModel();

        modelo.addColumn("ID");

        modelo.addColumn("Título");

        modelo.addColumn("Prazo");

        modelo.addColumn("Status");

        modelo.addColumn("Prioridade");

        tabela =
        new JTable(modelo);

        tabela.setRowHeight(35);

        tabela.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                14
            )
        );

        tabela.getTableHeader().setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        tabela.setBackground(
            Tema.painel
        );

        tabela.setForeground(
            Color.WHITE
        );

        tabela.getTableHeader().setBackground(
            Tema.botao
        );

        tabela.getTableHeader().setForeground(
            Color.WHITE
        );

        scroll =
        new JScrollPane(tabela);

        scroll.setBounds(
            40,
            160,
            1000,
            340
        );

        add(scroll);

        // BOTÃO PDF
        botaoPDF =
        criarBotao(
            "Exportar PDF",
            40
        );

        botaoPDF.setBounds(
            40,
            540,
            200,
            45
        );

        add(botaoPDF);

        // BOTÃO ATUALIZAR
        botaoAtualizar =
        criarBotao(
            "Atualizar",
            430
        );

        botaoAtualizar.setBounds(
            430,
            540,
            220,
            45
        );

        add(botaoAtualizar);

        // CARREGAR
        carregarTabela();

        // PESQUISAR
        botaoPesquisar.addActionListener(e -> {

            pesquisar();
        });

        // FILTRO
        comboFiltro.addActionListener(e -> {

            filtrar();
        });

        // CONCLUIR
        botaoConcluir.addActionListener(e -> {

            concluirTarefa();
        });

        // EXCLUIR
        botaoExcluir.addActionListener(e -> {

            excluirTarefa();
        });

        // ATUALIZAR
        botaoAtualizar.addActionListener(e -> {

            carregarTabela();
        });

        // PDF
        botaoPDF.addActionListener(e -> {

            exportarPDF();
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // BOTÃO
    public JButton criarBotao(
        String texto,
        int x
    ) {

        JButton botao =
        new JButton(texto);

        botao.setBounds(
            x,
            90,
            150,
            40
        );

        botao.setBackground(
            Tema.botao
        );

        botao.setForeground(
            Color.WHITE
        );

        botao.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        botao.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        botao.setFocusPainted(false);

        return botao;
    }

    // CARREGAR
    public void carregarTabela() {

        modelo.setRowCount(0);

        TarefaDAO dao =
        new TarefaDAO();

        ArrayList<String[]> lista =
        dao.buscarTarefas();

        for(String[] linha : lista) {

            modelo.addRow(linha);
        }
    }

    // PESQUISAR
    public void pesquisar() {

        modelo.setRowCount(0);

        String pesquisa =
        campoPesquisa.getText();

        TarefaDAO dao =
        new TarefaDAO();

        ArrayList<String[]> lista =
        dao.pesquisarTarefas(
            pesquisa
        );

        for(String[] linha : lista) {

            modelo.addRow(linha);
        }
    }

    // FILTRAR
    public void filtrar() {

        String filtro =
        comboFiltro
        .getSelectedItem()
        .toString();

        if(filtro.equals("TODAS")) {

            carregarTabela();

            return;
        }

        modelo.setRowCount(0);

        TarefaDAO dao =
        new TarefaDAO();

        ArrayList<String[]> lista =
        dao.filtrarPrioridade(
            filtro
        );

        for(String[] linha : lista) {

            modelo.addRow(linha);
        }
    }

    // CONCLUIR
    public void concluirTarefa() {

        int linha =
        tabela.getSelectedRow();

        if(linha == -1) {

            JOptionPane.showMessageDialog(
                null,
                "Selecione uma tarefa!"
            );

            return;
        }

        int id =
        Integer.parseInt(
            modelo.getValueAt(
                linha,
                0
            ).toString()
        );

        TarefaDAO dao =
        new TarefaDAO();

        dao.atualizarStatus(
            id,
            "Concluída"
        );

        carregarTabela();

        JOptionPane.showMessageDialog(
            null,
            "Tarefa concluída!"
        );
    }

    // EXCLUIR
    public void excluirTarefa() {

        int linha =
        tabela.getSelectedRow();

        if(linha == -1) {

            JOptionPane.showMessageDialog(
                null,
                "Selecione uma tarefa!"
            );

            return;
        }

        int confirmar =
        JOptionPane.showConfirmDialog(

            null,

            "Deseja excluir?",

            "Confirmação",

            JOptionPane.YES_NO_OPTION
        );

        if(confirmar != 0) {

            return;
        }

        int id =
        Integer.parseInt(
            modelo.getValueAt(
                linha,
                0
            ).toString()
        );

        TarefaDAO dao =
        new TarefaDAO();

        dao.excluirTarefa(id);

        carregarTabela();

        JOptionPane.showMessageDialog(
            null,
            "Tarefa excluída!"
        );
    }

    // PDF
    public void exportarPDF() {

        TarefaDAO dao =
        new TarefaDAO();

        ArrayList<String[]> lista =
        dao.buscarTarefas();

        GeradorPDF.gerarPDF(lista);

        JOptionPane.showMessageDialog(

            null,

            "PDF gerado com sucesso!"
        );
    }
}