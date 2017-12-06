package fr.univavignon.graphcentr.g07.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.lang.reflect.ParameterizedType;

/**
 * 
 * @author Holstein Kelian
 *  
 * @param <NodeType> Type of node used by graph
 * @param <LinkType> Type of link used by graph
 * @brief Generic class that represents a graph. Provides few methods for node creation/linking
 */
public abstract class AbstractGraph
<
	 NodeType extends Node
	,LinkType extends Link
>
{
	/** Vector of nodes */
	protected List<NodeType> nodes;
	/** Vector of created links */
	protected List<List<LinkType>> links;
	
	/** String used to resolve template-class type */
	private String nodeTypeClassName;
	/** String used to resolve template-class type */
	private String nodeLinkClassName;
	
	/** Node class for NodeType instances */
	private Class<NodeType> nodeClass;
	/** Link class for LinkType instances */
	private Class<LinkType> linkClass;
	
	/**
	 * Default constructor
	 */
	public AbstractGraph()
	{
		nodes = new ArrayList<NodeType>();
		links = new ArrayList<List<LinkType>>();
		
		initializeClass();
	}
	
	/**
	 * Initialise graph classes (NodeClass & LinkClass)
	 */
	@SuppressWarnings("unchecked")
	private void initializeClass()
	{
		// Get templates parameters class names
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		nodeTypeClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1]; 
		nodeLinkClassName = pt.getActualTypeArguments()[1].toString().split("\\s")[1]; 
		
		// Initialise Node/Link class for future instances
		try 
		{
			nodeClass = (Class<NodeType>) Class.forName(nodeTypeClassName);
			linkClass = (Class<LinkType>) Class.forName(nodeLinkClassName);
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a class of node type
	 * @return Class<NodeType>
	 */
	public Class<NodeType> getNodeClass()
	{
		return nodeClass;
	}
	/**
	 * Returns a class of link type
	 * @return Class<LinkType>
	 */
	public Class<LinkType> getLinkClass()
	{
		return linkClass;
	}
		
	/**
	 * Create a NodeType and add it to graph
	 * @return Created node
	 */
	public NodeType createNode()
	{
		NodeType newNode = null;
		
		// Useless try and catch 
		try 
		{
			newNode = nodeClass.newInstance();
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		// Set node id
		addNode(newNode);
		initializeNode(newNode);
		
		return newNode;
	}
	
	/**
	 * Initialise given node
	 * @param inNode Node to initialise
	 */
	protected abstract void initializeNode(NodeType inNode);
	
	/**
	 * Add a node
	 * @param inNode Node to add
	 */
	public void addNode(NodeType inNode)
	{
		inNode.setIdentifier(nodes.size());
		nodes.add(inNode);
		links.add(new ArrayList<LinkType>());
	}
	
	/**
	 * Remove a node
	 * @param inNode Node to remove
	 */
	public void removeNode(NodeType inNode)
	{
		removeNode(getNodeIndex(inNode));
	}
	
	/**
	 * Remove a node at given index
	 * @param inIndexToRemove Node to remove
	 */
	public void removeNode(int inIndexToRemove)
	{
		nodes.remove(inIndexToRemove);
		links.remove(inIndexToRemove);
		// Decrease by one all node indexes if above removed one
		// same for links
		for(NodeType currentNode : nodes)
		{
			if(currentNode.getIdentifier() > inIndexToRemove)
				currentNode.setIdentifier(currentNode.getIdentifier() - 1);
			
			List<LinkType> nodeLinks = links.get(currentNode.getIdentifier());
			Iterator<LinkType> it = nodeLinks.iterator();
			while(it.hasNext())
			{
				LinkType currentLink = it.next();
				int srcID = currentLink.getSourceIdentifier();
				int dstID = currentLink.getDestinationIdentifier();
				
				// If current link have source node or destination node equals to removed one, delete it
				if(srcID == inIndexToRemove || dstID == inIndexToRemove)
				{
					it.remove();
					continue;
				}
				
				if(srcID > inIndexToRemove)
					currentLink.setSourceIdentifier(srcID - 1);
				if(dstID > inIndexToRemove)
					currentLink.setDestinationIdentifier(dstID - 1);
			}
		}
	}
	
	/**
	 * Returns graph node at given index
	 * @param inIndex
	 * @return Node
	 */
	public NodeType getNodeAt(int inIndex)
	{		
		return nodes.get(inIndex);
	}
	
	/**
	 * Returns node index
	 * @param inNode
	 * @return Node's index
	 */
	public int getNodeIndex(NodeType inNode)
	{
		return nodes.indexOf(inNode);
	}
	
	/**
	 * Returns all nodes
	 * @return Nodes
	 */
	public List<NodeType> getNodes()
	{
		return Collections.unmodifiableList(nodes);
	}
	
	/**
	 * Returns node count
	 * @return Node count
	 */
	public int getNodeCount()
	{
		return nodes.size();
	}
	
	/**
	 * Test if source node is adjacent to destination node
	 * @param inSourceNode Source node
	 * @param inDestinatonNode Destination node
	 * @return Adjacent to destination node 
	 */
	public boolean isAdjacentTo(NodeType inSourceNode, NodeType inDestinatonNode)
	{
		int destinationNodeIndex = getNodeIndex(inDestinatonNode);
		
		for(LinkType currentLink : getNodeLinks(inSourceNode))
		{
			if(currentLink.getDestinationIdentifier() == destinationNodeIndex)
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * Link two nodes
	 * @param inSourceNode
	 * @param inDestinationNode
	 * @return Created link
	 */
	public abstract LinkType createLink(NodeType inSourceNode, NodeType inDestinationNode);
	/**
	 * Initialise created link
	 * @param inLink
	 */
	protected abstract void initializeLink(LinkType inLink);
	/**
	 * Link two nodes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 * @return Created link
	 */
	public abstract LinkType createLink(int inSourceNodeIndex, int inDestinationNodeIndex);

	/**
	 * Create a NodeType and add it to graph
	 * @return Created node
	 */
	protected LinkType createLink()
	{
		LinkType newLink = null;
		
		// Useless try and catch 
		try 
		{
			newLink = linkClass.newInstance();
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		return newLink;
	}
	
	/**
	 * Add a link 
	 * @param inLink
	 */
	public void addLink(LinkType inLink)
	{
		links.get(inLink.getSourceIdentifier()).add(inLink);
	}
	
	/**
	 * Returns graph's link count
	 * @return Link count
	 */
	public int getLinkCount()
	{
		return links.size();
	}
	
	/**
	 * Returns all linksfrom given node
	 * @param inNode 
	 * @return Nodes
	 */
	public List<LinkType> getNodeLinks(NodeType inNode)
	{
		return Collections.unmodifiableList(links.get(inNode.getIdentifier()));
	}
	
	/**
	 * Transform graph to an adjacency matrix
	 * @return Adjacency matrix
	 */
	public abstract double[][] toAdjacencyMatrix(); 
	/**
	 * Transform graph to an incidence matrix
	 * @return Incidence matrix
	 */
	public abstract double[][] toIncidenceMatrix(); 
}
