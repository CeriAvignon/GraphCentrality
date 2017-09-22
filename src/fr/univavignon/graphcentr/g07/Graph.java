package Core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Generic class that represents a graph. Provides few methods for node creation/linking
 * @param <NodeType> Type of node used by graph
 * @param <LinkType> Type of link used by graph
 */
public class Graph
<
	 NodeType extends AbstractNode<?>
	,LinkType extends AbstractLink<?>
>
{
	protected Vector<NodeType> Nodes;
	
	/**
	 * Default constructor
	 */
	public Graph()
	{
		Nodes = new Vector<NodeType>();
	}
	
	/**
	 * Add a node
	 * @param InNode Node to add
	 */
	public void AddNode(NodeType InNode)
	{
		Nodes.add(InNode);
	}
	
	/**
	 * Remove a node
	 * @param InNode Node to remove
	 */
	public void RemoveNode(NodeType InNode)
	{
		Nodes.remove(InNode);
	}
	
	/**
	 * Return all nodes
	 * @return
	 */
	public Vector<NodeType> GetNodes()
	{
		return Nodes;
	}
	
	/**
	 * Returns graph node at given index
	 * @param InIndex
	 * @return Can be null if index is invalid
	 */
	public NodeType At(int InIndex)
	{
		if(InIndex < 0 || InIndex >= Nodes.size())
			return null;
		
		return Nodes.get(InIndex);
	}
	
	/**
	 * Returns node count
	 * @return
	 */
	public int GetNodeCount()
	{
		return Nodes.size();
	}
}
