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
        DisciplinaView.Atualizar(disciplina);
        System.out.println("Disciplina atualizada com sucesso!");
    }

    public static void Deletar() {
        String codigo = DisciplinaView.GetCodigo();

        // Busca o objeto Disciplina COMPLETO
        Disciplina disciplinaParaRemover = DisciplinaDAO.Get(codigo);

        // Verifica se a disciplina foi encontrada
        if (disciplinaParaRemover == null) {
            System.out.println("Erro: Disciplina não encontrada.");
            return;
        }
        // Pega o "pai" da disciplina
        Curso cursoPai = disciplinaParaRemover.getCurso();

        // Manda o "pai" remover a disciplina da sua lista interna
        if (cursoPai != null) {
            cursoPai.removeDisciplina(disciplinaParaRemover);
        }

        // Remove a disciplina do DAO principal
        DisciplinaDAO.Delete(codigo);

        System.out.println("Disciplina removida com sucesso!");
    }
}