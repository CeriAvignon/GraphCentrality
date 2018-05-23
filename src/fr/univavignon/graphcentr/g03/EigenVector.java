package fr.univavignon.graphcentr.g03;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.centrality.*;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.linalg.Algebra;
import cern.jet.math.Functions;

/**
 * 
 * @author Delfeil
 * @brief compute the EigenVector centrality using the Power Method
 */
class EigenVector implements SimpleCentrality
{
	double p;
	int nbIt;
	
	public EigenVector()
	{
		this.p=0.1;
		this.nbIt=1000;
	}
	
	public EigenVector(double precision)
	{
		this.p=precision;
	}
	
	@Override
	public CentralityResult evaluate(SimpleGraph g)
	{
		CentralityResult r=new CentralityResult();
		double[] eigenV=EigenVectorAlgorithm(g);
		for(int i=0; i<eigenV.length; i++)
		{
			r.add(eigenV[i]);
		}
		return r;
	}
	
	/**
	 * 
	 * @param g the graph
	 * @return array containing each node's centrality
	 */
	public double[] EigenVectorAlgorithm(SimpleGraph g)
	{
		//Initialisation
		double[] values;
		double [][] adjMat=g.toAdjacencyMatrix();
		DenseDoubleMatrix2D adj=new DenseDoubleMatrix2D(adjMat);
		int n=g.getNodeCount();
		
			//Création de deux vecteurs aléatoires
		DoubleFactory1D randV=DoubleFactory1D.dense;
		DoubleMatrix1D v0=randV.sample(n, 1, 1);	//Vecteur de l'itération précédente
		DoubleMatrix1D v1=randV.sample(n, 0.5, 1);	//Vecteur de l'itération actuelle
		DoubleMatrix1D vtmp;
		
		//Algo
		double lambda;
		Algebra calNorm=new Algebra();
		int i=0;
			//tant que la précision est inférieure à la différence entre la norme du vecteur de l'itération précédente, et la norme du vecteur de l'itération courante:
		//while(Math.abs(calNorm.norm2(v1)-calNorm.norm2(v0))>=p)
		DoubleMatrix1D comp=v1.assign(v0, Functions.minus);
		comp.assign(Functions.abs);
		while(comp.zSum()>p)
		{
			/*System.out.println("v0: "+i+": "+v0);
			System.out.println("v1: "+i+": "+v1);
			System.out.println("|v0-v1|= "+(calNorm.norm2(v0)-calNorm.norm2(v1)));*/
			v0.assign(v1);			//v0=v1
			//v1=adj.zMult(v1, v1);	//v1= A*v1
			v1=calNorm.mult(adj, v1);	//v1=A*v1
			lambda=calNorm.norm2(v1);
			//System.out.println("lambda: " + lambda);
			v1=v1.assign(Functions.mult(1/lambda));
			//System.out.println("v1/LAMBDA: "+i+": "+v1);
			comp=v1.assign(v0, Functions.minus);
			comp.assign(Functions.abs);
			//i++;
			Benchmark.addIteration();
		}
		values=v0.toArray();
		return values;
	}
	
	
	public double[] EigenVectorAlgorithmNbIt(SimpleGraph g)
	{
		//Initialisation
		double[] values;
		double [][] adjMat=g.toAdjacencyMatrix();
		DenseDoubleMatrix2D adj=new DenseDoubleMatrix2D(adjMat);
		int n=g.getNodeCount();
		
			//Création de deux vecteurs aléatoires
		DoubleFactory1D randV=DoubleFactory1D.dense;
		DoubleMatrix1D v0=randV.sample(n, 1, 1);	//Vecteur de l'itération précédente
		DoubleMatrix1D v1=randV.sample(n, 0.5, 1);	//Vecteur de l'itération actuelle
		DoubleMatrix1D vtmp;
		
		//Algo
		double lambda;
		Algebra calNorm=new Algebra();
			//tant que la précision est inférieure à la différence entre la norme du vecteur de l'itération précédente, et la norme du vecteur de l'itération courante:
		for(int i=0; i<nbIt; i++)
		{
			/*System.out.println("v0: "+i+": "+v0);
			System.out.println("v1: "+i+": "+v1);
			System.out.println("|v0-v1|= "+(calNorm.norm2(v0)-calNorm.norm2(v1)));*/
			v0.assign(v1);			//v0=v1
			//v1=adj.zMult(v1, v1);	//v1= A*v1
			v1=calNorm.mult(adj, v1);	//v1=A*v1
			lambda=calNorm.norm2(v1);
			//System.out.println("lambda: " + lambda);
			v1=v1.assign(Functions.mult(1/lambda));
			//System.out.println("v1/LAMBDA: "+i+": "+v1);
			//i++;
			Benchmark.addIteration();
		}
		values=v0.toArray();
		return values;
	}
}
