package epfl.sweng.matrix;

import org.la4j.LinearAlgebra.InverterFactory;
import org.la4j.inversion.MatrixInverter;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class SquareMatrix {
	private Basic2DMatrix matrix;

	public SquareMatrix(double[][] values) {
		if (values.length != values[0].length) {
			throw new IllegalArgumentException(
					"#rows and #columns must be the same");
		}
		matrix = new Basic2DMatrix(values);
	}

	protected SquareMatrix(Matrix matrix_) {
		this.matrix = new Basic2DMatrix(matrix_);
	}

	protected SquareMatrix getFromLA4JMatrix(Matrix matrix_) {
		return new SquareMatrix(matrix_);
	}

	public double get(int row, int column) {
		try {
			return matrix.get(row, column);
		} catch (ArrayIndexOutOfBoundsException aiob) {
			throw new IllegalArgumentException(aiob.getMessage());
		}
	}

	public void set(int row, int column, double vlaue) {
		try {
			matrix.set(row, column, vlaue);
		} catch (ArrayIndexOutOfBoundsException aiob) {
			throw new IllegalArgumentException(aiob.getMessage());
		}
	}

	public double det() {
		return matrix.determinant();
	}

	public SquareMatrix inverse() {
		MatrixInverter INVERTER = matrix.withInverter(InverterFactory.SMART);
		Matrix inverse = INVERTER.inverse();
		Basic2DMatrix inverseBasic2D = new Basic2DMatrix(inverse);
		SquareMatrix result = getFromLA4JMatrix(inverseBasic2D);
		return result;
	}

	public double[][] values() {
		return matrix.toArray();
	}

	protected Basic2DMatrix matrix() {
		return matrix;
	}

	public SquareMatrix muliplyWith(SquareMatrix matrix) {
		Matrix multiplication = this.matrix.multiply(matrix.matrix);
		return getFromLA4JMatrix(multiplication);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matrix == null) ? 0 : matrix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SquareMatrix)) {
			return false;
		}
		SquareMatrix other = (SquareMatrix) obj;
		if (matrix == null) {
			if (other.matrix != null) {
				return false;
			}
		} else if (!matrix.equals(other.matrix)) {
			return false;
		}
		return true;
	}
}
