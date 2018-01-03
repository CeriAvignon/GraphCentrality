package fr.univavignon.graphcentr.g07.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Holstein Kelian
 *
 * @param <NodeType> Type of node
 * @param <LinkType> Type of link
 * @brief Generic graph for directed-like graph, links are directed
 */
public abstract class AbstractDirectedGraph 
<
	NodeType extends Node
	,LinkType extends Link
>
extends AbstractGraph<NodeType, LinkType>
{
	/** Incoming links */
	protected List<List<LinkType>> incomingLinks = new ArrayList<List<LinkType>>();
	
	@Override
	protected void initializeNode(NodeType inLink)
	{
		// Nothing to do here
	}
	
	@Override
	protected void initializeLink(LinkType inLink)
	{
		// Nothing to do here
	}
	
	@Override
	public void clear()
	{
		super.clear();
		incomingLinks.clear();
	}
	
	@Override
	public void addNode(NodeType inNode)
	{
		super.addNode(inNode);
		incomingLinks.add(new ArrayList<LinkType>());
	}
	
	@Override
	public void addLink(LinkType inLink)
	{
		super.addLink(inLink);
		incomingLinks.get(inLink.getDestinationIdentifier()).add(inLink);
	}
	
	@Override
	public LinkType createLink(NodeType inSourceNode, NodeType inDestinationNode)
	{
		return createLink(getNodeIndex(inSourceNode), getNodeIndex(inDestinationNode));
	}
	
	@Override
	public LinkType createLink(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		// If link already exists, return it
		if(isAdjacentTo(inSourceNodeIndex, inDestinationNodeIndex))
			return getLink(inSourceNodeIndex, inDestinationNodeIndex);
		
		// Create link between source and destination node
		LinkType link = createLink();
		link.setSourceIdentifier(inSourceNodeIndex);
		link.setDestinationIdentifier(inDestinationNodeIndex);
		addLink(link);
		initializeLink(link);
		
		return link;
	}
	
	@Override
	public void removeNode(int inIndexToRemove)
	{
		nodes.remove(inIndexToRemove);
		links.remove(inIndexToRemove);
		incomingLinks.remove(inIndexToRemove);
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
					linkCount--;
					continue;
				}
				
				if(srcID > inIndexToRemove)
					currentLink.setSourceIdentifier(srcID - 1);
				if(dstID > inIndexToRemove)
					currentLink.setDestinationIdentifier(dstID - 1);
			}
			
			List<LinkType> nodeIncomingLinks = incomingLinks.get(currentNode.getIdentifier());
			it = nodeIncomingLinks.iterator();
			
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
	 * Returns node degree
	 * @param inNode
	 * @return Node's degree
	 */
	public int getIncomingDegree(NodeType inNode)
	{
		return incomingLinks.get(inNode.getIdentifier()).size();
	}
	
	/**
	 * Returns node incoming degree
	 * @param inNodeIndex Node's index
	 * @return Node's degree
	 */
	public int getIncomingDegree(int inNodeIndex)
	{
		return getIncomingDegree(getNodeAt(inNodeIndex));
	}
	
	/**
	 * Returns all incoming links of given node
	 * @param inNode 
	 * @return Nodes
	 */
	public List<LinkType> getNodeIncomingLinks(NodeType inNode)
	{
		return Collections.unmodifiableList(incomingLinks.get(inNode.getIdentifier()));
	}
	
	/**
	 * Returns node outgoing degree
	 * @param inNode	
	 * @return Node's degree
	 */
	public int getOutgoingDegree(NodeType inNode)
	{
		return links.get(inNode.getIdentifier()).size();
	}
	
	/**
	 * Returns node outgoing degree
	 * @param inNodeIndex Node's index
	 * @return Node's degree
	 */
	public int getOutgoingDegree(int inNodeIndex)
	{
		return getOutgoingDegree(getNodeAt(inNodeIndex));
	}
	
	
	/**
	 * Returns an array of nodes incoming degree
	 * @return All incoming degrees
	 */
	public int[] getNodesIncomingDegree()
	{
		int[] degrees = new int[nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++)
		{
			degrees[i] = getIncomingDegree(nodes.get(i));
		}
		
		return degrees;
	}
	
	/**
	 * Returns an array of nodes outgoing degree
	 * @return All outgoing degrees
	 */
	public int[] getNodesOutgoingDegree()
	{
		int[] degrees = new int[nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++)
		{
			degrees[i] = getOutgoingDegree(nodes.get(i));
		}
		
		return degrees;
	}
	
	@Override
	public void removeLink(int inSourceNodeID, int inDestinationNodeID)
	{
		List<LinkType> nodeLinks = links.get(inSourceNodeID);
		Iterator<LinkType> it = nodeLinks.iterator();
		while(it.hasNext())
		{
			LinkType currentLink = it.next();
			if(currentLink.getDestinationIdentifier() == inDestinationNodeID)
			{
				it.remove();
				linkCount--;
				break;
			}
		}
		
		// Remove incoming link as well
		nodeLinks = incomingLinks.get(inDestinationNodeID);
		it = nodeLinks.iterator();
		while(it.hasNext())
		{
			LinkType currentLink = it.next();
			if(currentLink.getSourceIdentifier() == inSourceNodeID)
			{
				it.remove();
				break;
			}
		}
	}
	
	@Override
	public double[][] toAdjacencyMatrix()
	{
		double[][] adjacencyMatrix = new double[nodes.size()][nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++)
		{
			for(LinkType currentLink : getNodeLinks(getNodeAt(i)))
			{
				adjacencyMatrix[i][currentLink.getDestinationIdentifier()] = 1;
			}
		}
		
		return adjacencyMatrix;
	}
	
	@Override
	public double[][] toIncidenceMatrix()
	{
		int linkCount = 0;
		for(int i = 0; i < nodes.size(); i++)
		{
			linkCount += getOutgoingDegree(getNodeAt(i));
		}
		double[][] incidenceMatrix = new double[nodes.size()][linkCount];
		
		int linkIndex = 0;
		for(int i = 0; i < nodes.size(); i++)
		{
			for(LinkType currentLink : getNodeLinks(getNodeAt(i)))
			{
				incidenceMatrix[i][linkIndex] = 1;
				incidenceMatrix[currentLink.getDestinationIdentifier()][linkIndex] = -1;
				linkIndex++;
			}
		}
		
		return incidenceMatrix;
	}
}
