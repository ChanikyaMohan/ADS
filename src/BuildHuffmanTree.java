import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import DataStructures.BinaryHeap;
import DataStructures.BinaryHeap.binary_heap;
import DataStructures.BinaryHeap.heap_element;
import DataStructures.FourWayHeap;
import DataStructures.HuffmanTree;
import DataStructures.HuffmanTree.huffman_tree;
import DataStructures.HuffmanTree.huffman_tree_node;
import DataStructures.PairingHeap;
import DataStructures.PairingHeap.pairing_heap;
import DataStructures.PairingHeap.pairing_heap_node;

public class BuildHuffmanTree {
	public static final int FREQ_TABLE_SIZE  = 1000000;
	public static final int[] freq_table = new int[1000000]; 
	public static final String[] code_table = new String[1000000];
	
	static void build_tree_using_binary_heap(){
		BinaryHeap bh = new BinaryHeap();
		binary_heap heap = bh.new binary_heap();
		heap_element elemOne = bh.new heap_element();
		heap_element elemTwo = bh.new heap_element();
		
		HuffmanTree ht = new HuffmanTree();
		
		for (int i=0;i<FREQ_TABLE_SIZE; i++){

			if (freq_table[i] == 0)
				continue;

			elemOne.freq = freq_table[i];
			elemOne.tree = ht.new huffman_tree();
			elemOne.tree.insert_root(ht.new huffman_tree_node(i));
			heap.insert(elemOne);
		}
		
		while (heap.size() > 1){
			//System.out.println(heap.size());
			elemOne = heap.get_min();
			elemTwo = heap.get_min();
			System.out.println(elemOne.freq+" "+elemTwo.freq);
			elemOne.tree.combine(elemTwo.tree);
			elemOne.freq += elemTwo.freq;
			heap.insert(elemOne);
		}
	}


	static void build_tree_using_4way_heap(){
		FourWayHeap heap = new FourWayHeap();
		
		BinaryHeap bheap = new BinaryHeap();
		HuffmanTree ht = new HuffmanTree();
		
		heap_element elemOne = bheap.new heap_element();
		heap_element elemTwo = bheap.new heap_element();

		
		for (int i=0;i<FREQ_TABLE_SIZE; i++){

			if (freq_table[i] == 0)
				continue;

			elemOne.freq = freq_table[i];
			elemOne.tree = ht.new huffman_tree();
			elemOne.tree.insert_root(ht.new huffman_tree_node(i));
			heap.insert(elemOne);
		}

		while (heap.size() > 4){
			elemOne = heap.get_min();
			elemTwo = heap.get_min();
			System.out.println(elemOne.freq+" "+elemTwo.freq);
			elemOne.tree.combine(elemTwo.tree);
			elemOne.freq += elemTwo.freq;
			heap.insert(elemOne);
		}
	}


	static void build_tree_using_pairing_heap(){
		PairingHeap ph = new PairingHeap();
		pairing_heap heap = ph.new pairing_heap();
		
		BinaryHeap bheap = new BinaryHeap();
		
		heap_element elemOne = bheap.new heap_element();
		heap_element elemTwo = bheap.new heap_element();
		
		HuffmanTree ht = new HuffmanTree();
		pairing_heap_node tmp = ph.new pairing_heap_node();

		for (int i=0;i<FREQ_TABLE_SIZE; i++){

			if (freq_table[i] == 0)
				continue;

			elemOne.freq = freq_table[i];
			elemOne.tree = ht.new huffman_tree();
			elemOne.tree.insert_root(ht.new huffman_tree_node(i));
			tmp = ph.new pairing_heap_node();
			tmp.elem = elemOne;
			heap.insert(tmp);
		}

		
		while (heap.size() > 1){
			elemOne = heap.get_min();
			elemTwo = heap.get_min();
			System.out.println(elemOne.freq+" "+elemTwo.freq);
			elemOne.tree.combine(elemTwo.tree);
			elemOne.freq += elemTwo.freq;
			tmp = ph.new pairing_heap_node();
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
	    System.out.println("in main");
	    FileReader fr=null;

		String path = "D:\\sample_input_small.txt";
			
		fr=new FileReader(path);
		
		BufferedReader br=new BufferedReader(fr);
		String S=new String();
		 int j = 0;
		while((S=br.readLine())!=null){
			 int s = Integer.parseInt(S);
			 freq_table[j]=s;
		     j++;
		}
		br.close();
		
		System.out.println("read file");
		
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
				System.out.println("Time with 4way heap ms: "+elapsedTime+" avg time :"+elapsedTime/10 );
				
				// pairing heap
				startTime = System.currentTimeMillis();
				for(int i = 0; i < 10; i++){
					//run 10 times on given data set
					//System.out.println("ph");
					build_tree_using_pairing_heap();
				}
				stopTime = System.currentTimeMillis();
				elapsedTime = stopTime - startTime;
				System.out.println("Time with pairing heap ms: "+elapsedTime+" avg time :"+elapsedTime/10 );

			}
		}
