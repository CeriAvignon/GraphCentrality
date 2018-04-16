package fr.univavignon.graphcentr.g12;
import java.util.ArrayList;
import java.lang.Math;

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
	
	/**
	 * @param graphe
	 * @param s
	 * @param c
	 * @return authority values for each node
	 */
	public ArrayList<Double> FindAuthorityCentrality(DirectedGraph graphe, int s, int c)
	{
		// Initialisation
		boolean loop = false;
		int iteration = 0;
		double norma;
		double normh;
		
		ArrayList<Double> a = new ArrayList<Double>();
		ArrayList<Double> h = new ArrayList<Double>();	
		for(int i = 0; i < graphe.size();i++)
		{
			a.add(1.0);
			h.add(1.0);
		}
		
		ArrayList<Double> atemp = new ArrayList<Double>();
		ArrayList<Double> htemp = new ArrayList<Double>();
		
		// Main loop
		while(loop)
		{
			norma = 0;
			normh = 0;
			
			atemp.clear();
			htemp.clear();
			for(int i = 0; i < graphe.size();i++)
			{
				atemp.add(a.get(i));
				htemp.add(h.get(i));
			}
			
			for(int i = 0; i < graphe.size();i++)
			{
				// Compute the authority value for node i
				ArrayList<Double> NodeIn = graphe.getIncomingDegree(i);
				for(int j = 0; j < NodeIn.size();j++)
					a.set(i, a.get(i) + NodeIn.get(j));

				// Compute the hub value for node i
				ArrayList<Double> NodeOut = graphe.getOutGoingDegree(i);
				for(int j = 0; j < NodeOut.size();j++)
					h.set(i, h.get(i) + NodeOut.get(j));
				
				norma += a.get(i)*a.get(i);
				normh += h.get(i)*h.get(i);
			}

			// Normalisation
			for(int i = 0; i < graphe.size();i++)
			{
				a.set(i, a.get(i)/Math.sqrt(norma));
				h.set(i, h.get(i)/Math.sqrt(normh));
			}

			// Check authority convergence
			if(s != 0)
			{
				loop = false;
				for(int i = 0; i < graphe.size();i++)
				{
					if(a.get(i) - atemp.get(i) > s)
					{
						loop = true;
						break;
					}
				}
			}

			// Check loop iteration
			if(c != 0)
			{
				iteration += 1;
				if(iteration == c)
					loop = false;
			}
		}
	}
}
