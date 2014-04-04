/**
 * 
 */
package examples;

import java.util.Iterator;

/**
 * @author ps
 *
 */
public class MySkipList<K extends Comparable<? super K>, E> implements
		OrderedDictionary<K, E> {

	// class for orr locator nodes
	class SLNode implements Locator<K, E>{
		K key;
		E elem;
		// neighbours
		SLNode left,right,above,below;
		Object owner = MySkipList.this;
		
		/**
		 * @param minKey
		 */
		public SLNode(K aKey) {
			key = aKey;
		}

		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}

		/* (non-Javadoc)
		 * @see examples.Locator#key()
		 */
		@Override
		public K key() {
			return key;
		}
		
	}
	
	
	private SLNode topLeft,topRight,bottomLeft,bottomRight;
	private int size;
	private int height;
	
	private K minKey, maxKey;
	
	MySkipList(K min, K max){
		minKey = min;
		maxKey = max;
		topLeft = new SLNode(minKey);
		topRight = new SLNode(maxKey);
		bottomLeft = new SLNode(minKey);
		bottomRight = new SLNode(maxKey);
		topLeft.right = topRight;
		topRight.left = topLeft;
		bottomLeft.right = bottomRight;
		bottomRight.left = bottomLeft;
		topLeft.below = bottomLeft;
		topRight.below = bottomRight;
		bottomLeft.above = topLeft;
		bottomRight.above = topRight;
		height = 2;
	}
	
	
	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#find(java.lang.Comparable)
	 */
	@Override
	public Locator<K, E> find(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#findAll(java.lang.Comparable)
	 */
	@Override
	public Locator<K, E>[] findAll(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public Locator<K, E> insert(K key, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#remove(examples.Locator)
	 */
	@Override
	public void remove(Locator<K, E> loc) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#closestBefore(java.lang.Comparable)
	 */
	@Override
	public Locator<K, E> closestBefore(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#closestAfter(java.lang.Comparable)
	 */
	@Override
	public Locator<K, E> closestAfter(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#next(examples.Locator)
	 */
	@Override
	public Locator<K, E> next(Locator<K, E> loc) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#previous(examples.Locator)
	 */
	@Override
	public Locator<K, E> previous(Locator<K, E> loc) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#min()
	 */
	@Override
	public Locator<K, E> min() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#max()
	 */
	@Override
	public Locator<K, E> max() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#sortedLocators()
	 */
	@Override
	public Iterator<Locator<K, E>> sortedLocators() {
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
