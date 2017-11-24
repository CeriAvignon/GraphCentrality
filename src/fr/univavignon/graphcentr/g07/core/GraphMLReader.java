package fr.univavignon.graphcentr.g07.core;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author JackassDestroyer
 * Read a GraphML file and update a graph according to its type
 */
public class GraphMLReader extends GraphFileReader 
{
	/** */
	protected DocumentBuilderFactory factory;
	/** */
	protected DocumentBuilder builder;

	/** User-defined attribute for nodes */
	protected Vector<XMLNodeInformation> NodeInfos;
	/** User-defined attribute for links */
	protected Vector<XMLLinkInformation> LinkInfos;
	/** User-defined attributes */
	protected XMLGraphAttributes Attributes;
	
	/** True if graph is weighted */
	protected boolean bGraphIsWeighted = false;
	/** True if graph is spatial */
	protected boolean bGraphIsSpatial = false;
	/** True if graph is oriented */
	protected boolean bGraphIsOriented = false;
	
	/**
	 * Default constructor
	 */
	public GraphMLReader()
	{
		super();
		
		NodeInfos = new Vector<>();
		LinkInfos = new Vector<>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try 
		{
		    builder = factory.newDocumentBuilder();
		}
		catch (final ParserConfigurationException e) 
		{
		    e.printStackTrace();
		}
	}
	
	
	/**
	 * Read given file and update given graph
	 * @param inFileName File to read
	 * @param inGraph Graph to update
	 */
	public <GraphNodeType extends AbstractNode<?>, GraphLinkType extends AbstractLink<?>> 
	void updateFromFile(String inFileName, Graph<GraphNodeType, GraphLinkType> inGraph)
	{
		openFile(inFileName);
		
		// Check graph compatibility
		bGraphIsWeighted = GraphCompatibilityChecker.linksAreCompatible(inGraph, AbstractWeightedInformation.class);
		bGraphIsSpatial = GraphCompatibilityChecker.nodesAreCompatible(inGraph, AbstractSpatialInformation.class);
		bGraphIsOriented = GraphCompatibilityChecker.isCompatible(inGraph, DirectedNode.class, DirectedLink.class);
		
		// Parse file
		Document ReadFile = null;
		try 
		{
			ReadFile = builder.parse(new File(inFileName));
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		// Extract user-defined attributes
		extractAttributes(ReadFile);
		// Parse nodes from file and extract XML infos
		parseNodes(ReadFile);
		// Parse links from file and extract XML infos
		parseLinks(ReadFile);
		// Create nodes from parsed XML infos
		createNodes(inGraph);
		// Create links from parsed XML infos
		createLinks(inGraph);
	}
	
	/**
	 * Extracts user-defined attributes from file
	 * @param inDocument XML Document
	 */
	protected void extractAttributes(Document inDocument)
	{
		Attributes = new XMLGraphAttributes(GraphFileType.GraphML);
		NodeList Graphs = inDocument.getElementsByTagName("graphml");
		
		for(int i = 0; i < Graphs.item(0).getChildNodes().getLength(); i++)
		{
			Attributes.extractAttributes(Graphs.item(0).getChildNodes().item(i));
		}
	}
	
	/**
	 * Search all nodes and create XMLNodeInformation
	 * @param inDocument XML Document
	 */
	protected void parseNodes(Document inDocument)
	{
		NodeInfos.clear();
		
		NodeList Graphs = inDocument.getElementsByTagName("graph");
		if(Graphs.getLength() <= 0)
			return;
		
		NodeList Child = Graphs.item(0).getChildNodes();
		
		for(int i = 0; i < Child.getLength(); i++)
		{
			org.w3c.dom.Node CurrentChild = Child.item(i);
			if(CurrentChild.getNodeName().equalsIgnoreCase("node"))
				NodeInfos.add(new XMLNodeInformation(GraphFileType.GraphML, CurrentChild, Attributes));	
		}
	}
	
	/**
	 * Search all links and create XMLLinkInformation
	 * @param inDocument
	 */
	protected void parseLinks(Document inDocument)
	{
		LinkInfos.clear();
		
		NodeList Graphs = inDocument.getElementsByTagName("graph");
		if(Graphs.getLength() <= 0)
			return;
		
		NodeList Child = Graphs.item(0).getChildNodes();
		
		for(int i = 0; i < Child.getLength(); i++)
		{
			org.w3c.dom.Node CurrentChild = Child.item(i);
			if(CurrentChild.getNodeName().equalsIgnoreCase("edge"))	
				LinkInfos.add(new XMLLinkInformation(GraphFileType.GraphML, CurrentChild, Attributes));	
		}
	}
	
	/**
	 * Create nodes in graph from XMLNodeInformation
	 * @param inGraph Graph to update
	 */
	protected <GraphNodeType extends AbstractNode<?>, GraphLinkType extends AbstractLink<?>> 
	void createNodes(Graph<GraphNodeType, GraphLinkType> inGraph)
	{
		for(XMLNodeInformation Current : NodeInfos)
		{
			Current.Node = inGraph.createNode();
			if(bGraphIsSpatial)
			{
				AbstractSpatialInformation CurrentNode = (AbstractSpatialInformation)Current.Node;
				CurrentNode.setPosition(Current.X, Current.Y);
			}
		}
	}
	
	/**
	 * Create links in graph from XMLLinkInformation
	 * @param inGraph Graph to update
	 */
	protected <GraphNodeType extends AbstractNode<?>, GraphLinkType extends AbstractLink<?>> 
	void createLinks(Graph<GraphNodeType, GraphLinkType> inGraph)
	{
		for(XMLLinkInformation Current : LinkInfos)
		{
			AbstractNode<?> SourceNode = null;
			AbstractNode<?> DestinationNode = null;
			
			for(XMLNodeInformation CurrentNode : NodeInfos)
			{
				if(CurrentNode.Identifier.equalsIgnoreCase(Current.SourceIdentifier))
					SourceNode = CurrentNode.Node;
				
				if(CurrentNode.Identifier.equalsIgnoreCase(Current.DestinationIdentifier))
					DestinationNode = CurrentNode.Node;
			}
			
			// Add error ?
			if(SourceNode == null || DestinationNode == null)
				continue;
			
			Current.Link = inGraph.linkNode(inGraph.indexOf(SourceNode), inGraph.indexOf(DestinationNode));
			
			if(bGraphIsWeighted)
			{
				AbstractWeightedInformation CurrentLink = (AbstractWeightedInformation)Current.Link;
				CurrentLink.setWeight(Current.Weight);
				// For simple-like graph, update duplicated link (last one)
				if(!bGraphIsOriented)
				{
					CurrentLink = (AbstractWeightedInformation)inGraph.getLastLink();
					CurrentLink.setWeight(Current.Weight);
				}
			}
		}
	}
	
}
