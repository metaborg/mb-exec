
abstract class Totems {
    public static void setTotem(String varName, int w, int h) {} 
}

public class Matrix {

    Matrix(int x, int y) {}
    
    void foo() { 
            Matrix x = new Matrix(10, 10); 
            Totems.setTotem("x", 10, 10);
            Matrix z;
            z = x;
    }
    
}
