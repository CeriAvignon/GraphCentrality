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
	// Temps = true, Nombre = false
	private boolean tempsOuNombre;
	private int tempsEnSeconde;
	private int nombreDePasParNoeud;
	
	public int getTempsEnSeconde()
	{
		return this.tempsEnSeconde;
	}
	
	public void setTempsEnSeconde(int temps)
	{
		this.tempsEnSeconde = temps;
	}
	
	public boolean getTempsOuNombre()
	{
		return this.tempsOuNombre;
	}
	
	public void setTempsOuNombre(boolean tempsOuNombre)
	{
		this.tempsOuNombre = tempsOuNombre;
	}
	
	public int getNombreDePasParNoeud()
	{
		return this.nombreDePasParNoeud;
	}
	
	public void setNombreDePasParNoeud(int nombreDePasParNoeud)
	{
		this.nombreDePasParNoeud = nombreDePasParNoeud;
	}
	
	Ecart_type()
	{
		tempsOuNombre = false;
		tempsEnSeconde = 10;
		nombreDePasParNoeud = 20000;
	}
	
	public CentralityResult AlgoPrincipaleTemps(SimpleGraph inGraph)
	{
		long tmpAuLancement = System.currentTimeMillis();
		long tmpMax = tmpAuLancement + (tempsEnSeconde * 1000);
		
		ArrayList<ArrayList<Integer>> CS = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<inGraph.getNodeCount(); i++)
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
			double dj = inGraph.getNodeDegree(j);
			double p = Math.random();
			if(p <= inGraph.getNodeDegree(i)/dj)
			{
				i = j.getIdentifier();
			}
		}
		CentralityResult resultat = new CentralityResult();
		
		for(int j=0; j<CS.size(); j++)
		{
			if(CS.get(j).size() > 3)
			{
				resultat.add(ET_calc(CS.get(j)));
			}
			else
			{
				System.out.print("Le noeud numéro " + j + "est en dessous des 3 valeurs nécessaire au calcul de cette mesure");
			}
		}
		return resultat;
	}
	
	public CentralityResult AlgoPrincipalePas(SimpleGraph inGraph)
	{
		ArrayList<ArrayList<Integer>> CS = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<inGraph.getNodeCount(); i++)
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
			double dj = inGraph.getNodeDegree(j);
			double p = Math.random();
			double di = inGraph.getNodeDegree(i);
			if(p <= di/dj)
			{
				i = j.getIdentifier();
			}

			suffisementDePas = true;
			for(ArrayList<Integer> tableau : CS)
				if(tableau.size() < nombreDePasParNoeud)
				{
					suffisementDePas = false;
					break;
				}
		}
		CentralityResult resultat = new CentralityResult();
		
		for(int j=0; j<CS.size(); j++)
		{
			if(CS.get(j).size() > 3)
			{
				resultat.add(ET_calc(CS.get(j)));
			}
			else
			{
				System.out.print("Le noeud numéro " + j + "est en dessous des 3 valeurs nécessaire au calcul de cette mesure");
			}
				
		}
		return resultat;
	}
	
	private Node rand(int a, SimpleGraph inGraph)
	{
		Node i = inGraph.getNodeAt(a);
		List<Link> noeudsEnLiens = inGraph.getNodeLinks(i);
		Node noeud = i;
		while(noeud.getIdentifier() == i.getIdentifier())
		{
			int random = (int)(Math.random() * (noeudsEnLiens.size()));
			noeud = inGraph.getNodeAt(noeudsEnLiens.get(random).getDestinationIdentifier());
		}
		return noeud;
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
		if(tempsOuNombre)
			return this.AlgoPrincipaleTemps(inGraph);
		return this.AlgoPrincipalePas(inGraph);
				
	}
}
