package model;

import java.util.List;

public class Aluno {

    private String nome;
    private int idade;
    private String matricula;
    private List<Curso> Curso;


    public Aluno(String nome, int idade, String matricula) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Curso> getCurso() {
        return Curso;
    }

//    public void setCurso(List<Curso> curso) {
//        Curso = curso;
//    }
}
