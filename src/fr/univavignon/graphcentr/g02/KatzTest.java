package fr.univavignon.graphcentr.g02;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.readers.*;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;


public class KatzTest 
{
	public static void main(String[] args)
	{
		// Graph from example
		/*DirectedGraph testGraph = new DirectedGraph();
		
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
		*/
		
		Benchmark.start();
		Benchmark.addSnapshot("Graph Loading");
		
		DirectedGraph testGraph = new DirectedGraph();
		GraphMLReader reader = new GraphMLReader();
		reader.updateFromFile("C:\\Users\\Loic\\git\\GraphCentrality\\src\\fr\\univavignon\\graphcentr\\g02\\n=10_m=2.graphml", testGraph);
				
		Benchmark.addSnapshot("Centrality Computing");
		Katz katzCentrality = new Katz(0.5);	
		CentralityResult katzResult = katzCentrality.evaluate(testGraph);
		
		Benchmark.stop();
		Benchmark.printSnapshots();
		
		

		for(int i = 0; i < katzResult.size(); i++)
		{
			System.out.println(katzResult.get(i));
		}
		
		
	}
}
