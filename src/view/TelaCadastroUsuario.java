package src.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.dao.UsuarioDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class TelaCadastroUsuario extends JFrame {

    JLabel textoTitulo;
    JLabel textoNome;
    JLabel textoEmail;
    JLabel textoSenha;

    JTextField campoNome;
    JTextField campoEmail;

    JPasswordField campoSenha;

    JButton botaoCadastrar;

    public TelaCadastroUsuario() {

        setTitle("Cadastro de Usuário");

        setSize(450, 420);

        setLayout(null);

        getContentPane().setBackground(
            new Color(245,245,245)
        );

        // TÍTULO
        textoTitulo =
        new JLabel("CADASTRAR USUÁRIO");

        textoTitulo.setBounds(
            70,
            20,
            320,
            40
        );

        textoTitulo.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                24
            )
        );

        add(textoTitulo);

        // NOME
        textoNome =
        new JLabel("Nome:");

        textoNome.setBounds(
            50,
            80,
            100,
            30
        );

        add(textoNome);

        campoNome =
        new JTextField();

        campoNome.setBounds(
            50,
            110,
            330,
            35
        );

        add(campoNome);

        // EMAIL
        textoEmail =
        new JLabel("Email:");

        textoEmail.setBounds(
            50,
            160,
            100,
            30
        );

        add(textoEmail);

        campoEmail =
        new JTextField();

        campoEmail.setBounds(
            50,
            190,
            330,
            35
        );

        add(campoEmail);

        // SENHA
        textoSenha =
        new JLabel("Senha:");

        textoSenha.setBounds(
            50,
            240,
            100,
            30
        );

        add(textoSenha);

        campoSenha =
        new JPasswordField();

        campoSenha.setBounds(
            50,
            270,
            330,
            35
        );

        add(campoSenha);

        // BOTÃO
        botaoCadastrar =
        new JButton("Cadastrar");

        botaoCadastrar.setBounds(
            120,
            330,
            200,
            40
        );

        botaoCadastrar.setBackground(
            Color.GREEN
        );

        botaoCadastrar.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                14
            )
        );

        add(botaoCadastrar);

        // EVENTO
        botaoCadastrar.addActionListener(e -> {

            String nome =
            campoNome.getText();

            String email =
            campoEmail.getText();

            String senha =
            new String(
                campoSenha.getPassword()
            );

            if(
                nome.isEmpty()
                ||
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

            dao.salvarUsuario(
                nome,
                email,
                senha
            );

            JOptionPane.showMessageDialog(
                null,
                "Usuário cadastrado!"
            );

            dispose();
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }
}
