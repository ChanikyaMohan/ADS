package DataStructures;
import java.util.ArrayList;
import java.util.Collections;

import DataStructures.heap_element;



		public class binary_heap{
			public ArrayList< heap_element > arr = new ArrayList< heap_element >();
			public void insert(heap_element newElem){
				//System.out.println("inserting"+newElem.freq);
				arr.add(newElem);
				int idx = arr.size() - 1;
				while(idx != 0){
					if (arr.get(idx).freq < arr.get((idx-1)/2).freq){
						Collections.swap(arr,idx, (idx-1)/2);
						idx = (idx - 1)/2;
					}
					else{
						break;
					}
				}
				//System.out.println("min elem : "+arr.get(0).freq);
			}

			public heap_element get_min(){
				heap_element mi = arr.get(0);
				arr.set(0,arr.get(arr.size()-1));
				arr.remove(arr.size()-1);
				int idx = 0;
				int minChild = 2*idx + 1;;
				while(minChild < arr.size()){
					if (minChild+1 < arr.size() && arr.get(minChild).freq > arr.get(minChild+1).freq)
						minChild++;
					
					if (arr.get(idx).freq > arr.get(minChild).freq){
						Collections.swap(arr,idx, minChild);
						idx = minChild;
						minChild = 2*idx + 1;
					}
					else{
						break;
					}
				}
				return mi;
			}

			public int size(){
				return arr.size();
			}
		}
