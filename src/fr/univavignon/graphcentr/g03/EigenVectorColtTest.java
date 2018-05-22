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
		double p=0;
		SimpleGraph g=new SimpleGraph();
		GraphMLReader r= new GraphMLReader();
		r.updateFromFile("./res/Benchmark/erdos_renyi/n="+n+"_p="+p+".graphml",g);
		
		Benchmark.start();
		EigenVectorColt eigen=new EigenVectorColt();
		CentralityResult res=eigen.evaluate(g);
		Benchmark.stop();
		
		System.out.println(res);		
	}
}