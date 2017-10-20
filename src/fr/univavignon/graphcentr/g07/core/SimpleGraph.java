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
	public void linkNode(Node inSourceNode, Node inDestinationNode)
	{
		Link NewLink = new Link(inSourceNode, inDestinationNode);
		Links.add(NewLink);
		inSourceNode.addLink(NewLink);
		
		NewLink = new Link(inDestinationNode, inSourceNode);
		Links.add(NewLink);
		inDestinationNode.addLink(NewLink);
	}
	
	/**
	 * Links two Nodes based on indexes
	 * Note : Link will be shared in both nodes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 */
	public void linkNode(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		Node SourceNode = Nodes.get(inSourceNodeIndex);
		Node DestinationNode = Nodes.get(inDestinationNodeIndex);

		linkNode(SourceNode, DestinationNode);
	}
	
	/**
	 * Create a node and push it in the graph
	 * @return Created node
	 */
	public Node createNode()
	{
		Node NewNode = new Node();
		addNode(NewNode);
		return NewNode;
	}
	
	@Override
	public int getLinkCount()
	{
		return super.getLinkCount() / 2;		
	}
}
