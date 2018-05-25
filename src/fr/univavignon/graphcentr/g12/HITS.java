package fr.univavignon.graphcentr.g12;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;

import java.lang.Math;
import java.util.ArrayList;

/**
* @author Alexandre TRUCCHIERO, Yacine BATTIS, Cédric LARROSA
*
*/
public class HITS implements DirectedCentrality
{
	/**
	 * default constructor
	 */
	public HITS()
	{
	}
	
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param graphe
	 * @param s
	 * @param c
	 * @return authority values for each node
	 */
	public ArrayList<Double> FindAuthorityCentrality(DirectedGraph graphe, double s, int c)
	{
		// Initialisation
		boolean loop = true;
		int iteration = 0;
		double norma;
		double normh;
		
		ArrayList<Double> a = new ArrayList<Double>();
		ArrayList<Double> h = new ArrayList<Double>();	
		for(int i = 0; i < graphe.getNodeCount();i++)
		{
			a.add(1.0);
			h.add(1.0);
		}
		
		ArrayList<Double> atemp = new ArrayList<Double>();
		ArrayList<Double> htemp = new ArrayList<Double>();
		
		// Main loop
		while(loop)
		{
			Benchmark.addIteration();

			norma = 0;
			normh = 0;
			
			atemp.clear();
			htemp.clear();
			for(int i = 0; i < graphe.getNodeCount();i++)
			{
				atemp.add(a.get(i));
				htemp.add(h.get(i));
			}
			
			for(int i = 0; i < graphe.getNodeCount();i++)
			{
				// Compute the authority value for node i
				double NodeIn = graphe.getIncomingDegree(i);
				a.set(i, a.get(i) + NodeIn);

				// Compute the hub value for node i
				double NodeOut = graphe.getOutgoingDegree(i);
				h.set(i, h.get(i) + NodeOut);
				
				norma += a.get(i)*a.get(i);
				normh += h.get(i)*h.get(i);
			}

			// Normalisation
			for(int i = 0; i < graphe.getNodeCount();i++)
			{
				a.set(i, a.get(i)/Math.sqrt(norma));
				h.set(i, h.get(i)/Math.sqrt(normh));
			}

			// Check authority convergence
			if(s != 0)
			{
				double asum = 0;
				double atempsum = 0;
				
				for(int i = 0; i < graphe.getNodeCount();i++)
				{
					asum += a.get(i);
					atempsum += atemp.get(i);
				}

				double offset = Math.abs((asum / graphe.getNodeCount()) - (atempsum / graphe.getNodeCount()));
				if(offset <= s)
					loop = false;
			}

			// Check loop iteration
			if(c != 0)
			{
				iteration += 1;
				if(iteration == c)
					loop = false;
			}
		}
		
		return a;
	}
	
	/**
	 * @param graphe
	 * @param s
	 * @param c
	 * @return hub values for each node
	 */
	public ArrayList<Double> FindHubCentrality(DirectedGraph graphe, double s, int c)
	{
		// Initialisation
		boolean loop = true;
		int iteration = 0;
		double norma;
		double normh;
		
		ArrayList<Double> a = new ArrayList<Double>();
		ArrayList<Double> h = new ArrayList<Double>();	
		for(int i = 0; i < graphe.getNodeCount();i++)
		{
			a.add(1.0);
			h.add(1.0);
		}
		
		ArrayList<Double> atemp = new ArrayList<Double>();
		ArrayList<Double> htemp = new ArrayList<Double>();
		
		// Main loop
		while(loop)
		{
			Benchmark.addIteration();

			norma = 0;
			normh = 0;
			
			atemp.clear();
			htemp.clear();
			for(int i = 0; i < graphe.getNodeCount();i++)
			{
				atemp.add(a.get(i));
				htemp.add(h.get(i));
			}
			
			for(int i = 0; i < graphe.getNodeCount();i++)
			{
				// Compute the authority value for node i
				double NodeIn = graphe.getIncomingDegree(i);
				a.set(i, a.get(i) + NodeIn);

				// Compute the hub value for node i
				double NodeOut = graphe.getOutgoingDegree(i);
				h.set(i, h.get(i) + NodeOut);
				
				norma += a.get(i)*a.get(i);
				normh += h.get(i)*h.get(i);
			}

			// Normalisation
			for(int i = 0; i < graphe.getNodeCount();i++)
			{
				a.set(i, a.get(i)/Math.sqrt(norma));
				h.set(i, h.get(i)/Math.sqrt(normh));
			}

			// Check hub convergence
			if(s != 0)
			{
				double hsum = 0;
				double htempsum = 0;
				
				for(int i = 0; i < graphe.getNodeCount();i++)
				{
					hsum += h.get(i);
					htempsum += htemp.get(i);
				}

				double offset = Math.abs((hsum / graphe.getNodeCount()) - (htempsum / graphe.getNodeCount()));
				if(offset <= s)
					loop = false;
			}

			// Check loop iteration
			if(c != 0)
			{
				iteration += 1;
				if(iteration == c)
					loop = false;
			}
		}
		
		return h;
	}
}
