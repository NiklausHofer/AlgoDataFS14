/**
 * 
 */
package examples;

import java.util.Iterator;

import examples.MyTree.TNode;

/**
 * @author ps
 *
 */
public class MyAVLTree<K extends Comparable<? super K>, E> implements
		OrderedDictionary<K, E> {

	class AVLNode implements Locator<K, E>{
		
		AVLNode parent,left,right;
		Object owner = MyAVLTree.this;
		E elem;
		K key;
		int height;

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
		
		boolean isExternal(){
			return left==null; // is also true for right
		}
		
		boolean isLeftChild(){
			return parent != null && parent.left==this;
		}
		
		boolean isRightChild(){
			return parent != null && parent.right==this;
		}
		
		void expand(K key,E elem){
			this.elem = elem;
			this.key = key;
			left = new AVLNode();
			right = new AVLNode();
			left.parent = this;
			right.parent = this;
			height = 1;
		}
	}

	
	
	// istance variables:
	private AVLNode root = new AVLNode();
	private int size;
	
	private AVLNode checkAndCast(Locator<K,E> p){
		try {
			AVLNode n = (AVLNode) p;			
			if (n.owner == null) throw new RuntimeException(" allready removed locator!");
			if (n.owner != this) throw new RuntimeException(" locator belongs to another AVLTree instance");
			return n;
		} catch (ClassCastException e) {
			throw new RuntimeException(" locator belongs to another container-type ");
		}
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#size()
	 */
	@Override
	public int size() {
		return size;
	}

	public Locator<K, E> find(K key) {
		// returns the leftmost occurence of
		// 'key' or null
		AVLNode n = root;
		AVLNode ret = null;
		while (! n.isExternal()){
			int comp = key.compareTo(n.key);
			if (comp==0){
				ret = n;
				n = n.left;
			}
			else if(comp<0) n = n.left;
			else n = n.right;
		}
		return ret;
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
		AVLNode n = root;
		while ( ! n.isExternal()){
			int comp = key.compareTo(n.key);
			if (comp <= 0) n=n.left;
			else n=n.right;
		}
		n.expand(key, o);
		size++;
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#remove(examples.Locator)
	 */
	@Override
	public void remove(Locator<K, E> loc) {
		AVLNode n = checkAndCast(loc);
		if (n.left.isExternal() || n.right.isExternal()){
			removeAboveExternal(n);
		}
		// find the most right node in the left subtree
		AVLNode p = n.left;
		while (! p.right.isExternal()) p=p.right;
		removeAboveExternal(p);
		// now we replace n by p
		p.parent = n.parent;
		p.right = n.right;
		p.left = n.left;
		if (n.isLeftChild()) n.parent.left=p;
		else if (n.isRightChild()) n.parent.right=p;
		else root = p;
		p.left.parent = p;
		p.right.parent = p;
		p.height = n.height;
		n.owner = null;
		size--;	
	}

	/**
	 * @param n
	 */
	private void removeAboveExternal(AVLNode n) {
		
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
		prittyPrint(root,"");
	}
	
	/**
	 * @param root2
	 * @param string
	 */
	private void print(AVLNode r, String ind) {
		if (r.isExternal()) return;
		print(r.right,ind+"..");
		System.out.println(ind+r.key);
		print(r.left,ind+"..");
	}
	
	
	private void prittyPrint(AVLNode r, String in) {
		if (r.isExternal()) return;		
		// right subtree 
		int sLen = in.length();
		String inNeu = in;
		if (r.isRightChild()) inNeu = in.substring(0,sLen-2)+"  ";
		prittyPrint(r.right,inNeu+" |");
		// root of the subtree
		String inN = in;
		if (sLen>0) inN = in.substring(0,sLen-1)+"+-";
		else inN = in+"-"; // root of the tree
		if ( ! r.right.isExternal()) System.out.println(inNeu+" |");
		else System.out.println(inNeu);
		System.out.println(inN+r.key()+":"+r.elem); //+"("+r.height+")"); 
		// left subtree
		inNeu = in;
		if (r.isLeftChild()){
			inNeu = in.substring(0,sLen-2)+"  ";
		}//		l = t.closestBefore(21);
//		System.out.println(l.key()+": "+l.element());

		prittyPrint(r.left,inNeu+" |");
	}

	
	
	public static void main(String[] argv){
		MyAVLTree<Integer,String> t = new MyAVLTree<>();
		t.insert(10,"");
		t.insert(5,"");
		t.insert(20,"occ_1");
		t.insert(30,"");
		t.insert(25,"");
		t.insert(20,"occ_2");
		t.insert(7,"");
		System.out.println("tree:");
		t.print();
		System.out.println();
		Locator<Integer,String> loc = t.find(20);
		System.out.println("found: "+loc.key()+":"+loc.element());
	}

}
