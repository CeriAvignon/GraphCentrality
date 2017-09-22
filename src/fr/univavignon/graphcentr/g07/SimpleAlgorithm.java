package Core;

import java.util.Vector;

public class SimpleAlgorithm implements AbstractAlgorithm<SimpleNode, SimpleLink>
{
	public SimpleAlgorithm()
	{
	}

	@Override
	public <GraphNodeType extends SimpleNode, GraphLinkType extends SimpleLink> 
	Vector<Integer> Evaluate(Graph<GraphNodeType, GraphLinkType> InGraph) 
	{
		Vector<Integer> Results = new Vector<Integer>();
		
		int NodeIndex = 0;
		for(int i = 0; i < InGraph.GetNodeCount(); i++)
		{
			SimpleNode CurrentNode = InGraph.At(i);
			if(CurrentNode.GetLinkCount() > InGraph.At(NodeIndex).GetLinkCount())
				NodeIndex = i;
		}
		
		Results.add(NodeIndex);
		
		return Results;
	}

	
}
