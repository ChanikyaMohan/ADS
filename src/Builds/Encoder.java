package Builds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DataStructures.huffman_tree;

public class encoder {
	public static final int FREQ_TABLE_SIZE  = 1000000;
	public static final int[] freq_table = new int[1000000]; 
	public static final String[] code_table = new String[1000000];
	
	public static void encodeText(String filename) throws IOException{
		
		BufferedReader input = new BufferedReader(new FileReader(filename));
		FileOutputStream out = new FileOutputStream("encoded.bin");

		int len=0, tmp=0;
		String S=new String();
		while((S=input.readLine())!=null){
			int s = Integer.parseInt(S);
			for(int i=0; i<code_table[s].length(); i++){
				if (code_table[s].charAt(i) == '1'){
					tmp += (1 << len);
				}
				len++;
				if (len == 8){
					out.write(tmp);
					len = 0;
					tmp = 0;
				}
			}
		}

		input.close();
		out.close();
		System.out.println("encoded done");
		try (BufferedWriter table = new BufferedWriter(new FileWriter("code_table.txt"))) {
			for(int i=0;i<FREQ_TABLE_SIZE ;i++){
				if (code_table[i] != null){
					table.write(i + " "+code_table[i]);
					table.newLine();
				}
			}
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("code_table.txt done");
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		System.out.println("in main");
		final int[] freq_table = new int[1000000];
		//if (args.length != 2){
		//	return;
		//}

		FileReader fr=null;

		//String path = "D:\\sample_input_large.txt";
			
		fr=new FileReader(args[0]);
		
		BufferedReader br=new BufferedReader(fr);
		String S=new String();
		while((S=br.readLine())!=null){
			 int s = Integer.parseInt(S);
			 freq_table[s]++;
		}
		br.close();
		
		
		huffman_tree tree = BuildHuffmanTree.build_tree_using_4way_heap(freq_table);
		
		char ch[] = new char[1000001];
		tree.traverse(tree.getRoot(), ch, 0,code_table);
			
		encodeText(args[0]);

	}
}
