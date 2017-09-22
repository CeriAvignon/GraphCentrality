package Core;

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
	public AbstractLink() {}
	
	/**
	 * Connect two nodes
	 * @param InSourceNode
	 * @param InDestinationNode
	 */
	public AbstractLink(NodeType InSourceNode, NodeType InDestinationNode)
	{		
		LinkNode(InSourceNode, InDestinationNode);
	}
	
	/**
	 * Links two nodes
	 * @param InSourceNode Source node
	 * @param InDestinationNode Destination node
	 */
	public void LinkNode(NodeType InSourceNode, NodeType InDestinationNode)
	{
		SourceNode = InSourceNode;
		DestinationNode = InDestinationNode;
	}
		
	/**
	 * Return source node
	 * @return
	 */
	public NodeType GetSourceNode() { return SourceNode; }
	
	/**
	 * Return destination node
	 * @return
	 */
	public NodeType GetDestinationNode() { return SourceNode; }
	
	
}

