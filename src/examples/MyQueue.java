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
	private E[] buf = (E[]) new Object[2];
	private int size;
	private int inPos, outPos;
	
	
	private void expand(){
		E [] oldBuf = buf;
		buf =  (E[]) new Object[2*size];
		for (int i=0; i < size;i++){
			buf[i] = oldBuf[outPos++ % size]; 
		}
		outPos=0;
		inPos=size;
	}
	
	/* (non-Javadoc)
	 * @see examples.Queue#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(E o) {
		if (size == buf.length) expand();
		if (inPos == buf.length) inPos = 0;
		buf[inPos++] = o;
		size++;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#dequeue()
	 */
	@Override
	public E dequeue() {
		if (size==0) throw new RuntimeException(" empty queue");
		if (outPos==buf.length) outPos = 0;
		size--;
		return buf[outPos++];
		
	}

	/* (non-Javadoc)
	 * @see examples.Queue#head()
	 */
	@Override
	public E head() {
		if (size==0) throw new RuntimeException(" empty queue");
		return buf[outPos % buf.length];  
	}

	/* (non-Javadoc)
	 * @see examples.Queue#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see examples.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Queue<String> q = new MyQueue<>();
		q.enqueue("hallo 1");
		String s = q.dequeue();
		System.out.println(s);
		q.enqueue("hallo 2");
		q.enqueue("hallo 3");
		q.enqueue("hallo 4");
		System.out.println("size: "+q.size());
		int len = q.size();
		for (int i=0;i<len;i++){
			s = q.dequeue();
			System.out.println(s);
		}
	}

}
