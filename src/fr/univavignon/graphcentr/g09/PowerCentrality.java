import cern.colt.matrix.DoubleMatrix2D;

public class PowerCentrality {
	public double findAlpha(DoubleMatrix2D matrix) {
		double sum = 0;
		int rows = matrix.rows();
		int columns = matrix.columns();
		for (int row = rows; --row >= 0;)
			for (int column = columns; --column >= 0;)
				sum += matrix.get(row, column) * matrix.get(row, column);

		return Math.sqrt(rows) / sum;
	}

	public double[] processNormResult(DoubleMatrix2D matrix, float a, float b) {
		int rows = matrix.rows();
		double[] r = new double[rows];

		for (int row = rows; --row >= 0;)
			r[row] = matrix.get((int) a, (int) b);
		return r;
	}

	public double FindBiggestEigenValues() {
		EigenvalueDecomposition ev = new EigenvalueDecomposition(G);
		DoubleMatrix1D __ev__ = ev.getRealEigenvalues();// ?
		double max = __ev__.get(0);

		for (int i = 1; i < __ev__.size(); i++)
			max = __ev__.get(i) > max ? __ev__.get(i) : max;
		return max;
	}

	public boolean IsSymetric(DoubleMatrix2D matrix) {

		return false;// isSymmetric(matrix);
	}

	public DoubleMatrix2D getAdjacencyMatrix() {
		return null;// ?
	}

	public DoubleMatrix2D multiply(DoubleMatrix2D matrix, float x) {
		return matrix.assign(cern.jet.math.Functions.mult(x));
	}

	public DoubleMatrix2D multiply(DoubleMatrix2D a, DoubleMatrix2D b) {
		return a.zMult(a, b);
	}

	public PowerCentrality(DoubleMatrix2D G,DoubleMatrix2D I) {
		this.G = G;
		this.I =I;
	}

	private DoubleMatrix2D G;
	private DoubleMatrix2D I;

	public DoubleMatrix2D sub(DoubleMatrix2D a, DoubleMatrix2D b) {
		if (a.rows() == b.rows() && a.columns() == b.columns())
			for (int r = 0; r < a.rows(); r++)
				for (int c = 0; c < a.columns(); c++)
					a.set(r, c, a.get(r, c) - b.get(r, c));
		return a;
	}

	public DoubleMatrix2D ResulatNorme(DoubleMatrix2D m, float a) {
		int i=0;
		int N=I.rows();
		while(i<N) 
			m.set(i,N,m.get(i,N)*a);
		return m;
	}
	public DoubleMatrix2D _PowerCentrality(float b) throws Exception {
		DoubleMatrix2D r = null;
		if (b > 1 || b < -1)
			throw new Exception("beta doit etre entre -1 et 1");
		if (Math.abs(b) < 1 / FindBiggestEigenValues()) {
			if ((b < 0 && IsSymetric(G)) || b > 0) {
				DoubleMatrix2D A = 	this.getAdjacencyMatrix();
				DoubleMatrix2D D = this.multiply(A, b);
				D = this.sub(null, D);
				if(!isSingular(D)) {
					A = this.multiply(A, I);
					D = this.multiply(D, A);
					double a = this.findAlpha(D);
					return ResulatNorme(D,a);
				}else throw new Exception("matrice non inversible");
					
			}else throw new Exception("La matrice est asymétrique, le beta ne peut pas etre négatif dans ce cas-là");
		}
	
	}

}
