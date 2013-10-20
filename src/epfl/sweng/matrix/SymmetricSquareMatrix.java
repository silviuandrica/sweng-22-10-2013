package epfl.sweng.matrix;

public class SymmetricSquareMatrix extends SquareMatrix {

	public SymmetricSquareMatrix(double[][] values) {
		super(values);
	}

	public void set(int row, int column, double value) {
		super.set(row, column, value);
		super.set(column, row, value);
	}

}
