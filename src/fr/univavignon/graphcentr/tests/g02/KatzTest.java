package fr.univavignon.graphcentr.tests.g02;

import fr.univavignon.graphcentr.g02.Katz;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.readers.*;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;


public class KatzTest 
{
	public static void main(String[] args)
	{
		Benchmark.start();
		Benchmark.addSnapshot("Graph Loading");
		DirectedGraph testGraph = new DirectedGraph();
		// Graph from example
		
		
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
		String filePath = "C:\\Users\\Loic\\git\\GraphCentrality\\src\\fr\\univavignon\\graphcentr\\tests\\g02\\KatzExemple.graphml";
		
		
		//Graph loaded from file
		//String filePath = "C:\\Users\\Loic\\git\\GraphCentrality\\src\\fr\\univavignon\\graphcentr\\tests\\g02\\watts_strogatz\\n=10000_k=100.graphml"; //Path to file
		//GraphMLReader reader = new GraphMLReader();
		//reader.updateFromFile(filePath, testGraph);
		
		Benchmark.addSnapshot("Centrality Computing");
		Katz katzCentrality = new Katz(0.5);	
		CentralityResult katzResult = katzCentrality.evaluate(testGraph);
		
		Benchmark.stop();
		Benchmark.printSnapshots();
		//Benchmark.saveToFile("C:\\Users\\Loic\\Documents\\results\\watts\\n10000k100_1.txt");
		
		

		for(int i = 0; i < katzResult.size(); i++) //Printing
		{
			System.out.println(katzResult.get(i));
		}
		
		
	}
}
