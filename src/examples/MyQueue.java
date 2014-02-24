/**
 * 
 */
package examples;

/**
 * @author ps
 * Simple queue based on a expandable circular array buffer
 *
 */
public class MyQueue<E> implements Queue<E> {
	
	//instance variables 
	private E[] buf;
	private int size;
	private int inPos, outPos;
	
	
	private void expand(){
		// expand (double) the buf array
	}
	
	/* (non-Javadoc)
	 * @see examples.Queue#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(E o) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see examples.Queue#dequeue()
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#head()
	 */
	@Override
	public E head() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#isEmpty()
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
		// TODO Auto-generated method stub

	}

}
