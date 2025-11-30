#include "hello.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    CLIENT *clnt;
    char **result;
    char *server;

    if (argc < 2) {
        printf("Usage: %s server_host\n", argv[0]);
        exit(1);
    }

    server = argv[1];

    /* Create client handle */
    clnt = clnt_create(server, HELLO_PROG, HELLO_VERS, "tcp");
    if (clnt == NULL) {
        clnt_pcreateerror(server);
        exit(1);
    }

    /* Call remote procedure */
    result = hello_1(NULL, clnt);
    if (result == NULL) {
        clnt_perror(clnt, "Call failed");
        exit(1);
    }

    printf("Client: Received message: %s\n", *result);

    /* Clean up */
    clnt_destroy(clnt);
    return 0;
}
