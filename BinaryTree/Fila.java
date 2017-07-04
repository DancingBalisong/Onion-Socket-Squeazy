package br.uefs.ecomp.siscareta.util;

public class Fila<T> implements IFila {//Fila generica
    private Node head, tail;
    private int tamanho;

    private class Node{
        private Node next;
        private T dado;
        
        public Node(T data){
            this.dado = data;
        }
        
        public Node getNext(){
            return this.next;
        }
        public void setNext(Node proximo){
            this.next = proximo;
        }
        
        public T getDado(){
            return this.dado;
        }
        @SuppressWarnings("unused")
		public void setDado(T data){
            this.dado = data;
        }
    }
    
    public void inserirFinal(T data) { //adiciona
        if(head == null){
            head = tail = new Node(data);//cria o tail e head apontando para o mesmo nó
        }else{
            Node temp = tail;//o temp guarda o tail
            tail = new Node(data);//tail vira um novo nó
            temp.setNext(tail);//temp aponta para o novo nó, a cauda, tail - head continua lá de boas
        }
        tamanho++;
    }

    public T removerInicio() {
        T retirado = null;
        if(! estaVazia()){//se head n for null
            retirado = head.getDado();
            if(head == tail){//se tiver um nó com tail e head apontados a ele
                head = tail = null; //tudo vira null, e o GC elimina o espaço n usado
            }else{
                head = head.getNext();//só faz apontar para o proximo
            }
        tamanho--;//se entrar no if excluira de qualquer jeito       
        }
        return retirado;
    }
    
    public T recuperarInicio() {
        return estaVazia() ? null : head.getDado();
    }

    public boolean estaVazia() {
        return head == null;
    }

    public int obterTamanho() {
        return tamanho;
    }
    
    @SuppressWarnings("unchecked")
	public Fila<T> inverterOrdem(Fila<T> fila){
        Pilha inverter = new Pilha();
        T dado;
        while(!fila.estaVazia()){
            dado = fila.removerInicio();
            inverter.inserirTopo(dado);
        } 
        while(!inverter.estaVazia()){
            dado = (T) inverter.removerTopo();
            fila.inserirFinal(dado);
        }
        return fila;
    }

	public void inserirFinali(Object o) {
	}
}

