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
        Node temp = head;// vc guarda a cabe�a
        head = new Node(data);//vc seta a nova cabe�a
        head.setNext(temp);//vc aponta esse novo n� para antiga cabe�a
    }

    public Object removerTopo() {
       if(! estaVazia()){
           Node temp = head;//guarda a cabe�a
           head.getNext();//pega o proximo n�
           return temp.getDado();//retorna o n� que estava na cabe�a
       }
       return null;
    }

    public Object recuperarTopo() {
        return estaVazia() ? null : head.getDado();//return true se head for null, se n�o, retorna o dado da cabe�a
    }

    public boolean estaVazia() {
        return head == null;
    }
}
