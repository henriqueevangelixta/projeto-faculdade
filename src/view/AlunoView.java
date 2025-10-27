package view;

import dao.CursoDAO;
import model.Aluno;
import model.Curso;

import java.util.List;
import java.util.Scanner;

public class AlunoView {

    public static void Criar(Aluno aluno) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Nome: ");
        aluno.setNome(scan.nextLine());

        System.out.print("Idade: ");
        aluno.setIdade(scan.nextInt());

        scan.nextLine();

        System.out.print("Matricula: ");
        aluno.setMatricula(scan.nextLine());

        System.out.println("Selecione o curso do aluno:");
        List<Curso> cursos = CursoDAO.GetAll();

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado. Aluno não pode ser matriculado.");
            return;
        }

        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + " - " + cursos.get(i).getNome());
        }
        System.out.print("Opção: ");
        int opc = scan.nextInt();
        scan.nextLine();

        if (opc > 0 && opc <= cursos.size()) {
            aluno.setCurso(cursos.get(opc - 1));
        } else {
            System.out.println("Opção inválida! O aluno será salvo sem curso.");
        }


    }

    public static String GetMatricula() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Informe sua matricula: ");
        String matricula = scan.next();
        return matricula;
    }

    public static void Consultar(Aluno aluno) {
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Idade: " + aluno.getIdade());
        System.out.println("Cadastrado no curso: " + aluno.getCurso().getNome());
        System.out.println();
    }

    public static void Atualizar(Aluno aluno) {
        Scanner scan = new Scanner(System.in);

        System.out.print("("+ aluno.getNome() + ") - Nome: ");
        String nome = scan.next();
        if (!nome.isEmpty()) { aluno.setNome(nome); }

        System.out.print("("+ aluno.getIdade() + ") - Idade: ");
        String idade = scan.nextLine();
        if (!idade.isEmpty()) { aluno.setIdade(Integer.parseInt(idade)); }

    }

//    public static void Listar(List<Aluno> alunos) {
//        for(Aluno a : alunos) {
//            Consultar(a);
//        }
//    }





}