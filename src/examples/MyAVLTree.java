/**
 * 
 */
package examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
				ret = n; // we found it but we want the leftmost
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
		ArrayList<Locator<K,E>> al = new ArrayList<>();
		findAll(key,root,al);
		return al.toArray(new Locator[0]);	
	}

	/**
	 * @param root2
	 * @param al
	 */
	private void findAll(K key,AVLNode n, ArrayList<Locator<K, E>> al) {
		if (n.isExternal()) return;
		int comp = key.compareTo(n.key);
		if (comp==0){
			findAll(key,n.left,al);
			al.add(n);
			findAll(key,n.right,al);
		}
		else if(comp<0) 
			findAll(key,n.left,al);
		else findAll(key,n.right,al);;	
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
		adjustHeightAboveAndRebalance(n);
		return n;
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#remove(examples.Locator)
	 */
	@Override
	public void remove(Locator<K, E> loc) {
		AVLNode n = checkAndCast(loc);
		AVLNode w;
		if (n.left.isExternal() || n.right.isExternal()){
			w = removeAboveExternal(n);
		}
		else {
		// find the most right node in the left subtree
			AVLNode p = n.left;
			while (! p.right.isExternal()) p=p.right;
			w = removeAboveExternal(p);
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
		}
		adjustHeightAboveAndRebalance(w);
		n.owner = null;
		size--;	
	}

	private void adjustHeightAboveAndRebalance(AVLNode n){
		// corrrect the height of all ancesters
		n = n.parent;
		while(n!=null){
			int newHeight = Math.max(n.left.height,n.right.height)+1;
			boolean balanced = Math.abs(n.left.height-n.right.height) < 2;
			if (n.height == newHeight && balanced) break;
			n.height = newHeight;
			if ( ! balanced) n=restructure(n);
			n=n.parent;
		}
	}

	private AVLNode restructure(AVLNode n) {
		// cnt++;
		// n is unbalanced
		// returns the node that takes the position of n
		AVLNode p=n.parent,z=n,x=null,y=null,
		a=null,b=null,c=null, t1=null,t2=null;
		if (z.left.height > z.right.height){
			//   z
			//  /
			// y
			c=z;
			y=z.left;
			if (y.left.height >=y.right.height){
				// in case we have two equal branches
				// concidering the length we take alway s the single
				// rotation
				//     z
				//    /
				//   y
				//  /
				// x
				x=y.left;
				t1=x.right;
				t2=y.right;
				b=y;
				a=x;
			}
			else {
				//     z
				//    /
				//   y
				//   \  
				//    x
				x=y.right;
				t1=x.left;
				t2=x.right;
				a=y;
				b=x;
			}
		}
		else{
			// z
			//   \
			//    y
			a=z;
			y=z.right;
			if (y.right.height >= y.left.height){
				//  z
				//   \
				//    y
				//     \  
				//      x
				x=y.right;
				b=y;
				c=x;
				t1=y.left;
				t2=x.left;
			}
			else {
				//  z
				//   \
				//    y
				//    /  
				//   x
				x=y.left;
				b=x;
				c=y;
				t1=x.left;
				t2=x.right;
			}
		}		
		// umhaengen
		b.parent = p;
		if (p != null){
			if (p.left == z) {
				p.left=b;
			}
			else p.right=b;
		}
		else {
			root=b;
		}
		b.right = c;
		b.left = a;
		// und umgekehrt
		a.parent = b;
		c.parent = b;

		// subtrees:
		a.right = t1;
		t1.parent = a;
		c.left = t2;
		t2.parent = c;
		
		
		a.height = Math.max(a.left.height, a.right.height)+1;
		c.height = Math.max(c.left.height, c.right.height)+1;
		// now we can calculate the height of b
		b.height = Math.max(b.left.height, b.right.height)+1;
		return b;
	}

	/**
	 * @param n
	 * @return 
	 */
	private AVLNode removeAboveExternal(AVLNode n) {
		AVLNode r; // takes the position of n
		if (n.left.isExternal()){
			r = n.right;
			r.parent = n.parent;
			if (n.isLeftChild()) r.parent.left = r;
			else if (n.isRightChild()) r.parent.right = r;
			else root=r; // n was the root node!
		}
		else {
			r = n.left;
			r.parent = n.parent;
			if (n.isLeftChild()) r.parent.left = r;
			else if (n.isRightChild()) r.parent.right = r;
			else root=r;		
		}
		return r;
		
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
		AVLNode n = checkAndCast(loc);
		if (! n.right.isExternal()){
			n=n.right;
			while (! n.isExternal()) n=n.left; // finally we end at a external!
		}
		else {
			while (n.isRightChild()) n=n.parent;
		}
		return n.parent;

	}
	
	
	private AVLNode findMin(AVLNode n){
		// returns the leftmost internal 
		// node of the subtree originating at n
		while(! n.isExternal()){
			n=n.left;
		}
		return n.parent;		
	}

	private AVLNode findMax(AVLNode n){
		// returns the leftmost internal 
		// node of the subtree originating at n
		while(! n.isExternal()){
			n=n.right;
		}
		return n.parent;		
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
		return findMin(root);
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#max()
	 */
	@Override
	public Locator<K, E> max() {
		return findMax(root);
	}

	/* (non-Javadoc)
	 * @see examples.OrderedDictionary#sortedLocators()
	 */
	@Override
	public Iterator<Locator<K, E>> sortedLocators() {
		return new Iterator<Locator<K,E>>(){
			AVLNode cur = findMin(root); 
			@Override
			public boolean hasNext() {
				return cur != null;
			}

			@Override
			public Locator<K, E> next() {
				AVLNode ret = cur;
				if (! cur.right.isExternal())
					cur = findMin(cur.right);
				else {
					while (cur.isRightChild()) cur=cur.parent;
					cur = cur.parent;
				}
				return ret;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(" remove not implemented");
				
			}};
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
		System.out.println(inN+r.key()+":"+r.elem+"("+r.height+")"); 
		// left subtree
		inNeu = in;
		if (r.isLeftChild()){
			inNeu = in.substring(0,sLen-2)+"  ";
		}
		prittyPrint(r.left,inNeu+" |");
	}

	public void check(){
		// throws a RuntimeException if
		// something is wrong with the tree!
		if (root.parent != null) throw new RuntimeException("root has a parent!!");
		check(root);
	}
	
	/**
	 * @param root2
	 */
	private void check(AVLNode n){
		// throws a RuntimeException if
		// something is wrong with the subtree
		// originating at n
		if (n.key != null){
			// check the subtrees
			check(n.left);
			check(n.right);
			// check the node owner:
			if (n.owner != this)
					throw new RuntimeException("invalid node "+n.key+":"+n.elem);
			if (n.height != Math.max(n.left.height,n.right.height)+1) 
					throw new RuntimeException("height not correct at "+n.key+":"+n.elem);
			// check the balancing:
			if (Math.abs(n.left.height-n.right.height)>1) 
					throw new RuntimeException("not balanced at "+n.key+":"+n.elem);
			// key order left:
			if (n.left.key != null && 
					n.key.compareTo(n.left.key) < 0)
					throw new RuntimeException("left child is greater at "+n.key+":"+n.elem);
			// key order right:
			if (n.right.key != null && 
					n.key.compareTo(n.right.key) > 0)
					throw new RuntimeException("right child is smaller at "+n.key+":"+n.elem);
			// chaining
			if (n.left.parent != n) throw 
					new RuntimeException(" left child has wrong parent! "+n.key+":"+n.elem);
			if (n.right.parent != n) throw 
					new RuntimeException(" right child has wrong parent! "+n.key+":"+n.elem);
		}
	}
	
	
	
	public static void main(String[] argv){
		MyAVLTree<Integer, String> t = new MyAVLTree<>();
		Random rand = new Random(3434534);
		int n  = 1000;
		Locator<Integer,String>[] locs = new Locator[n];
		long time1 = System.currentTimeMillis();
		for (int i=0;i<n;i++) {
			locs[i]=t.insert(rand.nextInt(n),""+i);
			//locs[i]=t.insert(i, "bla");
		}
		for (int i=0;i<n/2;i++) {
			t.remove(t.find(locs[i].key()));
		}
//		Locator<Integer,String> [] found = t.findAll(16); 
//		for (Locator f:found) System.out.println(f.element());
//		System.out.println(found);
//		
//		for (int i=0;i<n;i++) {
//			Locator<Integer,String> loc = t.find(locs[i].key());
//			System.out.println(loc.key()+":"+loc.element());
//		}
		long time2 = System.currentTimeMillis();
		System.out.println("elapsed time: "+(time2-time1)/1000.0);
//		t.print();
		t.check();
		int siz = t.size();
		Locator loc = t.min();
//		while (loc != null){
//			System.out.println(loc.key());
//			loc = t.next(loc);
//		}
		Iterator<Locator<Integer,String>> it = t.sortedLocators();
		while (it.hasNext()){
			System.out.println(it.next().key());
		}
//		Locator[] l = t.findAll(0);
//		for (int i = 0; i<l.length;i++) System.out.println(l[i].element());
	}

}
