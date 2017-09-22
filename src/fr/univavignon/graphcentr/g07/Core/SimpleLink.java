package Core;

/**
 * @author JackassDestroyer
 * @brief Simple link between two nodes, 0 informations added
 */
public class SimpleLink extends AbstractLink<SimpleNode>
{
	public SimpleLink(SimpleNode InSourceNode, SimpleNode InDestinationNode) 
	{
		super(InSourceNode, InDestinationNode);
	}
}
