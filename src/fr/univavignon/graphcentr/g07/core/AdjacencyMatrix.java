package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Transform a graph into an adjacency matrix.
 */
public class AdjacencyMatrix extends Matrix<Integer> implements AbstractDataStructure
{

	/**
	 * Default constructor
	 */
	public AdjacencyMatrix()
	{
		matrix = new Vector<Vector<Integer>>();
	}
	
	/**
	 * Copy graph overriding for simple-like-graph
	 */
	@Override
	public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>>
	void copyGraph(Graph<NodeType, LinkType> inGraph) 
	{			
		matrix.clear();
		int NodeCount = inGraph.getNodeCount();
		
		// Initialise matrix
		setRowCount(NodeCount);
		setColumnCount(NodeCount);
		fill(0);
		
		// Parse each nodes
		for(int i = 0; i < NodeCount; i++)
		{
			NodeType CurrentNode = inGraph.getNodeAt(i);
			Vector<?> Links = CurrentNode.getLinks();
			
			for(int j = 0; j < CurrentNode.getLinkCount(); j++)
			{
				AbstractLink<?> CurrentLink = (AbstractLink<?>)Links.get(j);
				int SrcIndex = inGraph.indexOf(CurrentLink.getSourceNode());
				int DestIndex = inGraph.indexOf(CurrentLink.getDestinationNode());
				int Value = 1;
				if (SrcIndex == DestIndex)
					Value = 2;
				
				setValueAt(Value, SrcIndex, DestIndex);
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
		int NumberSize = 3;
		
		for(int i = 0; i < matrix.size(); i++)
		{
			for(int j = 0; j < matrix.get(i).size(); j++)
			{
				String Number = "0";
				if(matrix.get(j).get(i) != null)
					Number = Integer.toString(matrix.get(j).get(i));
				
				System.out.print(Number);
				for(int SpaceCount = 0; SpaceCount < NumberSize - Number.length(); SpaceCount++)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
