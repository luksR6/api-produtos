package br.com.estoque_produtos.Exceptions;

public class CodigoBarrasException extends Exception{
    public CodigoBarrasException(String mensagem){
        super(mensagem);
    }
}
