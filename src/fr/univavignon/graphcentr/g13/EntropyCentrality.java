package fr.univavignon.graphcentr.g13;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

public class EntropyCentrality implements SimpleCentrality
{


	public void ElemPath(SimpleGraph inGraph, int v1, int v2,  ArrayList Parcours, ArrayList[] Res, int v3)
	{
		 Parcours.add(v3);
		 if(v2 == v3)
		 {
			 boolean b = false;
			 int i = 0;
			 while (b == false)
			 {
				 if(Res[i].isEmpty())
				 {
					 b = true;
				 }
				 else
				 {
					 i++;
				 }
			 }
			 Res[i+1].add(Parcours);
		 }
		 else
		 {
			 ArrayList Tab = new ArrayList();
			 for (int i = 0; i < Parcours.size(); i++)
			 {
				 Tab.add(Parcours.get(i));
			 }
			 Node n = inGraph.getNodeAt(v3);
			 inGraph.getNodeLinks(n);
			 List<Link> links = inGraph.getNodeLinks(n);
			 for (int j = 0; j < links.size(); j++)
			 {
				 if(!Tab.contains( links.get(j).getDestinationIdentifier()) )
				 {
					 ElemPath(inGraph, v1, v2, Tab, Res, links.get(j).getDestinationIdentifier());
				 }
			 }
		 }
	 }
	
	public int tau(int a)
	{
		return 1;
	}
	
	public int tau2(int a)
	{
		return 1;
	}
	
	public int calculProba(SimpleGraph inGraph,int i,int j)
	{
		int z = 0;
		int b;
		
		if(i != j)
		{
				ArrayList res = new ArrayList();
				ArrayList parcours = new ArrayList();
				
				ElemPath(inGraph,i,j,parcours,res);
					
				int k=0;
				for( k=0 ; k<res.size(); k++)
				{
					int y=1;
					int t=1;
					for(t=1;t<res.get(1).size()-1;t++)
					{
						b=tau(res.get(k).get(t));
						y=y*b;
					}
					y=y*tau2(j);
					z=z+y;
				}
		}
		else
		{
			Node v=inGraph.getNodeAt(i);
			z= 1 / (inGraph.getNodeDegree(v));
		}
		
		return z;	
	}

	public void AlgoPrincipale(SimpleGraph inGraph,boolean normalise)
	{
		int n = inGraph.getNodeCount();

		double[] C_Ent = new double[n];
		
		double x=0;

		for(int i = 0; i <= n ; i++)
		{
			for(int j = 0; j <=n ; j++)
			{
				x = x + calculProba(inGraph,i,j) * Math.log( calculProba(inGraph,i,j) );
			}
			if(normalise == true)
			{
				x = x * (-1) / Math.log(n);
			}

			C_Ent[i] = x;	  			
		}
	}

	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		

		
		// TODO Auto-generated method stub
		return null;
	}

}
