package fr.univavignon.graphcentr.g07.core.readers;



import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
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
 * Read a GEXF file and update a graph according to its type
 */
public class GEXFReader extends AbstractGraphFileReader
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
	private boolean bWeightedGraph = false;
	/** True if graph is spatial */
	private boolean bSpatialGraph = false;
	/** True if graph is directed */
	protected boolean bDirectedGraph = false;
	
	/**
	 * Default constructor
	 */
	public GEXFReader()
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
		Attributes = new XMLGraphAttributes(GraphFileType.GEXF);
		NodeList Graphs = inDocument.getElementsByTagName("attributes");
		
		for(int i = 0; i < Graphs.getLength(); i++)
		{
			Attributes.extractAttributes(Graphs.item(i));
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
		org.w3c.dom.Node Nodes = null;
		
		for(int i = 0; i < Child.getLength(); i++)
		{
			org.w3c.dom.Node CurrentChild = Child.item(i);
			if(CurrentChild.getNodeName() == "nodes")
				Nodes = CurrentChild;
		}
		
		if(Nodes == null)
			return;
		
		for(int i = 0; i < Nodes.getChildNodes().getLength(); i++)
		{
			org.w3c.dom.Node CurrentNode = Nodes.getChildNodes().item(i);
			if(CurrentNode.getNodeName() == "node")
				NodeInfos.add(new XMLNodeInformation(GraphFileType.GEXF, CurrentNode, Attributes));		
		}
	}
	
	/**
	 * Search all links and create XMLLinkInformation
	 * @param inFile
	 */
	protected void parseLinks(Document inFile)
	{
		NodeList Graphs = inFile.getElementsByTagName("graph");
		if(Graphs.getLength() <= 0)
			return;
		
		NodeList Child = Graphs.item(0).getChildNodes();
		org.w3c.dom.Node Links = null;
		
		for(int i = 0; i < Child.getLength(); i++)
		{
			org.w3c.dom.Node CurrentChild = Child.item(i);
			if(CurrentChild.getNodeName() == "edges")
				Links = CurrentChild;
		}
		
		// Add error ?
		if(Links == null)
			return;
		
		for(int i = 0; i < Links.getChildNodes().getLength(); i++)
		{
			org.w3c.dom.Node CurrentLink = Links.getChildNodes().item(i);
			if(CurrentLink.getNodeName() == "edge")
				LinkInfos.add(new XMLLinkInformation(GraphFileType.GEXF, CurrentLink, Attributes));			
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
