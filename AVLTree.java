package Util;

import java.util.ArrayList;
import model.Mercadoria;

public class AVLTree {
	protected No root;
	//a chave passou a ser a mercadoria
	public class No {
        private No left;
		private No right;
		private No parent;
		private Mercadoria chave;//chave � uma mercadoria
		private int balanceamento;
	
		public No(Mercadoria k) {
			setLeft(setRight(setParent(null)));
			setBalanceamento(0);
			setchave(k);
		}
	
		public String toString() {
			return Integer.toString(getchave().getNumero());
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
	
		public No getParent() {
			return parent;
		}
	
		public No setParent(No parent) {
			this.parent = parent;
			return parent;
		}
	
		public No getRight() {
			return right;
		}
	
		public No setRight(No right) {
			this.right = right;
			return right;
		}
	
		public No getEsquerda() {
			return left;
		}
	
		public void setLeft(No left) {
			this.left = left;
		}
	}
	public void inserir(Mercadoria k) {
		No n = new No(k);
		inserirAVL(this.root, n);
	}

	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.root = aInserir;//o primerio item inserido se torna a raiz

		} else {

			if (aInserir.getchave().getNumero() < aComparar.getchave().getNumero()) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setLeft(aInserir);
					aInserir.setParent(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getchave().getNumero() > aComparar.getchave().getNumero()) {

				if (aComparar.getRight() == null) {
					aComparar.setRight(aInserir);
					aInserir.setParent(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getRight(), aInserir);
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

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getRight())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getRight().getRight()) >= altura(atual.getRight().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getParent() != null) {
			verificarBalanceamento(atual.getParent());
		} else {
			this.root = atual;
		}
	}

	public void remover(int k) {
		removerAVL(this.root, k);
	}

	public void removerAVL(No atual, int k) {
		if (atual == null) {
			return;

		} else {

			if (atual.getchave().getNumero() > k) {
				removerAVL(atual.getEsquerda(), k);

			} else if (atual.getchave().getNumero() < k) {
				removerAVL(atual.getRight(), k);

			} else if (atual.getchave().getNumero() == k) {
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(No aRemover) {
		No r;

		if (aRemover.getEsquerda() == null || aRemover.getRight() == null) {

			if (aRemover.getParent() == null) {
				this.root = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setchave(r.getchave());
		}

		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getRight();
		}

		if (p != null) {
			p.setParent(r.getParent());
		}

		if (r.getParent() == null) {
			this.root = p;
		} else {
			if (r == r.getParent().getEsquerda()) {
				r.getParent().setLeft(p);
			} else {
				r.getParent().setRight(p);
			}
			verificarBalanceamento(r.getParent());
		}
		r = null;
	}

	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getRight();
		direita.setParent(inicial.getParent());

		inicial.setRight(direita.getEsquerda());

		if (inicial.getRight() != null) {
			inicial.getRight().setParent(inicial);
		}

		direita.setLeft(inicial);
		inicial.setParent(direita);

		if (direita.getParent() != null) {

			if (direita.getParent().getRight() == inicial) {
				direita.getParent().setRight(direita);
			
			} else if (direita.getParent().getEsquerda() == inicial) {
				direita.getParent().setLeft(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setParent(inicial.getParent());

		inicial.setLeft(esquerda.getRight());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setParent(inicial);
		}

		esquerda.setRight(inicial);
		inicial.setParent(esquerda);

		if (esquerda.getParent() != null) {

			if (esquerda.getParent().getRight() == inicial) {
				esquerda.getParent().setRight(esquerda);
			
			} else if (esquerda.getParent().getEsquerda() == inicial) {
				esquerda.getParent().setLeft(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setLeft(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setRight(rotacaoDireita(inicial.getRight()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		if (q.getRight() != null) {
			No r = q.getRight();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getParent();
			while (p != null && q == p.getRight()) {
				q = p;
				p = q.getParent();
			}
			return p;
		}
	}

	public int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getRight() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getRight());
		
		} else if (atual.getRight() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getRight()));
		}
	}

	private void setBalanceamento(No No) {
		No.setBalanceamento(altura(No.getRight()) - altura(No.getEsquerda()));
	}

	final protected ArrayList<No> iNorder() {
		ArrayList<No> ret = new ArrayList<No>();
		iNorder(root, ret);
		return ret;
	}

	final protected void iNorder(No No, ArrayList<No> lista) {
		if (No == null) {
			return;
		}
		iNorder(No.getEsquerda(), lista);
		lista.add(No);
		iNorder(No.getRight(), lista);
	}
}
