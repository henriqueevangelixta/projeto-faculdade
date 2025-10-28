package view;

import model.Curso;

import java.util.List;
import java.util.Scanner;

public class CursoView {

    public static void Criar(Curso curso) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Código do curso: ");
        curso.setCodigo(scan.next());

        scan.nextLine();

        System.out.print("Nome do curso: ");
        curso.setNome(scan.nextLine());

        System.out.print("Turno: ");
        curso.setTurno(scan.next());
    }

    public static void Consultar(Curso curso) {
        if (curso == null) {
            System.out.println("Curso não encontrado!");
            return;
        }

        System.out.println("Código: " + curso.getCodigo());
        System.out.println("Nome: " + curso.getNome());
        System.out.println("Turno: " + curso.getTurno());

        if (!curso.getDisciplinas().isEmpty()) {
            System.out.println("Disciplinas:");
            curso.getDisciplinas().forEach(d ->System.out.println(" - " + d.getNome() + " (" + d.getCargaHoraria() + "h)") );
        } else {
            System.out.println("Nenhuma disciplina cadastrada neste curso.");
        }
        System.out.println();
    }

    public static void Atualizar(Curso curso) {
        Scanner scan = new Scanner(System.in);

        System.out.print("(" + curso.getNome() + ") - Novo nome do curso (ou Enter p/ manter): ");
        String nome = scan.nextLine();
        if (!nome.isEmpty()) curso.setNome(nome);

        System.out.print("(" + curso.getTurno() + ") - Novo turno (ou Enter p/ manter): ");
        String turno = scan.nextLine();
        if (!turno.isEmpty()) curso.setTurno(turno);
    }

    public static String GetCodigo() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Informe o código do curso: ");
        return scan.next();
    }
}