import java.util.ArrayList;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;
import fr.univavignon.graphcentr.g12.HITS;

public class TestHITS {
	
	public static void main(String [] args)
	{		
		DirectedGraph graph = new DirectedGraph();
		
		GraphMLReader GMLReader = new GraphMLReader();
		GMLReader.updateFromFile("exemple.graphml", graph);
		
		double s = 0;
		int c = 1000;
		
		HITS hitsMethod = new HITS();
		
		ArrayList<Double> result = new ArrayList<Double>();
		ArrayList<Double> resultl = new ArrayList<Double>();
		
		Benchmark.start();
		Benchmark.addSnapshot("authority");
		result = hitsMethod.FindAuthorityCentrality(graph, s, c);

		Benchmark.addSnapshot("hub");
		resultl = hitsMethod.FindhubCentrality(graph, s, c);
		
		Benchmark.stop();
		
		/*for(int i = 0; i < result.size(); i++)
			System.out.println("a"+i+" = "+result.get(i));
		System.out.println("\n");
		
		for(int i = 0; i < resultl.size(); i++)
			System.out.println("h"+i+" = "+resultl.get(i));*/

		Benchmark.printSnapshots();
	}
}
