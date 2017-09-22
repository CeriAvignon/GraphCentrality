package Tests;

import Core.SimpleLink;
import Core.SimpleNode;

public class WeightedLink extends SimpleLink
{
	protected int Weight;

	public WeightedLink(SimpleNode InSourceNode, SimpleNode InDestinationNode) 
	{
		super(InSourceNode, InDestinationNode);
		// TODO Auto-generated constructor stub
		Weight = 0;
	}
	
}
