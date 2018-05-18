package fr.univavignon.graphcentr.g01;

import java.util.List;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;



/**
 * Centrality of Leverage Directed Graph
 * @author Riviere Colin, Benoit Loris
 *
 */
public class LeverageDirected implements DirectedCentrality{

	@Override
	public CentralityResult evaluate(DirectedGraph inGraph) {
		CentralityResult result = new CentralityResult();
		for(int i =0; i < inGraph.getNodeCount();i++) {
			double score = 0;
			Node u = inGraph.getNodeAt(i);
			int nbVoisinU = inGraph.getOutgoingDegree(u);
			List<Link> liste = inGraph.getNodeLinks(u);
			for(int j=0 ;j<liste.size();j++) {
				Link test = liste.get(j);
				int nbVoisinW =0;
				if(test.getSourceIdentifier() == u.getIdentifier()) {
					nbVoisinW = inGraph.getOutgoingDegree(test.getDestinationIdentifier());
				}else {
					nbVoisinW = inGraph.getOutgoingDegree(test.getSourceIdentifier());
				}
				score += ((double)(nbVoisinU-nbVoisinW))/((double)(nbVoisinU+nbVoisinW));
					
			}
			result.add(score/((double)nbVoisinU));
		}
		return result;
	}

}
