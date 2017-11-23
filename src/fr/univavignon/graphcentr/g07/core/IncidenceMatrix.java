package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Transform a graph into an incidence matrix.
 */
public class IncidenceMatrix extends Matrix<Integer> implements AbstractDataStructure
{
	/**
	 * Default constructor
	 */
	public IncidenceMatrix()
	{
		matrix = new Vector<Vector<Integer>>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>> 
	void copyGraph(Graph<NodeType, LinkType> inGraph)
	{
		matrix.clear();
		// Special for oriented graphs
		if(GraphCompatibilityChecker.isCompatible(inGraph, DirectedNode.class, DirectedLink.class))
		{
			copyOrientedGraph((Graph<DirectedNode, DirectedLink>)inGraph);
			return;
		}
		
		Vector<NodeType> Nodes = inGraph.getNodes();
		Vector<LinkType> Links = inGraph.getLinks();
		
		int NodeCount = inGraph.getNodeCount();
		int LinkCount = inGraph.getLinkCount();
		
		// Initialise matrix
		setRowCount(NodeCount);
		setColumnCount(LinkCount);
		fill(0);
		
		// For each nodes get links
		for(int i = 0; i < Nodes.size(); i++)
		{
			NodeType CurrentNode = Nodes.get(i);
			// For each links get index of it in graph's links array
			for(int j = 0; j < CurrentNode.getLinkCount(); j++)
			{
				int LinkIndex = Links.indexOf(CurrentNode.getLinks().get(j));
				if(LinkIndex != -1)
				{
					setValueAt(1, LinkIndex / 2, i);
				}
			}
		}
	}
	
	/**
	 * Specialisation for oriented graph
	 * @param inGraph
	 */
	protected <NodeType extends DirectedNode, LinkType extends DirectedLink> 
	void copyOrientedGraph(Graph<NodeType, LinkType> inGraph)
	{
		Vector<NodeType> Nodes = inGraph.getNodes();
		Vector<LinkType> Links = inGraph.getLinks();
		
		int NodeCount = inGraph.getNodeCount();
		int LinkCount = inGraph.getLinkCount();
		
		// Initialise matrix
		setRowCount(NodeCount);
		setColumnCount(LinkCount);
		fill(0);
		
		for(int i = 0; i < Nodes.size(); i++)
		{
			NodeType CurrentNode = Nodes.get(i);
			for(int j = 0; j < CurrentNode.getLinkCount(); j++)
			{
				int LinkIndex = Links.indexOf(CurrentNode.getLinks().get(j));
				if(LinkIndex != -1)
				{
					setValueAt(-1, LinkIndex, i);
					setValueAt(1, LinkIndex, Nodes.indexOf(CurrentNode.getLinks().get(j).getDestinationNode()));
				}
			}
		}
	}
	
	@Override
	public <NodeType extends Node, LinkType extends Link> 
	void updateGraph(Graph<NodeType, LinkType> inOutGraph) 
	{		
		// @TODO
	}
	

	@Override
	public void print() 
	{
		int NumberSize = 4;
		
		for(int i = 0; i < matrix.size(); i++)
		{
			for(int j = 0; j < matrix.get(i).size(); j++)
			{
				String Number = "0";
				if(matrix.get(i).get(j) != null)
					Number = Integer.toString(matrix.get(i).get(j));
				
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
