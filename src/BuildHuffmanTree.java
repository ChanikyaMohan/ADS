import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import DataStructures.binary_heap;
import DataStructures.heap_element;
import DataStructures.FourWayHeap;
import DataStructures.huffman_tree;
import DataStructures.huffman_tree_node;
import DataStructures.pairing_heap;
import DataStructures.pairing_heap_node;

public class BuildHuffmanTree {
	public static final int FREQ_TABLE_SIZE  = 1000000;
	public static final int[] freq_table = new int[1000000]; 
	public static final String[] code_table = new String[1000000];
	
	static void build_tree_using_binary_heap(){
		binary_heap heap = new binary_heap();
		heap_element elemOne = new heap_element();
		heap_element elemTwo = new heap_element();
		
		huffman_tree ht = new huffman_tree();
		
		for (int i=0;i<FREQ_TABLE_SIZE; i++){

			if (freq_table[i] == 0)
				continue;
			elemOne = new heap_element();

			elemOne.freq = freq_table[i];
			//System.out.println(freq_table[i]);
			elemOne.tree = new huffman_tree();
			elemOne.tree.insert_root(new huffman_tree_node(i));
			heap.insert(elemOne);
		}
		
		while (heap.size() > 1){
			//System.out.println(heap.size());
			elemOne = heap.get_min();
			elemTwo = heap.get_min();
			//System.out.println(elemOne.freq+" "+elemTwo.freq);
			elemOne.tree.combine(elemTwo.tree);
			elemOne.freq += elemTwo.freq;
			heap.insert(elemOne);
		}
	}


	static void build_tree_using_4way_heap(){
		FourWayHeap heap = new FourWayHeap();

		huffman_tree ht = new huffman_tree();
		
		heap_element elemOne = new heap_element();
		heap_element elemTwo = new heap_element();

		
		for (int i=0;i<FREQ_TABLE_SIZE; i++){

			if (freq_table[i] == 0)
				continue;
			
			elemOne = new heap_element();
			elemOne.freq = freq_table[i];
			elemOne.tree = new huffman_tree();
			elemOne.tree.insert_root(new huffman_tree_node(i));
			heap.insert(elemOne);
		}

		while (heap.size() > 4){
			elemOne = heap.get_min();
			elemTwo = heap.get_min();
			//System.out.println(elemOne.freq+" "+elemTwo.freq);
			elemOne.tree.combine(elemTwo.tree);
			elemOne.freq += elemTwo.freq;
			heap.insert(elemOne);
		}
	}


	static void build_tree_using_pairing_heap(){
		pairing_heap heap = new pairing_heap();
		
		heap_element elemOne = new heap_element();
		heap_element elemTwo = new heap_element();
		
		huffman_tree ht = new huffman_tree();
		
		pairing_heap_node tmp = new pairing_heap_node();

		for (int i=0;i<FREQ_TABLE_SIZE; i++){

			if (freq_table[i] == 0)
				continue;
			
			elemOne = new heap_element();
			elemOne.freq = freq_table[i];
			elemOne.tree = new huffman_tree();
			elemOne.tree.insert_root(new huffman_tree_node(i));
			tmp = new pairing_heap_node();
			tmp.elem = elemOne;
			heap.insert(tmp);
		}

		
		while (heap.size() > 1){
			elemOne = heap.get_min();
			elemTwo = heap.get_min();
			//System.out.println(elemOne.freq+" "+elemTwo.freq);
			elemOne.tree.combine(elemTwo.tree);
			elemOne.freq += elemTwo.freq;
			tmp = new pairing_heap_node();
			tmp.elem = elemOne;
			heap.insert(tmp);
		}
	}


	public static void display_freq_table(int size){
		for(int i=0;i<FREQ_TABLE_SIZE && size > 0;i++){
			if (freq_table[i]!=0){
				size--;
				System.out.print(i+" : "+freq_table[i]+" ");
			}
		}
	}

	
	public static void main(String args[]) throws NumberFormatException, IOException {
	    //System.out.println("in main");
	    FileReader fr=null;

		String path = "D:\\sample_input_large.txt";
			
		fr=new FileReader(path);
		
		BufferedReader br=new BufferedReader(fr);
		String S=new String();
		while((S=br.readLine())!=null){
			 int s = Integer.parseInt(S);
			 freq_table[s]++;
		}
		br.close();
		//display_freq_table(10);
		//System.out.println("read file");
		
		//BinaryHeap
				long startTime = System.currentTimeMillis();
				for(int i = 0; i < 10; i++){
					//System.out.println("bh");
					build_tree_using_binary_heap();
					
				}
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;
				System.out.println("Time with binary heap ms: "+elapsedTime +" avg time :"+(double) elapsedTime/10);
				
				
				// 4-way heap
				startTime = System.currentTimeMillis();
				for(int i = 0; i < 10; i++){
					//run 10 times on given data set
					//System.out.println("4h");
					build_tree_using_4way_heap();
				}
				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println("Time with 4way heap ms: "+elapsedTime+" avg time :"+(double)elapsedTime/10 );
				
				// pairing heap
				startTime = System.currentTimeMillis();
				for(int i = 0; i < 10; i++){
					//run 10 times on given data set
					//System.out.println("ph");
					build_tree_using_pairing_heap();
				}
				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println("Time with pairing heap ms: "+elapsedTime+" avg time :"+ (double)elapsedTime/10 );

			}
		}
