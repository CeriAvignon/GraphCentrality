package fr.univavignon.graphcentr.g03;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.readers.*;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;


public class EigenVectorColtTest
{
	public static void execute()
	{
		int n=10000;
		int k=2;
		SimpleGraph g=new SimpleGraph();
		GraphMLReader r= new GraphMLReader();
		r.updateFromFile("./res/Benchmark/watts_strogatz/n="+n+"_k="+k+".graphml",g);
		
		Benchmark.start();
		EigenVectorColt eigen=new EigenVectorColt();
		CentralityResult res=eigen.evaluate(g);
		Benchmark.stop();
		
		Benchmark.printSnapshots();
	}
}