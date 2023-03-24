package coen352.tree;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoThreeTree tree = new TwoThreeTree();
		tree.add(10);
		tree.add(20);
		tree.add(15);
		tree.add(12);
		tree.add(11);
		
		System.out.println(tree.root);
	}

}
