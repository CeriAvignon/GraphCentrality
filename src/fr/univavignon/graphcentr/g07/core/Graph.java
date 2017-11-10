package fr.univavignon.graphcentr.g07.core;

import java.util.Iterator;
import java.util.Vector;
import java.lang.reflect.ParameterizedType;

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
	/** Vector of nodes */
	protected Vector<NodeType> Nodes;
	/** Vector of created links */
	protected Vector<LinkType> Links;
	
	/** String used to resolve template-class type */
	private String NodeTypeClassName;
	/** String used to resolve template-class type */
	private String NodeLinkClassName;
	
	/** Node class for NodeType instances */
	private Class<NodeType> NodeClass;
	/** Link class for LinkType instances */
	private Class<LinkType> LinkClass;
	
	/**
	 * Default constructor
	 */
	public Graph()
	{
		Nodes = new Vector<NodeType>();
		Links = new Vector<LinkType>();
		
		initializeClass();
	}
	
	/**
	 * Initialise graph classes (NodeClass & LinkClass)
	 */
	@SuppressWarnings("unchecked")
	protected void initializeClass()
	{
		// Get templates parameters class names
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		NodeTypeClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1]; 
		NodeLinkClassName = pt.getActualTypeArguments()[1].toString().split("\\s")[1]; 
		
		// Initialise Node/Link class for future instances
		try 
		{
			NodeClass = (Class<NodeType>) Class.forName(NodeTypeClassName);
			LinkClass = (Class<LinkType>) Class.forName(NodeLinkClassName);
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns node class (for node instancing)
	 * @return Node class
	 */
	public Class<NodeType> getNodeClass()
	{
		return NodeClass;
	}
	
	/**
	 * Returns link class (for link instancing)
	 * @return Link class
	 */
	public Class<LinkType> getLinkClass()
	{
		return LinkClass;
	}
	
	/**
	 * Create a NodeType and add it to graph
	 * @return Created node
	 */
	public NodeType createNode()
	{
		NodeType NewNode = null;
		
		// Useless try and catch 
		try 
		{
			NewNode = NodeClass.newInstance();
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		addNode(NewNode);
		
		return NewNode;
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
		NodeType RemovedNode = inNode;
		Nodes.remove(inNode);
		
		// Remove all removed node's links
		for(AbstractLink<?> CurrentLink : RemovedNode.getLinks())
			Links.remove(CurrentLink);
		
		// Then search for each node if links are related with deleted node
		for(NodeType CurrentNode : Nodes)
		{
			@SuppressWarnings("unchecked")
			Iterator<AbstractLink<?>> LinkIterator = (Iterator<AbstractLink<?>>) CurrentNode.getLinks().iterator();
			while(LinkIterator.hasNext())
			{
				AbstractLink<?> CurrentLink = LinkIterator.next();
				
				if(CurrentLink.nodeIsConcerned(RemovedNode))
				{
					//CurrentNode.removeLink(CurrentLink);
					LinkIterator.remove();
					Links.remove(CurrentLink);
				}
			}
		}
	}
	
	/**
	 * Remove a node at given index
	 * @param inIndex Node to remove
	 */
	public void removeNode(int inIndex)
	{
		NodeType NodeToRemove = Nodes.get(inIndex);
		removeNode(NodeToRemove);
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
	 * Link two nodes
	 * @param inSourceNode
	 * @param inDestinationNode
	 * @return Created link
	 */
	public LinkType linkNode(NodeType inSourceNode, NodeType inDestinationNode)
	{
		int SourceNodeIndex = Nodes.indexOf(inSourceNode);
		int DestinationNodeIndex = Nodes.indexOf(inDestinationNode);
		
		return linkNode(SourceNodeIndex, DestinationNodeIndex);
	}
	
	/**
	 * Link two nodes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 * @return Created link
	 */
	public LinkType linkNode(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		NodeType SourceNode = Nodes.get(inSourceNodeIndex);
		NodeType DestinationNode = Nodes.get(inDestinationNodeIndex);
		
		LinkType NewLink = null;
		
		try 
		{
			NewLink = LinkClass.newInstance();
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		NewLink.setSourceNode(SourceNode);
		NewLink.setDestinationNode(DestinationNode);
		SourceNode.addLink(NewLink);
		Links.add(NewLink);
		
		return NewLink;
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
	 * Returns last link
	 * @return Last link
	 */
	@SuppressWarnings("unchecked")
	public <T>
	T getLastLink()
	{
		return (T) Links.lastElement();
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
