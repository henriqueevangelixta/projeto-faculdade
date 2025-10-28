package dao;

import database.DatabaseConnection;
import model.Disciplina;
import model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    // --- CREATE (Adicionar) ---
    // Assume que 'disciplina.getCurso()' e 'disciplina.getCurso().getId()' são válidos
    public static void Add(Disciplina disciplina) {

        String sql = "INSERT INTO disciplina (codigo, nome, carga_horaria, curso_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getCodigo());
            stmt.setString(2, disciplina.getNome());
            stmt.setInt(3, disciplina.getCargaHoraria());
            stmt.setInt(4, disciplina.getCurso().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar disciplina: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar disciplina", e);
        }
    }

    // --- READ (Buscar Todos) ---
    public static List<Disciplina> GetAll() {
        List<Disciplina> disciplinas = new ArrayList<>();

        String sql = """
            SELECT d.id, d.codigo, d.nome, d.carga_horaria,
                   c.id AS curso_pk, c.codigo AS curso_codigo, 
                   c.nome AS curso_nome, c.turno AS curso_turno
            FROM disciplina d
            JOIN curso c ON d.curso_id = c.id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // 1. "Monta" o Curso
                Curso curso = new Curso();
                curso.setId(rs.getInt("curso_pk"));
                curso.setCodigo(rs.getString("curso_codigo"));
                curso.setNome(rs.getString("curso_nome"));
                curso.setTurno(rs.getString("curso_turno"));

                // 2. "Monta" a Disciplina
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setCodigo(rs.getString("codigo"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));

                // 3. Associa o Curso à Disciplina
                disciplina.setCurso(curso);

                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplinas: " + e.getMessage());
            throw new RuntimeException("Falha ao buscar disciplinas", e);
        }
        return disciplinas;
    }

    // --- READ (Buscar Um por Código) ---
    public static Disciplina Get(String codigo) {
        String sql = """
            SELECT d.id, d.codigo, d.nome, d.carga_horaria,
                   c.id AS curso_pk, c.codigo AS curso_codigo, 
                   c.nome AS curso_nome, c.turno AS curso_turno
            FROM disciplina d
            JOIN curso c ON d.curso_id = c.id
            WHERE d.codigo = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Se encontrou
                    // 1. "Monta" o Curso
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("curso_pk"));
                    curso.setCodigo(rs.getString("curso_codigo"));
                    curso.setNome(rs.getString("curso_nome"));
                    curso.setTurno(rs.getString("curso_turno"));

                    // 2. "Monta" a Disciplina
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("id"));
                    disciplina.setCodigo(rs.getString("codigo"));
                    disciplina.setNome(rs.getString("nome"));
                    disciplina.setCargaHoraria(rs.getInt("carga_horaria"));

                    // 3. Associa
                    disciplina.setCurso(curso);
                    return disciplina;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplina: " + e.getMessage());
            throw new RuntimeException("Falha ao buscar disciplina", e);
        }
        return null;
    }

    // --- UPDATE (Atualizar) ---
    public static void Update(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET nome = ?, carga_horaria = ?, curso_id = ? WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getCurso().getId()); // Pega o ID do curso
            stmt.setString(4, disciplina.getCodigo()); // O WHERE

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disciplina: " + e.getMessage());
            throw new RuntimeException("Falha ao atualizar disciplina", e);
        }
    }

    // --- DELETE (Remover por Código) ---
    public static void Delete(String codigo) {
        String sql = "DELETE FROM disciplina WHERE codigo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar disciplina: " + e.getMessage());
            throw new RuntimeException("Falha ao deletar disciplina", e);
        }
    }
}