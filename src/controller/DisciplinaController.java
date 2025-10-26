package controller;

import dao.DisciplinaDAO;
import model.Disciplina;
import view.DisciplinaView;

public class DisciplinaController {

    public static void Criar() {
        Disciplina disciplina = new Disciplina();
        DisciplinaView.Criar(disciplina);
        if (disciplina != null) {
            DisciplinaDAO.Add(disciplina);
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
    }

    public static void Deletar() {
        String codigo = DisciplinaView.GetCodigo();
        DisciplinaDAO.Delete(codigo);
    }
}
