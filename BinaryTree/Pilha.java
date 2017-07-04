package br.uefs.ecomp.siscareta.util;

public class Pilha implements IPilha{
    private Node head;

    private class Node{
      private Node next; // proximo no
      private Object dado;  //dado armazenado
      
        public Node(Object data){
            this.dado = data;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public Object getDado(){
            return dado;
        }
    
    }
    
    public void inserirTopo(Object data) {
        Node temp = head;// vc guarda a cabeça
        head = new Node(data);//vc seta a nova cabeça
        head.setNext(temp);//vc aponta esse novo nó para antiga cabeça
    }

    public Object removerTopo() {
       if(! estaVazia()){
           Node temp = head;//guarda a cabeça
           head.getNext();//pega o proximo nó
           return temp.getDado();//retorna o nó que estava na cabeça
       }
       return null;
    }

    public Object recuperarTopo() {
        return estaVazia() ? null : head.getDado();//return true se head for null, se não, retorna o dado da cabeça
    }

    public boolean estaVazia() {
        return head == null;
    }
}
