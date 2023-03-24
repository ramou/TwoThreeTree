package coen352;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node {
	List<Integer> vals = new LinkedList<Integer>();
	List<Node> children = new LinkedList<Node>();

	
	Node(Integer val) {
		vals.add(val);
	}
	
	Node(Integer val, Node left, Node right) {
		vals.add(val);
		children.add(left);
		children.add(right);
	}

	public Node add(Integer val) {
		if(children.isEmpty()) {
			//we're at a leaf, so we add it to the node
			vals.add(val);
			Collections.sort(vals);
		} else {
			Node potentiallySplit = null;
			int i = 0;
			for(; i < vals.size() && val >= vals.get(i); ++i) {
				
			}
			
			potentiallySplit = children.get(i).add(val);
			
			if( children.get(i) != potentiallySplit) {
				vals.add(i,potentiallySplit.vals.get(0));
				children.remove(i);
				children.addAll(i, potentiallySplit.children);
			}
		
		}
		return vals.size() > 2?split():this;
	}

	/*
	 * This node has three vals and four children
	 * Promote the middle val to a new node with the
	 * appropraite corresponding two children
	 */
	private Node split() {
		Node left = null, right = null;
		if(children.isEmpty()) {
			left = new Node(vals.get(0));
			right = new Node(vals.get(2));
		} else {
			left = new Node(vals.get(0), 
				children.get(0), 
				children.get(1));
			right = new Node(vals.get(2), 
				children.get(2), 
				children.get(3));
		}
		Integer toPromote = vals.get(1);
		Node newNode = new Node(toPromote, left, right);
		return newNode;
	}	
	
	@Override
	public String toString() {
		return "\t" + vals + " " + children;
	}
	
}
