package src.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.util.Tema;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;

public class TelaPrincipal extends JFrame {

    JPanel menuLateral;

    JLabel titulo;
    JLabel subtitulo;
    JLabel logo;

    JButton botaoDashboard;
    JButton botaoTarefas;
    JButton botaoCadastrar;
    JButton botaoSair;

    public TelaPrincipal() {

        setTitle("Sistema de Estudos");

        setSize(1000, 650);

        setLayout(null);

        setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        getContentPane().setBackground(
            Tema.fundo
        );

        // MENU LATERAL
        menuLateral =
        new JPanel();

        menuLateral.setLayout(null);

        menuLateral.setBounds(
            0,
            0,
            250,
            650
        );

        menuLateral.setBackground(
            Tema.painel
        );

        add(menuLateral);

        // LOGO
        ImageIcon logoOriginal =
        new ImageIcon(
            "C:/Users/User/sistemas-estudos/resources/logo.png"
        );

        Image imagem =
        logoOriginal.getImage();

        Image novaImagem =
        imagem.getScaledInstance(
            100,
            100,
            Image.SCALE_SMOOTH
        );

        ImageIcon logoFinal =
        new ImageIcon(novaImagem);

        logo =
        new JLabel(logoFinal);

        logo.setBounds(
            70,
            20,
            100,
            100
        );

        menuLateral.add(logo);

        // TÍTULO
        titulo =
        new JLabel(
            "FocusMind"
        );

        titulo.setBounds(
            45,
            130,
            200,
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

        menuLateral.add(titulo);

        // SUBTÍTULO
        subtitulo =
        new JLabel(
            "Sistema de Estudos"
        );

        subtitulo.setBounds(
            45,
            165,
            200,
            30
        );

        subtitulo.setForeground(
            Color.LIGHT_GRAY
        );

        subtitulo.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                14
            )
        );

        menuLateral.add(subtitulo);

        // BOTÃO DASHBOARD
        botaoDashboard =
        criarBotao(
            "Dashboard",
            240
        );

        menuLateral.add(
            botaoDashboard
        );

        // BOTÃO TAREFAS
        botaoTarefas =
        criarBotao(
            "Tarefas",
            310
        );

        menuLateral.add(
            botaoTarefas
        );

        // BOTÃO CADASTRAR
        botaoCadastrar =
        criarBotao(
            "Cadastrar",
            380
        );

        menuLateral.add(
            botaoCadastrar
        );

        // BOTÃO SAIR
        botaoSair =
        criarBotao(
            "Sair",
            500
        );

        menuLateral.add(
            botaoSair
        );

        // TEXTO CENTRAL
        JLabel textoCentro =
        new JLabel(
            "Bem-vindo ao FocusMind"
        );

        textoCentro.setBounds(
            340,
            220,
            500,
            50
        );

        textoCentro.setForeground(
            Color.WHITE
        );

        textoCentro.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                36
            )
        );

        add(textoCentro);

        JLabel textoMenor =
        new JLabel(
            "Organize seus estudos de forma inteligente."
        );

        textoMenor.setBounds(
            340,
            280,
            500,
            40
        );

        textoMenor.setForeground(
            Color.LIGHT_GRAY
        );

        textoMenor.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                20
            )
        );

        add(textoMenor);

        // EVENTOS
        botaoDashboard.addActionListener(e -> {

            new TelaDashboard();
        });

        botaoTarefas.addActionListener(e -> {

            new TelaTarefas();
        });

        botaoCadastrar.addActionListener(e -> {

            new TelaCadastroTarefa();
        });

        botaoSair.addActionListener(e -> {

            System.exit(0);
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // MÉTODO BOTÃO
    public JButton criarBotao(
        String texto,
        int y
    ) {

        JButton botao =
        new JButton(texto);

        botao.setBounds(
            25,
            y,
            200,
            50
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
                16
            )
        );

        botao.setFocusPainted(false);

        botao.setBorderPainted(false);

        botao.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        return botao;
    }
}