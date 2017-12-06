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
	protected String FileContent = "";
	/** File content's lines */
	protected Vector<String> Lines = null;
	
	/**
	 * Default constructor
	 */
	public AbstractGraphFileReader()
	{
		Lines = new Vector<String>();
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
			
			FileContent = "";
			Lines.clear();
			
			String currentLine;
			
			while ((currentLine = bufferReader.readLine()) != null) 
			{
				Lines.add(currentLine);
				FileContent += currentLine+"\n";
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
}