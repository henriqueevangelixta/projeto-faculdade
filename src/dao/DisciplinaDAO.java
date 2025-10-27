package dao;

import database.DatabaseConnection;
import model.Disciplina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplinaDAO {

    private static List<Disciplina> listDisciplinas = new ArrayList<>();

    public static void Add(Disciplina disciplina) {
        listDisciplinas.add(disciplina);
    }

//    public static void Add(Disciplina disciplina) {
//        String sql = "INSERT INTO disciplinas (codigo, nome, carga_horaria) VALUES (?, ?, ?)";
//
//        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, disciplina.getCodigo());
//            stmt.setString(2, disciplina.getNome());
//            stmt.setInt(3, disciplina.getCargaHoraria());
//
//            stmt.executeUpdate();
//            System.out.println("Disciplina cadastrada com sucesso!");
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
//            throw new RuntimeException("Erro ao cadastrar disciplina no banco de dados", e);
//        }
//    }

    public static Disciplina Get(String codigo) {
        return listDisciplinas.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst()
                .get();
    }

//    public static Disciplina Get(String codigo) {
//        String sql = "SELECT * FROM disciplinas WHERE codigo = ?";
//        Optional<Disciplina> disciplina = Optional.empty();
//
//        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, codigo);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    disciplina = Optional.of(new Disciplina(
//                            rs.getString("codigo"),
//                            rs.getString("nome"),
//                            rs.getInt("carga_horaria")
//                    ));
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao consultar disciplina: " + e.getMessage());
//            throw new RuntimeException("Erro ao consultar disciplina no banco de dados", e);
//        }
//
//        return disciplina.get();
//    }

    public static List<Disciplina> GetAllByNameContains(String name) {
        return listDisciplinas.stream()
                .filter(d -> d.getNome().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public static List<Disciplina> GetAllByCargaHoraria(int cargaHoraria) {
        return listDisciplinas.stream()
                .filter(d -> d.getCargaHoraria() == cargaHoraria)
                .toList();
    }

    public static List<Disciplina> GetAll() {
        return listDisciplinas;
    }

    public static void Delete(String codigo) {
        Disciplina disciplina = Get(codigo);
        listDisciplinas.remove(disciplina);
    }

//    public static void Criar() {
//        String sqlCreateTable = """
//            CREATE TABLE IF NOT EXISTS disciplinas (
//                codigo VARCHAR(10) PRIMARY KEY,
//                nome VARCHAR(100) NOT NULL,
//                carga_horaria INT NOT NULL
//            );
//        """;
//
//        try (Statement stmt = DatabaseConnection.getConnection().createStatement()) {
//
//            stmt.executeUpdate(sqlCreateTable);
//            System.out.println("Tabela 'disciplinas' criada com sucesso!");
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao criar a tabela: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}