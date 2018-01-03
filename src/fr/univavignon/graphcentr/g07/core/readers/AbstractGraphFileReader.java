package fr.univavignon.graphcentr.g07.core.readers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Base class for graph file reader
 */
abstract class AbstractGraphFileReader 
{
	/** */
	protected BufferedReader bufferReader = null;
	/** */
	protected FileReader fileReader = null;
	
	/** File content */
	protected String fileContent = "";
	/** File content's lines */
	protected Vector<String> lines = null;
	
	/**
	 * Default constructor
	 */
	public AbstractGraphFileReader()
	{
		lines = new Vector<String>();
	}
	
	/**
	 * Open given file
	 * @param inFileName
	 * @return If file is opened 
	 */
	final protected boolean openFile(String inFileName)
	{
		try
		{
			fileReader = new FileReader(inFileName);
			bufferReader = new BufferedReader(fileReader);
			
			fileContent = "";
			lines.clear();
			
			String currentLine;
			
			while ((currentLine = bufferReader.readLine()) != null) 
			{
				lines.add(currentLine);
				fileContent += currentLine+"\n";
			}
			
			return true;
		}
		catch(IOException execption)
		{
			execption.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (bufferReader != null)
					bufferReader.close();

				if (fileReader != null)
					fileReader.close();

			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Sets content
	 * @param inContent
	 */
	final protected void setContent(String inContent)
	{
		fileContent = inContent;
		lines.clear();
		
		String currentLine = "";
		
		for(int i = 0; i < inContent.length(); i++)
		{
			char currentChar = inContent.charAt(i);
			if(currentChar == '\n')
			{
				lines.add(currentLine);
				currentLine = "";
				continue;
			}
			
			currentLine += currentChar;
		}
		
		if(currentLine != "")
			lines.add(currentLine);
	}
}
