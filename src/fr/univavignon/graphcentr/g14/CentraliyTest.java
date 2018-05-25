package fr.univavignon.graphcentr.g14;



import fr.univavignon.graphcentr.g07.core.WeightedLink;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;

/**
 * @author Soufiane.sel
 *
 */
public class CentraliyTest {


	public static void main(String[] args) {
		DirectedWeightedGraph graph = new DirectedWeightedGraph ();
		//Create Nodes
		graph.createNode();//0
		graph.createNode();//1
		graph.createNode();//2
		graph.createNode();//3
		//Create Link 
		graph.createLink(0, 1, 3);
		graph.createLink(0, 2, 7);
		graph.createLink(1, 0, 6);
		graph.createLink(1, 3, 4);
		graph.createLink(2, 0, 5);
		graph.createLink(2, 3, 5);
		//Centrality
		RandomWalkCloseness Random = new RandomWalkCloseness();
		//System.out.println(graph.getLinkCount());
		CentralityResult result = Random.evaluate(graph);
		
		//for(int i = 0; i < result.size(); i++) {   
		//System.out.print(result.get(i)+"\n");
		    
		//}  

	}

}
