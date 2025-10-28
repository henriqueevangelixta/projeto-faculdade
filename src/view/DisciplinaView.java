package view;

import dao.CursoDAO;
import model.Curso;
import model.Disciplina;

import java.util.List;
import java.util.Scanner;

public class DisciplinaView {

    public static boolean Criar(Disciplina disciplina) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Código: ");
        disciplina.setCodigo(scan.nextLine()); // Usar nextLine()

        System.out.print("Nome: ");
        disciplina.setNome(scan.nextLine()); // Usar nextLine()

        int cargaHoraria = 0;
        while (true) {
            System.out.print("Carga horária: ");
            if (scan.hasNextInt()) {
                cargaHoraria = scan.nextInt();
                scan.nextLine(); // Limpa o buffer do nextInt()
                disciplina.setCargaHoraria(cargaHoraria);
                break;
            } else {
                System.out.println("Inválido! Digite apenas números.");
                scan.next(); // Limpa a entrada inválida (ex: "abc")
            }
        }

        System.out.println("Selecione o curso ao qual pertence esta disciplina:");
        List<Curso> cursos = CursoDAO.GetAll();

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado. A disciplina não pode ser criada.");
            return false;
        }

        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + " - " + cursos.get(i).getNome());
        }

        int opc = 0;
        while (true) {
            System.out.print("Opção: ");
            if (scan.hasNextInt()) {
                opc = scan.nextInt();
                scan.nextLine(); // Limpa o buffer

                if (opc > 0 && opc <= cursos.size()) {
                    disciplina.setCurso(cursos.get(opc - 1));
                    cursos.get(opc - 1).addDisciplina(disciplina);
                    break;
                } else {
                    System.out.println("Opção inválida! Digite um número entre 1 e " + cursos.size() + ".");
                }
            } else {
                System.out.println("Entrada inválida! Digite apenas o número da opção.");
                scan.next();
            }
        }
        return true;
    }

    public static void Consultar(Disciplina disciplina) {
        System.out.println("Código: " + disciplina.getCodigo());
        System.out.println("Nome: " + disciplina.getNome());
        System.out.println("Carga horária: " + disciplina.getCargaHoraria());
        System.out.println();
    }

    public static void Atualizar(Disciplina disciplina) {
        Scanner scan = new Scanner(System.in);

        System.out.print("(" + disciplina.getNome() + ") - Novo nome (ou Enter p/ manter): ");
        String nome = scan.nextLine();
        if (!nome.isEmpty()) {
            disciplina.setNome(nome);
        }

        System.out.print("(" + disciplina.getCargaHoraria() + ") - Nova carga horária (ou Enter p/ manter): ");
        String cargaStr = scan.nextLine();
        if (!cargaStr.isEmpty()) {
            try {
                disciplina.setCargaHoraria(Integer.parseInt(cargaStr));
            } catch (NumberFormatException e) {
                System.out.println("Carga horária inválida, mantendo a anterior.");
            }
        }

        System.out.println("Curso atual: " + disciplina.getCurso().getNome());
    }

    public static String GetCodigo() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Informe o código da disciplina: ");
        String codigo = scan.next();
        return codigo;
    }
}
