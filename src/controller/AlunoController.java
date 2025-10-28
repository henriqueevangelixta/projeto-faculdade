package controller;

import dao.AlunoDAO;
import model.Aluno;
import view.AlunoView;

public class AlunoController {

    public static void Criar() {
        Aluno aluno = new Aluno();
        AlunoView.Criar(aluno);
        if (aluno != null) {
            AlunoDAO.Add(aluno);
        }
    }

    public static void Consultar() {
        String matricula = AlunoView.GetMatricula();
        Aluno aluno = AlunoDAO.Get(matricula);
        AlunoView.Consultar(aluno);
    }

    public static void Atualizar() {
        String matricula = AlunoView.GetMatricula();

        Aluno aluno = AlunoDAO.Get(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        AlunoView.Atualizar(aluno);

        try {
            AlunoDAO.Update(aluno);
            System.out.println("\nAluno atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("\nFalha ao atualizar o aluno no banco de dados:");
            e.printStackTrace();
        }
    }

    public static void Deletar() {
        String matricula = AlunoView.GetMatricula();

        Aluno aluno = AlunoDAO.Get(matricula);
        if (aluno == null) {
            System.out.println("Erro: Aluno não encontrado com a matrícula " + matricula);
            return;
        }

        try {
            AlunoDAO.Delete(matricula);
            System.out.println("Aluno removido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao remover o aluno:");
            e.printStackTrace();
        }
    }

}
