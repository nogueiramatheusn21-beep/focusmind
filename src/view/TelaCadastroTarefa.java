package src.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import java.util.Date;

import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

import src.dao.TarefaDAO;
import src.util.Tema;

public class TelaCadastroTarefa extends JFrame {

    JLabel titulo;

    JLabel textoTitulo;
    JLabel textoPrazo;
    JLabel textoStatus;
    JLabel textoMateria;

    JTextField campoTitulo;

    JDateChooser campoPrazo;

    JComboBox<String> comboStatus;

    JComboBox<String> comboMateria;

    JButton botaoSalvar;

    public TelaCadastroTarefa() {

        setTitle("Cadastrar Tarefa");

        setSize(600, 500);

        setLayout(null);

        getContentPane().setBackground(
            Tema.fundo
        );

        // TÍTULO
        titulo =
        new JLabel(
            "CADASTRAR TAREFA"
        );

        titulo.setBounds(
            130,
            20,
            400,
            40
        );

        titulo.setForeground(
            Color.WHITE
        );

        titulo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                28
            )
        );

        add(titulo);

        // TEXTO TÍTULO
        textoTitulo =
        new JLabel("Título");

        textoTitulo.setBounds(
            60,
            90,
            120,
            30
        );

        textoTitulo.setForeground(
            Color.WHITE
        );

        textoTitulo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        add(textoTitulo);

        // CAMPO TÍTULO
        campoTitulo =
        new JTextField();

        campoTitulo.setBounds(
            60,
            120,
            460,
            40
        );

        campoTitulo.setBackground(
            Tema.painel
        );

        campoTitulo.setForeground(
            Color.WHITE
        );

        campoTitulo.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        add(campoTitulo);

        // TEXTO PRAZO
        textoPrazo =
        new JLabel("Prazo");

        textoPrazo.setBounds(
            60,
            180,
            120,
            30
        );

        textoPrazo.setForeground(
            Color.WHITE
        );

        textoPrazo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        add(textoPrazo);

        // CALENDÁRIO
        campoPrazo =
        new JDateChooser();

        campoPrazo.setBounds(
            60,
            210,
            460,
            40
        );

        campoPrazo.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        add(campoPrazo);

        // STATUS
        textoStatus =
        new JLabel("Status");

        textoStatus.setBounds(
            60,
            270,
            120,
            30
        );

        textoStatus.setForeground(
            Color.WHITE
        );

        textoStatus.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        add(textoStatus);

        comboStatus =
        new JComboBox<>();

        comboStatus.addItem(
            "Pendente"
        );

        comboStatus.addItem(
            "Concluída"
        );

        comboStatus.setBounds(
            60,
            300,
            460,
            40
        );

        comboStatus.setBackground(
            Tema.painel
        );

        comboStatus.setForeground(
            Color.BLACK
        );

        comboStatus.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        add(comboStatus);

        // MATÉRIA
        textoMateria =
        new JLabel("Matéria");

        textoMateria.setBounds(
            60,
            350,
            120,
            30
        );

        textoMateria.setForeground(
            Color.WHITE
        );

        textoMateria.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        add(textoMateria);

        comboMateria =
        new JComboBox<>();

        comboMateria.addItem(
            "Matemática"
        );

        comboMateria.addItem(
            "Português"
        );

        comboMateria.addItem(
            "História"
        );

        comboMateria.addItem(
            "Geografia"
        );

        comboMateria.addItem(
            "Física"
        );

        comboMateria.addItem(
            "Biologia"
        );

        comboMateria.setBounds(
            60,
            380,
            460,
            40
        );

        comboMateria.setBackground(
            Tema.painel
        );

        comboMateria.setForeground(
            Color.BLACK
        );

        comboMateria.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        add(comboMateria);

        // BOTÃO
        botaoSalvar =
        new JButton(
            "Salvar Tarefa"
        );

        botaoSalvar.setBounds(
            170,
            435,
            220,
            45
        );

        botaoSalvar.setBackground(
            Tema.botao
        );

        botaoSalvar.setForeground(
            Color.WHITE
        );

        botaoSalvar.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                16
            )
        );

        botaoSalvar.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        add(botaoSalvar);

        // EVENTO
        botaoSalvar.addActionListener(e -> {

            try {

                String titulo =
                campoTitulo.getText();

                Date dataSelecionada =
                campoPrazo.getDate();

                SimpleDateFormat formato =
                new SimpleDateFormat(
                    "yyyy-MM-dd"
                );

                String prazo =
                formato.format(
                    dataSelecionada
                );

                String status =
                comboStatus
                .getSelectedItem()
                .toString();

                int materiaId =
                comboMateria
                .getSelectedIndex() + 1;

                if(
                    titulo.isEmpty()
                    ||
                    dataSelecionada == null
                ) {

                    JOptionPane.showMessageDialog(
                        null,
                        "Preencha todos os campos!"
                    );

                    return;
                }

                TarefaDAO dao =
                new TarefaDAO();

                dao.salvarTarefa(
                    titulo,
                    prazo,
                    status,
                    materiaId
                );

                JOptionPane.showMessageDialog(
                    null,
                    "Tarefa cadastrada!"
                );

                dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                    null,
                    "Erro ao salvar tarefa!"
                );

                ex.printStackTrace();
            }
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }
}