import MatrixOpsApp.*;

public class MatrixOpsPOAImpl extends MatrixOpsPOA {
    
    public MatrixOpsPOAImpl() {
    }
    
    // Validate matrix structure
    private void validateMatrix(double[][] matrix) throws InvalidMatrix {
        if (matrix == null || matrix.length == 0) {
            throw new InvalidMatrix("Matrix is null or empty.");
        }
        
        int cols = matrix[0].length;
        if (cols == 0) {
            throw new InvalidMatrix("Matrix has no columns.");
        }
        
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != cols) {
                throw new InvalidMatrix("Matrix rows have different lengths.");
            }
        }
    }
    
    @Override
    public double[][] add(double[][] m1, double[][] m2) 
            throws IncompatibleDimensions, InvalidMatrix {
        
        validateMatrix(m1);
        validateMatrix(m2);
        
        int rows1 = m1.length;
        int cols1 = m1[0].length;
        int rows2 = m2.length;
        int cols2 = m2[0].length;
        
        if (rows1 != rows2 || cols1 != cols2) {
            throw new IncompatibleDimensions(
                "Matrices must have the same dimensions for addition. " +
                "M1: " + rows1 + "x" + cols1 + ", M2: " + rows2 + "x" + cols2
            );
        }
        
        double[][] result = new double[rows1][cols1];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        
        System.out.println("Matrix addition completed: " + rows1 + "x" + cols1);
        return result;
    }
    
    @Override
    public double[][] multiply(double[][] m1, double[][] m2) 
            throws IncompatibleDimensions, InvalidMatrix {
        
        validateMatrix(m1);
        validateMatrix(m2);
        
        int rows1 = m1.length;
        int cols1 = m1[0].length;
        int rows2 = m2.length;
        int cols2 = m2[0].length;
        
        if (cols1 != rows2) {
            throw new IncompatibleDimensions(
                "Number of columns in M1 must equal number of rows in M2. " +
                "M1: " + rows1 + "x" + cols1 + ", M2: " + rows2 + "x" + cols2
            );
        }
        
        double[][] result = new double[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        
        System.out.println("Matrix multiplication completed: " + rows1 + "x" + cols2);
        return result;
    }
    
    @Override
    public double[][] transpose(double[][] m) throws InvalidMatrix {
        validateMatrix(m);
        
        int rows = m.length;
        int cols = m[0].length;
        
        double[][] result = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = m[i][j];
            }
        }
        
        System.out.println("Matrix transpose completed: " + rows + "x" + cols + " -> " + cols + "x" + rows);
        return result;
    }
    
    @Override
    public double trace(double[][] m) throws InvalidMatrix {
        validateMatrix(m);
        
        int rows = m.length;
        int cols = m[0].length;
        
        if (rows != cols) {
            throw new InvalidMatrix(
                "Matrix must be square to calculate trace. Current: " + rows + "x" + cols
            );
        }
        
        double sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += m[i][i];
        }
        
        System.out.println("Matrix trace calculated: " + sum);
        return sum;
    }
}
