# Exercise 1: Simple RPC Hello Service (C)

## 1. What does RPC mean?

**RPC** stands for **Remote Procedure Call**.

**Purpose in Distributed Information Systems:**
- Allows a program to execute a procedure (function) on a remote server as if it were a local call
- Abstracts network communication details from developers
- Enables client-server architectures where clients can invoke services on remote machines
- Facilitates distributed computing by allowing applications to be split across multiple systems
- Provides a mechanism for inter-process communication across different machines

## 2. RPC Application Architecture Diagram

```
┌─────────────────┐                    ┌─────────────────┐
│                 │                    │                 │
│  CLIENT APP     │                    │  SERVER APP     │
│                 │                    │                 │
│  ┌───────────┐  │                    │  ┌───────────┐  │
│  │ Client    │  │                    │  │ Server    │  │
│  │ Stub      │  │                    │  │ Stub      │  │
│  └─────┬─────┘  │                    │  └─────┬─────┘  │
│        │        │                    │        │        │
│  ┌─────▼─────┐  │                    │  ┌─────▼─────┐  │
│  │ RPC       │  │    Network Call    │  │ RPC       │  │
│  │ Runtime   │◄─┼────────────────────┼─►│ Runtime   │  │
│  └───────────┘  │                    │  └───────────┘  │
│                 │                    │                 │
└─────────────────┘                    └─────────────────┘
```

## 3. Implementation Steps

### Step 1: RPC Interface Definition (hello.x)
The `hello.x` file defines:
- Program: `HELLO_PROG` (0x20000001)
- Version: `HELLO_VERS` (1)
- Procedure: `HELLO()` returns a string

### Step 2: Generate RPC Code

```bash
rpcgen hello.x
```

This generates:
- `hello.h` - Header file with declarations
- `hello_clnt.c` - Client stub
- `hello_svc.c` - Server stub
- `hello_xdr.c` - XDR routines for data serialization

### Step 3: Server Implementation (hello_server.c)
Implements `hello_1_svc()` which returns "Hello from RPC Server!"

### Step 4: Client Implementation (hello_client.c)
Connects to the server and calls the remote HELLO procedure

## 4. Compilation and Execution

### On Linux/Unix:

#### Generate RPC files:
```bash
rpcgen hello.x
```

#### Compile the server:
```bash
gcc -o hello_server hello_server.c hello_svc.c hello_xdr.c -lnsl
```

#### Compile the client:
```bash
gcc -o hello_client hello_client.c hello_clnt.c hello_xdr.c -lnsl
```

#### Run the server (Terminal 1):
```bash
./hello_server
```

#### Run the client (Terminal 2):
```bash
./hello_client localhost
```

### Expected Output:

**Server terminal:**
```
Server: Received HELLO request
```

**Client terminal:**
```
Client: Received message: Hello from RPC Server!
```

## Notes

- The server must be running before starting the client
- Use `localhost` or the actual server hostname/IP address
- The `-lnsl` library is required for RPC functions on Linux
- On some systems, you may need additional libraries like `-ltirpc`
- Press Ctrl+C to stop the server

## Troubleshooting

If you encounter compilation errors:
- Ensure `rpcgen` is installed
- On modern Linux, you may need to install `libtirpc-dev` and use `-I/usr/include/tirpc -ltirpc` instead of `-lnsl`
- On Windows, consider using WSL (Windows Subsystem for Linux) or a Unix-like environment
"# sid" 
