package Util;
import model.Mercadoria;

public class AVLTree {
	protected No root;
	//a chave passou a ser a mercadoria
	
	public class No {

        private No esquerda;
		private No direita;
		private No pai;
		private Mercadoria chave;//chave é um endereço de mercadoria.
		private int balanceamento;//para balancear, é necessário que o valor do nó da arvore/sub-arvore esteja entre [-1,1].
	
		public No(Mercadoria chave) {
			setEsquerda(setDireita(setpai(null)));//não tem pai
			setBalanceamento(0);//a raiz inserida no inicio é sempre balanceada.
			setchave(chave);//insere o
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
		inserirBalanceado(this.root, n);
	}

	public void inserirBalanceado(No comparar, No inserir) {
		if (comparar == null) {
			this.root = inserir;//o primerio item inserido se torna a raiz.
		} else {
			if (inserir.getchave().pegarLinha().compareTo(comparar.getchave().pegarLinha()) < 0) {
				//se o lote do filho for menor que o lote da mercadoria pai, o lote será inserido na esquerda.
				if (comparar.getEsquerda().equals(null)) {
					comparar.setEsquerda(inserir);//seta a mercadoria como filho da esquerda
					inserir.setpai(comparar);//seta o pai da mercadoria inserida
					verificarBalanceamento(comparar);//checa o balanceamento
					//caso o filho da esquerda não tenha filho, insere no lugar.
				} else {
					//se não, chama recursivamente até achar um nó vazio.
					inserirBalanceado(comparar.getEsquerda(), inserir);
				}

			} else if (inserir.getchave().pegarLinha().compareTo(comparar.getchave().pegarLinha()) > 0) {
				//se o lote do filho for maior que o lote da mercadoria pai, o lote será inserido na direita.
				if (comparar.getDireita() == null) {
					comparar.setDireita(inserir);
					inserir.setpai(comparar);
					verificarBalanceamento(comparar);
					
				} else {
					inserirBalanceado(comparar.getDireita(), inserir);
				}
			} else {
				// O nó já existe, pq já existe a chave
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
		//ele começa pelo root, e depois chama recursivamente.
		if (atual == null) {
			return;
			//se nó for vazio, não faz nada.
		} else {
			//método de busca binária, para maior, busca-se no lado direito, no menor, lado esquerdo. Recursivo.
			if ((atual.getchave().pegarLinha().compareTo(chave.pegarLinha()) > 0)) {
				removerAVL(atual.getEsquerda(), chave);
			} else if (atual.getchave().pegarLinha().compareTo(chave.pegarLinha()) < 0) {
				removerAVL(atual.getDireita(), chave);
			} else if (atual.getchave().pegarLinha().compareTo(chave.pegarLinha()) == 0) {
				deletarNo(atual);
				//remove quando encontra. ai chama o balanceamento
			}
		}
	}

	public void deletarNo(No mr) {
		//recebe mercadoria a ser removida, ou o nó dela.
		No r;
		if (mr.getEsquerda() == null || mr.getDireita() == null) {
			//caso o nó seja a raiz, remove.
			if (mr.getpai() == null) {
				this.root = null;
				mr = null;
				return;
			}
			r = mr;
			//se apenas um nó folha.

		} else {
			r = sucessor(mr);
			//r é um nó q remove a mercadoria por um de seus sucessores.
			mr.setchave(r.getchave());
			//chama o sucessor para o nó removido.
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

	public Mercadoria buscar(Mercadoria buscada){
		return buscarBinariamente(this.root, buscada);
	}
	
	private Mercadoria buscarBinariamente(No daBusca, Mercadoria buscada) {
		if (daBusca == null) {
			return null;
		} else {
			//método de busca binária, para maior, busca-se no lado direito, no menor, lado esquerdo. Recursivo.
			if ((daBusca.getchave().pegarLinha().compareTo(buscada.pegarLinha()) > 0)) {
				buscarBinariamente(daBusca.getEsquerda(), buscada);
			} else if (daBusca.getchave().pegarLinha().compareTo(buscada.pegarLinha()) < 0) {
				buscarBinariamente(daBusca.getDireita(), buscada);
			} else if (daBusca.getchave().pegarLinha().compareTo(buscada.pegarLinha()) == 0) {
				return daBusca.getchave();
				//retorna a mercadoria.
			}
		}
		return null;
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
		//se a raiz n existir, retorna -1.
		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		//só a raiz
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		//Somatório da altura pelos nós da direita
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		//Somatório da altura pelo nós da esquerda
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}
	
	private void setBalanceamento(No No) {
		No.setBalanceamento(altura(No.getDireita()) - altura(No.getEsquerda()));
		//seta o balanceamento pela subtração das alturas das subarvores.
	}
	
	public Iterator iterador(){
		return new Iterador();
	}
	
	private class Iterador implements Iterator{
		private IQueue queue;
		
		public Iterador(){
			queue = new Queue<No>();
			queue.inserirFinal(root);
		}
		
		public boolean hasNext(){
			return !queue.estaVazia();
		}
		
		public Object next(){
			No n = (No) queue.removerInicio();
			if(n.getEsquerda()!=null) 
				queue.inserirFinal(n.getEsquerda());
			else if(n.getDireita()!=null) 
				queue.inserirFinal(n.getDireita());
			return n;
		}
	}
}
