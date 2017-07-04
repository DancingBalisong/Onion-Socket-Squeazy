package br.uefs.ecomp.siscareta.util;

public interface IPilha {
    
    public boolean estaVazia();
    
    public Object removerTopo();
    
    public void inserirTopo(Object obj);
    
    public Object recuperarTopo();
}

