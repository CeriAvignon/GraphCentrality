package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Read a edge list file and update a graph
 */
public class EdgeListReader extends GraphFileReader
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
	public <GraphNodeType extends AbstractNode<?>, GraphLinkType extends AbstractLink<?>> 
	void updateFromFile(String inFileName, char inSeparator, Graph<GraphNodeType, GraphLinkType> inGraph)
	{
		openFile(inFileName);
		
		Vector<Pair<String, AbstractNode<?>>> ReadNodes = new Vector<>();
		Vector<Vector<String>> ReadLinks = new Vector<>();
		
		for(int i = 0; i < Lines.size(); i++)
		{
			String CurrentLine = Lines.get(i);
			Vector<String> Nodes = splitBySeparator(CurrentLine, inSeparator);
			ReadLinks.addElement(Nodes);
			for(String CurrentNode : Nodes)
				addUnique(ReadNodes, CurrentNode);
		}
		
		for(Pair<String, AbstractNode<?>> Current : ReadNodes)
			Current.SecondValue = inGraph.createNode();
		
		for(Vector<String> CurrentVector : ReadLinks)
		{
			if(CurrentVector.size() <= 1)
				continue;
			
			String SourceNodeName = CurrentVector.get(0);
			@SuppressWarnings("unchecked")
			GraphNodeType SourceNode = (GraphNodeType)findNode(ReadNodes, SourceNodeName);
			for(int i = 1; i < CurrentVector.size(); i++)
			{
				String DestinationNodeName = CurrentVector.get(i);
				@SuppressWarnings("unchecked")
				GraphNodeType DestinationNode = (GraphNodeType)findNode(ReadNodes, DestinationNodeName);
				
				if(!SourceNode.isLinkedWith(DestinationNode))
					inGraph.linkNode(SourceNode, DestinationNode);
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
	protected void addUnique(Vector<Pair<String, AbstractNode<?>>> inArray, String inNodeName)
	{
		for(Pair<String, AbstractNode<?>> Current : inArray)
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
	protected AbstractNode<?> findNode(Vector<Pair<String, AbstractNode<?>>> inArray, String inNodeName)
	{
		for(Pair<String, AbstractNode<?>> Current : inArray)
		{
			if(Current.FirstValue.equalsIgnoreCase(inNodeName))
				return Current.SecondValue;
		}
		return null;
	}
}
