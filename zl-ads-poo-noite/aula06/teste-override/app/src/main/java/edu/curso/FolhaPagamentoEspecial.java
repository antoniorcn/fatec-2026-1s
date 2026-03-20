package edu.curso;

public class FolhaPagamentoEspecial extends FolhaPagamento {

    @Override
    public void fazerPagamento(Funcionario f, double salario ) { 
        f.receberPagamento( salario * 1.2 );
    }
    
}
