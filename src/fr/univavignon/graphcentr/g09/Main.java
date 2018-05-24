
public class Main {
	public static void main(String r[]) {
		DenseDoubleMatrix2D mat = new DenseDoubleMatrix2D(3, 4);
		mat.set(0, 0, 1.2);
		mat.set(1, 1, 1.2);
		System.out.println(PowerCentrality.findAlpha(mat,5));
	}
}
