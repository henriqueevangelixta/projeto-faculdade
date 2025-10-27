import controller.MenuController;
import dao.AlunoDAO;
import database.DatabaseConnection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getConnection();
        //AlunoDAO.Criar();
        //AlunoDAO.Add();
        MenuController.show();
        DatabaseConnection.closeConnection();


            System.out.println("=====================================");
            System.out.println("   SISTEMA DE GESTÃO DA FACULDADE   ");
            System.out.println("=====================================");
            System.out.println("Iniciando sistema...\n");

            // Inicializa o menu principal
            MenuController.show();

            System.out.println("\nSistema finalizado. Até logo!");

    }
}