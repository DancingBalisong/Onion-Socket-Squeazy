package br.uefs.ecomp.siscareta.util;


public interface IFila {
  
    public boolean estaVazia();

    public int obterTamanho();

    public void inserirFinali(Object o);

    public Object removerInicio();

    public Object recuperarInicio();        
}
