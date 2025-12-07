# Exercise 4: CORBA Matrix Operations Service

## Description
A distributed matrix operations service using CORBA that provides add, multiply, transpose, and trace operations on matrices remotely.

## Files
- `MatrixOps.idl`: IDL definition for Matrix type and matrix operations
- `MatrixOpsPOAImpl.java`: Servant implementation with matrix algorithms
- `ServerPOA.java`: CORBA server
- `Client.java`: Interactive client for matrix operations

## Compilation Steps

### 1. Generate Java classes from IDL
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\idlj.exe" -fall MatrixOps.idl
```

### 2. Compile all Java files
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\javac.exe" MatrixOpsApp\*.java *.java
```

## Running the Application

### 1. Start the naming service (in separate terminal)
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\orbd.exe" -ORBInitialPort 1050 -ORBInitialHost localhost
```

### 2. Start the server (in separate terminal)
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\java.exe" ServerPOA -ORBInitialPort 1050 -ORBInitialHost localhost
```

### 3. Run the client
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\java.exe" Client -ORBInitialPort 1050 -ORBInitialHost localhost
```

## Features

### Matrix Operations
- **Add**: Add two matrices of the same dimensions
- **Multiply**: Multiply two compatible matrices (M1 cols = M2 rows)
- **Transpose**: Transpose a matrix (swap rows and columns)
- **Trace**: Calculate the trace (sum of diagonal elements) of a square matrix

### Interactive Client
- Manual matrix input for all operations
- Automated test cases demonstrating all features
- Comprehensive error handling
- Formatted matrix display

### Error Handling
- `IncompatibleDimensions`: For operations requiring specific dimension relationships
- `InvalidMatrix`: For malformed matrices or operations requiring square matrices
- Validation of matrix structure and dimensions

## Test Cases
The client includes pre-built test cases demonstrating:
1. Matrix addition (2x3 + 2x3)
2. Matrix multiplication (2x2 × 2x2)
3. Matrix transpose (2x3 → 3x2)
4. Matrix trace calculation (3x3 square matrix)
5. Error handling for incompatible dimensions
6. Error handling for trace on non-square matrix
