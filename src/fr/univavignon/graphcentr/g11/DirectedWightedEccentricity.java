package fr.univavignon.graphcentr.g11;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedWeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;
/**
 * 
 * @author KHELAFI Abdelhamid
 * 
 *

 */
public class DirectedWightedEccentricity implements DirectedWeightedCentrality {
	
		@Override
		public CentralityResult evaluate(DirectedWeightedGraph inGraph) {
			CentralityResult result = new CentralityResult();
			int n = inGraph.getNodeCount();
			double Cex[] = new double[n];
			
			
			double dist[][]= FloydWarshall.findShortestDistances(inGraph);
			
			for(int i = 0; i < n; i++) {
				Cex[i]= 0;
			}
			
			for(int i = 0; i < n; i++) {
				for(int j =0; j < n; j++) {
					if(Cex[i] < dist[i][j]) {
						Cex[i] = dist[i][j];
					}
					
				}
				result.add(1/Cex[i]);
			}
			
			return result;
		}

	}


