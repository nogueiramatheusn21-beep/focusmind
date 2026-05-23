package src.model;
import java.sql.Connection;
import java.sql.PreparedStatement;

import src.util.Conexao;

public class MateriaDAO {

    public void salvarMateria(
        String nome,
        int usuarioId
    ) {

        String sql =
        "INSERT INTO materia(nome, usuario_id) VALUES(?, ?)";

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setInt(2, usuarioId);

            stmt.execute();

            System.out.println("Matéria salva!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}