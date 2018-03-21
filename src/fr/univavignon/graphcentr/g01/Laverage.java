package fr.univavignon.graphcentr.g01;



import java.util.List;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;


/**
 * @author Riviere Colin, Benoit Loris
 *
 */
public class Laverage implements SimpleCentrality{

	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		CentralityResult result = new CentralityResult();
		for(int i =0; i < inGraph.getNodeCount();i++) {
			double score = 0;
			Node u = inGraph.getNodeAt(i);
			int nbVoisinU = inGraph.getNodeDegree(u);
			List<Link> liste = inGraph.getNodeLinks(u);
			for(int j=0 ;j<liste.size();j++) {
				Link test = liste.get(j);
				int nbVoisinW =0;
				if(test.getSourceIdentifier() == u.getIdentifier()) {
					nbVoisinW = inGraph.getNodeDegree(test.getDestinationIdentifier());
				}else {
					nbVoisinW = inGraph.getNodeDegree(test.getSourceIdentifier());
				}
				score += ((double)(nbVoisinU-nbVoisinW))/((double)(nbVoisinU+nbVoisinW));
					
			}
			result.add(score/((double)nbVoisinU));
		}
		return result;
	}

}
