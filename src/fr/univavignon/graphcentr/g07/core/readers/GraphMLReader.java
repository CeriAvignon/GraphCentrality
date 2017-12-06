package fr.univavignon.graphcentr.g07.core.readers;


import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.univavignon.graphcentr.g07.core.AbstractDirectedGraph;
import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.AbstractSimpleGraph;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.WeightedLink;

/**
 * 
 * @author JackassDestroyer
 * Read a GraphML file and update a graph according to its type
 */
public class GraphMLReader extends AbstractGraphFileReader 
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
	protected boolean bWeightedGraph = false;
	/** True if graph is spatial */
	protected boolean bSpatialGraph = false;
	/** True if graph is directed */
	protected boolean bDirectedGraph = false;
	
	/** Node identifier in GraphML file */
	private static final String NODE_IDENTIFIER = "node";
	/** Edge identifier in GraphML file */
	private static final String EDGE_IDENTIFIER = "edge";
	/** Graph identifier in GraphML file */
	private static final String GRAPH_IDENTIFIER = "graph";
	/** GraphML header identifier in GraphML file */
	private static final String GRAPHML_IDENTIFIER = "graphml";
	
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
	public <GraphNodeType extends Node, GraphLinkType extends Link> 
	void updateFromFile(String inFileName, AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		if(!openFile(inFileName))
			return;
		
		// Check graph compatibility
		try 
		{
			bWeightedGraph = WeightedLink.class.isInstance(inGraph.getLinkClass().newInstance());
		} 
		catch (InstantiationException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IllegalAccessException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			bSpatialGraph = SpatialNode.class.isInstance(inGraph.getNodeClass().newInstance());
		} 
		catch (InstantiationException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IllegalAccessException e1) 
		{
			e1.printStackTrace();
		}
		
		bDirectedGraph = inGraph instanceof AbstractDirectedGraph<?, ?>;
		
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
		NodeList Graphs = inDocument.getElementsByTagName(GRAPHML_IDENTIFIER);
		
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
		
		NodeList Graphs = inDocument.getElementsByTagName(GRAPH_IDENTIFIER);
		if(Graphs.getLength() <= 0)
			return;
		
		NodeList Child = Graphs.item(0).getChildNodes();
		
		for(int i = 0; i < Child.getLength(); i++)
		{
			org.w3c.dom.Node CurrentChild = Child.item(i);
			if(CurrentChild.getNodeName().equalsIgnoreCase(NODE_IDENTIFIER))
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
		
		NodeList Graphs = inDocument.getElementsByTagName(GRAPH_IDENTIFIER);
		if(Graphs.getLength() <= 0)
			return;
		
		NodeList Child = Graphs.item(0).getChildNodes();
		
		for(int i = 0; i < Child.getLength(); i++)
		{
			org.w3c.dom.Node CurrentChild = Child.item(i);
			if(CurrentChild.getNodeName().equalsIgnoreCase(EDGE_IDENTIFIER))	
				LinkInfos.add(new XMLLinkInformation(GraphFileType.GraphML, CurrentChild, Attributes));	
		}
	}
	
	/**
	 * Create nodes in graph from XMLNodeInformation
	 * @param inGraph Graph to update
	 */
	protected <GraphNodeType extends Node, GraphLinkType extends Link> 
	void createNodes(AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		for(XMLNodeInformation Current : NodeInfos)
		{
			Current.Node = inGraph.createNode();
			if(bSpatialGraph)
			{
				SpatialNode CurrentNode = (SpatialNode)Current.Node;
				CurrentNode.setX(Current.X);
				CurrentNode.setY(Current.Y);
			}
		}
	}
	
	/**
	 * Create links in graph from XMLLinkInformation
	 * @param inGraph Graph to update
	 */
	protected <GraphNodeType extends Node, GraphLinkType extends Link> 
	void createLinks(AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		for(XMLLinkInformation Current : LinkInfos)
		{
			Node SourceNode = null;
			Node DestinationNode = null;
			
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
			
			Current.Link = inGraph.createLink(SourceNode.getIdentifier(), DestinationNode.getIdentifier());
			
			if(bWeightedGraph)
			{
				WeightedLink CurrentLink = (WeightedLink)Current.Link;
				CurrentLink.setWeight(Current.Weight);
				// For simple-like graph, update duplicated link (last one)
				if(!bDirectedGraph)
				{
					AbstractSimpleGraph<GraphNodeType, GraphLinkType> simpleGraph = (AbstractSimpleGraph<GraphNodeType, GraphLinkType>)inGraph;
					CurrentLink = (WeightedLink)simpleGraph.getLastBackLink();
					CurrentLink.setWeight(Current.Weight);
				}
			}
		}
	}
	
}

