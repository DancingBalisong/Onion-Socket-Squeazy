
package arvoreavl;

import java.util.ArrayList;


public class ArvoreAvl {
    protected No root;

	public void inserir(int k) {
		No n = new No(k);
		inserirAVL(this.root, n);
	}

	public void inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.root = aInserir;

		} else {

			if (aInserir.getKey() < aComparar.getKey()) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setLeft(aInserir);
					aInserir.setParent(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getKey() > aComparar.getKey()) {

				if (aComparar.getRight() == null) {
					aComparar.setRight(aInserir);
					aInserir.setParent(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirAVL(aComparar.getRight(), aInserir);
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

			if (atual.getKey() > k) {
				removerAVL(atual.getEsquerda(), k);

			} else if (atual.getKey() < k) {
				removerAVL(atual.getRight(), k);

			} else if (atual.getKey() == k) {
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
			aRemover.setKey(r.getKey());
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

	private int altura(No atual) {
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

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getRight()) - altura(no.getEsquerda()));
	}

	final protected ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorder(root, ret);
		return ret;
	}

	final protected void inorder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getRight(), lista);
	}
}
