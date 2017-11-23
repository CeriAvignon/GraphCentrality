package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Abstract class to link two Nodes. 
 * Inherit from it to add other information when linking two nodes
 * @param <NodeType> Node type to link
 */
public abstract class AbstractLink<NodeType>
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
		setSourceNode(InSourceNode);
		setDestinationNode(InDestinationNode);
	}
		
	/**
	 * Set source node
	 * @param inNode
	 */
	@SuppressWarnings("unchecked")
	public <T> void setSourceNode(T inNode)
	{
		SourceNode = (NodeType) inNode;
	}
	
	/**
	 * Set destination node
	 * @param inNode
	 */
	@SuppressWarnings("unchecked")
	public <T> void setDestinationNode(T inNode)
	{
		DestinationNode = (NodeType) inNode;
	}
	
	/**
	 * Returns true if given node is used in this link
	 * @param inNode Node to test
	 * @return True if node is concerned
	 */
	public <T> boolean nodeIsConcerned(T inNode)
	{
		return (inNode == SourceNode || inNode == DestinationNode);
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

