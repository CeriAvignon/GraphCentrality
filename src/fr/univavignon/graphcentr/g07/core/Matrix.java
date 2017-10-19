package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Base class of matrix
 * @param <ValueType> Type of data manipulated (must be a number)
 */
abstract class Matrix <ValueType extends Number>
{
	/**
	 * 
	 */
	protected Vector<Vector<ValueType>> matrix;
	
	/**
	 * Set value at coords
	 * @param inValue
	 * @param inX
	 * @param inY
	 */
	public void setValueAt(ValueType inValue, int inX, int inY)
	{
		matrix.get(inY).set(inX, inValue);
	}
	
	/**
	 * Returns value at coords
	 * @param inX
	 * @param inY
	 * @return value
	 */
	public ValueType getValueAt(int inX, int inY)
	{
		return matrix.get(inY).get(inX);
	}
	
	/**
	 * Set row count
	 * @param inCount
	 */
	public void setRowCount(int inCount)
	{
		matrix.removeAllElements();
		for(int i = 0; i < inCount; i++)
		{
			matrix.add(new Vector<ValueType>());
		}
	}
	
	/**
	 * Set column count
	 * @param inCount
	 */
	public void setColumnCount(int inCount)
	{
		for(int i = 0; i < matrix.size(); i++)
		{
			matrix.get(i).setSize(inCount);
		}
	}
	
	/**
	 * Fill matrix with given value
	 * @param inValue
	 */
	public void fill(ValueType inValue)
	{
		for(int i = 0; i < matrix.size(); i++)
		{
			for(int j = 0; j < matrix.get(i).size(); j++)
			{
				matrix.get(i).set(j, inValue);
			}
		}
	}
}
