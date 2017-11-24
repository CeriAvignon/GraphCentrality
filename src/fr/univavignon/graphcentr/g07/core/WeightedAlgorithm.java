package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Inherit from this to create weightedGraph-based algorithms
 */
public class WeightedAlgorithm 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return AlgorithmResult
	 */
	public <GraphNodeType extends Node, GraphLinkType extends Link & AbstractWeightedInformation> 
	AlgorithmResult evaluate(AbstractGraph<GraphNodeType, GraphLinkType> inGraph) 
	{
		// Instantiate new algorithm result
		AlgorithmResult Result = new AlgorithmResult("Weightest node");
		
		int NodeIndex = 0;
		double NodeWeight = 0;
		for(int i = 0; i < inGraph.getNodeCount(); i++)
		{
			Node CurrentNode = inGraph.getNodeAt(i);
			double CurrentNodeWeight = 0;
			for(int j = 0; j < CurrentNode.getLinkCount(); j++)
			{
				@SuppressWarnings("unchecked")
				GraphLinkType CurrentLink = (GraphLinkType) CurrentNode.getLinks().get(j);
				CurrentNodeWeight += CurrentLink.getWeight();
			}
			if(CurrentNodeWeight > NodeWeight)
			{
				NodeIndex = i;
				NodeWeight = CurrentNodeWeight;
			}
		}
		
		// Push result
		Result.add(inGraph.getNodeAt(NodeIndex), NodeIndex);
		
		return Result;
	}	
}
