package fr.univavignon.graphcentr.g02;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

//import java.util.*;

import cern.colt.matrix.impl.*;
import cern.colt.matrix.linalg.*;
import cern.jet.math.*;

/**
* @author MENGUY Loïc 
*
* @brief Computes centrality using Katz Algorithm
*/

public class Katz implements DirectedCentrality
{
	/**
	 * alpha value
	 */
	private double alpha;
	
	//Constructor with alpha arg
	/**
	 * @param alphaArg alpha parameter
	 */
	public Katz(double alphaArg)
	{
		this.alpha = alphaArg;
	}
	
	/**
	 * @brief Create Katz class with alpha value = 0.5
	 */
	//Default constructor
	public Katz()
	{
		this.alpha = 0.5;
	}
	
	/**
	 * @return alpha value
	 */
	//Alpha getter
	public double getAlpha()
	{
		return this.alpha;
	}
	
	
	/**
	 * @param alphaArg New alpha value
	 */
	//Alpha setter
	public void setAlpha(double alphaArg)
	{
		this.alpha = alphaArg;
	}
	
	
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph)
	{
		CentralityResult returnResult = new CentralityResult();
		double[] katzResult = KatzAlgorithm(inGraph);
		
		for(int i = 0; i<katzResult.length; i++)
		{
			returnResult.add(katzResult[i]);
		}
		return returnResult;
	}
	
	
	
	/**
	 * @param inGraph
	 * @return Array containing each node's centrality
	 */
	private double[] KatzAlgorithm(DirectedGraph inGraph)
	{
		int n = inGraph.getNodeCount(); 														//Number of nodes in the graph
		double[][] adjacencyMatrix = inGraph.toAdjacencyMatrix(); 								//Converting graph to adjacency matrix (to be able to use Colt functions)
		
		//Creating identity matrix
		double[][] identityMatrix = new double[n][n]; 
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i==j)
				{
					identityMatrix[i][j] = 1;
				}
			}
		}
		
		double[] result = new double[n]; 														//Return vector
		for(int i = 0; i < n; i++)																//Initializing all elements of return vector at 1
		{
			result[i] = 1;
		}
		DenseDoubleMatrix1D coltResult = new DenseDoubleMatrix1D(result); 						//Return vector using Colt needed type
		
		DenseDoubleMatrix2D coltInGraph = new DenseDoubleMatrix2D(adjacencyMatrix); 			//Creating a matrix using the Colt needed type
		DenseDoubleMatrix2D coltIdentityMatrix1 = new DenseDoubleMatrix2D(identityMatrix);		//Indentity matric using Colt needed type (will be changed for computing)
		DenseDoubleMatrix2D coltIdentityMatrix2 = new DenseDoubleMatrix2D(identityMatrix);		//Indentity matric using Colt needed type (will not be changed)
		DenseDoubleMatrix2D coltTransposedMatrix = (DenseDoubleMatrix2D)coltInGraph.viewDice(); //Transposing Matrix

		coltTransposedMatrix.assign(Functions.mult(this.alpha));										//Multiplying all values in matrix by alpha.

		coltTransposedMatrix = (DenseDoubleMatrix2D)coltIdentityMatrix1.assign(coltTransposedMatrix, Functions.minus); // I - alpha*transposedMatrix

		Algebra varAlgebra = new Algebra(); //To be able to use inverse function
		coltTransposedMatrix = (DenseDoubleMatrix2D)varAlgebra.inverse(coltTransposedMatrix);	//Inversing matrix

		coltTransposedMatrix.assign(coltIdentityMatrix2, Functions.minus); // ((I - alpha*transposedMatrix)^-1) - I

		coltResult = (DenseDoubleMatrix1D) varAlgebra.mult(coltTransposedMatrix, coltResult);	//Multiplying Matrix by vector fulfilled with 1
		
		//Normalization
		// s computing
		double s = Arithmetic.factorial(n-1);
		
		s = s * Math.pow(this.alpha, n-1) * Math.exp(1/this.alpha);
		
		coltResult.toArray(result); //Converting colt format vector to array
		
		for(int i = 0; i < n; i++)	//Normalizing all values											
		{
			result[i] = result[i] / s;
		}
		

		return result;
		
	}
	
}
