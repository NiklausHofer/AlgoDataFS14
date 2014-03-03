/**
 * 
 */
package examples;

import java.util.Iterator;

import examples.MyLinkedList.LLNode;

/**
 * @author ps
 *
 */
public class MyTree<E> implements Tree<E> {
	// TNode auxiliary class
	class TNode implements Position<E>{
		TNode parent;
		MyLinkedList children = new MyLinkedList<>();
		E elem;
		Position<TNode> myChildrenposition; // if we have a parent
											// then we are in a LinkedList
		Object owner = MyTree.this;
		

		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}
		
	}
	
	// instance variables of MyTree
	
	private TNode  root;
	private int size;

	/* (non-Javadoc)
	 * @see examples.Tree#root()
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#createRoot(java.lang.Object)
	 */
	@Override
	public Position<E> createRoot(E o) {
		if (root!=null) throw new RuntimeException("allready a root present");
		TNode n = new TNode();
		n.elem=o;
		size++;
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#parent(examples.Position)
	 */
	@Override
	public Position<E> parent(Position<E> child) {
		
		return null;
	}

	private TNode checkAndCast(Position<E> p){
		try {
			TNode n = (TNode) p;			
			if (n.owner == null) throw new RuntimeException(" allready removed position!");
			if (n.owner != this) throw new RuntimeException(" position belongs to another MyTree instance");
			return n;
		} catch (ClassCastException e) {
			throw new RuntimeException(" position belongs to another container-type ");
		}
	}

	/* (non-Javadoc)
	 * @see examples.Tree#childrenPositions(examples.Position)
	 */
	@Override
	public Iterator<Position<E>> childrenPositions(Position<E> parent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#childrenElements(examples.Position)
	 */
	@Override
	public Iterator<E> childrenElements(Position<E> parent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#numberOfChildren(examples.Position)
	 */
	@Override
	public int numberOfChildren(Position<E> parent) {
		TNode n = checkAndCast(parent);
		return n.children.size();
	}

	/* (non-Javadoc)
	 * @see examples.Tree#insertParent(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertParent(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addChild(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addChild(Position<E> parent, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addChildAt(int, examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addChildAt(int pos, Position<E> parent, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addSiblingAfter(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addSiblingAfter(Position<E> sibling, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#addSiblingBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> addSiblingBefore(Position<E> sibling, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see examples.Tree#isExternal(examples.Position)
	 */
	@Override
	public boolean isExternal(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#isInternal(examples.Position)
	 */
	@Override
	public boolean isInternal(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see examples.Tree#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
