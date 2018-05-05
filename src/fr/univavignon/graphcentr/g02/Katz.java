package fr.univavignon.graphcentr.g02;

import java.util.*;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;

public class Katz implements DirectedCentrality
{
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph)
	{
		CentralityResult result = new CentralityResult();
		
		//result.add(KatzAlgorithm(inGraph));
		return result;
	}
	
	private Vector KatzAlgorithm(DirectedGraph inGraph)
	{
		int n = inGraph.getNodeCount();
		Vector result = new Vector(n);
		
		
		return result;
		
	}
	
}
