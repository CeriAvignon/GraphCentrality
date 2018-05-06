package fr.univavignon.graphcentr.g02;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

public class KatzTest 
{
	public static void main(String[] args)
	{
		DirectedGraph testGraph = new DirectedGraph();
		
		testGraph.createNode();
		testGraph.createNode();
		testGraph.createNode();
		testGraph.createNode();
		
		
		testGraph.createLink(0,2);
		testGraph.createLink(0,3);
		
		testGraph.createLink(1,0);
		
		testGraph.createLink(2,1);
		testGraph.createLink(2,3);
		
		testGraph.createLink(3,1);
		
		Katz katzCentrality = new Katz(0.5);
		
		CentralityResult katzResult = katzCentrality.evaluate(testGraph);
		

		for(int i = 0; i < katzResult.size(); i++)
		{
			System.out.println(katzResult.get(i));
		}
		
		
	}
}
