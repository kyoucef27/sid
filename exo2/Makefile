# Makefile for RPC Computation Service

# Compiler
CC = gcc

# Compiler flags
CFLAGS = -Wall -I/usr/include/tirpc

# Libraries
LIBS = -ltirpc

# Targets
all: compute_server compute_client

# Generate RPC files from compute.x
rpcgen: compute.x
	rpcgen compute.x

# Build server
compute_server: compute_server.c compute_svc.c compute_xdr.c compute.h
	$(CC) $(CFLAGS) -o compute_server compute_server.c compute_svc.c compute_xdr.c $(LIBS)

# Build client
compute_client: compute_client.c compute_clnt.c compute_xdr.c compute.h
	$(CC) $(CFLAGS) -o compute_client compute_client.c compute_clnt.c compute_xdr.c $(LIBS)

# Clean generated files
clean:
	rm -f compute_server compute_client
	rm -f compute.h compute_clnt.c compute_svc.c compute_xdr.c
	rm -f *.o

# Clean only executables
clean-bin:
	rm -f compute_server compute_client

.PHONY: all rpcgen clean clean-bin
