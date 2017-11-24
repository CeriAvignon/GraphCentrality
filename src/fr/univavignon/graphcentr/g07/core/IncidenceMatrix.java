package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Transform a graph into an incidence matrix.
 */
public class IncidenceMatrix extends Matrix<Double> implements AbstractDataStructure
{
	/**
	 * Default constructor
	 */
	public IncidenceMatrix()
	{
		matrix = new Vector<Vector<Double>>();
	}
	
	@Override
	public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>> 
	void copyGraph(AbstractGraph<NodeType, LinkType> inGraph)
	{
		matrix.clear();
		// Special for oriented graphs
		if(GraphCompatibilityChecker.isDirected(inGraph))
		{
			copyOrientedGraph((DirectedGraph<NodeType, LinkType>)inGraph);
			return;
		}
		
		boolean bWeighted = GraphCompatibilityChecker.linksAreCompatible(inGraph, AbstractWeightedInformation.class);
		
		Vector<NodeType> Nodes = inGraph.getNodes();
		Vector<LinkType> Links = inGraph.getLinks();
		
		int NodeCount = inGraph.getNodeCount();
		int LinkCount = inGraph.getLinkCount();
		
		// Initialise matrix
		setRowCount(NodeCount);
		setColumnCount(LinkCount);
		fill(0.0);
		
		// For each nodes get links
		for(int i = 0; i < Nodes.size(); i++)
		{
			NodeType CurrentNode = Nodes.get(i);
			// For each links get index of it in graph's links array
			for(int j = 0; j < CurrentNode.getLinkCount(); j++)
			{
				int LinkIndex = Links.indexOf(CurrentNode.getLinks().get(j));
				double Value = 1.0;
				
				if(bWeighted)
					Value = ((AbstractWeightedInformation)CurrentNode.getLinks().get(j)).getWeight();
				
				if(LinkIndex != -1)
					setValueAt(Value, LinkIndex / 2, i);
			}
		}
	}
	
	/**
	 * Specialisation for oriented graph
	 * @param inGraph
	 */
	protected <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>> 
	void copyOrientedGraph(DirectedGraph<NodeType, LinkType> inGraph)
	{
		boolean bWeighted = GraphCompatibilityChecker.linksAreCompatible(inGraph, AbstractWeightedInformation.class);
		
		Vector<NodeType> Nodes = inGraph.getNodes();
		Vector<LinkType> Links = inGraph.getLinks();
		
		int NodeCount = inGraph.getNodeCount();
		int LinkCount = inGraph.getLinkCount();
		
		// Initialise matrix
		setRowCount(NodeCount);
		setColumnCount(LinkCount);
		fill(0.0);
		
		for(int i = 0; i < Nodes.size(); i++)
		{
			NodeType CurrentNode = Nodes.get(i);
			for(int j = 0; j < CurrentNode.getLinkCount(); j++)
			{
				int LinkIndex = Links.indexOf(CurrentNode.getLinks().get(j));
				double Value = 1;
				
				if(bWeighted)
					Value = ((AbstractWeightedInformation)CurrentNode.getLinks().get(j)).getWeight();
				
				if(LinkIndex != -1)
				{
					setValueAt(-Value, LinkIndex, i);
					setValueAt(Value, LinkIndex, Nodes.indexOf(CurrentNode.getLinks().get(j).getDestinationNode()));
				}
			}
		}
	}
	
	@Override
	public <NodeType extends Node, LinkType extends Link> 
	void updateGraph(AbstractGraph<NodeType, LinkType> inOutGraph) 
	{		
		// @TODO
	}
	

	@Override
	public void print() 
	{
		int NumberSize = 5;
		
		for(int i = 0; i < matrix.size(); i++)
		{
			for(int j = 0; j < matrix.get(i).size(); j++)
			{
				String Number = "0";
				if(matrix.get(i).get(j) != null)
					Number = Double.toString(matrix.get(i).get(j));
				
				if(matrix.get(i).get(j) >= 0)
				{
					Number = " "+Number;
				}
				System.out.print(Number);
				for(int SpaceCount = 0; SpaceCount < NumberSize - Number.length(); SpaceCount++)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
