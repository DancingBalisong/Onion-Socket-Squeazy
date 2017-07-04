package BinaryTree;
//by: João Rocha Junior
public interface BinaryTree {
	public void addRoot(Object v);
	public Element getRoot();
	public void addLeft(Element e, Object v);
	public void addRight(Element e, Object v);
	public Element getLeft(Element e);
	public Element getRight(Element e);
	public void set(Element e, Object v);
	public void remove(Element e);
	public int size();
	public int height (Element e);
	public Iterator iterator();
}
