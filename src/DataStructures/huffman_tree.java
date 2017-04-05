package DataStructures;

import DataStructures.huffman_tree_node;

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
	
	public void traverse(huffman_tree_node node, char[] ch, int len,  String[] code_table){
		if (node == null){
			return;
		}
		if (node.left == null && node.right == null){
			StringBuilder sb = new StringBuilder();
			sb.append(ch, 0,len);
			
			code_table[node.val] = sb.toString();
		} else{
			ch[len] = '0';
			traverse(node.left, ch, len+1, code_table);
			ch[len] = '1';
			traverse(node.right, ch, len+1, code_table);
		}
	}
	
	public void insert(int key, String s){
		huffman_tree_node node = root;
		if (s == "")
			return;
		for(int i=0;i<s.length();i++){
			if (s.charAt(i) == '0'){
				if (node.left == null){
					node.left = new huffman_tree_node();
				}
				node = node.left;
			}
			else{
				if (node.right == null){
					node.right = new huffman_tree_node();
				}
				node = node.right;
			}
		}
		node.val = key;
	}
}

