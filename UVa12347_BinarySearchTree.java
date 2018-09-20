import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class UVa12347_BinarySearchTree {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		BST<Integer> tree = new BST<Integer>();
		
		
		String i;
		while((i=in.readLine())!=null) {
			if(i.equals(""))
				break;
			tree.add(Integer.parseInt(i));
		}
		List list = tree.posorden();
		for (int j = 0; j < list.size(); j++) {
			out.println(list.get(j));
		}
				
		out.close();
	}
}

class BST<T extends Comparable<T>> {

	class Node {
		T data;
		Node left;
		Node right;
	}

	Node root;

	public BST() {
		root = null;
	}

	public void add(T x) {
		Node node = new Node();
		node.data = x;
		if (root == null)
			root = node;
		else {
			Node cur = root;
			Node prev_cur = null;
			while (cur != null) {
				prev_cur = cur;
				if (x.compareTo(cur.data) <= 0)
					cur = cur.left;
				else
					cur = cur.right;
			}
			if (x.compareTo(prev_cur.data) <= 0)
				prev_cur.left = node;
			else
				prev_cur.right = node;
		}
	}

	/*
	 * Hace una lista enlazada con el metodo preorden() y retorna su tamaño A su
	 * vez, se puede crear una variable size
	 */
	public int size() {
		return preorden().size();
	}

	/* Remover elemento x */
	public boolean remove(T x) {
		Node precur = null;
		if (root == null)
			return false;
		Node cur = root;
		// Buscar nodo que contiene x
		while (cur != null && !cur.data.equals(x)) {
			if (x.compareTo(cur.data) <= 0) {
				precur = cur;
				cur = cur.left;
			} else {
				precur = cur;
				cur = cur.right;
			}
		}
		if (cur == null)
			return false;
		if (cur.right == null && cur.left == null) {
			if (cur == root)
				root = null;
			else {
				if (precur.right == cur)
					precur.right = null;
				else
					precur.left = null;
			}
			return true;
		}
		// Caso 2, nodo con un hijo
		if (cur.right == null) {
			if (cur != root) {
				if (precur.right == cur)
					precur.right = cur.left;
				else
					precur.left = cur.left;
			} else
				root = cur.left;
			return true;
		}
		if (cur.left == null) {
			if (cur != root) {
				if (precur.right == cur)
					precur.right = cur.right;
				else
					precur.left = cur.right;
			} else
				root = cur.right;
			return true;
		}
		// Caso 3, nodo con dos hijos
		Node prevsuc = cur;
		Node suc = cur.right;
		while (suc.left != null) {
			prevsuc = suc;
			suc = suc.left;
		}
		// El 'if' evalua si el sucesor es hijo del nodo a quitar
		if (prevsuc != cur) {
			prevsuc.left = suc.right;
			cur.data = suc.data;
		} else {
			cur.data = suc.data;
			cur.right = suc.right;
		}
		return true;
	}

	/* Altura del árbol */
	public int height() {
		if (root == null)
			return -1;
		return h(root);
	}

	private int h(Node cur) {
		if (cur == null)
			return -1;
		int x = h(cur.right) + 1;
		int y = h(cur.left) + 1;
		return Math.max(x, y);
	}

	/* El Arbol está completo */
	public boolean isComplete() {
		return size() == Math.pow(2, height() + 1) - 1;
	}

	/* Igualar árboles */
	public boolean equals(BST<T> tree) {
		return preorden().equals(tree.preorden()) && inorden().equals(tree.inorden())
				&& posorden().equals(tree.posorden()) && isComplete() == tree.isComplete();
	}

	/* Retorna el mayor valor en el árbol menor que x */
	public T antecessor(T x) {
		Node ant = null;
		Node cur = root;
		while (cur != null) {
			if (x.compareTo(cur.data) <= 0)
				cur = cur.left;
			else {
				ant = cur;
				cur = cur.right;
			}
		}
		return ant.data;
	}

	/* Retorna el menor valor en el árbol mayor que x */
	public T successor(T x) {
		Node suc = null;
		Node cur = root;
		while (cur != null) {
			if (x.compareTo(cur.data) < 0) {
				suc = cur;
				cur = cur.left;
			} else
				cur = cur.right;
		}
		return suc.data;
	}

	public boolean search(T x) {
		if (root == null)
			return false;
		Node cur = root;
		while (cur != null && !cur.data.equals(x)) {
			if (x.compareTo(cur.data) <= 0)
				cur = cur.left;
			else
				cur = cur.right;
		}
		return cur != null;
	}

	public List<T> preorden() {
		LinkedList<T> list = new LinkedList<T>();
		preorden(list, root);
		return list;
	}

	private void preorden(List<T> list, Node node) {
		if (node == null)
			return;
		list.add(node.data);
		preorden(list, node.left);
		preorden(list, node.right);
	}

	public List<T> inorden() {
		LinkedList<T> list = new LinkedList<T>();
		inorden(list, root);
		return list;
	}

	private void inorden(List<T> list, Node node) {
		if (node == null)
			return;
		inorden(list, node.left);
		list.add(node.data);
		inorden(list, node.right);
	}

	public List<T> posorden() {
		LinkedList<T> list = new LinkedList<T>();
		posorden(list, root);
		return list;
	}

	private void posorden(List<T> list, Node node) {
		if (node == null)
			return;
		posorden(list, node.left);
		posorden(list, node.right);
		list.add(node.data);
	}
}
