


/**
	@author Jarnell Hayes
	@version 1.1
*/

import java.util.List;
import java.util.ArrayList;

public class BinaryTree<T>{
	BinaryNode<T> root = null;	
	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}

	private void initFromBfsSequence(T[] seq){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
				
		List<BinaryNode<T>> nodes = new ArrayList<BinaryNode<T>>(seq.length);
		this.root = new BinaryNode<T>(seq[0]);
		nodes.add(root);

		for(int i = 0; i < seq.length; i++){			
			if(nodes.get(i) == null){ 				
				handelNullParentNode(nodes, i, seq.length);				
			}else{				
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}

	// This method will handle the null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handelNullParentNode(List<BinaryNode<T>> nodes, 
						int nullNodeIndex, int seqLength){
		int leftIndex = (nullNodeIndex * 2) + 1; // finding the left child index from formula 
				
		if(leftIndex < seqLength){
			nodes.add(null);

			int rightIndex = (nullNodeIndex * 2) + 2; // finding the right child index from formula
			if(rightIndex < seqLength){
				nodes.add(null);
			}
		}
	}

	// This method will handle the non-null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, 
								int parentIndex, T[] seq){
		int leftIndex = (parentIndex * 2) + 1;			
		if(leftIndex < seq.length){ //need to check if the index falls outdise of the list index
			BinaryNode<T> leftNode = null;
			if(!seq[leftIndex].equals(nullSymbol)){
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			nodes.get(parentIndex).leftNode = leftNode;
			nodes.add(leftNode);

			int rightIndex = (parentIndex * 2) + 2;				
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				if(!seq[rightIndex].equals(nullSymbol)){
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				nodes.get(parentIndex).rightNode = rightNode;
				nodes.add(rightNode);			
			}
		}
	}

	public int height(){
		if (root == null) return 0;	
		
		return root.height();
	}

	public int width(){
		
		if(root == null)return 0;
		
		return root.width();
	}

	public String breadthFirstTraverse(){
		// TODO: Modify this method-body to return a string corresponding
		// to the bread-first-traversal of the tree	
		return root.breadthFirstTraverse().trim();
		
	}

	public String preOrderTraverse(){
		return root.preOrderTraverse().trim();				
	}

	public String postOrderTraverse(){
		return root.postOrderTraverse().trim();
	}

	public String inOrderTraverse(){
		return root.inOrderTraverse().trim();
	}
	
	class BinaryNode<T>{
		private T data = null;
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;

		public BinaryNode(T data){
			this.data = data;			
		}

		public String toString(){
			return "" + data;
		}

		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}

		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}

		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}

		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}

		public T getData(){
			return this.data;
		}

		public void setData(T data){
			this.data = data;
		}

		public int height(){
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;

			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}

			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;

			return maxHeight + 1 ;
		}

		//The width method will calculate the maximum height of a given tree
		public int width(){
			
			Queue q = new Queue();
			q.enqueue(root);

			int maxWidth = 0;
			
			if(root == null){
				return 0;
			}else{

			while (!q.isEmpty()){

				int count = q.size();

				maxWidth = Math.max(maxWidth, count);

				while(count-- > 0){
					
					BinaryNode temp = (BinaryNode) q.dequeue();

					if(temp.leftNode != null){
						q.enqueue(temp.leftNode);
					}
					if(temp.rightNode != null){
						q.enqueue(temp.rightNode);
					}
				}
			}

			return maxWidth;
		}

		}

		public boolean isLeaf(){
			return (leftNode == null && rightNode == null);
		}

		//pre order method of traversing the tree
		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			
			stringBuffer.append(" " + data);
			
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}

		//Post order method of traversing the tree
		public String postOrderTraverse(){			
			StringBuilder stringBuffer = new StringBuilder();

			if (leftNode != null){
			   stringBuffer.append(leftNode.postOrderTraverse());
			   
			}

			if(rightNode != null){
				stringBuffer.append(rightNode.postOrderTraverse());
			}

			stringBuffer.append(" " + data);

			return stringBuffer.toString();
		}

		//Searches from the least nodes first then the root of the tree and finally the right nodes 
		public String inOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();

			if (leftNode != null){

				stringBuffer.append(leftNode.inOrderTraverse());

		
			}

			stringBuffer.append(" " + data);

			if(rightNode != null){
				stringBuffer.append(rightNode.inOrderTraverse());
			}

			return stringBuffer.toString();
		}

		//Breadth first method of traversing the tree
		public String breadthFirstTraverse(){
			Queue q = new Queue();
			q.enqueue(root);
		
			StringBuilder string = new StringBuilder();

			while (!q.isEmpty()){
					
					
				BinaryNode temp = (BinaryNode) q.dequeue();
					
					    
				if(temp.leftNode != null){
						q.enqueue(temp.leftNode);
						

						
				}
				

				if(temp.rightNode != null){
						q.enqueue(temp.rightNode);
						
					    
				}
					
				string.append( " " + temp);
				 
			}


			return string.toString();
		}
	}
}