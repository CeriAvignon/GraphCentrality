package fr.univavignon.graphcentr.g02;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

import cern.colt.matrix.impl.*;
import cern.colt.matrix.linalg.*;
import cern.jet.math.*;

public class Katz implements DirectedCentrality
{
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph)
	{
		CentralityResult result = new CentralityResult();
		
		//result.add(KatzAlgorithm(inGraph));
		return result;
	}
	
	private double[] KatzAlgorithm(DirectedGraph inGraph, float alpha)
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
		DenseDoubleMatrix2D coltIdentityMatrix = new DenseDoubleMatrix2D(identityMatrix);		//Indentity matric using Colt needed type
		DenseDoubleMatrix2D coltTransposedMatrix = (DenseDoubleMatrix2D)coltInGraph.viewDice(); //Transposing Matrix
		
		coltTransposedMatrix.assign(Functions.mult(alpha));										//Multiplying all values in matrix by alpha.
		coltTransposedMatrix = (DenseDoubleMatrix2D)coltIdentityMatrix.assign(coltTransposedMatrix, Functions.minus); // I - alpha*transposedMatrix
		
		Algebra varAlgebra = new Algebra(); //To be able to use inverse function
		coltTransposedMatrix = (DenseDoubleMatrix2D)varAlgebra.inverse(coltTransposedMatrix);	//Inversing matrix
		
		coltTransposedMatrix = (DenseDoubleMatrix2D)coltIdentityMatrix.assign(coltIdentityMatrix, Functions.minus); // ((I - alpha*transposedMatrix)^-1) - I
		
		coltResult = (DenseDoubleMatrix1D)coltTransposedMatrix.zMult(coltResult, coltResult);	//Multiplying Matrix by vector fulfilled with 1

		
		//Normalization
		// s computing
		double s = Arithmetic.factorial(n);
		
		s = s * Math.pow(alpha, n-1) * Math.exp(1/alpha);
		
		coltResult.toArray(result); //Converting colt format vector to array
		
		for(int i = 0; i < n; i++)	//Normalizing all values											
		{
			result[i] = result[i] / s;
		}
		
		return result;
		
	}
	
}
