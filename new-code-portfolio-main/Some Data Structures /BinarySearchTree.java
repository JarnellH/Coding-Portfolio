
/**
	
	@author Jarnell Hayes
	@version 1.1


*/


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	private T data;
	private BinaryNode<T> left;
	private BinaryNode<T> right;

	public BinarySearchTree(){

		super();
		this.left = left;
		this.right = right;
		this.data = data;
		
	}

	/**
	The super constructor of the Binary Tree class
	@param seq this array will be the array of values that will become nodes in the tree
	*/
	public BinarySearchTree(T[] seq){
		super(seq);
	}
	/**
		A super constructor that Handles null representation within the tree
		@param seq the arrray of the nodes in the Binary tree
		@param nullSym this is a null symbol value that will set null values in the tree as null
	*/
	public BinarySearchTree(T[] seq , T nullSym){
		super(seq, nullSym);
	}

	/**
	This method will insert a value into the Binary Search tree in its respective location
	@param element the value to be inserted 
	*/

	public void insert(T element){
		BinaryNode temp = root;
		BinaryNode temp2 = null;
		BinaryNode insertion = new BinaryNode (element);
		int val = 0;
		int val2 = 0;

		
		if(root == null){
			root = new BinaryNode (element);
		} else {
			int ele = (Integer) element;
			int node = (Integer) temp.getData();


			while(true){
				 node = (Integer) temp.getData();

				 //The following block will search for an insertion spot while maintaining 
				 //structural order of the tree
				if(node < ele){

					//no change occurs if the value already exists
					if(element == temp){
						temp = temp;
					}	
					//Iterates through until null or until a node which is greater than element is found 
					//in the right sub tree and does the opposite for the left subtree
					if(temp.getRightNode() != null && node < ele){

						temp = temp.getRightNode();


					}else{
						temp.setRightNode(insertion);
						break;
					}
				}else{
					if(temp.getLeftNode()!= null && node > ele){

						temp = temp.getLeftNode();

					}else{
						temp.setLeftNode(insertion);
						break;
					}

				}
			}

		}
					
}
		
	
/**
	Original implementation of the contains method will invoke the helper
	method which will find the element if it exists within the tree
	@param element the value to be searched in the Tree
*/
	
public boolean contains(T element){
	
	return contains(root , element);
}

/**
	This is the hepler method for the contains implementation it recursively searches for the element 
	in the tree
	@param root The root of the tree 
	@param element The element to be searched for in the Tree 

*/

public boolean contains(BinaryNode root , T element){

	int ele = (Integer) element;
	int node = (Integer) root.getData();


	if(root == null){
		return false;
	}

	if(node ==  ele){
		return true;
	}
	if( ele < node && root.getLeftNode() != null){
		return contains(root.getLeftNode(),element);
	}else if( ele > node && root.getRightNode() != null){
		return contains(root.getRightNode(), element);
	}else{
		return false;
	}
}
	/**
		This method will compare an element to the root of the tree
		@param element the element that is being compared 
	*/

	public int compareTo(T element){
	
		int result = 0;

	if (root != null){
		int val = (Integer) element;
		int val2 = (Integer) root.getData();

		if(val2 == val){
			result = 0;
		}
		if(val2 < val){
			result = -1;
		}else{
			result = 1;
		}

	}
	return result;	
		
	}

	/**
		This method will remove and reassign the needed values to the appropriate positiona
		@param element The element that is going to be removed

	*/	

	public void remove(T element){
		int ele = (Integer) element;
		root = remove(root, ele);
		
	}

	/**
		The helper method for the remove method this method will iterate and check the cases of the removal
		and then remove
		@param node this node will either be the root node by default and will change recursively depending on the cases
		@param element the element to be removed  
		
	*/


	public BinaryNode remove (BinaryNode node , int element){
			BinaryNode current = null;
	 		BinaryNode temp = node;
	
			int node1 = (Integer) temp.getData();
			int ele = (Integer) element;

			if(temp == null){
				return root;
			}else if (ele < node1){
				temp.setLeftNode(remove(temp.getLeftNode(), element));
			}else if(ele > node1 ){
				temp.setRightNode(remove(temp.getRightNode() , element));
			}else{

				//Checks if the target node is a leaf node and then removes it
				if(temp.getLeftNode() == null && temp.getRightNode() == null){
					temp = null;
					return temp;

				}else if(temp.getLeftNode() == null){
					//Checks for 1 child node left of right and then reassigns accordingly 
					//and removes the target node

					current = root;
					temp = temp.getRightNode();

					current = null;
					return temp;
				}
				else if(temp.getRightNode() == null){
					current = root;
					temp = temp.getLeftNode();
					current = null;
					return temp;
				}else{
					//This finds the minimum value in the subtree and assigns it to the target node
				   int  min = (Integer) minValue(temp.getRightNode()).getData();
				   BinaryNode val = minValue(temp.getRightNode());
					temp.setData(val.getData());
					temp.setRightNode(remove(temp.getRightNode(), min));
				}
	
			}
			return temp;
		}

		/**
			The following method will find the smallest value in the right subtree of the Binary Tree
			@param node This node will always be the right subtree's parent node 

		*/

		public BinaryNode minValue(BinaryNode node){
			if(node.getLeftNode()!= null){
				return minValue(node.getLeftNode());
			}
			return node;
		}
		

}
