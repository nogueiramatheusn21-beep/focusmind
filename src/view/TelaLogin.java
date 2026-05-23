package src.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.dao.UsuarioDAO;
import src.util.Sessao;
import src.util.Tema;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;

public class TelaLogin extends JFrame {

    JLabel textoTitulo;
    JLabel textoEmail;
    JLabel textoSenha;
    JLabel imagemLogo;

    JTextField campoEmail;

    JPasswordField campoSenha;

    JButton botaoLogin;
    JButton botaoCadastro;

    public TelaLogin() {

        setTitle("Login");

        setSize(500, 500);

        setLayout(null);

        setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        // FUNDO
        getContentPane().setBackground(
            Tema.fundo
        );

        // LOGO
        ImageIcon logoOriginal =
        new ImageIcon(
            "C:/Users/User/sistemas-estudos/resources/logo.png"
        );

        Image imagem =
        logoOriginal.getImage();

        Image novaImagem =
        imagem.getScaledInstance(
            120,
            120,
            Image.SCALE_SMOOTH
        );

        ImageIcon logo =
        new ImageIcon(novaImagem);

        imagemLogo =
        new JLabel(logo);

        imagemLogo.setBounds(
            180,
            10,
            120,
            120
        );

        add(imagemLogo);

        // TÍTULO
        textoTitulo =
        new JLabel(
            "LOGIN DO SISTEMA"
        );

        textoTitulo.setBounds(
            110,
            130,
            320,
            40
        );

        textoTitulo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                26
            )
        );

        textoTitulo.setForeground(
            Tema.texto
        );

        add(textoTitulo);

        // EMAIL
        textoEmail =
        new JLabel("Email");

        textoEmail.setBounds(
            60,
            200,
            100,
            30
        );

        textoEmail.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        textoEmail.setForeground(
            Tema.texto
        );

        add(textoEmail);

        campoEmail =
        new JTextField();

        campoEmail.setBounds(
            60,
            230,
            360,
            40
        );

        campoEmail.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        campoEmail.setBackground(
            Tema.painel
        );

        campoEmail.setForeground(
            Color.WHITE
        );

        add(campoEmail);

        // SENHA
        textoSenha =
        new JLabel("Senha");

        textoSenha.setBounds(
            60,
            290,
            100,
            30
        );

        textoSenha.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        textoSenha.setForeground(
            Tema.texto
        );

        add(textoSenha);

        campoSenha =
        new JPasswordField();

        campoSenha.setBounds(
            60,
            320,
            360,
            40
        );

        campoSenha.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                15
            )
        );

        campoSenha.setBackground(
            Tema.painel
        );

        campoSenha.setForeground(
            Color.WHITE
        );

        add(campoSenha);

        // BOTÃO LOGIN
        botaoLogin =
        new JButton("Entrar");

        botaoLogin.setBounds(
            60,
            390,
            160,
            45
        );

        botaoLogin.setBackground(
            Tema.botao
        );

        botaoLogin.setForeground(
            Color.WHITE
        );

        botaoLogin.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        botaoLogin.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        add(botaoLogin);

        // BOTÃO CADASTRO
        botaoCadastro =
        new JButton("Cadastrar");

        botaoCadastro.setBounds(
            260,
            390,
            160,
            45
        );

        botaoCadastro.setBackground(
            Tema.botao
        );

        botaoCadastro.setForeground(
            Color.WHITE
        );

        botaoCadastro.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                15
            )
        );

        botaoCadastro.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        add(botaoCadastro);

        // LOGIN
        botaoLogin.addActionListener(e -> {

            String email =
            campoEmail.getText();

            String senha =
            new String(
                campoSenha.getPassword()
            );

            if(
                email.isEmpty()
                ||
                senha.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                    null,
                    "Preencha todos os campos!"
                );

                return;
            }

            UsuarioDAO dao =
            new UsuarioDAO();

            boolean login =
            dao.fazerLogin(
                email,
                senha
            );

            if(login) {

                int idUsuario =
                dao.buscarIdUsuario(
                    email,
                    senha
                );

                Sessao.idUsuario =
                idUsuario;

                JOptionPane.showMessageDialog(
                    null,
                    "Login realizado!"
                );

                new TelaPrincipal();

                dispose();
            }

            else {

                JOptionPane.showMessageDialog(
                    null,
                    "Email ou senha incorretos!"
                );
            }
        });

        // CADASTRO
        botaoCadastro.addActionListener(e -> {

            new TelaCadastroUsuario();
        });

        setLocationRelativeTo(null);

        repaint();

        revalidate();

        setVisible(true);
    }
}