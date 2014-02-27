/**
 * 
 */
package examples;

import java.util.Iterator;

/**
 * @author ps
 *
 */
public class MyLinkedList<E> implements List<E> {
	class LLNode implements Position<E>{
		LLNode prev,next; // neighbours of this node
		E elem;

		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}
		
	}

	// instance variables:
	private LLNode first,last;
	private int size;
	
	/* (non-Javadoc)
	 * @see examples.List#first()
	 */
	@Override
	public Position<E> first() {
		return first;
	}

	/* (non-Javadoc)
	 * @see examples.List#last()
	 */
	@Override
	public Position<E> last() {
		return last;
	}

	/* (non-Javadoc)
	 * @see examples.List#isFirst(examples.Position)
	 */
	@Override
	public boolean isFirst(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.List#isLast(examples.Position)
	 */
	@Override
	public boolean isLast(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see examples.List#next(examples.Position)
	 */
	@Override
	public Position<E> next(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#previous(examples.Position)
	 */
	@Override
	public Position<E> previous(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertFirst(java.lang.Object)
	 */
	@Override
	public Position<E> insertFirst(E o) {
		LLNode n = new LLNode();
		n.elem = o;
		n.next = first;
		if (first != null) first.prev = n;
		else last = n;
		first = n;
		size++;
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertLast(java.lang.Object)
	 */
	@Override
	public Position<E> insertLast(E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertAfter(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertAfter(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see examples.List#positions()
	 */
	@Override
	public Iterator<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#elements()
	 */
	@Override
	public Iterator<E> elements() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.List#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see examples.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> l = new MyLinkedList<>();
		l.insertFirst("hallo 1");
		System.out.println(l.first().element());
	}

}
