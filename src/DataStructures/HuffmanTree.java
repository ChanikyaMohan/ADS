package DataStructures;

public class HuffmanTree {
	public class huffman_tree_node{
			public huffman_tree_node left;
			public huffman_tree_node right;
			public int val;
			public huffman_tree_node(){
				left = null;
				right = null;
				val = -1;
			}

			public huffman_tree_node(int v){
				left = null;
				right = null;
				val = v;
			}
		}


		public class huffman_tree{
			public huffman_tree_node root;
			public int sz;
			public huffman_tree(){
				root = null;
				sz = 0;
			}

			public huffman_tree_node getRoot(){
				return root;
			}

			public void insert_root(huffman_tree_node node){
				root = node;
				sz++;
			}


			public void combine(huffman_tree second){
				huffman_tree_node newRoot = new huffman_tree_node();
				newRoot.left = root;
				newRoot.right = second.root;
				sz += second.size();
				root = newRoot;
			}

			public int size(){
				return sz;
			}
		}

}
