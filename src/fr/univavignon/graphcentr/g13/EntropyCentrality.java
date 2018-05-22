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

	/**
	 * @param inGraph
	 * @param v1
	 * @param v2
	 * @param Parcours
	 * @param Res
	 * @param v3
	 */
	public void ElemPath(SimpleGraph inGraph, int v1, int v2,  ArrayList<Integer> Parcours, ArrayList<ArrayList<Integer>> Res, int v3)
	{
		 Parcours.add(v3);
		 if(v2 == v3)
		 {
			 Res.add(Parcours);
		 }
		 else
		 {
			 ArrayList<Integer> Tab = new ArrayList<Integer>();
			 for (int i = 0; i < Parcours.size(); i++)
			 {
				 Tab.add(Parcours.get(i));
			 }
			 Node v = inGraph.getNodeAt(v3);
			 List<Link> links = inGraph.getNodeLinks(v);
			 List<Link> links_v2 = new ArrayList<Link>();
			 for (int i=0; i < links.size(); i++)
			 {
				 if (links.get(i).getDestinationIdentifier() != links.get(i).getSourceIdentifier())
					 links_v2.add(links.get(i));
			 }
			 
			 for (int j = 0; j < links_v2.size(); j++)
			 {
				 if(!Tab.contains( links_v2.get(j).getDestinationIdentifier()) )
				 {
					 ElemPath(inGraph, v1, v2, Tab, Res, links_v2.get(j).getDestinationIdentifier());
				 }
			 }
		 }
	 }
	
	/**
	 * @param inGraph
	 * @param a
	 * 
	 * @return
	 */
	public double tau(SimpleGraph inGraph , int a , int b)
	{
		if( inGraph.getNodeDegree(a) < 2.  )
		{
			return 0;
		}
		else
		{
			double[][] matrice = inGraph.toAdjacencyMatrix();
			return (matrice[a][b]/(inGraph.getNodeDegree(a)-1));
		}
	}
	
	/**
	 * calcul du taux sigma
	 * 
	 * @param inGraph
	 * @param a
	 * @return double, le taux calculé
	 */
	public double sigma(SimpleGraph inGraph , int a)
	{
		if( inGraph.getNodeDegree(a) < 2 )
		{
			return 1;
		}
		else
		{
			double[][] matrice = inGraph.toAdjacencyMatrix();
			

			System.out.println("deg : "+inGraph.getNodeDegree(a));
			return (matrice[a][a]/(inGraph.getNodeDegree(a)-1));
		}
	}
	
	/**
	 * @param inGraph
	 * @param i
	 * @param j
	 * @return
	 */
	public double calculProba(SimpleGraph inGraph,int i,int j)
	{
		double z = 0;
		double b;
		
		if(i != j)
		{
				ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
				ArrayList<Integer> parcours = new ArrayList<Integer>();
				

				ElemPath(inGraph,i,j,parcours,res,i);
				
				
				int k=0;
				for( k=0 ; k<res.size(); k++)
				{
					System.out.println("chemin "+ k );
					double y=1;
					for(int t=0;t<res.get(k).size()-1;t++)
					{
						b=tau(inGraph,(int)res.get(k).get(t),(int)res.get(k).get(t+1));
						y=y*b;
						System.out.println("b = "+b);
					}
					y=y*sigma(inGraph,j);
					z=z+y;
					System.out.println("sigma(inGraph,j) :"+sigma(inGraph,j));
					System.out.println("z :"+z);
				}
		}
		else
		{
			Node v=inGraph.getNodeAt(i);
			z= 1.0 / (inGraph.getNodeDegree(v)-1);
		}
		return z;	
	}

	/**
	 * @param inGraph
	 * @param normalise
	 * @return résultat de l'algo
	 */
	public CentralityResult AlgoPrincipale(SimpleGraph inGraph,boolean normalise)
	{
		int n = inGraph.getNodeCount();

		CentralityResult C_Ent = new CentralityResult();
		
		double x=0;

		for(int i = 0; i < n ; i++)
		{
			x=0;
			for(int j = 0; j <n ; j++)
			{
				System.out.println("step 1 :"+x+"ij"+i+j);
				System.out.println("prob : " +calculProba(inGraph,i,j)+"ij"+i+j);
				x = x + calculProba(inGraph,i,j) * Math.log10( calculProba(inGraph,i,j) );
				System.out.println("step 2 :"+x+"ij"+i+j);

			}
			if(normalise == true)
			{
				x = x * (-1) / Math.log10(n);
				System.out.println("step 3 :"+x);

			}

			C_Ent.add(x);	  			
		}
		
		for(int i=0; i<4; i++)
		{
			System.out.println(inGraph.getNodeDegree(i));
		}
		
		return C_Ent;
	}

	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		
		return AlgoPrincipale(inGraph, true);
		// TODO Auto-generated method stub
	}

}
