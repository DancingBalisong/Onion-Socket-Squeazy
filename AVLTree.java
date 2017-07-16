/***************************
Autor: Rafael Araújo e Igor Garcia
Componente Curricular: MI programação 
Concluído em: 16/07/2017
Declaro que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estamos cientes que estes trechos não serão considerados para fins de avaliação.
******************************/
package Util;

import java.util.ArrayList;

import model.Mercadoria;

public class AVLTree {
	protected No root;
	//a chave passou a ser a mercadoria
	
	public class No {

        /**
         * chave é um endereço de mercadoria.
         * para balancear, é necessário que o valor do nó da arvore/sub-arvore esteja entre [-1,1].
         * a raiz inserida no inicio é sempre balanceada.
         */
        private No esquerda;
		private No direita;
		private No pai;
		private Mercadoria chave;
		private int balanceamento;
	
		public No(Mercadoria chave) {
			setEsquerda(setDireita(setpai(null)));
			setBalanceamento(0);
			setchave(chave);
		}
	
		public String toString() {
			return Integer.toString(getchave().getLote());
		}
	
		public Mercadoria getchave() {
			return chave;
		}
	
		public void setchave(Mercadoria chave) {
			this.chave = chave;
		}
	
		public int getBalanceamento() {
			return balanceamento;
		}
	
		public void setBalanceamento(int balanceamento) {
			this.balanceamento = balanceamento;
		}
	
		public No getpai() {
			return pai;
		}
	
		public No setpai(No pai) {
			this.pai = pai;
			return pai;
		}
	
		public No getDireita() {
			return direita;
		}
	
		public No setDireita(No direita) {
			this.direita = direita;
			return direita;
		}
	
		public No getEsquerda() {
			return esquerda;
		}
	
		public void setEsquerda(No esquerda) {
			this.esquerda = esquerda;
		}
	}

	public void inserir(Mercadoria chave) {
		No n = new No(chave);
		inserirAVL(this.root, n);
	}

	/**
         * o primerio item inserido se torna a raiz.
         * se o lote do filho for menor que o lote da mercadoria pai, o lote será inserido na esquerda.
         * seta a mercadoria como filho da esquerda
         * seta o pai da mercadoria inserida
         * checa o balanceamento
         * caso o filho da esquerda não tenha filho, insere no lugar.
         * se não, chama recursivamente até achar um nó vazio.
         * se o lote do filho for maior que o lote da mercadoria pai, o lote será inserido na direita.
         * 
         */
	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.root = aInserir;

		} else {

			if (aInserir.getchave().linha().compareTo(aComparar.getchave().linha()) < 0){
				
				if (aComparar.getEsquerda().equals(null)) {
					aComparar.setEsquerda(aInserir);
					aInserir.setpai(aComparar);
					verificarBalanceamento(aComparar);
					
				} else {
					
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getchave().linha().compareTo(aComparar.getchave().linha()) > 0 ) {
				
				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setpai(aComparar);
					verificarBalanceamento(aComparar);
					
				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}
			} else {
				// O nó já existe
			}
		}
	}

	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getpai() != null) {
			verificarBalanceamento(atual.getpai());
		} else {
			this.root = atual;
		}
	}

	public void remover(Mercadoria chave) {
		//a chave seria o numero lote.
		removerAVL(this.root, chave);
	}

	/**
         * o AVL tenta remover sem contrabalancear.
         * ele começa pelo root, e depois chama recursivamente.
         * se nó for vazio, não faz nada.
         * método de busca binária, para maior, busca-se no lado direito, no menor, lado esquerdo.
         * 
         */
	public void removerAVL(No atual, Mercadoria chave) {
		
		
		if (atual == null) {
			return;
			
		} else {
			
			if (atual.getchave().getLote() > chave.getLote()) {
				removerAVL(atual.getEsquerda(), chave);
			} else if (atual.getchave().getLote() < chave.getLote()) {
				removerAVL(atual.getDireita(), chave);
			} else if (atual.getchave().getLote() == chave.getLote()) {
				deletarNo(atual);
				//remove quando encontra. E se o lote for igual?
				//...
			}
		}
	}
	
	
	/**
         * recebe mercadoria a ser removida, ou o nó dela.
         * caso o nó seja a raiz, remove.
         * r é um nó q remove a mercadoria por um de seus sucessores.
         * chama o sucessor para o nó removido.
         * se o no a ser removido tiver a raiz como pai, troca
         */
	public void deletarNo(No mr) {
		
		No r;
		if (mr.getEsquerda() == null || mr.getDireita() == null) {
			
			if (mr.getpai() == null) {
				this.root = null;
				mr = null;
				return;
			}
			r = mr;
			//se apenas um nó folha.

		} else {
			r = sucessor(mr);
			
			mr.setchave(r.getchave());
			
		}

		No p;//pai.
		if (r.getEsquerda() != null) 
			p = r.getEsquerda();
			//
		else 
			p = r.getDireita();

		if (p != null) 
			p.setpai(r.getpai());
		
		else if (r.getpai() == null)
			this.root = p;
			
		
		else {
			if (r == r.getpai().getEsquerda()) {
				r.getpai().setEsquerda(p);
			} else {
				r.getpai().setDireita(p);
			}
			verificarBalanceamento(r.getpai());
		}
		r = null;//o r acabou n usado.
	}
	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setpai(inicial.getpai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setpai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setpai(direita);

		if (direita.getpai() != null) {

			if (direita.getpai().getDireita() == inicial) {
				direita.getpai().setDireita(direita);
			
			} else if (direita.getpai().getEsquerda() == inicial) {
				direita.getpai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setpai(inicial.getpai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setpai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setpai(esquerda);

		if (esquerda.getpai() != null) {

			if (esquerda.getpai().getDireita() == inicial) {
				esquerda.getpai().setDireita(esquerda);
			
			} else if (esquerda.getpai().getEsquerda() == inicial) {
				esquerda.getpai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		//nó qualquer.
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getpai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getpai();
			}
			return p;
		}
	}

	public int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No No) {
		No.setBalanceamento(altura(No.getDireita()) - altura(No.getEsquerda()));
	}
	
	public Iterator iterador(){
		return new Iterador();
	}
	
	private class Iterador implements Iterator{
		private IQueue queue = new Queue();
		
		public Iterador(){
			((Queue) queue).inserirFinal(root);
		}
		public boolean hasNext(){
			return !queue.estaVazia();
		}
		public Object next(){
			No n = (No) queue.removerInicio();
			if(n.getEsquerda()!=null) 
				((Queue) queue).inserirFinal(n.getEsquerda());
			else if(n.getDireita()!=null) 
				((Queue) queue).inserirFinal(n.getDireita());
			return n;
		}
	}
}
