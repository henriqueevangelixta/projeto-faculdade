package dao;

import database.DatabaseConnection;
import model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    // (CREATE)
    public static void Add(Curso curso) {
        String sql = "INSERT INTO curso (codigo, nome, turno) VALUES (?, ?, ?)";

        // Usamos o 'try-with-resources' para garantir que a conexão e o 'statement' sejam fechados automaticamente
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getCodigo());
            stmt.setString(2, curso.getNome());
            stmt.setString(3, curso.getTurno());

            stmt.executeUpdate();
            System.out.println("Curso cadastrado com sucesso no banco!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar curso no banco de dados", e);
        }
    }

    // (READ)
    public static List<Curso> GetAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, codigo, nome, turno FROM curso";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso();

                curso.setId(rs.getInt("id"));
                curso.setCodigo(rs.getString("codigo"));
                curso.setNome(rs.getString("nome"));
                curso.setTurno(rs.getString("turno"));

                cursos.add(curso);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar cursos: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar cursos no banco de dados", e);
        }

        return cursos;
    }

    // (READ)
    public static Curso Get(String codigo) {
        String sql = "SELECT id, codigo, nome, turno FROM curso WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setCodigo(rs.getString("codigo"));
                    curso.setNome(rs.getString("nome"));
                    curso.setTurno(rs.getString("turno"));
                    return curso;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar curso no banco de dados", e);
        }

        return null;
    }

    // 'Update' (UPDATE)
    public static void Update(Curso curso) {
        String sql = "UPDATE curso SET nome = ?, turno = ? WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getTurno());
            stmt.setString(3, curso.getCodigo());

            int rowsAffected = stmt.executeUpdate(); // Executa o UPDATE

            if (rowsAffected > 0) {
                System.out.println("Curso atualizado com sucesso!");
            } else {
                System.out.println("Nenhum curso encontrado com esse código para atualizar.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar curso no banco de dados", e);
        }
    }

    // 'Delete' (DELETE)
    public static void Delete(String codigo) {
        String sql = "DELETE FROM curso WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Curso removido com sucesso!");
            } else {
                System.out.println("Nenhum curso encontrado com esse código.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar curso no banco de dados", e);
        }
    }


}