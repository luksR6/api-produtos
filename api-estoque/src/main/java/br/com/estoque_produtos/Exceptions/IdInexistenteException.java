package br.com.estoque_produtos.Exceptions;

public class IdInexistenteException extends Exception{
    public IdInexistenteException(String mensagem){
        super(mensagem);
    }
}
