package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;

import src.util.Conexao;
import src.util.Sessao;

public class TarefaDAO {

    // SALVAR
    public void salvarTarefa(
        String titulo,
        String prazo,
        String status,
        int materiaId
    ) {

        String sql =
        """
        INSERT INTO tarefa
        (
        titulo,
        prazo,
        status,
        prioridade,
        materia_id,
        usuario_id
        )

        VALUES(?,?,?,?,?,?)
        """;

        String prioridade = "";

        LocalDate hoje =
        LocalDate.now();

        LocalDate dataPrazo =
        LocalDate.parse(prazo);

        long dias =
        ChronoUnit.DAYS.between(
            hoje,
            dataPrazo
        );

        if(dias <= 2) {

            prioridade = "URGENTE";
        }

        else if(dias <= 7) {

            prioridade = "MÉDIA";
        }

        else {

            prioridade = "BAIXA";
        }

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(1, titulo);

            stmt.setString(2, prazo);

            stmt.setString(3, status);

            stmt.setString(4, prioridade);

            stmt.setInt(5, materiaId);

            stmt.setInt(
                6,
                Sessao.idUsuario
            );

            stmt.execute();

            System.out.println(
                "Tarefa salva!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // BUSCAR TAREFAS
    public ArrayList<String[]> buscarTarefas() {

        ArrayList<String[]> lista =
        new ArrayList<>();

        String sql =
        """
        SELECT * FROM tarefa

        WHERE usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(
                1,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            while(rs.next()) {

                String[] linha = {

                    String.valueOf(
                        rs.getInt("id")
                    ),

                    rs.getString("titulo"),

                    rs.getString("prazo"),

                    rs.getString("status"),

                    rs.getString("prioridade")
                };

                lista.add(linha);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // PESQUISAR
    public ArrayList<String[]> pesquisarTarefas(
        String pesquisa
    ) {

        ArrayList<String[]> lista =
        new ArrayList<>();

        String sql =
        """
        SELECT * FROM tarefa

        WHERE titulo LIKE ?
        AND usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(
                1,
                "%" + pesquisa + "%"
            );

            stmt.setInt(
                2,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            while(rs.next()) {

                String[] linha = {

                    String.valueOf(
                        rs.getInt("id")
                    ),

                    rs.getString("titulo"),

                    rs.getString("prazo"),

                    rs.getString("status"),

                    rs.getString("prioridade")
                };

                lista.add(linha);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // FILTRAR PRIORIDADE
    public ArrayList<String[]> filtrarPrioridade(
        String prioridade
    ) {

        ArrayList<String[]> lista =
        new ArrayList<>();

        String sql =
        """
        SELECT * FROM tarefa

        WHERE prioridade = ?
        AND usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(
                1,
                prioridade
            );

            stmt.setInt(
                2,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            while(rs.next()) {

                String[] linha = {

                    String.valueOf(
                        rs.getInt("id")
                    ),

                    rs.getString("titulo"),

                    rs.getString("prazo"),

                    rs.getString("status"),

                    rs.getString("prioridade")
                };

                lista.add(linha);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // CONCLUIR
    public void atualizarStatus(
        int id,
        String novoStatus
    ) {

        String sql =
        """
        UPDATE tarefa

        SET status = ?

        WHERE id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(
                1,
                novoStatus
            );

            stmt.setInt(
                2,
                id
            );

            stmt.execute();

            System.out.println(
                "Status atualizado!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // EXCLUIR
    public void excluirTarefa(
        int id
    ) {

        String sql =
        """
        DELETE FROM tarefa

        WHERE id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(
                1,
                id
            );

            stmt.execute();

            System.out.println(
                "Tarefa excluída!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // EDITAR
    public void editarTitulo(
        int id,
        String novoTitulo
    ) {

        String sql =
        """
        UPDATE tarefa

        SET titulo = ?

        WHERE id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setString(
                1,
                novoTitulo
            );

            stmt.setInt(
                2,
                id
            );

            stmt.execute();

            System.out.println(
                "Título atualizado!"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // TOTAL
    public int contarTarefas() {

        String sql =
        """
        SELECT COUNT(*) AS total

        FROM tarefa

        WHERE usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(
                1,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            if(rs.next()) {

                return rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // PENDENTES
    public int contarPendentes() {

        String sql =
        """
        SELECT COUNT(*) AS total

        FROM tarefa

        WHERE status = 'Pendente'
        AND usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(
                1,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            if(rs.next()) {

                return rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // CONCLUÍDAS
    public int contarConcluidas() {

        String sql =
        """
        SELECT COUNT(*) AS total

        FROM tarefa

        WHERE status = 'Concluída'
        AND usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(
                1,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            if(rs.next()) {

                return rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // URGENTES
    public int contarUrgentes() {

        String sql =
        """
        SELECT COUNT(*) AS total

        FROM tarefa

        WHERE prioridade = 'URGENTE'
        AND usuario_id = ?
        """;

        try {

            Connection conn =
            Conexao.conectar();

            PreparedStatement stmt =
            conn.prepareStatement(sql);

            stmt.setInt(
                1,
                Sessao.idUsuario
            );

            var rs =
            stmt.executeQuery();

            if(rs.next()) {

                return rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}