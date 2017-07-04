package BinaryTree;

public class ÁrvoreBinaria<T> implements BinaryTree{
	
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
		((Node)e).setLeft(new Node((ÁrvoreBinaria<T>.Node) e, v)); size++;
	}
	
	public void addRight(Element e, Object v){
		((Node)e).setRight(new Node((ÁrvoreBinaria<T>.Node) e, v)); size++;
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
		Node n = (Node)e;//armazena o elemento no nó n
		if(n.getLeft()!=null && n.getRight()!=null){//se o pai de n tiver dois filhos
			n.setData(n.getLeft().getData());
			remove(n.getLeft());
		}else if(n.getLeft()!=null || n.getRight()!=null){//se o pai de n só tiver ele como filho
			Node c = n.getLeft()!=null ? n.getLeft() : n.getRight();
			replace(n, c);//substituição e exclusão de n
		}else{
			replace(n, null);
		}
		size--;
	}	
	
	private void replace(ÁrvoreBinaria<T>.Node n, ÁrvoreBinaria<T>.Node c) {
		if(n == root){
			root = c;//o n será substituido pelo nó c, ouseja, será excluido, isso no caso de n ser a raiz
		}else{
			if(n == n.getParent().getLeft()){
				n.getParent().setLeft(c);//verifica se -n- é o filho esquerdo, ai seta c lá 
			}else{
				n.getParent().setRight(c);//verifica se -n- é o filho direito, ai seta c lá
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
			Node n = (ÁrvoreBinaria<T>.Node) queue.removerInicio();
			if(n.getLeft()!=null) queue.inserirFinali(n.getLeft());;
			if(n.getRight()!=null) queue.inserirFinali(n.getRight());;
			return n.getData();
		}
	}

}
