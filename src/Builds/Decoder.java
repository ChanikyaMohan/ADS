package Builds;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DataStructures.huffman_tree;
import DataStructures.huffman_tree_node;

public class Decoder {
	public static final String[] code_table = new String[1000000];
	
	static void decodeText(huffman_tree tree, String filename) throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(filename));
		BufferedWriter out = new BufferedWriter(new FileWriter("decoded.txt"));
		
	    StringBuilder buffer = new StringBuilder();
	    
	    String S=new String();
	    while((S=input.readLine())!=null){
	    	buffer.append(S);
	    }
	    
	    String data = new String();
	    data = buffer.toString();
	    
		huffman_tree_node node = tree.getRoot();

		for (int i=0;i<data.length(); i++) {
			int k = (int)data.charAt(i);
			for(int p=0;p<8;p++){
				if ((k&(1<<p)) > 0)
					node = node.right;
				else
					node = node.left;
				if (node.left == null && node.right == null){
					out.write(node.val);
					out.newLine();
					node = tree.getRoot();
				}
			}
		}
		input.close();
		out.close();
		System.out.println("decoded.txt done");
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		huffman_tree tree = new huffman_tree();
		tree.insert_root(new huffman_tree_node());
		
		//System.out.println(args[0]+" "+args[1]);
		
		FileReader fr=null;
		fr=new FileReader(args[0]);
		
		BufferedReader br=new BufferedReader(fr);
		String S=new String();
		while((S=br.readLine())!=null){
			String[] words = S.split(" ");
			//System.out.println(words);
			code_table[Integer.parseInt(words[0])] = words[1];
			tree.insert(Integer.parseInt(words[0]),words[1]);
		}
		br.close();
		//System.out.println(args[0]+" "+args[1]);
		decodeText(tree, args[1]);
		
		
	}
}
