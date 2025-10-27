package controller;

import dao.CursoDAO;
import model.Curso;
import view.CursoView;

public class CursoController {

    public static void Criar() {
        Curso curso = new Curso();
        CursoView.Criar(curso);
        if (curso != null) {
            CursoDAO.Add(curso);
        }
    }

    public static void Consultar() {
        String codigo = CursoView.GetCodigo();
        Curso curso = CursoDAO.Get(codigo);
        CursoView.Consultar(curso);
    }

    public static void Listar() {
        CursoView.Listar(CursoDAO.GetAll());
    }

    public static void Atualizar() {
        String codigo = CursoView.GetCodigo();
        Curso curso = CursoDAO.Get(codigo);
        CursoView.Atualizar(curso);
    }

    public static void Deletar() {
        String codigo = CursoView.GetCodigo();
        CursoDAO.Delete(codigo);
    }
}
