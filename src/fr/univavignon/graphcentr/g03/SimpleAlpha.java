package fr.univavignon.graphcentr.g03;

import fr.univavignon.graphcentr.g07.core.centrality.*;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import cern.jet.math.Functions;

/**
 * 
 * @author Delfeil
 * @brief compute the Alpha centrality
 *  
 */
class SimpleAlpha implements SimpleCentrality
{
	private double alpha;		//Paramètre alpha
	private DenseDoubleMatrix1D e;			//vecteur contenant les valeurs des scores externes
	private int nbIt;					//Nombre d'itérations
	
	/**
	 * @brief Constructeur avec tous les paramètres
	 * @param alpha paramètre d'incfluence des valeurs internes/esternes
	 * @param p précision attendue
	 * @param e vecteur contenant les valeurs des scores externes
	 */
	public SimpleAlpha(double alpha, double[] e, int nbIt)
	{
		this.alpha=alpha;
		this.nbIt=nbIt;;
		this.e=new DenseDoubleMatrix1D(e);
	}
	
	@Override
	public CentralityResult evaluate(SimpleGraph g)
	{
		CentralityResult r=new CentralityResult();
		double[] res=AlphaAlgorithm(g);
		for(int i=0; i<res.length; i++)
		{
			r.add(res[i]);
		}
		return r;
	}
	
	/**
	 * 
	 * @param g the graph
	 * @return array containing each node's centrality
	 */
	public double[] AlphaAlgorithm(SimpleGraph g)
	{
		//Initialisation
				double[] values;
				double [][] adjMat=g.toAdjacencyMatrix();
				DenseDoubleMatrix2D adj=new DenseDoubleMatrix2D(adjMat);
				DoubleMatrix2D adjT=adj.viewDice().copy();		//Récupération de la transposée de la matrice d'adjacence
				int n=g.getNodeCount();
				
					//Création de deux vecteurs aléatoires
				DoubleFactory1D randV=DoubleFactory1D.dense;
				DoubleMatrix1D v0=randV.sample(n, 1, 1);	//Vecteur de l'itération précédente
				DoubleMatrix1D v1=randV.sample(n, 0.5, 1);	//Vecteur de l'itération actuelle
				DoubleMatrix1D vtmp;
				
				//Algo
				int i=0;
				Algebra calNorm=new Algebra();		//Object servant à calculer les normes et les multiplications de vecteurs et matrices
				while(i<nbIt)
				{
					v0.assign(v1);			//v0=v1
					v1=calNorm.mult(adjT, v1);	//v1=AT*v1
					v1=v1.assign(Functions.mult(this.alpha));	//v1=v1*alpha
					v1=v1.assign(this.e, Functions.plus);		//v1=v1+e
					i++;
				}
				values=v1.toArray();
				return values;
	}
	
}