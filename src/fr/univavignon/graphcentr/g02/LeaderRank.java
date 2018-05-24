package fr.univavignon.graphcentr.g02;

import java.util.ArrayList;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;

/**
 * LeaderRank centrality
 * @author MENGUY Loïc, MONTET Raphaël
 *
 */
public class LeaderRank implements DirectedCentrality
{
	double err = 0.0;
	
	public LeaderRank(double error) throws BadErrorValue
	{
		if (error <= 0)
		{
			throw new BadErrorValue("L'erreur doit être supérieure à 0!");
		}
		
		err = error;
	}
	
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph)
	{
		CentralityResult result = new CentralityResult();
		int n = inGraph.getNodeCount();
		inGraph.createNode();
		
		for (int i = 0; i < n; i++)					//Root node addition
		{
			inGraph.createLink(i, n);
			inGraph.createLink(n, i);
		}

		n++;										//New graph size (with root node

		for (int i = 0; i < n - 1; i++)					//Initial centrality scores
		{
			result.add(1.0);
		}

		result.add(0.0);

		double actErr = Double.MAX_VALUE;
		int iter = 0;	

		while (actErr > Double.MIN_VALUE && actErr > err)
		{
			Benchmark.addIteration();
			
			iter++;
			double store = 0.0;
			ArrayList<Double> resultPrec = new ArrayList<Double>(result);	//Previous iteration results
			
			for (int i = 0; i < n; i++)
			{
				double nodeCent = 0.0;
				
				for (int j = 0; j < n; j++)
				{
					if (!inGraph.isAdjacentTo(j, i))	//No adjacency
					{
						continue;
					}
					
					nodeCent += ((1.0 / (double)inGraph.getOutgoingDegree(j)) * resultPrec.get(j));
				}

				result.set(i, nodeCent);
				store += Math.abs(resultPrec.get(i) - result.get(i));
			}
			
			actErr = store / (double)n;
		}

		double rootCent = result.get(n - 1) / (n - 1);
		
		for (int i = 0; i < n; i++)
		{
			result.set(i, result.get(i) + rootCent);
		}
		
		result = normalize(result, n);
		
		return result;
	}
	
	public CentralityResult normalize(CentralityResult result, int size)
	{
		for (int i = 0; i < size - 1; i++)
		{
			double value = result.get(i) / ((Math.pow(size - 1, 2) + size) / (2 * (size - 1)));
			result.set(i, value);
		}

		return result;
	}
}

class BadErrorValue extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public BadErrorValue(String mes)
	{
		super(mes);
	}
}











