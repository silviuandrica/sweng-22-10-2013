package epfl.sweng.matrix;

import org.la4j.LinearAlgebra.DecompositorFactory;
import org.la4j.decomposition.MatrixDecompositor;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class PseudoInvertibleSquareMatrix extends SquareMatrix {

	public PseudoInvertibleSquareMatrix(double[][] values) {
		super(values);
	}

	protected PseudoInvertibleSquareMatrix(Matrix _matrix) {
		super(_matrix);
	}

	protected SquareMatrix getFromLA4JMatrix(Matrix matrix_) {
		return new PseudoInvertibleSquareMatrix(matrix_);
	}

	public SquareMatrix inverse() {
		if (det() != 0) {
			return super.inverse();
		}

		MatrixDecompositor decompositor = matrix().withDecompositor(
				DecompositorFactory.SVD);
		Matrix[] decomposition = decompositor.decompose();
		Matrix U = decomposition[0];
		Matrix S = decomposition[1];
		Matrix V_T = decomposition[2];
		Matrix V = V_T.transpose();

		Matrix S_I = new Basic2DMatrix(S);
		for (int i = 0; i < S_I.columns(); i++) {
			double diagValue = S_I.get(i, i);
			if (diagValue == 0) {
				break;
			}
			S_I.set(i, i, 1 / diagValue);
		}
		Matrix result = V.multiply(S_I).multiply(U.transpose());
		return new PseudoInvertibleSquareMatrix(result);
	}

}
