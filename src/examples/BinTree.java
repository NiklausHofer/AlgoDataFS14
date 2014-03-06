/**
 * 
 */
package examples;

import java.util.Iterator;

/**
 * Position based multi-way tree. Children are always 
 * reached via the iterator interface 
 * @author ps
 *
 * @param <E> The type of the objects stored in this tree
 */

public interface BinTree<E> {
	/**
	 * @return the root Position of this tree (or null)
	 */
	public Position<E> root();
	
	/**
	 * @param o the object to be stored
	 * @return the root Position 
	 */
	public Position<E> createRoot(E o);
	
	/**
	 * @param child a valid position of this tree
	 * @return the Position of the parent  of 'child' (or null)
	 */
	public Position<E> parent(Position<E> child);
	
	/**
	 * @param p a Position of this tree
	 * @return the left child of 'p'
	 */
	public Position<E> leftChild(Position<E> p);

	/**
	 * @param p a Position of this tree
	 * @return the left child of 'p'
	 */
	public Position<E> rightChild(Position<E> p);

	/**
	 * @param p a Position of this tree
	 * @param o the object attached to the new position
	 * @return the created new position which is the left child of 'p'
	 * (if the current left child is not null an Exception is thrown)  
	 */
	public Position<E> addLeftChild(Position<E> p, E o);

	/**
	 * @param p a Position of this tree
	 * @param o the object attached to the new position
	 * @return the created new position which is the right child of 'p'
	 * (if the current right child is not null an Exception is thrown)  
	 */
	public Position<E> addRightChild(Position<E> p, E o);

	/**
	 * @param p a (valid) external Position to be removed from this tree
	 *  (for internal Position an Exception is thrown)
	 */
	public void remove(Position<E> p);

	/**
	 * @param p a valid Position belonging to this tree
	 * @return true if 'p' has no children
	 */
	public boolean isExternal(Position<E> p);

	/**
	 * @param p a valid Position belonging to this tree
	 * @return true if 'p' has children
	 */
	public boolean isInternal(Position<E> p);

	/**
	 * @param p a valid Position belonging to this tree
	 * @return true if 'p' has a parent and is a left child of this parent
	 */
	public boolean isLeftChild(Position<E> p);

	/**
	 * @param p a valid Position belonging to this tree
	 * @return true if 'p' has a parent and is a right child of this parent
	 */
	public boolean isRightChild(Position<E> p);
	
	/**
	 * @param p a valid Position belonging to this tree
	 * @return true if 'p' has no parent (and therefore is the root node) 
	 */
	public boolean isRoot(Position<E> p);
	/**
	 * @return the number of elements (nodes) in this Tree
	 */
	public int size(); 

	/**
	 * @param p the poition where the element will be replaced 
	 * @param o the new object to replace the former object at 'p'
	 * @return the former object stored at 'p'
	 */
	public E replaceElement(Position<E> p, E o);
}
