package coen352.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node {
	List<Integer> vals = new LinkedList<Integer>();
	List<Node> children = new LinkedList<Node>();
	
	
	private Node() {
		
	}
	
	Node(Integer val) {
		vals.add(val);
	}
	
	Node(Integer val, Node left, Node right) {
		vals.add(val);
		children.add(left);
		children.add(right);
	}

	public Node add(Integer val, Node parent) {
		if(children.isEmpty()) {
			//we're at a leaf, so we add it to the node
			vals.add(val);
			Collections.sort(vals);
			
			//We are a single node in the tree, but promoting a new root
			if(vals.size() == 3 && parent == null) {
				Node newRoot = new Node();
				newRoot.children.add(this);
				split(newRoot);
				return newRoot;
			}
			return this;
		}
		Node potentiallyNeedingSplitting = null;
		int i = 0;
		for(; i < vals.size(); ++i) {
			if(vals.get(i) < val) {
				potentiallyNeedingSplitting = children.get(i).add(val, this);
				break;
			}
		}
		if(i == vals.size()) {
			potentiallyNeedingSplitting = children.get(i).add(val, this);
		}
		
		if(potentiallyNeedingSplitting.children.size() > 2) {
			//We have to split it and that's 
			//the next thing we figure out
			potentiallyNeedingSplitting.split(this);
		}
		
		return this;
	}

	/*
	 * This node has three vals and four children
	 * Promote the middle val to the parent and
	 * replace ourself in the parents children with
	 * two new nodes
	 * 
	 */
	private void split(Node parent) {
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
		for(int i = 0; i < parent.children.size(); ++i) {
			if(parent.children.get(i).equals(this)) {
				parent.children.add(i, right);
				parent.children.add(i, left);
				parent.children.remove(this);
				parent.vals.add(i, toPromote);
			}
		}
		
	}	
	
	@Override
	public String toString() {
		return "\t" + vals + " " + children;
	}
	
}
