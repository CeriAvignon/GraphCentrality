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

	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		// TODO Auto-generated method stub
		return null;
	}

}
