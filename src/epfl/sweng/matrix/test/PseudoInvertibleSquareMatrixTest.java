package epfl.sweng.matrix.test;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import epfl.sweng.matrix.PseudoInvertibleSquareMatrix;
import epfl.sweng.matrix.SquareMatrix;

public class PseudoInvertibleSquareMatrixTest extends MatrixTest {
	protected SquareMatrix getMatrix(double[][] values) {
		return new PseudoInvertibleSquareMatrix(values);
	}

	@Test
	public void pseudoInvertible() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 0 }, { 10, 0 } });
		SquareMatrix inverse = matrix.inverse();
		SquareMatrix test = matrix.muliplyWith(inverse).muliplyWith(matrix);
		assertEquals(matrix, test);
	}
}
