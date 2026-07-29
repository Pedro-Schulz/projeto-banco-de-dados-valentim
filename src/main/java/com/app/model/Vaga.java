package com.app.model;

public class Vaga {

    private Long idVaga;
    private String tituloVaga;
    private String descricao;
    private double salario;
    private String setor;
    private boolean disponivel;

    public Vaga() {
    }

    public Vaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Vaga(String tituloVaga, String descricao, double salario, String setor, boolean disponivel) {
        this.tituloVaga = tituloVaga;
        this.descricao = descricao;
        this.salario = salario;
        this.setor = setor;
        this.disponivel = disponivel;
    }

    public Vaga(Long idVaga, String tituloVaga, String descricao, double salario, String setor, boolean disponivel) {
        this.idVaga = idVaga;
        this.tituloVaga = tituloVaga;
        this.descricao = descricao;
        this.salario = salario;
        this.setor = setor;
        this.disponivel = disponivel;
    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public String getTituloVaga() {
        return tituloVaga;
    }

    public void setTituloVaga(String tituloVaga) {
        this.tituloVaga = tituloVaga;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}