/**
 * 
 */
package examples;

import java.util.Iterator;
import java.util.Random;

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

		/**
		 * @param aKey
		 * @param o
		 */
		public SLNode(K aKey, E o) {
			if (aKey.compareTo(minKey)<0) throw new RuntimeException(aKey+ " must be greater than "+minKey);
			if (aKey.compareTo(maxKey)>0) throw new RuntimeException(aKey+ " must be smaller than "+maxKey);
			key = aKey;
			elem = o;
		}

		/**
		 * @param aKey
		 * @param o
		 * @param n
		 * @param object
		 */
		public SLNode(K aKey, E o, SLNode left, SLNode below) {
			this(aKey,o);
			this.left = left;
			this.below = below;
			if (left!=null) this.right = left.right;
			if (below != null) this.above = below.above;			
			if (left != null) left.right = this;			
			if (right != null) right.left = this;
			if (below!=null) below.above = this;
			if (above!=null) above.below = this;
			
			
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
	private Random rand = new Random(5345);
	
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
		SLNode n = search(key);
		// find the leftmost occurence of this key
		if (n.left!= null && n.left.key.compareTo(key)== 0) n=n.left;
		return n;
	}

	private SLNode search(K key){
		SLNode n = topLeft;
		while (n.below != null){
			n = n.below;
			while (n.right.key.compareTo(key) < 0) n=n.right;
		}		
		return n;
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
		if (key.compareTo(minKey)<=0) throw new RuntimeException("key not bigger than minKey!");
		if (key.compareTo(maxKey)>=0) throw new RuntimeException("key not smaller than maxKey!");
		SLNode n = search(key);
		// we take the rightmost Locator with valid key
		while (n.right.key.compareTo(key)== 0) n=n.right;		
		// now we want to insert a node at the nition n.right:
		SLNode nNew = new SLNode(key,o,n,null);
		SLNode pb = nNew;
		boolean generateIndexNode = rand.nextDouble()<0.5;
		while (generateIndexNode){
			// System.out.println("index generated");
			while (n.above==null) n = n.left;
			n = n.above;
			// create a new index
			SLNode index = new SLNode(key,o,n,pb);
			pb=index;
			
			// if n is topLeft we have to expand by one index level
			if (n == topLeft) expand();
			generateIndexNode = rand.nextDouble() < 0.5;
		}
		size++;
		return nNew;
	}

	/**
	 * 
	 */
	private void expand() {
		topLeft = new SLNode (minKey,null,null,topLeft);
		topRight = new SLNode (maxKey,null,topLeft,topRight);
		height++;
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
	
	public void print(){
		System.out.println("-------start------");
		SLNode n = bottomLeft;
		n=n.right;
		StringBuffer lev = new StringBuffer();
		while (n!=bottomRight){
			lev.delete(0,lev.length());
			SLNode m = n;
			int index = 0;
			while (m.above != null) {
				index++;
				m=m.above;
				lev.append("+");
			}
			while(index<height-2){
				index++;
				lev.append("|");
			}
			System.out.println(String.format("%11d", n.key())+lev.toString()+"    elem: "+n.elem);
			n=n.right;
		}
		System.out.println("--------end-------");
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MySkipList<Integer,String> sl = new MySkipList<>(-1,100);
		Random rand = new Random();
		for (int i=1;i<200;i++) sl.insert(rand.nextInt(100),""+i);
		sl.print();
		System.out.println(sl.height);
	}

}
