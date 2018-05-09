package fr.univavignon.graphcentr.g07.core.readers;

import fr.univavignon.graphcentr.g07.core.Node;

/**
 * 
 * @author JackassDestroyer
 * XML-extracted information for nodes
 */
class XMLNodeInformation
{
	/** Concerned node */
	public Node Node;
	/** X position */
	public double X;
	/** Y position */
	public double Y;
	/** Node's ID */
	public String Identifier;
	/** File's type */
	public GraphFileType Type;
	
	/** X identifier in XML files */
	private static final String X_IDENTIFIER = "X";
	/** Y identifier in XML files */
	private static final String Y_IDENTIFIER = "Y";
	/** XML Node identifier in XML files */
	private static final String NODE_IDENTIFIER = "id";
	
	/** Attributes identifier in GEXF files */
	private static final String ATTRIBUTES_IDENTIFIER = "attvalues";

	/**
	 * Default constructor
	 * @param inType Type of file
	 * @param inNode
	 * @param inAttributes
	 */
	public XMLNodeInformation(GraphFileType inType, org.w3c.dom.Node inNode, XMLGraphAttributes inAttributes)
	{
		Type = inType;
		Node = null;
		Identifier = "";		
		X = 0;
		Y = 0;
		
		extractInformation(inNode, inAttributes);
	}
	
	/**
	 * Extracts informations from XMLNode by parsing it
	 * @param inNode XMLNode
	 * @param inAttributes Document user-defined attributes
	 */
	public void extractInformation(org.w3c.dom.Node inNode, XMLGraphAttributes inAttributes)
	{
		String XValue = "";
		String YValue = "";
		
		if(Type == GraphFileType.GEXF)
		{
			org.w3c.dom.Node Id = inNode.getAttributes().getNamedItem(NODE_IDENTIFIER);
			if(Id != null)
				Identifier = Id.getNodeValue();
			
			org.w3c.dom.NodeList Attributes = null;
			for(int i = 0; i < inNode.getChildNodes().getLength(); i++)
			{
				org.w3c.dom.Node CurrentNode = inNode.getChildNodes().item(i);
				if(CurrentNode.getNodeName().equalsIgnoreCase(ATTRIBUTES_IDENTIFIER))
				{
					Attributes = CurrentNode.getChildNodes();
					break;
				}
			}
			
			XValue = inAttributes.getNodeAttribute(Attributes, X_IDENTIFIER);
			YValue = inAttributes.getNodeAttribute(Attributes, Y_IDENTIFIER);
		}
		else if(Type == GraphFileType.GraphML)
		{
			org.w3c.dom.Node Id = inNode.getAttributes().getNamedItem(NODE_IDENTIFIER);
			if(Id != null)
				Identifier = Id.getNodeValue();
			
			org.w3c.dom.NodeList Attributes = inNode.getChildNodes();
			
			XValue = inAttributes.getNodeAttribute(Attributes, X_IDENTIFIER);
			YValue = inAttributes.getNodeAttribute(Attributes, Y_IDENTIFIER);
		}
		
		if(XValue != "")
			X = Double.parseDouble(XValue);
		
		if(YValue != "")
			Y = Double.parseDouble(YValue);
	}
}
	

