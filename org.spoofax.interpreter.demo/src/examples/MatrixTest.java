package examples;

import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.Matrix;

abstract class Totems {
    public static void setTotem(String varName, String totemName, Object ...args) {} 
}

public class MatrixTest {

    private static void testAdd() {
        Matrix a = new DenseMatrix(4,3);
        Matrix b = new DenseMatrix(4,3);
        b.add(a);
    }
    
    private static void testMult() {
        Matrix a = new DenseMatrix(4,3);
        Matrix b = new DenseMatrix(3,4);
        Matrix c = new DenseMatrix(3,3);
        b.mult(a, c);
    }

    private static void testMultFail() {
        Matrix a = new DenseMatrix(4,3);
        Matrix b = new DenseMatrix(3,4);
        Matrix c = new DenseMatrix(3,4); // must be 3x3
        b.mult(a, c);
    }

    public static void main(String[] args) {
        testAdd();
        testMult();
        try { testMultFail(); }
        catch(Exception e) { e.printStackTrace(); }
    }
    
}
