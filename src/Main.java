import controller.MenuController;
import dao.AlunoDAO;
import dao.CursoDAO;
import database.DatabaseConnection;
import view.MenuView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getConnection();
        System.out.println("=====================================");
        System.out.println("   SISTEMA DE GESTÃO DA FACULDADE   ");
        System.out.println("=====================================");
        System.out.println("Iniciando sistema...\n");

        MenuController.show();
        // DatabaseConnection.closeConnection();
        System.out.println("\nSistema finalizado. Até logo!");

    }
}