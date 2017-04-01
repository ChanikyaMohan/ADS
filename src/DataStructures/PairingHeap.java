package DataStructures;

import java.util.ArrayList;

import DataStructures.BinaryHeap.heap_element;

public class PairingHeap {
		public class pairing_heap_node{
			public ArrayList< pairing_heap_node > arr = new ArrayList< pairing_heap_node >();
			public heap_element elem;
		};


		public class pairing_heap{
			pairing_heap_node root;
			int sz;
			
			public pairing_heap(){
				root = null;
				sz = 0;
			}

			public void insert(pairing_heap_node newElem){
				sz++;
				if (root == null){
					root = newElem;
				}
				else if (root.elem.freq < newElem.elem.freq)
					root.arr.add(newElem);
				else{
					newElem.arr.add(root);
					root = newElem;
				}
			}

			public heap_element get_min(){
				sz--;
				heap_element mi = root.elem;
				ArrayList<pairing_heap_node > tmp = new ArrayList<pairing_heap_node >();
				for(int i=root.arr.size()-1; i>0; i=i-2){
					if (root.arr.get(i).elem.freq > root.arr.get(i-1).elem.freq){
						tmp.add(root.arr.get(i-1));
						root.arr.get(i-1).arr.add(root.arr.get(i));
					}
					else{
						tmp.add(root.arr.get(i));
						root.arr.get(i).arr.add(root.arr.get(i-1));
					}
					root.arr.remove(root.arr.size()-1);
					root.arr.remove(root.arr.size()-1);
				}

				if (root.arr.size() > 0){
					tmp.add(root.arr.get(root.arr.size()-1));
					root.arr.remove(root.arr.size()-1);
				}

				pairing_heap_node tmpRoot = null;
				if (tmp.size() > 0){
					tmpRoot = tmp.get(tmp.size()-1);
					for(int i=tmp.size()-2; i>=0; i--){
						if (tmp.get(i).elem.freq > tmpRoot.elem.freq)
							tmpRoot.arr.add(tmp.get(i));
						else{
							tmp.get(i).arr.add(tmpRoot);
							tmpRoot = tmp.get(i);
						}
					}
				}
				root = tmpRoot;
				return mi;
			}

			public int size(){
				return sz;
			}
		}
}
