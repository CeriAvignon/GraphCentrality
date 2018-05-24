package fr.univavignon.graphcentr.g02;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.readers.*;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;

public class Test {

	public static void main(String[] args)
	{
		DirectedGraph graph = new DirectedGraph();
		
		/* Test graph */
		/*graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		
		graph.createLink(0, 4);
		graph.createLink(1, 0);
		graph.createLink(2, 0);
		graph.createLink(3, 0);
		graph.createLink(5, 0);
		graph.createLink(3, 2);
		graph.createLink(4, 1);
		graph.createLink(4, 5);
		graph.createLink(5, 2);
		graph.createLink(5, 3);*/

		try
		{
			LeaderRank leader = new LeaderRank(0.000002);
			
			Benchmark.start();
			Benchmark.addSnapshot("Graph loading...");
			
			GraphMLReader reader = new GraphMLReader();
			reader.updateFromFile("C:/Users/Raph/Google Drive/Cours/L3/S1/Projet/Benchmark/erdos_renyi/n=1000_p=0.1.graphml", graph);
			
			Benchmark.addSnapshot("Centrality processing...");
			
			leader.evaluate(graph);
			
			Benchmark.stop();
			Benchmark.printSnapshots();
		}
		catch (BadErrorValue bev)
		{
			System.out.print(bev.getMessage());
		}
	}
}
