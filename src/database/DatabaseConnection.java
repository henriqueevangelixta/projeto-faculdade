package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DATABASE = "projeto-faculdade";

    private DatabaseConnection() {}

    // Este método agora CRIA e RETORNA uma NOVA conexão toda vez que é chamado.

    public static Connection getConnection() {
        try {
            // Não há mais 'if (connection == null)' Ele sempre cria uma nova conexão
            return DriverManager.getConnection(
                    HOST + DATABASE, DB_USER, DB_PASS
            );
        } catch (SQLException e) {
            // Se falhar, lança a exceção para o DAO saber
            throw new RuntimeException("Falha ao conectar ao banco de dados", e);
        }
    }
}