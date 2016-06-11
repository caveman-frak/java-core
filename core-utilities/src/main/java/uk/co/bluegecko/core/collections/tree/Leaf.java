package uk.co.bluegecko.core.collections.tree;


/**
 * Leaf of a tree.
 *
 * @author tim
 *
 * @param <T>
 *            type of value for the tree
 */
public interface Leaf< T >
{

	/**
	 * The parent of this leaf.
	 * 
	 * @return the parent
	 */
	public Tree< T > parent();

	/**
	 * The value of this leaf.
	 * 
	 * @return the value
	 */
	public T value();

	/**
	 * Is this a leaf or a node.
	 * 
	 * @return true if a leaf
	 */
	public boolean isLeaf();

}
