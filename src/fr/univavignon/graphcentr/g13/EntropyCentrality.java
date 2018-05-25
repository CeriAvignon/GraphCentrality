package fr.univavignon.graphcentr.g13;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * 
 * The EntropyCentrality class can be used to calculate the centrality of each node in simple graphs
 * The result can be normalised if you want to, just call the main method with true in the second argument 
 * @author Thomas Duvignau & Florian Chevalier
 * 
 */
public class EntropyCentrality implements SimpleCentrality
{

	/**
	 * The ElemPath method is designed to find all the paths between two given vertices. All the paths will be stored in the Res array
	 * @param inGraph
	 * @param actualNode 
	 * @param finalNode 
	 * @param Path 
	 * @param Res
	 */
	public void ElemPath(SimpleGraph inGraph, int actualNode, int finalNode, ArrayList<Integer> Path, ArrayList<ArrayList<Integer>> Res)
	{
		Path.add(actualNode);
		 if(actualNode == finalNode)
		 {
			 Res.add(Path);
		 }
		 else
		 {
			 Node n = inGraph.getNodeAt(actualNode);
			 List<Link> links = inGraph.getNodeLinks(n);
			 for (int j = 0; j < links.size(); j++)
			 {
				 if(!Path.contains( links.get(j).getDestinationIdentifier()) )
				 {
					 ArrayList<Integer> Tab = new ArrayList<Integer>();
					 for (int i = 0; i < Path.size(); i++)
					 {
						 Tab.add(new Integer(Path.get(i)));
					 }
					 ElemPath(inGraph, links.get(j).getDestinationIdentifier() , finalNode, Tab, Res);
				 }
			 }
		 }
	}

	
	/**
	 * Calculate the transfer probability of the actualNode
	 * @param inGraph
	 * @param actualNode 
	 * @param followingNode 
	 * @param Path 
	 * @return double
	 */
	public double tau(SimpleGraph inGraph , int actualNode, int followingNode, ArrayList<Integer> Path)
	{
		if( inGraph.getNodeDegree(actualNode) -1 == 0  )
		{
			return 0;
		}
		else
		{
			int downstreamDegree;
			double[][] matrix = inGraph.toAdjacencyMatrix();

			downstreamDegree = (inGraph.getNodeDegree(actualNode)-1);
			
			int i=0;
			while(Path.get(i) != actualNode)
			{
				if( matrix[actualNode][Path.get(i)] !=0 )
				{
					downstreamDegree--;
				}
				i++;
			}
			
			return (matrix[actualNode][followingNode]/(downstreamDegree));
		}
	}
	
	/**
	 * Calculate the probability that the flow stay on the same node
	 * @param inGraph 
	 * @param actualNode 
	 * @param Path 
	 * @return double
	 */
	public double sigma(SimpleGraph inGraph , int actualNode , ArrayList<Integer> Path)
	{
		if( inGraph.getNodeDegree(actualNode) -1  == 0 )
		{
			return 1;
		}
		else
		{
			int downstreamDegree;
			double[][] matrix = inGraph.toAdjacencyMatrix();

			downstreamDegree = (inGraph.getNodeDegree(actualNode)-1);
			
			int i=0;
			while(Path.get(i) != actualNode)
			{
				if( matrix[actualNode][Path.get(i)] !=0 )
				{
					downstreamDegree--;
				}
				i++;
			}
			
			return (matrix[actualNode][actualNode]/downstreamDegree);
		}
	}
	
	/**
	 * Calculate the probability to pass from the actualNode to the finalNode
	 * @param inGraph
	 * @param actualNode 
	 * @param finalNode 
	 * @return double
	 */
	public double calculProba(SimpleGraph inGraph,int actualNode,int finalNode)
	{
		double z = 0;
		double b;
		
		if(actualNode != finalNode)
		{
			System.out.println("Chemin de " + actualNode + " a " +finalNode );

				ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
				ArrayList<Integer> parcours = new ArrayList<Integer>();
				
				ElemPath(inGraph,actualNode,finalNode,parcours,res);				
				int k=0;
				for( k=0 ; k<res.size(); k++)
				{
					double y=1;
					for(int t=0;t<res.get(k).size()-1;t++)
					{
						b=tau(inGraph,res.get(k).get(t),res.get(k).get(t+1),res.get(k));
						y=y*b;
					}
					y=y*sigma(inGraph,res.get(k).get(res.get(k).size()-1),res.get(k));
					z=z+y;

				}
		}
		else
		{	
			System.out.println("Chemin de " + actualNode + " a " +finalNode );
			Node v=inGraph.getNodeAt(actualNode);
			z= 1.0 / (inGraph.getNodeDegree(v)-1);
		}
		return z;	
	}

	/**
	 * Main algorithm to find the entropy centrality of each node
	 * @param inGraph
	 * @param normalise
	 * @return CentralityResult
	 */
	public CentralityResult MainAlgorithm(SimpleGraph inGraph,boolean normalise)
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
				x = x + resultproba * (Math.log( resultproba )/Math.log(10));

			}
			
			if(normalise == true)
			{
				x = x * (-1) / (Math.log(n)/Math.log(10));

			}

			C_Ent.add(x);	  			
		}

		return C_Ent;
	}

	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		for (int i=0; i<inGraph.getNodeCount(); i++)
		{
			inGraph.createLink(i, i);
		}
		return MainAlgorithm(inGraph, true);
		// TODO Auto-generated method stub
	}

}