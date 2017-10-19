package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Inherit from this to create simpleGraph-based algorithms
 */
public class SimpleAlgorithm
{	
	/**
	 * Evaluate given simple-like-graph
	 * @param inGraph Graph to evaluate
	 * @return AlgorithmResult
	 */
	static public <GraphNodeType extends Node, GraphLinkType extends Link> 
	AlgorithmResult evaluate(Graph<GraphNodeType, GraphLinkType> inGraph) 
	{
		AlgorithmResult Result = new AlgorithmResult("Highest degree node");
		
		int NodeIndex = 0;
		for(int i = 0; i < inGraph.getNodeCount(); i++)
		{
			Node CurrentNode = inGraph.at(i);
			if(CurrentNode.getLinkCount() > inGraph.at(NodeIndex).getLinkCount())
				NodeIndex = i;
		}
		
		Result.add(inGraph.at(NodeIndex), NodeIndex);
		
		return Result;
	}	
}
