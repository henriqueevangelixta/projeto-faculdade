package controller;

import dao.DisciplinaDAO;
import model.Curso;
import model.Disciplina;
import view.DisciplinaView;

public class DisciplinaController {

    public static void Criar() {
        Disciplina disciplina = new Disciplina();
        DisciplinaView.Criar(disciplina);
        if (disciplina != null) {
            DisciplinaDAO.Add(disciplina);
            System.out.println("Disciplina cadastrada com sucesso!");
        } else {
            System.out.println("A disciplina não foi cadastrada!");
        }
    }

    public static void Consultar() {
        String codigo = DisciplinaView.GetCodigo();
        Disciplina disciplina = DisciplinaDAO.Get(codigo);
        DisciplinaView.Consultar(disciplina);
    }

    public static void Atualizar() {
        String codigo = DisciplinaView.GetCodigo();
        Disciplina disciplina = DisciplinaDAO.Get(codigo);
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }
        DisciplinaView.Atualizar(disciplina);

        try {
            DisciplinaDAO.Update(disciplina);
            System.out.println("Disciplina atualizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Falha ao atualizar a disciplina no banco de dados:");
            e.printStackTrace();
        }
    }

    public static void Deletar() {
        String codigo = DisciplinaView.GetCodigo();
        Disciplina disciplinaParaRemover = DisciplinaDAO.Get(codigo);

        if (disciplinaParaRemover == null) {
            System.out.println("Erro: Disciplina não encontrada.");
            return;
        }

        Curso cursoPai = disciplinaParaRemover.getCurso();

        if (cursoPai != null) {
            cursoPai.removeDisciplina(disciplinaParaRemover);
        }

        DisciplinaDAO.Delete(codigo);
        System.out.println("Disciplina removida com sucesso!");
    }
}