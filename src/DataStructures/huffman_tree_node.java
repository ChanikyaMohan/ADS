package DataStructures;

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