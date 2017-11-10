package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Derived of Graph, override few methods to match simple-like graph behaviour
 * @param <NodeType>
 * @param <LinkType>
 */
public class SimpleLikeGraph  
<
NodeType extends AbstractNode<?>
,LinkType extends AbstractLink<?>
>
extends Graph<NodeType, LinkType>
{
	@Override
	public LinkType linkNode(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		LinkType NewLink = super.linkNode(inSourceNodeIndex, inDestinationNodeIndex);
		super.linkNode(inDestinationNodeIndex, inSourceNodeIndex);
		
		return NewLink;
	}
			
	@Override
	public int getLinkCount()
	{
		return super.getLinkCount() / 2;		
	}
}
