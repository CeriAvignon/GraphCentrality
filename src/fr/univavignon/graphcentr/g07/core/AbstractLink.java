package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Abstract class to link two Nodes. 
 * Inherit from it to add other information when linking two nodes
 * @param <NodeType> Node type to link
 */
abstract class AbstractLink<NodeType>
{
	/** "Parent" node */
	protected NodeType SourceNode;
	/** Node linked to SourceNode */
	protected NodeType DestinationNode;

	/**
	 * Default constructor
	 */
	public AbstractLink() 
	{
	}
	
	/**
	 * Connect two nodes
	 * @param InSourceNode
	 * @param InDestinationNode
	 */
	public AbstractLink(NodeType InSourceNode, NodeType InDestinationNode)
	{		
		linkNode(InSourceNode, InDestinationNode);
	}
	
	/**
	 * Links two nodes
	 * @param InSourceNode Source node
	 * @param InDestinationNode Destination node
	 */
	public void linkNode(NodeType InSourceNode, NodeType InDestinationNode)
	{
		SourceNode = InSourceNode;
		DestinationNode = InDestinationNode;
	}
		
	/**
	 * Return source node
	 * @return Source node
	 */
	public NodeType getSourceNode() { return SourceNode; }
	
	/**
	 * Return destination node
	 * @return Destination node
	 */
	public NodeType getDestinationNode() { return DestinationNode; }
	
	
}

