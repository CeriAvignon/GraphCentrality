package fr.univavignon.graphcentr.g07.core.readers;

import java.util.Vector;

import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.Link;

/**
 * 
 * @author JackassDestroyer
 * Read a edge list file and update a graph
 */
public class EdgeListReader extends AbstractGraphFileReader
{
	/**
	 * Default constructor
	 */
	public EdgeListReader()
	{
		super();
	}
	
	/**
	 * Update given graph from file
	 * @param inFileName File to read
	 * @param inSeparator Which char separates datas
	 * @param inGraph Graph to update
	 */
	public <GraphNodeType extends Node, GraphLinkType extends Link> 
	void updateFromFile(String inFileName, char inSeparator, AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		if(!openFile(inFileName))
			return;
		
		Vector<Pair<String, GraphNodeType>> ReadNodes = new Vector<>();
		Vector<Vector<String>> ReadLinks = new Vector<>();
		
		for(int i = 0; i < Lines.size(); i++)
		{
			String CurrentLine = Lines.get(i);
			Vector<String> Nodes = splitBySeparator(CurrentLine, inSeparator);
			ReadLinks.addElement(Nodes);
			for(String CurrentNode : Nodes)
				addUnique(ReadNodes, CurrentNode);
		}
		
		for(Pair<String, GraphNodeType> Current : ReadNodes)
			Current.SecondValue = inGraph.createNode();
		
		for(Vector<String> CurrentVector : ReadLinks)
		{
			if(CurrentVector.size() <= 1)
				continue;
			
			String SourceNodeName = CurrentVector.get(0);
			GraphNodeType SourceNode = findNode(ReadNodes, SourceNodeName);
			for(int i = 1; i < CurrentVector.size(); i++)
			{
				String DestinationNodeName = CurrentVector.get(i);
				GraphNodeType DestinationNode = findNode(ReadNodes, DestinationNodeName);
				
				if(!inGraph.isAdjacentTo(SourceNode, DestinationNode))
					inGraph.createLink(SourceNode, DestinationNode);
			}
		}
	}
	
	/**
	 * Split string by inSeparator
	 * @param inLine
	 * @param inSeparator
	 * @return Splited string
	 */
	protected Vector<String> splitBySeparator(String inLine, char inSeparator)
	{
		Vector<String> SplitedString = new Vector<>();
		
		String CurrentString = "";
		for(int i = 0; i < inLine.length(); i++)
		{
			char CurrentChar = inLine.charAt(i);
			if(CurrentChar == inSeparator)
			{
				SplitedString.add(CurrentString.trim());
				CurrentString = "";
			}
			else
			{
				CurrentString += CurrentChar;
			}
		}
		if(CurrentString != "")
			SplitedString.add(CurrentString.trim());
		
		return SplitedString;
	}
	
	/**
	 * Add a string if not in inArray
	 * @param inArray
	 * @param inNodeName
	 */
	protected <NodeType extends Node> void addUnique(Vector<Pair<String, NodeType>> inArray, String inNodeName)
	{
		for(Pair<String, NodeType> Current : inArray)
		{
			if(Current.FirstValue.equalsIgnoreCase(inNodeName))
				return;
		}
		inArray.add(new Pair<>(inNodeName, null));
	}
	
	/**
	 * Find node
	 * @param inArray
	 * @param inNodeName
	 * @return Node
	 */
	protected <NodeType extends Node> NodeType findNode(Vector<Pair<String,NodeType>> inArray, String inNodeName)
	{
		for(Pair<String, NodeType> Current : inArray)
		{
			if(Current.FirstValue.equalsIgnoreCase(inNodeName))
				return Current.SecondValue;
		}
		return null;
	}
}

