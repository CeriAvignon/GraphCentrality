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
extends AbstractGraph<NodeType, LinkType>
{
	@Override
	public LinkType createLink(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		NodeType SourceNode = Nodes.get(inSourceNodeIndex);
		NodeType DestinationNode = Nodes.get(inDestinationNodeIndex);
		
		LinkType NewLink = null;
		LinkType BackLink = null;
		
		try 
		{
			NewLink = LinkClass.newInstance();
			BackLink = LinkClass.newInstance();
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		NewLink.setSourceNode(SourceNode);
		NewLink.setDestinationNode(DestinationNode);
		
		BackLink.setSourceNode(DestinationNode);
		BackLink.setDestinationNode(SourceNode);
		
		SourceNode.addLink(NewLink);
		DestinationNode.addLink(BackLink);
		Links.add(NewLink);
		Links.add(BackLink);
		
		return NewLink;
	}
			
	@Override
	public int getLinkCount()
	{
		return super.getLinkCount() / 2;		
	}
}
