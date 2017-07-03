
package arvoreavl;




public class No {
        private No left;
	private No right;
	private No parent;
	private int key;
	private int balanceamento;

	public No(int k) {
		setLeft(setRight(setParent(null)));
		setBalanceamento(0);
		setKey(k);
	}

	public String toString() {
		return Integer.toString(getKey());
	}

	public int getKey() {
		return key;
	}

	public void setKey(int chave) {
		this.key = chave;
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
