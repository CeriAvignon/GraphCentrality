package fr.univavignon.graphcentr.g14;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedWeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import cern.jet.math.Functions;

/**
 * @author Soufiane.sel
 *
 */
public class RandomWalkCloseness implements DirectedWeightedCentrality {

	
	/**
	 * 
	 */
	public RandomWalkCloseness () {}
	/**
	 * @param inGraph
	 * @return
	 * Creation d'un matrice de transition pour le graphe orienté et pendéré 
	 */
	public static double [][] transitionWeightedMatrix (DirectedWeightedGraph inGraph)
	{

		double [] tableau = new double [inGraph.getNodeCount()]; 
		double [][] tableau2 = inGraph.toAdjacencyMatrix();
		for (int i=0;i<inGraph.getNodeCount();i++) 
		{
			double sommeW = 0;
			for (int j=0; j<inGraph.getNodeLinks(inGraph.getNodeAt(i)).size();j++) 
			{
				sommeW = sommeW + inGraph.getNodeLinks(inGraph.getNodeAt(i)).get(j).getWeight();
			}
			tableau[i]=sommeW;
		}
		double [][] tableau3 = new double [tableau.length][tableau.length];
		for (int i=0;i<tableau.length;i++) 
		{
			for (int j=0; j<tableau.length;j++) 
			{
				if(tableau2[i][j] != 0) {
					tableau3[i][j]= tableau2[i][j]/tableau[i];
				} else {
					tableau3[i][j]=0;
				}
			}
		}
		return tableau3;
	}

    /**
     * @param tableau
     * @param index
     * @return
     * Creation d'une nouvelle matrice sans la ligne et la colonne donner en paramatére a partir de matrice de transition.
     */
    public static DenseDoubleMatrix2D removColumnAndRow(double[][] tableau, int index) {
        int nRows = tableau.length;
        int nColumns = tableau[0].length;
 
        if (index >= nRows || index >= nColumns) {
            // Return exception ?
            return new DenseDoubleMatrix2D(new double [0][0]);
        }
 
        double[][] newTab = new double [nRows-1][nColumns-1];
        int newTabRow = 0;
        int newTabCol = 0;
 
        for(int i = 0 ; i<nRows ; ++i){
            if(i != index){
                for(int j = 0 ; j<nColumns ; ++j){
                    if(j != index){
                        newTab[newTabRow][newTabCol] = tableau[i][j];
                        ++newTabCol;
                    }
                }
                ++newTabRow;
                newTabCol = 0;
            }
        }
        return  new DenseDoubleMatrix2D(newTab);
        
    }
    
    /**
     * @param inGraph
     * @return
     * Creation d'une matrice d'identité.
     */
    public static DenseDoubleMatrix2D matrixIdentityCreation (DirectedWeightedGraph inGraph)
    {
    	double [][] identityMatrix = new double [inGraph.getNodeCount()-1][inGraph.getNodeCount()-1];
		for ( int i=0;i<inGraph.getNodeCount()-1;i++)
		{
			for ( int j=0;j<inGraph.getNodeCount()-1;j++)
			{
				if (i==j) {identityMatrix[i][j]=1;} else {identityMatrix[i][j]=0;}
			}
		}
		return  new DenseDoubleMatrix2D(identityMatrix);
    }
    
    /**
     * @param inGraph
     * @return
     * creation d'une vacteur qui contient des uns.
     */
    public static DenseDoubleMatrix1D vectorOfOneCreation (DirectedWeightedGraph inGraph) 
    {
    	double [] vectorOfOne = new double [inGraph.getNodeCount()-1];
    	for ( int i=0;i<inGraph.getNodeCount()-1;i++)
		{
    		vectorOfOne[i]=1;
		}
    	return  new DenseDoubleMatrix1D(vectorOfOne);
    }
    

	@Override
	public CentralityResult evaluate(DirectedWeightedGraph inGraph) {
		// TODO Auto-generated method stub
		CentralityResult result = new CentralityResult();
		Algebra a = new Algebra();
		double sommeCentrality = 0;
		double [][] MatirxTransition = transitionWeightedMatrix(inGraph);
		int i =0;
		while (i !=inGraph.getNodeCount() ) 
		{
			DenseDoubleMatrix2D MatrixOfNode = removColumnAndRow(MatirxTransition,i);
			DoubleMatrix1D MatrixResult= a.inverse(matrixIdentityCreation(inGraph).assign(MatrixOfNode, Functions.minus)).zMult(vectorOfOneCreation(inGraph), null);
			for ( int j=0; j<MatrixResult.size();j++) 
			{
				sommeCentrality += MatrixResult.get(j); 
			}
			result.add(inGraph.getNodeCount()/sommeCentrality);
			//System.out.print(inGraph.getNodeCount()/sommeCentrality+"\n");
			sommeCentrality=0;
			i++;
		}
		return result;
	}

}
