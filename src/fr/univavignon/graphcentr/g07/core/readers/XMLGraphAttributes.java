package fr.univavignon.graphcentr.g07.core.readers;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Scan <attributes> tag and extract all defined attributes with their default values
 * XMLLink/Node Information use this to test if attribute exists and get default value
 */
class XMLGraphAttributes 
{
	/** Attributes for nodes */
	protected Vector<XMLUserDefinedAttribute> NodeAttributes;
	/** Attributes for links */
	protected Vector<XMLUserDefinedAttribute> LinkAttributes;
	/** Type of XML graph */
	public GraphFileType Type;
	
	/** Node identifier in XML file */
	private static final String NODE_IDENTIFIER = "node";
	/** Edge identifier in XML file */
	private static final String EDGE_IDENTIFIER = "edge";
	/** Attribute target in XML file */
	private static final String ATTRIBUTE_TARGET = "for";
	
	/** Attribute identifier in GEXF files */
	private static final String GEXF_ATT_IDENTIFIER = "attribute";
	/** Attribute class in GEXF files */
	private static final String GEXF_ATT_CLASS = "class";
	/** Attribute value in GEXF files */
	private static final String GEXF_ATT_VALUE = "value";
	
	/** Attribute identifier in GraphML file */
	private static final String GRAPHML_ATT_IDENTIFIER = "data";
	/** Attribute name in GraphML file */
	private static final String GRAPHML_ATT_NAME = "key";
	
	/**
	 * Default constructor
	 * @param inType
	 */
	public XMLGraphAttributes(GraphFileType inType)
	{
		Type = inType;
		NodeAttributes = new Vector<>();
		LinkAttributes = new Vector<>();
	}
	
	/**
	 * Extracts attributes from given node
	 * @param inNode Root node of attributes
	 */
	public void extractAttributes(org.w3c.dom.Node inNode)
	{
		if(Type == GraphFileType.GEXF)
		{
			org.w3c.dom.Node Class = inNode.getAttributes().getNamedItem(GEXF_ATT_CLASS);
			if(Class == null)
				return;
	
			if(Class.getNodeValue().equalsIgnoreCase(NODE_IDENTIFIER))
			{
				for(int i = 0; i < inNode.getChildNodes().getLength(); i++)
				{
					org.w3c.dom.Node CurrentNode = inNode.getChildNodes().item(i);
					if(CurrentNode.getNodeName().equalsIgnoreCase(GEXF_ATT_IDENTIFIER))
						NodeAttributes.add(new XMLUserDefinedAttribute(Type, CurrentNode));
				}
			}
			else if(Class.getNodeValue().equalsIgnoreCase(EDGE_IDENTIFIER))
			{
				for(int i = 0; i < inNode.getChildNodes().getLength(); i++)
				{
					org.w3c.dom.Node CurrentNode = inNode.getChildNodes().item(i);
					if(CurrentNode.getNodeName().equalsIgnoreCase(GEXF_ATT_IDENTIFIER))
						LinkAttributes.add(new XMLUserDefinedAttribute(Type, CurrentNode));
				}
			}
		}
		else if(Type == GraphFileType.GraphML)
		{
			if(!inNode.getNodeName().equalsIgnoreCase(GRAPHML_ATT_NAME))
				return;
			
			org.w3c.dom.Node NodeType = inNode.getAttributes().getNamedItem(ATTRIBUTE_TARGET);
			if(NodeType == null)
				return;
			
			if(NodeType.getNodeValue().equalsIgnoreCase(NODE_IDENTIFIER))
				NodeAttributes.add(new XMLUserDefinedAttribute(Type, inNode));
			else if(NodeType.getNodeValue().equalsIgnoreCase(EDGE_IDENTIFIER))
				LinkAttributes.add(new XMLUserDefinedAttribute(Type, inNode));
		}
	}
	
	/**
	 * Returns a String of attribute's value. If not found returns empty String
	 * @param inNodes List of Node's attributes (<attvalue> tag) 
	 * @param inAttributeName Attribute name to find
	 * @return Attribute's value
	 */
	public String getNodeAttribute(org.w3c.dom.NodeList inNodes, String inAttributeName)
	{
		return getAttribute(NodeAttributes, inNodes, inAttributeName);
	}
	
	/**
	 * Returns a String of attribute's value. If not found returns empty String
	 * @param inNodes List of Link's attributes (<attvalue> tag) 
	 * @param inAttributeName Attribute to find by name
	 * @return Attribute's value
	 */
	public String getLinkAttribute(org.w3c.dom.NodeList inNodes, String inAttributeName)
	{
		return getAttribute(LinkAttributes, inNodes, inAttributeName);
	}
	
	/**
	 * Scan all given attributes and search inAttributeName. If found, returns 
	 * attribute's value (if tag has value parameter) otherwise returns default value.
	 * Can return empty String if attribute not found.
	 * @param inArray XMLAttributes to test with
	 * @param inNodes List of attributes (<attvalue> tag)
	 * @param inAttributeName Attribute to find by name
	 * @return Attribute's value
	 */
	protected String getAttribute(Vector<XMLUserDefinedAttribute> inArray, org.w3c.dom.NodeList inNodes, String inAttributeName)
	{
		XMLUserDefinedAttribute Attribute = null;
		String OutputValue = "";
		for(XMLUserDefinedAttribute CurrentAttribute : inArray)
		{
			if(CurrentAttribute.AttributeName.equalsIgnoreCase(inAttributeName))
				Attribute = CurrentAttribute;
		}
		
		if(Attribute == null)
			return OutputValue;
		
		OutputValue = Attribute.DefaultValue;

		if(inNodes == null || inNodes.getLength() <= 0)
			return OutputValue;

		
		if(Type == GraphFileType.GEXF)
		{
			for(int i = 0; i < inNodes.getLength(); i++)
			{
				org.w3c.dom.Node CurrentNode = inNodes.item(i);
				if(CurrentNode.getAttributes() == null)
					continue;
				
				org.w3c.dom.Node Id = CurrentNode.getAttributes().getNamedItem(ATTRIBUTE_TARGET);
				if(Id == null)
					continue;
				
				if(Id.getNodeValue().equalsIgnoreCase(Attribute.AttributeIdentifier))
				{
					org.w3c.dom.Node Value = CurrentNode.getAttributes().getNamedItem(GEXF_ATT_VALUE);
					if(Value == null)
						return OutputValue;
					
					return Value.getNodeValue();
				}
			}
			return OutputValue;
		}
		else if(Type == GraphFileType.GraphML)
		{
			for(int i = 0; i < inNodes.getLength(); i++)
			{
				org.w3c.dom.Node CurrentNode = inNodes.item(i);
				if(CurrentNode.getAttributes() == null)
					continue;
				if(!CurrentNode.getNodeName().equalsIgnoreCase(GRAPHML_ATT_IDENTIFIER))
					continue;
				
				org.w3c.dom.Node Id = CurrentNode.getAttributes().getNamedItem(GRAPHML_ATT_NAME);
				if(Id == null)
					continue;
				
				if(Id.getNodeValue().equalsIgnoreCase(Attribute.AttributeIdentifier))
				{
					String Value = CurrentNode.getTextContent();
					if(Value == null)
						return OutputValue;
					
					return Value;
				}
			}
			return OutputValue;
		}
		
		return "";
	}
}
