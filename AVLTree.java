package Util;

import java.util.ArrayList;

import model.Mercadoria;

public class AVLTree {
	protected No root;
	//a chave passou a ser a mercadoria
	
	public class No {

        private No esquerda;
		private No direita;
		private No pai;
		private Mercadoria chave;//chave � um endere�o de mercadoria.
		private int balanceamento;//para balancear, � necess�rio que o valor do n� da arvore/sub-arvore esteja entre [-1,1].
	
		public No(Mercadoria chave) {
			setEsquerda(setDireita(setpai(null)));//n�o tem pai
			setBalanceamento(0);//a raiz inserida no inicio � sempre balanceada.
			setchave(chave);//insere o
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

	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.root = aInserir;//o primerio item inserido se torna a raiz.

		} else {

			if (aInserir.getchave().getLote() < aComparar.getchave().getLote()) {
				//se o lote do filho for menor que o lote da mercadoria pai, o lote ser� inserido na esquerda.
				if (aComparar.getEsquerda().equals(null)) {
					aComparar.setEsquerda(aInserir);//seta a mercadoria como filho da esquerda
					aInserir.setpai(aComparar);//seta o pai da mercadoria inserida
					verificarBalanceamento(aComparar);//checa o balanceamento
					//caso o filho da esquerda n�o tenha filho, insere no lugar.
				} else {
					//se n�o, chama recursivamente at� achar um n� vazio.
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getchave().getLote() > aComparar.getchave().getLote()) {
				//se o lote do filho for maior que o lote da mercadoria pai, o lote ser� inserido na direita.
				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setpai(aComparar);
					verificarBalanceamento(aComparar);
					
				} else {
					inserirAVL(aComparar.getDireita(), aInserir);
				}
			} else {
				// O n� j� existe
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

	public void removerAVL(No atual, Mercadoria chave) {
		//o AVL tenta remover sem contrabalancear.
		//ele come�a pelo root, e depois chama recursivamente.
		if (atual == null) {
			return;
			//se n� for vazio, n�o faz nada.
		} else {
			//m�todo de busca bin�ria, para maior, busca-se no lado direito, no menor, lado esquerdo.
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

	public void deletarNo(No mr) {
		//recebe mercadoria a ser removida, ou o n� dela.
		No r;
		if (mr.getEsquerda() == null || mr.getDireita() == null) {
			//caso o n� seja a raiz, remove.
			if (mr.getpai() == null) {
				this.root = null;
				mr = null;
				return;
			}
			r = mr;
			//se apenas um n� folha.

		} else {
			r = sucessor(mr);
			//r � um n� q remove a mercadoria por um de seus sucessores.
			mr.setchave(r.getchave());
			//chama o sucessor para o n� removido.
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
			//se o no a ser removido tiver a raiz como pai, troca
		
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
		//n� qualquer.
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
