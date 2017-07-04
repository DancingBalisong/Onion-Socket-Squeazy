package BinaryTree;

import MestreKami.Element;

public class No implements Element<Object>, Node{
	private Object data;
	private Node parent;
	private Node left;
	private Node right;
	
	public No(Node parent, Object data){
		this.parent = parent;
		this.data = data;
	}
	public Node getParent(){return parent;}
	public void setParent(Node n){parent= n;}
	public Node getLeft(){return left;}
	public void setLeft(Node n){left = n;}
	public Node getRight(){return right;}
	public void setRight(Node n){right = n;}
	public Object getData(){return data;}
	public void setData(Object v){data = v;}
}
