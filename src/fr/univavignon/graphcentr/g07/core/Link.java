package fr.univavignon.graphcentr.g07.core;

/**
 * @author JackassDestroyer
 * @brief Simple link between two nodes, 0 informations added
 */
public class Link extends AbstractLink<Node>
{	
	/**
	 * Default constructor
	 */
	public Link()
	{
		super();
	}
	
	/**
	 * Register both nodes
	 * @param InSourceNode
	 * @param InDestinationNode
	 */
	public Link(Node InSourceNode, Node InDestinationNode) 
	{
		super(InSourceNode, InDestinationNode);
	}
}
