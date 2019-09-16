package common.structure;

/**
 * Basic class for all node types that {@link Tree<T>} subclasses
 * contain. It may be node for binary trees, of node for regular trees.
 * Every derived class has {@link #parent} reference to a parent member,
 * All relations are bidirectioinal. That means, that it is possible to
 * make chain from leaves to root and vice versa.
 * @param <T> Type of objects that node can contain.
 */
public abstract class Node<T> {

	/** Stores actual content of node. */
	public final T content;
	/** Reference to a parent node or null if node has no parent. */
	protected Node<T> parent;

	/**
	 * Creates a node with specified content and parent node.
	 * @param content Object is stored inside node.
	 * @param parent A node that is to be parent for current.
	 *               {@code null} if node does not have parent.
	 */
	public Node(T content, Node<T> parent){
		this.content = content;
		this.parent = parent;
	}

	/**
	 * Default constructor for nodes.
	 * @param content Object is stored inside node.
	 */
	public Node(T content){
		this.content = content;
	}

	public Node(){
		this.content = null;
	}

	/**
	 * Returns parent node for current node.
	 * Node cannot have more than one parent.
	 * @return Parent node for current node or null if node has no parent (i.e. it is root itself).
	 */
	public final Node<T> getParent(){
		return this.parent;
	}

	/**
	 * Returns top-level parent node that has no parent.
	 * In tree, it means that {@code Node<T>.getRoot() == Tree<T>.getRoot()}.
	 * @return Topest node that has no parent or null if current node
	 *         does not have parent.
	 */
	public final Node<T> getRoot(){
		if(this.parent == null)
			return null;
		var root = this.parent;
		while(root.parent != null)
			root = root.parent;
		return root;
	}

	/**
	 * Returns depth of the current node.
	 * If it does not have parent then its depth is 1 (or if it root which might mean the same).
	 * @return Depth of the node.
	 */
	public final int getDepth(){
		int depth = 1;
		if(this.parent == null)
			return depth;
		var root = this.parent;
		while (root.parent != null) {
			root = this.parent;
			depth++;
		}
		return depth;
	}

	/**
	 * Leaf node is a node that has no children.
	 * @return {@code true} if current node is leaf.
	 */
	public final boolean isLeaf(){
		return this.getChildNodesCount() == 0;
	}

	/**
	 * Returns an amount of children.
	 * @return Amount of child nodes corresponding to the current node.
	 */
	public abstract int getChildNodesCount();

	/**
	 * Destroys bidirectional bonds between current node
	 * and its parent. I.e. removes so-called "edge" between them.
	 * Does nothing if node does not have parent.
	 */
	public void unleash() {
		if (this.parent == null)
			return;
		this.parent.removeNode(this);
	}

	/**
	 * Removes child node from current node. If node has
	 * more than one children equal to {@code node}, then
	 * the first occurence will be removed.
	 * @param node A node to be removed.
	 * @return Removed node or null if supplied
	 *         node does not equal to any of children.
	 */
	public abstract Node<T> removeNode(Node<T> node);

	/**
	 * Removes child node from current, if any of children
	 * has content in way that expression {@code content.equals(node.content)}
	 * evaluates to {@code true}. It is the same as {@link #removeNode(Node)},
	 * except that argument is not wrapper. If node has
	 * more than one children equal to {@code node}, then
	 * the first occurence will be removed.
	 * @param content Node with this object inside will be removed.
	 * @return Removed child or null if there is no child with {@code content} content.
	 */
	public abstract Node<T> removeNode(T content);

	/**
	 * Check if node has specified child.
	 * @param node A node is considered as child of current node.
	 * @return {@code true} if current node has {@code node} child.
	 */
	public abstract boolean hasNode(Node<T> node);

	/**
	 * Returns an array of children of current node.
	 * @return An array.
	 */
	public abstract Node<T>[] getChildren();

	@Override
	public final int hashCode(){
		return this.content.hashCode();
	}

	@Override
	public final boolean equals(Object obj){
		return this.content.equals(obj);
	}
}