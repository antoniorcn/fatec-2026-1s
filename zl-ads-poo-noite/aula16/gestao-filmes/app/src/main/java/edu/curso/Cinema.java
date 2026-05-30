package edu.curso;

public class Cinema {
    private long id = 0;
    private String franquia = "";
    private int qtdSalas = 0;
    private String endereco = "";
    private String cidade = "";
    private String estado = "";

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFranquia() {
        return franquia;
    }
    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public int getQtdSalas() {
        return qtdSalas;
    }
    public void setQtdSalas(int qtdSalas) {
        this.qtdSalas = qtdSalas;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }   
}
