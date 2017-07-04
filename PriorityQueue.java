package BinaryTree;

public class PriorityQueue implements IPriorityQueue {
	private Comparable[] data;
	private int size;
	
	public PriorityQueue(int initialSize){
		data = new Comparable[initialSize];
	}
	
	@Override
	public void add(Comparable object) {
		if(size <= data.length){
			Comparable[] temp = new Comparable[size*2];
			System.arraycopy(data, 0, temp, 0, size);
			data = temp;
		}
		data[size] = object;
		int parent = (size-1)/2;
		int child = size;
		while(child>0 && data[parent].compareTo(data[child])<0){
			swap(data,parent, child);
			child = parent;
			parent = (parent-1)/2;
		}
		size++;
	}

	@Override
	public Comparable remove() {
		Comparable ret = data[0];
		data[0] = data[size-1];
		size--;
		int parent = 0;
		int child = max(parent*2+1, parent*2+2);
		while(child<size && data[child].compareTo(data[parent])>0){
			swap(data, parent, child);
			parent = child;
			child = max(parent*2+1, parent*2+2);
		}
		return ret;
	}

	@Override
	public Comparable peek() {
		return data[0];
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}
	private int max(int i, int j){
		if(i<size && j<size){
			return data[i].compareTo(data[j])>0 ? i : j;
		} else if(i<size){
			return i;
		} else if(j<size){
			return j;
		}else
			return data.length;
		}
	
	private static void swap(Comparable[] ret, int pivor1, int pivor2){
		Comparable temp = ret[pivor1];//guarda o atual
		ret[pivor1] = ret[pivor2];//troca atual pelo sucessor
		ret[pivor2] = temp;//cloca o atual na posição do sucessor
	}
}
