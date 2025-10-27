package dao;

import database.DatabaseConnection;
import model.Curso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO {

    private static List<Curso> listCursos = new ArrayList<>();

    public static void Add(Curso curso) {
        listCursos.add(curso);
        System.out.println("Curso cadastrado com sucesso!");
    }

    public static List<Curso> GetAll() {
        return listCursos;
    }

//    public static void Add(Curso curso) {
//        String sql = "INSERT INTO cursos (codigo, nome, turno) VALUES (?, ?, ?)";
//
//        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, curso.getCodigo());
//            stmt.setString(2, curso.getNome());
//            stmt.setString(3, curso.getTurno());
//
//            stmt.executeUpdate();
//            System.out.println("Curso cadastrado com sucesso!");
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
//            throw new RuntimeException("Erro ao cadastrar curso no banco de dados", e);
//        }
//    }

    public static Curso Get(String codigo) {
        return listCursos.stream()
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public static void Delete(String codigo) {
        Curso curso = Get(codigo);
        if (curso != null) {
            listCursos.remove(curso);
            System.out.println("Curso removido com sucesso!");
        }
    }

//    public static Curso Get(String codigo) {
//        String sql = "SELECT * FROM cursos WHERE codigo = ?";
//        Optional<Curso> curso = Optional.empty();
//
//        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
//
//            stmt.setString(1, codigo);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    curso = Optional.of(new Curso(
//                            rs.getString("codigo"),
//                            rs.getString("nome"),
//                            rs.getString("turno")
//                    ));
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao consultar curso: " + e.getMessage());
//            throw new RuntimeException("Erro ao consultar curso no banco de dados", e);
//        }
//
//        return curso.get();
//    }





//    public static void Criar() {
//        String sqlCreateTable = """
//            CREATE TABLE IF NOT EXISTS cursos (
//                codigo VARCHAR(10) PRIMARY KEY,
//                nome VARCHAR(100) NOT NULL,
//                turno VARCHAR(50) NOT NULL
//            );
//        """;
//
//        try (Statement stmt = DatabaseConnection.getConnection().createStatement()) {
//
//            stmt.executeUpdate(sqlCreateTable);
//            System.out.println("Tabela 'cursos' criada com sucesso!");
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao criar a tabela: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}