# Exercise 2: RPC Computation Server (C)

## Overview
This exercise implements a complete RPC-based computation server that performs arithmetic operations (addition, subtraction, multiplication, and division) on two integers.

## Files Structure

- `compute.x` - RPC interface definition
- `compute_server.c` - Server implementation with arithmetic operations
- `compute_client.c` - Interactive client application
- `Makefile` - Build automation

## Implementation Details

### 1. RPC Interface (compute.x)

Defines:
- **Structure**: `operands` containing two integers (a, b)
- **Program**: `COMP_PROG` (0x20000002)
- **Version**: `COMP_VERS` (1)
- **Procedures**:
  - `ADD(operands)` - Returns sum
  - `SUB(operands)` - Returns difference
  - `MUL(operands)` - Returns product
  - `DIV(operands)` - Returns quotient (with zero check)

### 2. Server Implementation

Each operation:
- Receives an `operands` structure
- Performs the computation
- Logs the operation to console
- Returns the result

**Division Safety**: Checks for division by zero and returns 0 with an error message.

### 3. Client Implementation

Interactive client that:
1. Prompts user for two integers
2. Displays operation menu (1-4)
3. Sends request to RPC server
4. Displays the computed result

## Compilation and Execution

### Step 1: Generate RPC Code
```bash
cd exo2
rpcgen compute.x
```

This generates:
- `compute.h`
- `compute_clnt.c`
- `compute_svc.c`
- `compute_xdr.c`

### Step 2: Compile
```bash
make
```

Or compile manually:
```bash
gcc -Wall -I/usr/include/tirpc -o compute_server compute_server.c compute_svc.c -ltirpc
gcc -Wall -I/usr/include/tirpc -o compute_client compute_client.c compute_clnt.c -ltirpc
```

### Step 3: Start rpcbind (if needed)
```bash
sudo service rpcbind start
```

### Step 4: Run Server (Terminal 1)
```bash
./compute_server
```

### Step 5: Run Client (Terminal 2)
```bash
./compute_client localhost
```

## Example Usage

```
Enter first integer: 15
Enter second integer: 3

Select operation:
1. Addition (+)
2. Subtraction (-)
3. Multiplication (*)
4. Division (/)
Enter your choice (1-4): 4

Result: 15 / 3 = 5
```

### Division by Zero Test
```
Enter first integer: 10
Enter second integer: 0

Select operation:
1. Addition (+)
2. Subtraction (-)
3. Multiplication (*)
4. Division (/)
Enter your choice (1-4): 4

Error: Division by zero is not allowed!
```

## Expected Output

**Server Terminal:**
```
Server: ADD(10, 5) = 15
Server: SUB(10, 5) = 5
Server: MUL(10, 5) = 50
Server: DIV(10, 5) = 2
Server: DIV(10, 0) - Error: Division by zero!
```

**Client Terminal:**
Displays the operation and result after each computation.

## Testing Checklist

- [x] Addition works correctly
- [x] Subtraction works correctly
- [x] Multiplication works correctly
- [x] Division works correctly
- [x] Division by zero is handled safely
- [x] Invalid operation choice is handled
- [x] Server logs all operations

## Clean Up

```bash
# Remove executables only
make clean-bin

# Remove all generated files
make clean
```

## Notes

- Server must be running before starting client
- Use `Ctrl+C` to stop the server
- Each client connection handles one computation
- Run multiple clients to test concurrent operations
