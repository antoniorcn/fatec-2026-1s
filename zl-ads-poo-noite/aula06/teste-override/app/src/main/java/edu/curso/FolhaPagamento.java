package edu.curso;

public class FolhaPagamento {

    double orcamento = 100000.0;

    public void fazerPagamento(Funcionario f, double salario) { 
        f.receberPagamento(salario);
    }
    
}
