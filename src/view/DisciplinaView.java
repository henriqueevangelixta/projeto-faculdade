package view;

import dao.CursoDAO;
import model.Curso;
import model.Disciplina;

import java.util.List;
import java.util.Scanner;

public class DisciplinaView {

    public static void Criar(Disciplina disciplina) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Código: ");
        disciplina.setCodigo(scan.next());

        System.out.print("Nome: ");
        disciplina.setNome(scan.next());

        System.out.print("Carga horária: ");
        disciplina.setCargaHoraria(scan.nextInt());

        System.out.println("Selecione o curso ao qual pertence esta disciplina:");
        List<Curso> cursos = CursoDAO.GetAll();
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + " - " + cursos.get(i).getNome());
        }
        int opc = scan.nextInt();
        disciplina.setCurso(cursos.get(opc - 1));
        cursos.get(opc - 1).addDisciplina(disciplina);
    }

    public static void Atualizar(Disciplina disciplina) {
        Scanner scan = new Scanner(System.in);

        System.out.print("(" + disciplina.getCodigo() + ") - Código: ");
        String codigo = scan.nextLine();
        if (!codigo.isEmpty()) { disciplina.setCodigo(codigo); }

        System.out.print("(" + disciplina.getNome() + ") - Nome: ");
        String nome = scan.nextLine();
        if (!nome.isEmpty()) { disciplina.setNome(nome); }

        System.out.print("(" + disciplina.getCargaHoraria() + ") - Carga horária: ");
        String carga = scan.nextLine();
        if (!carga.isEmpty()) { disciplina.setCargaHoraria(Integer.parseInt(carga)); }
    }

    public static void Listar(List<Disciplina> disciplinas) {
        for (Disciplina d : disciplinas) {
            Consultar(d);
        }
    }

    public static void Consultar(Disciplina disciplina) {
        System.out.println("Código: " + disciplina.getCodigo());
        System.out.println("Nome: " + disciplina.getNome());
        System.out.println("Carga horária: " + disciplina.getCargaHoraria());
        System.out.println();
    }

    public static String GetCodigo() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Informe o código da disciplina: ");
        String codigo = scan.next();
        return codigo;
    }
}