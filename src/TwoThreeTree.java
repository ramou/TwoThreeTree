package coen352.tree;

public class TwoThreeTree {
	
	Node root;
	
	/*
	 * 
	 * This will find where there is a leaf
	 * to add this value and then propogate
	 * appropriate changes up.
	 */
	public void add(Integer val) {
		if(root == null) {
			root = new Node(val);
		} else {
			root = root.add(val, null);
		}
		
		
	}
	
}
