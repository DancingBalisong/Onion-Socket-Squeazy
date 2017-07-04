package BinaryTree;

public interface IPriorityQueue {
	public void add(Comparable object);
	public Comparable remove();
	public Comparable peek();
	public boolean isEmpty();
	public int size();
}
