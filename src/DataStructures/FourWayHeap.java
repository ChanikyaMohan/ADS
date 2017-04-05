package DataStructures;
import java.util.ArrayList;
import java.util.Collections;

import DataStructures.heap_element;
import DataStructures.huffman_tree;

public class FourWayHeap {
	public ArrayList< heap_element > arr = new ArrayList< heap_element >();
	

	public FourWayHeap(){
		heap_element tmp = new heap_element();
		for(int i=0;i<3;i++)
			arr.add(tmp);
	}

	public void insert(heap_element newElem){
		arr.add(newElem);
		int idx = arr.size() - 1;
		while(idx > 3){
			if (arr.get(idx).freq < arr.get(idx/4+2).freq ){
				Collections.swap(arr,idx, idx/4+2);
				idx = idx/4 + 2;
			}
			else{
				break;
			}
		}
	}

	public heap_element get_min(){
		heap_element mi = arr.get(3);
		arr.set(3, arr.get(arr.size()-1));
		arr.remove(arr.size()-1);
		int idx = 3;
		int minChild = 4*(idx - 2);
		while(minChild < arr.size()){
			int tmp=minChild;
			for(int i=1;i<4 && minChild+i<arr.size();i++){
				if (arr.get(tmp).freq > arr.get(minChild+i).freq )
					tmp = minChild + i;
			}
			minChild = tmp;
			
			if (arr.get(idx).freq > arr.get(minChild).freq ){
				Collections.swap(arr,idx, minChild);
				idx = minChild;
				minChild = 4*(idx - 2);
			}
			else
				break;
		}
		return mi;	
	}

	public int size(){
		return arr.size();
	}


	public huffman_tree getHuffmanTree(){
		return arr.get(3).tree;
	}
}
