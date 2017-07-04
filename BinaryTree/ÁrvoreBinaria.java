package BinaryTree;

public class �rvoreBinaria<T> implements BinaryTree{
	
	private Node root;
	private int size;
	
	private class Node implements Element{
		private Object data;
		private Node parent;
		private Node left;
		private Node right;
		
		public Node(Node parent, Object data){
			this.parent = parent;
			this.data = data;
		}
		public Node getParent(){
			return parent;
		}
		public void setParent(Node n){
			parent= n;
		}
		public Node getLeft(){
			return left;
		}
		public void setLeft(Node n){
			left = n;
		}
		public Node getRight(){
			return right;
		}
		public void setRight(Node n){
			right = n;
		}
		public Object getData(){
			return data;
		}
		public void setData(Object v){
			data = v;
		}
		}
	
	public void addRoot(Object e){
		if(root == null){
		root = new Node(null, e);
		}
	}
	
	public Element getRoot(){
		return root;
	}
	
	public void addLeft(Element e, Object v){
		((Node)e).setLeft(new Node((�rvoreBinaria<T>.Node) e, v)); size++;
	}
	
	public void addRight(Element e, Object v){
		((Node)e).setRight(new Node((�rvoreBinaria<T>.Node) e, v)); size++;
	}
	
	public Element getLeft(Element e){
		return ((Node)e).getLeft();
	}
	
	public Element getRight(Element e){
		return ((Node)e).getRight();
	}
	
	public Element getParent(Element e){
		return ((Node)e).getParent();
	}
	
	public void set(Element e, Object v){
		((Node)e).setData(v);
	}
	
	public void remove(Element e) {
		Node n = (Node)e;//armazena o elemento no n� n
		if(n.getLeft()!=null && n.getRight()!=null){//se o pai de n tiver dois filhos
			n.setData(n.getLeft().getData());
			remove(n.getLeft());
		}else if(n.getLeft()!=null || n.getRight()!=null){//se o pai de n s� tiver ele como filho
			Node c = n.getLeft()!=null ? n.getLeft() : n.getRight();
			replace(n, c);//substitui��o e exclus�o de n
		}else{
			replace(n, null);
		}
		size--;
	}	
	
	private void replace(�rvoreBinaria<T>.Node n, �rvoreBinaria<T>.Node c) {
		if(n == root){
			root = c;//o n ser� substituido pelo n� c, ouseja, ser� excluido, isso no caso de n ser a raiz
		}else{
			if(n == n.getParent().getLeft()){
				n.getParent().setLeft(c);//verifica se -n- � o filho esquerdo, ai seta c l� 
			}else{
				n.getParent().setRight(c);//verifica se -n- � o filho direito, ai seta c l�
			}
		}
	}

	public int height(Element e){
		Node n = (Node)e;
		if(n==null){
			return 0;
		}else{
			return Math.max(1 + height(n.getLeft()),1 + height(n.getRight()));
		}						
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator{
		private IQueue queue = new Queue<T>();
		
		public MyIterator(){
			queue.inserirFinali(root);
		}
		@SuppressWarnings("unused")
		public boolean hasNext(){
			return !queue.estaVazia();
		}
		@SuppressWarnings("unused")
		public Object next(){
			@SuppressWarnings("unchecked")
			Node n = (�rvoreBinaria<T>.Node) queue.removerInicio();
			if(n.getLeft()!=null) queue.inserirFinali(n.getLeft());;
			if(n.getRight()!=null) queue.inserirFinali(n.getRight());;
			return n.getData();
		}
	}

}
