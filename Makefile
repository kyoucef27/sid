# Makefile for RPC Hello Service

# Compiler
CC = gcc

# Compiler flags
CFLAGS = -Wall

# Libraries
LIBS = -lnsl

# For systems using tirpc instead of nsl
# CFLAGS = -Wall -I/usr/include/tirpc
# LIBS = -ltirpc

# Targets
all: hello_server hello_client

# Generate RPC files from hello.x
rpcgen: hello.x
	rpcgen hello.x

# Build server
hello_server: hello_server.c hello_svc.c hello_xdr.c hello.h
	$(CC) $(CFLAGS) -o hello_server hello_server.c hello_svc.c hello_xdr.c $(LIBS)

# Build client
hello_client: hello_client.c hello_clnt.c hello_xdr.c hello.h
	$(CC) $(CFLAGS) -o hello_client hello_client.c hello_clnt.c hello_xdr.c $(LIBS)

# Clean generated files
clean:
	rm -f hello_server hello_client
	rm -f hello.h hello_clnt.c hello_svc.c hello_xdr.c
	rm -f *.o

# Clean only executables
clean-bin:
	rm -f hello_server hello_client

.PHONY: all rpcgen clean clean-bin
