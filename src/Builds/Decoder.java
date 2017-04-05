package Builds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DataStructures.huffman_tree;
import DataStructures.huffman_tree_node;

public class decoder {
	public static final String[] code_table = new String[1000000];
	
	static void decodeText(huffman_tree tree, String filename) throws IOException{
		FileInputStream input = new FileInputStream(filename);
		FileWriter br = new FileWriter("decoded.txt");
		BufferedWriter out = new  BufferedWriter(br);
		
		byte[] codeByte = new byte[input.available()];
		input.read(codeByte);
		
		
	    StringBuilder buffer = new StringBuilder();
	    
	    for(int i=0;i<codeByte.length;i++){
	    	byte indByte = codeByte[i];
	    	//System.out.print((int)indByte);
	    	buffer.append(String.format("%8s", Integer.toBinaryString(indByte & 0XFF )).replace(' ', '0'));
	    	
	    }
	    
	   
		huffman_tree_node node = tree.getRoot();
		
		
		System.out.println("decoding...");
		huffman_tree_node tempRoot = node;
		for(int i =0;i< buffer.length();i++)
		{
			if(buffer.charAt(i) == '0')
			{
				tempRoot = tempRoot.left;
				if(tempRoot.left == null && tempRoot.right == null)
				{
					out.write(Integer.toString(tempRoot.val)+ "\n");
					//System.out.println("left:" + tempRoot.data);
					tempRoot = node;
				}
			}
			else
			{
				tempRoot = tempRoot.right;
				if(tempRoot.right == null && tempRoot.left == null)
				{
				out.write(Integer.toString(tempRoot.val)+ "\n");
				//System.out.println("right:"+tempRoot.data);
				tempRoot = node;
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
		fr=new FileReader(args[1]);
		
		long startTime = System.currentTimeMillis();
		BufferedReader br=new BufferedReader(fr);
		String S=new String();
		while((S=br.readLine())!=null){
			//System.out.println(S);
			String[] words = S.split(" ");
			//System.out.println(words[0] + " "+ words[1]);
			code_table[Integer.parseInt(words[0])] = words[1];
			//System.out.println("code table : " +code_table[Integer.parseInt(words[0])]);
			tree.insert(Integer.parseInt(words[0]),words[1]);
		}
		br.close();
		//System.out.println(args[0]+" "+args[1]);
		decodeText(tree, args[0]);
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time for decoding: "+elapsedTime +" avg time :"+(double) elapsedTime/10);
		
	}
}