package epfl.sweng.matrix.test;

import epfl.sweng.matrix.SquareMatrix;
import epfl.sweng.matrix.SymmetricSquareMatrix;

public class SymmetricSquareMatrixTest extends MatrixTest{
	protected SquareMatrix getMatrix(double[][] values) {
		return new SymmetricSquareMatrix(values);
	}
}
