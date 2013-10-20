package epfl.sweng.matrix.test;

import static junit.framework.Assert.*;

import org.junit.Test;

import epfl.sweng.matrix.SquareMatrix;

public class MatrixTest {

	protected SquareMatrix getMatrix(double[][] values) {
		return new SquareMatrix(values);
	}

	@Test
	public void determinant() {
		SquareMatrix matrix = getMatrix(new double[][] { { -2, 2, 3 },
				{ -1, 1, 3 }, { 2, 0, -1 } });
		double det = matrix.det();
		assertEquals(6.0, det, 6.0 / 100);
	}

	@Test
	public void determinantIsZero() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 0 }, { 10, 0 } });
		double det = matrix.det();
		assertEquals(0, det, 0);
	}

	@Test
	public void inverse() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 2 }, { 3, 4 } });

		SquareMatrix inverse = matrix.inverse();

		assertEquals(-2, inverse.get(0, 0), 0.01);
		assertEquals(1, inverse.get(0, 1), 0.01);
		assertEquals(1.5, inverse.get(1, 0), 0.01);
		assertEquals(-0.5, inverse.get(1, 1), 0.01);
	}

	@Test
	public void get() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
		assertEquals(1, matrix.get(0, 0), 0.01);
	}

	@Test
	public void set() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
		matrix.set(1, 1, 5);
		assertEquals(5, matrix.get(1, 1), 0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void notSquare() {
		getMatrix(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public void notSquareBug() {
		// is supposed to fail
		getMatrix(new double[][] { { 1, 2 }, { 3, 4, 5 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public void inverseOfNonInversible() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 0 }, { 10, 0 } });
		matrix.inverse();
	}

	@Test(expected = IllegalArgumentException.class)
	public void wrongGet() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 0 }, { 10, 0 } });
		matrix.get(10, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void wrongSet() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 0 }, { 10, 0 } });
		matrix.set(0, 10, -1);
	}

	@Test
	public void inverseIsCorrectInverseSecond() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
		SquareMatrix identity = getMatrix(new double[][] { { 1, 0 }, { 0, 1 } });
		SquareMatrix inverse = matrix.inverse();
		SquareMatrix multiplication = matrix.muliplyWith(inverse);
		assertEquals(identity, multiplication);
	}

	@Test
	public void inverseIsCorrectInverseFirst() {
		SquareMatrix matrix = getMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
		SquareMatrix identity = getMatrix(new double[][] { { 1, 0 }, { 0, 1 } });
		SquareMatrix inverse = matrix.inverse();
		SquareMatrix multiplication = inverse.muliplyWith(matrix);
		assertEquals(identity, multiplication);
	}
}
