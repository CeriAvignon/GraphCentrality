package fr.univavignon.graphcentr.g07.core;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Holstein Kelian
 *
 * @param <NodeType> Type of node
 * @param <LinkType> Type of link
 * @brief Generic graph for simple-like graph, links are not directed
 */
public abstract class AbstractSimpleGraph 
<
	NodeType extends Node
	,LinkType extends Link
>
extends AbstractGraph<NodeType, LinkType>
{
	/** Last "back-link" created */
	protected LinkType lastBackLink;
	
	/**
	 * Returns last back link
	 * @return Last back link
	 */
	public LinkType getLastBackLink()
	{
		return lastBackLink;
	}

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
		
		// Create "back-link" between destination and source node
		LinkType backLink = createLink();
		backLink.setSourceIdentifier(inDestinationNodeIndex);
		backLink.setDestinationIdentifier(inSourceNodeIndex);
		addLink(backLink);
		initializeLink(backLink);
		lastBackLink = backLink;
		
		return link;
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
		
		// Remove "back-link" as well
		nodeLinks = links.get(inDestinationNodeID);
		it = nodeLinks.iterator();
		while(it.hasNext())
		{
			LinkType currentLink = it.next();
			if(currentLink.getDestinationIdentifier() == inSourceNodeID)
			{
				it.remove();
				linkCount--;
				break;
			}
		}
	}
	
	/**
	 * Returns node degree
	 * @param inNode
	 * @return Node's degree
	 */
	public int getNodeDegree(NodeType inNode)
	{
		return links.get(inNode.getIdentifier()).size();
	}
	
	/**
	 * Returns node degree
	 * @param inNodeIndex Node index
	 * @return Node's degree
	 */
	public int getNodeDegree(int inNodeIndex)
	{
		return getNodeDegree(getNodeAt(inNodeIndex));
	}
	
	/**
	 * Returns an array of nodes degree 
	 * @return All nodes degree
	 */
	public int[] getNodesDegree()
	{
		int[] degrees = new int[nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++)
		{
			degrees[i] = getNodeDegree(nodes.get(i));
		}
		
		return degrees;
	}
	
	@Override
	public double[][] toAdjacencyMatrix()
	{
		double[][] adjacencyMatrix = new double[getNodeCount()][getNodeCount()];
		
		for(int i = 0; i < getNodeCount(); i++)
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
			linkCount += getNodeDegree(getNodeAt(i));
		}
		double[][] incidenceMatrix = new double[nodes.size()][linkCount];
		
		int linkIndex = 0;
		for(int i = 0; i < nodes.size(); i++)
		{
			for(LinkType currentLink : getNodeLinks(getNodeAt(i)))
			{
				incidenceMatrix[i][linkIndex] = 1;
				incidenceMatrix[currentLink.getDestinationIdentifier()][linkIndex] = 1;
				linkIndex++;
			}
		}
		
		return incidenceMatrix;
	}
	
	@Override
	public int getLinkCount()
	{
		// On simple graphs there are twice more links because of back-links
		return linkCount / 2;
	}
}
