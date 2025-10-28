package dao;

import database.DatabaseConnection; // Sua classe de conexão
import model.Aluno;
import model.Curso; // Precisamos do Curso para "montar"

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // --- CREATE (Adicionar) ---
    public static void Add(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, nome, idade, curso_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getIdade());
            stmt.setInt(4, aluno.getCurso().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar aluno", e);
        }
    }

    // --- READ (Buscar Todos) ---
    public static List<Aluno> GetAll() {
        List<Aluno> alunos = new ArrayList<>();

        // Usamos JOIN (INNER JOIN) porque a regra é que todo aluno TEM um curso.
        String sql = """
            SELECT a.id, a.matricula, a.nome, a.idade,
                   c.id AS curso_pk, c.codigo AS curso_codigo, 
                   c.nome AS curso_nome, c.turno AS curso_turno
            FROM aluno a
            JOIN curso c ON a.curso_id = c.id
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

                // 2. "Monta" o Aluno
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));

                // 3. Associa o Curso ao Aluno
                aluno.setCurso(curso);

                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos: " + e.getMessage());
            throw new RuntimeException("Falha ao buscar alunos", e);
        }
        return alunos;
    }

    // --- READ (Buscar Um por Matrícula) ---
    public static Aluno Get(String matricula) {

        String sql = """
            SELECT a.id, a.matricula, a.nome, a.idade,
                   c.id AS curso_pk, c.codigo AS curso_codigo, 
                   c.nome AS curso_nome, c.turno AS curso_turno
            FROM aluno a
            JOIN curso c ON a.curso_id = c.id
            WHERE a.matricula = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Se encontrou
                    // 1. "Monta" o Curso
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("curso_pk"));
                    curso.setCodigo(rs.getString("curso_codigo"));
                    curso.setNome(rs.getString("curso_nome"));
                    curso.setTurno(rs.getString("curso_turno"));

                    // 2. "Monta" o Aluno
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setMatricula(rs.getString("matricula"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setIdade(rs.getInt("idade"));

                    // 3. Associa
                    aluno.setCurso(curso);
                    return aluno;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
            throw new RuntimeException("Falha ao buscar aluno", e);
        }
        return null;
    }

    // --- UPDATE (Atualizar) ---
    public static void Update(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, idade = ?, curso_id = ? WHERE matricula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setInt(3, aluno.getCurso().getId());
            stmt.setString(4, aluno.getMatricula());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
            throw new RuntimeException("Falha ao atualizar aluno", e);
        }
    }

    // --- DELETE (Remover por Matrícula) ---
    public static void Delete(String matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
            throw new RuntimeException("Falha ao deletar aluno", e);
        }
    }


}