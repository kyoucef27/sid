import MatrixOpsApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class Client {
    static MatrixOps matrixOpsImpl;
    
    public static void main(String args[]) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // Resolve the Object Reference in Naming
            String name = "MatrixOps";
            matrixOpsImpl = MatrixOpsHelper.narrow(ncRef.resolve_str(name));
            
            System.out.println("Obtained a handle on server object: " + matrixOpsImpl);
            System.out.println("===== CORBA Matrix Operations Client =====\n");
            
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            
            while (running) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Matrices");
                System.out.println("2. Multiply Matrices");
                System.out.println("3. Transpose Matrix");
                System.out.println("4. Calculate Trace");
                System.out.println("5. Run Test Cases");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        addMatrices(scanner);
                        break;
                    case 2:
                        multiplyMatrices(scanner);
                        break;
                    case 3:
                        transposeMatrix(scanner);
                        break;
                    case 4:
                        calculateTrace(scanner);
                        break;
                    case 5:
                        runTestCases();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            
            scanner.close();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
    
    private static double[][] readMatrix(Scanner scanner) {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        
        double[][] matrix = new double[rows][cols];
        System.out.println("Enter matrix elements row by row:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        
        return matrix;
    }
    
    private static void printMatrix(double[][] matrix) {
        System.out.println("\nResult Matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    private static void addMatrices(Scanner scanner) {
        try {
            System.out.println("\n--- Matrix 1 ---");
            double[][] m1 = readMatrix(scanner);
            System.out.println("\n--- Matrix 2 ---");
            double[][] m2 = readMatrix(scanner);
            
            double[][] result = matrixOpsImpl.add(m1, m2);
            printMatrix(result);
        } catch (IncompatibleDimensions e) {
            System.out.println("Error: " + e.reason);
        } catch (InvalidMatrix e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void multiplyMatrices(Scanner scanner) {
        try {
            System.out.println("\n--- Matrix 1 ---");
            double[][] m1 = readMatrix(scanner);
            System.out.println("\n--- Matrix 2 ---");
            double[][] m2 = readMatrix(scanner);
            
            double[][] result = matrixOpsImpl.multiply(m1, m2);
            printMatrix(result);
        } catch (IncompatibleDimensions e) {
            System.out.println("Error: " + e.reason);
        } catch (InvalidMatrix e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void transposeMatrix(Scanner scanner) {
        try {
            System.out.println("\n--- Matrix ---");
            double[][] m = readMatrix(scanner);
            
            double[][] result = matrixOpsImpl.transpose(m);
            printMatrix(result);
        } catch (InvalidMatrix e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void calculateTrace(Scanner scanner) {
        try {
            System.out.println("\n--- Matrix (must be square) ---");
            double[][] m = readMatrix(scanner);
            
            double trace = matrixOpsImpl.trace(m);
            System.out.println("\nTrace: " + trace);
        } catch (InvalidMatrix e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void runTestCases() {
        System.out.println("\n===== Running Test Cases =====\n");
        
        // Test 1: Addition
        try {
            System.out.println("Test 1: Matrix Addition");
            double[][] m1 = {{1, 2, 3}, {4, 5, 6}};
            double[][] m2 = {{7, 8, 9}, {10, 11, 12}};
            System.out.println("M1: 2x3 matrix");
            System.out.println("M2: 2x3 matrix");
            double[][] result = matrixOpsImpl.add(m1, m2);
            printMatrix(result);
        } catch (Exception e) {
            System.out.println("Test 1 failed: " + e.getMessage());
        }
        
        // Test 2: Multiplication
        try {
            System.out.println("\nTest 2: Matrix Multiplication");
            double[][] m1 = {{1, 2}, {3, 4}};
            double[][] m2 = {{5, 6}, {7, 8}};
            System.out.println("M1: 2x2 matrix");
            System.out.println("M2: 2x2 matrix");
            double[][] result = matrixOpsImpl.multiply(m1, m2);
            printMatrix(result);
        } catch (Exception e) {
            System.out.println("Test 2 failed: " + e.getMessage());
        }
        
        // Test 3: Transpose
        try {
            System.out.println("\nTest 3: Matrix Transpose");
            double[][] m = {{1, 2, 3}, {4, 5, 6}};
            System.out.println("M: 2x3 matrix");
            double[][] result = matrixOpsImpl.transpose(m);
            printMatrix(result);
        } catch (Exception e) {
            System.out.println("Test 3 failed: " + e.getMessage());
        }
        
        // Test 4: Trace
        try {
            System.out.println("\nTest 4: Matrix Trace");
            double[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            System.out.println("M: 3x3 matrix");
            System.out.println("Matrix:");
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    System.out.printf("%8.2f ", m[i][j]);
                }
                System.out.println();
            }
            double trace = matrixOpsImpl.trace(m);
            System.out.println("Trace: " + trace + " (should be 1+5+9=15)");
        } catch (Exception e) {
            System.out.println("Test 4 failed: " + e.getMessage());
        }
        
        // Test 5: Error handling - incompatible dimensions for addition
        try {
            System.out.println("\nTest 5: Error Handling - Addition with incompatible dimensions");
            double[][] m1 = {{1, 2}, {3, 4}};
            double[][] m2 = {{5, 6, 7}};
            System.out.println("M1: 2x2 matrix");
            System.out.println("M2: 1x3 matrix");
            double[][] result = matrixOpsImpl.add(m1, m2);
            printMatrix(result);
        } catch (Exception e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
        
        // Test 6: Error handling - trace on non-square matrix
        try {
            System.out.println("\nTest 6: Error Handling - Trace on non-square matrix");
            double[][] m = {{1, 2, 3}, {4, 5, 6}};
            System.out.println("M: 2x3 matrix (non-square)");
            double trace = matrixOpsImpl.trace(m);
            System.out.println("Trace: " + trace);
        } catch (Exception e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
        
        System.out.println("\n===== Test Cases Completed =====");
    }
}
