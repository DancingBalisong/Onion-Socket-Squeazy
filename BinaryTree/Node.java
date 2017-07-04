package BinaryTree;

public interface Node {
	public Node getParent();
	public void setParent(Node n);
	public Node getLeft();
	public void setLeft(Node n);
	public Node getRight();
	public void setRight(Node n);
	public Object getData();
	public void setData(Object v);
}
