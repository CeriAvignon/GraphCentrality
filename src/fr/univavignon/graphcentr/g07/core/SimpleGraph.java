package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Simple graph base class
 */
public class SimpleGraph extends Graph<Node, Link>
{
	
	/**
	 * Links two Nodes
	 * Note : Link will be shared in both nodes
	 * @param inSourceNode
	 * @param inDestinationNode
	 */
	@Override
	public Link linkNode(Node inSourceNode, Node inDestinationNode)
	{
		Link NewLink = super.linkNode(inSourceNode, inDestinationNode);
		super.linkNode(inDestinationNode, inSourceNode);
		
		return NewLink;
	}
	
	@Override
	public Link linkNode(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		Link NewLink = super.linkNode(inSourceNodeIndex, inDestinationNodeIndex);
		super.linkNode(inDestinationNodeIndex, inSourceNodeIndex);
		
		return NewLink;
	}
			
	@Override
	public int getLinkCount()
	{
		return super.getLinkCount() / 2;		
	}
}
