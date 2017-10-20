package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Oriented link used in oriented graph and derived
 */
public class OrientedLink extends AbstractLink<OrientedNode>
{
	/**
	 * Default constructor
	 */
	public OrientedLink()
	{
		super();
	}
	
	/**
	 * Register both nodes
	 * @param InSourceNode
	 * @param InDestinationNode
	 */
	public OrientedLink(OrientedNode InSourceNode, OrientedNode InDestinationNode) 
	{
		super(InSourceNode, InDestinationNode);
	}
}
