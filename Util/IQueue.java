package Util;

public interface IQueue {

    public boolean estaVazia();

    public int obterTamanho();

    public void inserirFinal(Object data);

    public Object removerInicio();

    public Object recuperarInicio();   
}
