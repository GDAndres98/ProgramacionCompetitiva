import java.io.*;
import java.util.*;

public class UVa12086_Potentiometers {
	public static void main(String[] args) throws IOException {
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		
		int caso = 1;
		while (true) {
			String s = kb.readLine().trim();
			if (s.charAt(0) == '0')
				break;
			if(caso != 1)
				System.out.println();
			System.out.println("Case " + caso + ":");
			int n = Integer.parseInt(s);
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = Long.parseLong(kb.readLine().trim());
			}
			SegmentTreeLazyPropagation1 lp = new SegmentTreeLazyPropagation1(arr);
			StringTokenizer st = new StringTokenizer(kb.readLine());
			while (st.countTokens() != 1) {
				char c = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (c == 'M') {
					System.out.println(lp.queryOfRange(x - 1, y - 1));
				} else {
					lp.update(x - 1, y);
				}

				st = new StringTokenizer(kb.readLine());
			}
			caso++;

		}

	}
	

}

class SegmentTreeLazyPropagation1 {
	long arr[];
	Node root;
	int n;

	public SegmentTreeLazyPropagation1(long[] arr) {
		this.arr = arr;
		n = arr.length - 1;
		// root = construction(0,n);
		root = new Node(0, n);
		root.left = construction(0, (n) / 2);
		root.right = construction((n) / 2 + 1, n);
		// criterio
		root.value = suma(root.left, root.right);

	}

	public Node construction(int i, int j) {
		if (i > j)
			return null;
		Node node = new Node(i, j);
		if (i == j)
			node.value = arr[i];
		else {
			node.left = construction(i, (i + j) / 2);
			node.right = construction((i + j) / 2 + 1, j);

			// criterio
			node.value = suma(node.left, node.right);
		}
		return node;
	}

	public long queryOfRange(int i, int j) {
		return queryOfRange(0, n, i, j, root);
	}

	private long queryOfRange(int i, int j, int k, int l, Node v) {
		if (l < i || k > j)
			return 0;
		if (v == null)
			return 0;
		if (k <= i && j <= l)
			return v.value;
		// criterio
		return queryOfRange(i, (j + i) / 2, k, l, v.left) + queryOfRange((i + j) / 2 + 1, j, k, l, v.right);
	}

	public void update(int i, long l) {
		update(i, l, root);
	}

	private long update(int k, long l, Node v) {
		if (v.i > k || v.j < k)
			return v.value;
		if (v.i == v.j && k == v.i) {
			v.value = l;
			return l;
		}

		else {
			v.value = update(k, l, v.left) + update(k, l, v.right);
		}
		return v.value;
	}

	private long suma(Node l, Node r) {
		long s = 0;
		if (r != null)
			s += r.value;
		if (l != null)
			s += l.value;
		return s;

	}

}

class Node {
	public Node left;
	public Node right;
	public int i;
	public int j;
	public long value;

	public Node(int i, int j) {
		this.i = i;
		this.j = j;
		this.left = null;
		this.right = null;
	}
}
