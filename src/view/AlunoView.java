package view;

import dao.CursoDAO;
import model.Aluno;
import model.Curso;
import model.Disciplina;

import java.util.List;
import java.util.Scanner;

public class AlunoView {

    public static void Criar(Aluno aluno) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Nome: ");
        aluno.setNome(scan.nextLine());

        int idade = 0;
        while (true) {
            System.out.print("Idade: ");
            if (scan.hasNextInt()) {
                idade = scan.nextInt();
                scan.nextLine(); // Limpa o buffer do nextInt()
                aluno.setIdade(idade);
                break; // Sai do loop, pois a idade é válida
            } else {
                System.out.println("Idade inválida! Digite apenas números.");
                scan.next(); // Limpa a entrada inválida (ex: "abc")
            }
        }

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

        int opc = 0;
        while (true) {
            System.out.print("Opção: ");

            if (scan.hasNextInt()) {
                opc = scan.nextInt();
                scan.nextLine(); // Limpa o buffer

                // Verifica se a opção está no intervalo correto
                if (opc > 0 && opc <= cursos.size()) {
                    aluno.setCurso(cursos.get(opc - 1));
                    break;
                } else {
                    System.out.println("Opção inválida! Digite um número entre 1 e " + cursos.size() + ".");
                }
            } else {
                System.out.println("Entrada inválida! Digite apenas o número da opção.");
                scan.next(); // Limpa a entrada inválida (ex: "a")
            }
        }
        System.out.println("Aluno cadastrado com sucesso!");
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

        Curso cursoDoAluno = aluno.getCurso();

        if (cursoDoAluno != null) {
            System.out.println("Cadastrado no curso: " + cursoDoAluno.getNome());
            // Pega a lista de disciplinas de DENTRO do objeto Curso
            List<Disciplina> disciplinasDoCurso = cursoDoAluno.getDisciplinas();
            // Verifica se o curso tem disciplinas cadastradas
            if (disciplinasDoCurso == null || disciplinasDoCurso.isEmpty()) {
                System.out.println("-> Disciplinas: (Este curso ainda não possui disciplinas)");
            } else {
                // Se tiver, faz um loop e imprime o nome de cada uma
                System.out.println("Disciplinas Cadastradas:");
                for (Disciplina disciplina : disciplinasDoCurso) {
                    System.out.println("- " + disciplina);
                }
            }
        }
        System.out.println();
    }

    public static void Atualizar(Aluno aluno) {
        Scanner scan = new Scanner(System.in);

        System.out.print("("+ aluno.getNome() + ") - Nome: ");
        String nome = scan.nextLine();
        if (!nome.isEmpty()) {
            aluno.setNome(nome);
        }

        System.out.print("("+ aluno.getIdade() + ") - Idade: ");
        String idadeStr = scan.nextLine(); // Mudei para 'idadeStr' para clareza

        if (!idadeStr.isEmpty()) {
            try {
                // .trim() remove espaços em branco antes ou depois
                aluno.setIdade(Integer.parseInt(idadeStr.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Entrada de idade inválida. Mantendo a idade anterior.");
            }
        }
    }

}