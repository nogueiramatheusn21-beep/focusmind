package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import src.util.Conexao;
import src.util.Criptografia;

public class UsuarioDAO {

    // SALVAR USUÁRIO
    public void salvarUsuario(
        String nome,
        String email,
        String senha
    ) {

        String sql =
        "INSERT INTO usuario(nome,email,senha) VALUES(?,?,?)";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(
    3,
            Criptografia.md5(senha)
        );

            stmt.execute();

            System.out.println(
                "Usuário salvo!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // LOGIN
    public boolean fazerLogin(
        String email,
        String senha
    ) {

        String sql =
        "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try {

            Connection conn =
            Conexao.conectar();
            senha =
            Criptografia.md5(senha);

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            var rs =
            stmt.executeQuery();

            if(rs.next()) {

                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

        public int buscarIdUsuario(
    String email,
    String senha
) {

    String sql =
    """
    SELECT id

    FROM usuario

    WHERE email = ?
    AND senha = ?
    """;

    try {

        Connection conn =
        Conexao.conectar();

        PreparedStatement stmt =
        conn.prepareStatement(sql);

        stmt.setString(1, email);

        stmt.setString(2, senha);

        var rs =
        stmt.executeQuery();

        if(rs.next()) {

            return rs.getInt("id");
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return 0;
 }

}