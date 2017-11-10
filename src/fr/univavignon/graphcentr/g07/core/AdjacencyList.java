package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Transform a graph into an adjacency list.
 */
public class AdjacencyList implements AbstractDataStructure
{
	/** */
	protected Vector< Pair<AbstractNode<?>,Vector<Integer>> > List;
	
	/**
	 * Default constructor
	 */
	public AdjacencyList()
	{
		List = new Vector< Pair<AbstractNode<?>,Vector<Integer>> >();
	}
	
	/**
	 * Returns created list
	 * @return List
	 */
	Vector<Pair<AbstractNode<?>,Vector<Integer>>> getList()
	{
		return List;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>> 
	void copyGraph(Graph<NodeType, LinkType> inGraph) 
	{
		List.clear();
		Vector<NodeType> Nodes = inGraph.getNodes();
		
		for(NodeType CurrentNode : Nodes)
		{
			Pair<AbstractNode<?>,Vector<Integer>> Node = new Pair<>(CurrentNode, new Vector<Integer>());
			// Useless unchecked warning
			for(LinkType CurrentLink : (Vector<LinkType>)CurrentNode.getLinks())
			{
				int Index = Nodes.indexOf(CurrentLink.getDestinationNode());
				Node.SecondValue.add(Index);
			}
			List.add(Node);
		}
	}

	@Override
	public <NodeType extends Node, LinkType extends Link> void updateGraph(Graph<NodeType, LinkType> inOutGraph) 
	{		
		// @TODO
	}

	@Override
	public void print() 
	{		
		for(Pair<AbstractNode<?>,Vector<Integer>> CurrentPair : List)
		{
			System.out.print(CurrentPair.FirstValue+": ");
			boolean bFirstOccurence = true;
			for(Integer Index : CurrentPair.SecondValue)
			{
				if(bFirstOccurence)
				{
					System.out.print(Index);
					bFirstOccurence = false;
					continue;
				}
				System.out.print(", "+Index);
			}
			System.out.println("");
		}
	}

}
