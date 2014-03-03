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
		Object owner = MyLinkedList.this;
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

	
	private LLNode checkAndCast(Position<E> p){
		try {
			LLNode n = (LLNode) p;			
			if (n.owner == null) throw new RuntimeException(" allready removed position!");
			if (n.owner != this) throw new RuntimeException(" position belongs to another MyLinkedList instance");
			return n;
		} catch (ClassCastException e) {
			throw new RuntimeException(" position belongs to another container-type ");
		}
	}
	
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
		return first == checkAndCast(p);
	}

	/* (non-Javadoc)
	 * @see examples.List#isLast(examples.Position)
	 */
	@Override
	public boolean isLast(Position<E> p) {
		return last == checkAndCast(p);
	}

	/* (non-Javadoc)
	 * @see examples.List#next(examples.Position)
	 */
	@Override
	public Position<E> next(Position<E> p) {
		return checkAndCast(p).next;
	}

	/* (non-Javadoc)
	 * @see examples.List#previous(examples.Position)
	 */
	@Override
	public Position<E> previous(Position<E> p) {
		return checkAndCast(p).prev;
	}

	/* (non-Javadoc)
	 * @see examples.List#replaceElement(examples.Position, java.lang.Object)
	 */
	@Override
	public E replaceElement(Position<E> p, E o) {
		LLNode n = checkAndCast(p);
		E old = n.element();
		n.elem = o;
		return old; 
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
		LLNode n = new LLNode();
		n.elem = o;
		n.prev = last;
		if (last != null) first.next = n;
		else first = n;
		last = n;
		size++;
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertBefore(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		LLNode n = checkAndCast(p);
		LLNode newN = new LLNode();
		newN.elem = o;
		newN.next = n;
		newN.prev = n.prev;
		if (n.prev != null) n.prev.next = newN;
		n.prev = newN;
		if (n==first) first = newN;
		size++;
		return newN;
	}

	/* (non-Javadoc)
	 * @see examples.List#insertAfter(examples.Position, java.lang.Object)
	 */
	@Override
	public Position<E> insertAfter(Position<E> p, E o) {
		LLNode n = checkAndCast(p);
		LLNode newN = new LLNode();
		newN.elem = o;
		newN.prev = n;
		newN.next = n.next;
		if (n.next != null) n.next.prev = newN;
		n.next = newN;
		if (n==last) last = newN;
		size++;
		return newN;
	}

	/* (non-Javadoc)
	 * @see examples.List#remove(examples.Position)
	 */
	@Override
	public void remove(Position<E> p) {
		// ......
	}

	/* (non-Javadoc)
	 * @see examples.List#positions()
	 */
	@Override
	public Iterator<Position<E>> positions() {
		return new Iterator<Position<E>>() {
			LLNode currentPos = first; 

			@Override
			public boolean hasNext() {
				return currentPos != null;
			}

			@Override
			public Position<E> next() {
				LLNode n = currentPos;
				currentPos = currentPos.next;
				return n;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(
						" please use the remove operation of MyLinkedList ");
			}
		};
	}

	/* (non-Javadoc)
	 * @see examples.List#elements()
	 */
	@Override
	public Iterator<E> elements() {
		return new Iterator<E>() {
			LLNode currentPos = first; 

			@Override
			public boolean hasNext() {
				return currentPos != null;
			}

			@Override
			public E next() {
				E e = currentPos.elem;
				currentPos = currentPos.next;
				return e;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(
						" please use the remove operation of MyLinkedList ");
			}

		};
	}

	/* (non-Javadoc)
	 * @see examples.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see examples.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> li = new MyLinkedList<>();
//		List<String> li2 = new MyLinkedList<>();
		Position<String> p1 =  li.insertFirst("hallo 1");
		Position<String>  pm  = li.insertFirst("hallo 2");
		li.insertFirst("hallo 3");
		Position<String> p = li.first();
		// li.remove(pm);
		li.insertAfter(pm,"hallo 2a");
		li.remove(pm);
		while (p!=null){
			System.out.println(p.element());
			p = li.next(p);
		}
	}

}
