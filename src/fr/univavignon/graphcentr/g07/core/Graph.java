package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;
import java.lang.reflect.ParameterizedType;

/**
 * 
 * @author JackassDestroyer
 * Generic class that represents a graph. Provides few methods for node creation/linking
 * @param <NodeType> Type of node used by graph
 * @param <LinkType> Type of link used by graph
 */
class Graph
<
	 NodeType extends AbstractNode<?>
	,LinkType extends AbstractLink<?>
>
{
	/**
	 * Vector of nodes
	 */
	protected Vector<NodeType> Nodes;
	/**
	 * Vector of created links
	 */
	protected Vector<LinkType> Links;
	
	/**
	 * String used to resolve template-class type
	 */
	private String NodeTypeClassName;
	/**
	 * String used to resolve template-class type
	 */
	private String NodeLinkClassName;
	
	/**
	 * Default constructor
	 */
	public Graph()
	{
		Nodes = new Vector<NodeType>();
		Links = new Vector<LinkType>();
	}
	
	/**
	 * Resolve both NodeTypeClassName & NodeLinkClassName
	 */
	public void resolveClassNames()
	{
		if(NodeTypeClassName == null)
		{
			ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
			NodeTypeClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1]; 
		}
		if(NodeLinkClassName == null)
		{
			ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
			NodeLinkClassName = pt.getActualTypeArguments()[1].toString().split("\\s")[1]; 
		}
	}
	
	/**
	 * Returns NodeTypeClassName
	 * @return NodeTypeClassName
	 */
	public String getNodeTypeClassName()
	{
		return NodeTypeClassName;
	}
	
	/**
	 * Returns NodeLinkClassName
	 * @return NodeLinkClassName
	 */
	public String getLinkTypeClassName()
	{
		return NodeLinkClassName;
	}
	
	/**
	 * Add a node
	 * @param inNode Node to add
	 */
	public void addNode(NodeType inNode)
	{
		Nodes.add(inNode);
	}
	
	/**
	 * Remove a node
	 * @param inNode Node to remove
	 */
	public void removeNode(NodeType inNode)
	{
		Nodes.remove(inNode);
	}
	
	/**
	 * Remove a node at given index
	 * @param inIndex Node to remove
	 */
	public void removeNode(int inIndex)
	{
		Nodes.remove(inIndex);
	}
	
	/**
	 * Returns all nodes
	 * @return Nodes
	 */
	public Vector<NodeType> getNodes()
	{
		return Nodes;
	}
	
	/**
	 * Returns node count
	 * @return Node count
	 */
	public int getNodeCount()
	{
		return Nodes.size();
	}
	
	/**
	 * Returns graph's link count
	 * @return Link count
	 */
	public int getLinkCount()
	{
		return Links.size();
	}
	
	/**
	 * Returns all links
	 * @return Links
	 */
	public Vector<LinkType> getLinks()
	{
		return Links;
	}
	
	/**
	 * Returns graph node at given index
	 * @param inIndex
	 * @return Node
	 */
	public NodeType at(int inIndex)
	{		
		return Nodes.get(inIndex);
	}
	
	/**
	 * Returns node index
	 * @param inNode
	 * @return Node's index
	 */
	@SuppressWarnings("unlikely-arg-type")
	public <T>
	int indexOf(T inNode)
	{
		return Nodes.indexOf(inNode);
	}
}
