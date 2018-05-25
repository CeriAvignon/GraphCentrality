package fr.univavignon.graphcentr.g02;

import java.util.ArrayList;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;

/**
 * LeaderRank centrality
 * @author MONTET Raphaël
 *
 */
public class LeaderRank implements DirectedCentrality
{
	double err = 0.0;										//Error value
	
	/**
	 * LeaderRank constructor
	 */
	public LeaderRank(double error) throws BadErrorValue
	{
		if (error <= 0)										//Negative error
		{
			throw new BadErrorValue("L'erreur doit être supérieure à 0!");
		}
		
		err = error;										//Error value
	}
	
	/**
	 * LeaderRank centrality algorithm
	 * @param inGraph Graph to evaluate
	 * @return ArrayList<Double> Centrality scores
	 */
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph)
	{
		CentralityResult result = new CentralityResult();	//Centrality scores
		int n = inGraph.getNodeCount();						//Number of nodes in input graph
		inGraph.createNode();								//Root node
		
		for (int i = 0; i < n; i++)							//Root node connection
		{
			inGraph.createLink(i, n);						//i Node ---> root node
			inGraph.createLink(n, i);						//Root node ---> i Node
		}

		n++;												//New graph size (incl. root node)

		for (int i = 0; i < n - 1; i++)						//Initial centrality scores
		{
			result.add(1.0);
		}

		result.add(0.0);									//Root score = 0.0

		double actErr = Double.MAX_VALUE;					//"Infinite" error value

		while (actErr > Math.sqrt(Double.MIN_VALUE) && actErr > err)	//Error greater than square root of machine-epsilon and greater than user-chosen error
		{
			Benchmark.addIteration();						//Loop counter
			
			double store = 0.0;								//Error storage
			ArrayList<Double> resultPrec = new ArrayList<Double>(result);	//Previous iteration results
			
			for (int i = 0; i < n; i++)						//For each node
			{
				double nodeCent = 0.0;						//Current centrality
				
				for (int j = 0; j < n; j++)					//For each node
				{
					if (!inGraph.isAdjacentTo(j, i))		//No adjacency
					{
						continue;							//Next node
					}
					
					nodeCent += ((1.0 / (double)inGraph.getOutgoingDegree(j)) * resultPrec.get(j));	//Centrality computing
				}

				result.set(i, nodeCent);					//New centrality score
				store += Math.abs(resultPrec.get(i) - result.get(i));	//Difference between t-1 and t centrality scores
			}
			
			actErr = store / (double)n;						//Current error
		}

		double rootCent = result.get(n - 1) / (n - 1);		//Root node centrality score
		
		for (int i = 0; i < n; i++)							//Centrality redistribution
		{
			result.set(i, result.get(i) + rootCent);
		}
		
		result = normalize(result, n - 1);					//Normalization
		
		return result;										//Final centrality scores
	}
	
	/**
	 * LeaderRank scores normalization
	 * @param result Centrality scores
	 * @return ArrayList<Double> Normalized centrality scores
	 */
	public CentralityResult normalize(CentralityResult result, int size)
	{
		for (int i = 0; i < size; i++)						//For each node
		{
			double value = result.get(i) / ((Math.pow(size, 2) + size + 1) / (2 * size));	//Normalized score
			result.set(i, value);							//Update score
		}

		return result;										//Normalized scores
	}
}











