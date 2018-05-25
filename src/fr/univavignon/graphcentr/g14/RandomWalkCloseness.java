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
	 */
	public static double [][] transitionWeightedMatrix (DirectedWeightedGraph inGraph)
	{
		double [][] tab2 = inGraph.toAdjacencyMatrix();
		double [] tab = new double [inGraph.getNodeCount()]; 
		for (int i=0;i<inGraph.getNodeCount();i++) 
		{
			double sommeW = 0;
			for (int j=0; j<inGraph.getNodeLinks(inGraph.getNodeAt(i)).size();j++) 
			{
				sommeW = sommeW + inGraph.getNodeLinks(inGraph.getNodeAt(i)).get(j).getWeight();
			}
			tab[i]=sommeW;
		}
		double [][] tab3 = new double [tab.length][tab.length];
		for (int i=0;i<tab.length;i++) 
		{
			for (int j=0; j<tab.length;j++) 
			{
				if(tab2[i][j] != 0) {
					tab3[i][j]= tab2[i][j]/tab[i];
				} else {
					tab3[i][j]=0;
				}
			}
		}
		return tab3;
	}

    /**
     * @param tableau
     * @param index
     * @return
     * Creation d'une 
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
			DenseDoubleMatrix2D identityMatrix21 = removColumnAndRow(MatirxTransition,i);
			DoubleMatrix1D identityMatrix4= a.inverse(matrixIdentityCreation(inGraph).assign(identityMatrix21, Functions.minus)).zMult(vectorOfOneCreation(inGraph), null);
			for ( int j=0; j<identityMatrix4.size();j++) 
			{
				sommeCentrality += identityMatrix4.get(j); 
			}
			System.out.print(sommeCentrality+"\n");
			result.add(sommeCentrality);
			sommeCentrality=0;
			System.out.print("***************"+"\n");
			i++;
		}
		
			//System.out.print(identityMatrix3.toString());
			//System.out.print(sommeCentrlaty);
		
		return result;
	}

}
