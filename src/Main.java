import controller.MenuController;
import dao.AlunoDAO;
import database.DatabaseConnection;
import view.MenuView;

public class Main {
    public static void main(String[] args) {

        //DatabaseConnection.getConnection();
        //AlunoDAO.Criar();
        //AlunoDAO.Add();
        System.out.println("=====================================");
        System.out.println("   SISTEMA DE GESTÃO DA FACULDADE   ");
        System.out.println("=====================================");
        System.out.println("Iniciando sistema...\n");

        MenuController.show();
        //DatabaseConnection.closeConnection();
        System.out.println("\nSistema finalizado. Até logo!");

    }
}