/**
 * 
 */
package examples;


/**
 * @author ps
 *
 */
public class MyBinTree<E> implements BinTree<E> {

	private class BNode implements Position<E>{
		 
		E elem;
		Object thisOwner = MyBinTree.this;
		BNode parent, left, right;
		
		
		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}
		
	}
	
	// instance variables:
	private BNode root;
	private int size;
	
	/* (non-Javadoc)
	 * @see examples.BinTree#root()
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#createRoot(java.lang.Object)
	 */
	@Override
	public Position<E> createRoot(E o) {
		root = new BNode();
		root.elem = o;
		size++;
		return root;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#parent(examples.Position)
	 */
	@Override
	public Position<E> parent(Position<E> child) {
		BNode n = (BNode) child;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n.parent;
	}
//	return n.children.elements();

	/* (non-Javadoc)
	 * @see examples.BinTree#leftChild(examples.Position)
	 */
	@Override
	public Position<E> leftChild(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n.left;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#rightChild(examples.Position)
	 */
	@Override
	public Position<E> rightChild(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n.right;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#addLeftChild(examples.Position)
	 */
	@Override
	public Position<E> addLeftChild(Position<E> p, E o) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		if (n.left != null) throw new RuntimeException("left child not null");
		BNode nNew = new BNode();
		nNew.elem = o;
		n.left = nNew;
		nNew.parent = n;
		size++;
		return nNew;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#addRightChild(examples.Position)
	 */
	@Override
	public Position<E> addRightChild(Position<E> p, E o) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		if (n.right != null) throw new RuntimeException("right child not null");
		BNode nNew = new BNode();
		nNew.elem = o;
		n.right = nNew;
		nNew.parent = n;
		size++;
		return nNew;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		if (n.left == null) throw new RuntimeException("left child not null");
		if (n.right == null) throw new RuntimeException("right child not null");
		if (n.parent==null) root = null;
		else if (n.parent.left==n) n.parent.left=null;
		else if (n.parent.right==n) n.parent.right=null;
		n.thisOwner=null;
		size--;	
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#isExternal(examples.Position)
	 */
	@Override
	public boolean isExternal(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n.left==null && n.right==null;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#isInternal(examples.Position)
	 */
	@Override
	public boolean isInternal(Position<E> p) {
		return ! isExternal(p);
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#size()
	 */
	@Override
	public int size() {
		return size;
	}


	/* (non-Javadoc)
	 * @see examples.BinTree#isLeftChild(examples.Position)
	 */
	@Override
	public boolean isLeftChild(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n.parent != null && n.parent.left==n;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#isRightChild(examples.Position)
	 */
	@Override
	public boolean isRightChild(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n.parent != null && n.parent.right==n;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#isRightChild(examples.Position)
	 */
//	@Override
	public boolean isRoot(Position<E> p) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		return n == null;
	}

	/* (non-Javadoc)
	 * @see examples.BinTree#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		BNode n = (BNode) p;
		if (n.thisOwner != this) throw new RuntimeException("invalid node");
		E old = n.elem;
		n.elem=o;
		return old;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyBinTree<String> t = new MyBinTree<>();
		Position<String> p =  t.createRoot("root");
		t.addLeftChild(p, "left ");
		t.addRightChild(p, "right ");
		p = t.addRightChild(t.leftChild(t.root()), "left right");
		t.addLeftChild(t.rightChild(t.root()), "right left");
		t.addLeftChild(p, "left right left");
		p=t.addLeftChild(t.leftChild(t.root()), "left left");
		t.addRightChild(p, "left left right");
	}

}
