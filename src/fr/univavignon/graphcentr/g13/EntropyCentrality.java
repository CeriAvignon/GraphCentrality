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
/*	public void ElemPath(SimpleGraph inGraph, int v1, int v2,  ArrayList<Integer> Parcours, ArrayList<ArrayList<Integer>> Res, int v3)
	{
		 Parcours.add(v3);
		 if(v2 == v3)
		 {
			 Res.add(Parcours);
			 return;
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
	*/
	public void ElemPath(SimpleGraph inGraph, int nouedActuel, int noeudFin, ArrayList<Integer> Parcours, ArrayList<ArrayList<Integer>> Res)
	{
		 Parcours.add(nouedActuel);
		 if(nouedActuel == noeudFin)
		 {
			 Res.add(Parcours);
		 }
		 else
		 {
			 Node n = inGraph.getNodeAt(nouedActuel);
			 List<Link> links = inGraph.getNodeLinks(n);
			 for (int j = 0; j < links.size(); j++)
			 {
				 if(!Parcours.contains( links.get(j).getDestinationIdentifier()) )
				 {
					 ArrayList<Integer> Tab = new ArrayList<Integer>();
					 for (int i = 0; i < Parcours.size(); i++)
					 {
						 Tab.add(new Integer(Parcours.get(i)));
					 }
					 ElemPath(inGraph, links.get(j).getDestinationIdentifier() , noeudFin, Tab, Res);
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
		if( inGraph.getNodeDegree(a) -1 == 0  )
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
		if( inGraph.getNodeDegree(a) -1  == 0 )
		{
			return 1;
		}
		else
		{
			double[][] matrice = inGraph.toAdjacencyMatrix();
			
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
				

				ElemPath(inGraph,i,j,parcours,res);
				
				
				int k=0;
				for( k=0 ; k<res.size(); k++)
				{
					double y=1;
					for(int t=0;t<res.get(k).size()-1;t++)
					{
						b=tau(inGraph,res.get(k).get(t),res.get(k).get(t+1));
						System.out.print("tau("+res.get(k).get(t)+")" + b +" * ");
						y=y*b;
					}
					y=y*sigma(inGraph,res.get(k).get(res.get(k).size()-1));
					System.out.print("sigma("+res.get(k).get(res.get(k).size()-1)+")" + sigma(inGraph,res.get(k).get(res.get(k).size()-1)) +" ");
					System.out.print("+");
					z=z+y;

				}
				System.out.println();
		}
		else
		{	
			System.out.println("Chemin de " + i + " a " +j );
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
				double resultproba =  calculProba(inGraph,i,j) ;
				
				
				x = x + resultproba * Math.log10( resultproba );

			}
			if(normalise == true)
			{
				x = x * (-1) / Math.log10(n);

			}

			C_Ent.add(x);	  			
		}
		
		for(int i=0; i<4; i++)
		{
		}
		
		return C_Ent;
	}

	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		
		return AlgoPrincipale(inGraph, true);
		// TODO Auto-generated method stub
	}

}
