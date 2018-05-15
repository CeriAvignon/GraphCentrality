package fr.univavignon.graphcentr.g13;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

public class Ecart_type implements SimpleCentrality
{
	public CentralityResult AlgoPrincipaleTemps(SimpleGraph inGraph, boolean normalize, int time)
	{
		long tmpAuLancement = System.currentTimeMillis();
		long tmpMax = tmpAuLancement + (time * 1000);
		
		ArrayList<ArrayList<Integer>> CS = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<inGraph.getLinkCount(); i++)
		{
			CS.add(new ArrayList<Integer>());
		}
		
		int pas = 0;
		int i = 0;
		
		while(System.currentTimeMillis() < tmpMax)
		{
			if(CS.get(i).isEmpty())
			{
				CS.get(i).add(pas);
			}
			else
			{
				int res = 0;
				for(int a =0; a < CS.get(i).size(); a++)
					res+= CS.get(i).get(a);
				CS.get(i).add(pas - res);
			}
			
			pas+=1;
			Node j = rand(i, inGraph);
			int dj = inGraph.getNodeDegree(j);
			double p = Math.random();
			if(p <= inGraph.getNodeDegree(i)/dj)
			{
				i = j.getIdentifier();
			}
		}
		CentralityResult resultat = new CentralityResult();
		
		for(int j=0; j<CS.size(); j++)
		{
			if(CS.get(j).size() <3)
			{
				resultat.add(ET_calc(CS.get(j)));
			}
		}
		return resultat;
	}
	

	public CentralityResult AlgoPrincipalePas(SimpleGraph inGraph, boolean normalize, int nbrPasMin)
	{
		ArrayList<ArrayList<Integer>> CS = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<inGraph.getLinkCount(); i++)
		{
			CS.add(new ArrayList<Integer>());
		}
		
		int pas = 0;
		int i = 0;
		boolean suffisementDePas = false;
		while(!suffisementDePas)
		{
			if(CS.get(i).isEmpty())
			{
				CS.get(i).add(pas);
			}
			else
			{
				int res = 0;
				for(int a =0; a < CS.get(i).size(); a++)
					res+= CS.get(i).get(a);
				CS.get(i).add(pas - res);
			}
			
			pas+=1;
			Node j = rand(i, inGraph);
			int dj = inGraph.getNodeDegree(j);
			double p = Math.random();
			if(p <= inGraph.getNodeDegree(i)/dj)
			{
				i = j.getIdentifier();
			}
			
			for(ArrayList<Integer> tableau : CS)
				if(tableau.size() < nbrPasMin)
				{
					suffisementDePas = true;
					break;
				}
		}
		CentralityResult resultat = new CentralityResult();
		
		for(int j=0; j<CS.size(); j++)
		{
			if(CS.get(j).size() <3)
			{
				resultat.add(ET_calc(CS.get(j)));
			}
		}
		return resultat;
	}
	
	private Node rand(int a, SimpleGraph inGraph)
	{
		List<Link> noeudsEnLiens = inGraph.getNodeLinks(inGraph.getNodeAt(a));
		int noeud = (int)(Math.random() * (noeudsEnLiens.size()+1));
		return inGraph.getNodeAt(noeud);
	}

	
	public double ET_calc(ArrayList<Integer> E)
	{
		int N = E.size();
		double res = 0;
		for(int i=0; i<N; i++)
		{
			res = res + E.get(i)*E.get(i);
		}
		res = res/N;
		double sum = 0;
		for (int k=1; k<N; k++)
		{
			sum = sum + E.get(k);
		}
		sum = (sum/N) * (sum/N);
		res = res - sum;
		return Math.sqrt(res); 
	}
	
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		// TODO Auto-generated method stub
		return null;
	}

}
