package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Directed graph
 * @param <NodeType> Type of node used by graph
 * @param <LinkType> Type of link used by graph
 */
public class DirectedGraph
<
	NodeType extends AbstractNode<?>
	,LinkType extends AbstractLink<?>
>
extends AbstractGraph<NodeType, LinkType>
{	
	/**
	 * Link two nodes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 * @return Created link
	 */
	@Override
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
}
