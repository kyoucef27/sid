#include "compute.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    CLIENT *clnt;
    int *result;
    operands ops;
    char *server;
    int operation;

    if (argc < 2) {
        printf("Usage: %s server_host\n", argv[0]);
        exit(1);
    }

    server = argv[1];

    /* Create client handle */
    clnt = clnt_create(server, COMP_PROG, COMP_VERS, "tcp");
    if (clnt == NULL) {
        clnt_pcreateerror(server);
        exit(1);
    }

    /* Request two integers from user */
    printf("Enter first integer: ");
    scanf("%d", &ops.a);
    
    printf("Enter second integer: ");
    scanf("%d", &ops.b);

    /* Request operation */
    printf("\nSelect operation:\n");
    printf("1. Addition (+)\n");
    printf("2. Subtraction (-)\n");
    printf("3. Multiplication (*)\n");
    printf("4. Division (/)\n");
    printf("Enter your choice (1-4): ");
    scanf("%d", &operation);

    /* Call appropriate remote procedure */
    switch(operation) {
        case 1:
            result = add_1(&ops, clnt);
            if (result == NULL) {
                clnt_perror(clnt, "ADD call failed");
                exit(1);
            }
            printf("\nResult: %d + %d = %d\n", ops.a, ops.b, *result);
            break;
            
        case 2:
            result = sub_1(&ops, clnt);
            if (result == NULL) {
                clnt_perror(clnt, "SUB call failed");
                exit(1);
            }
            printf("\nResult: %d - %d = %d\n", ops.a, ops.b, *result);
            break;
            
        case 3:
            result = mul_1(&ops, clnt);
            if (result == NULL) {
                clnt_perror(clnt, "MUL call failed");
                exit(1);
            }
            printf("\nResult: %d * %d = %d\n", ops.a, ops.b, *result);
            break;
            
        case 4:
            if (ops.b == 0) {
                printf("\nError: Division by zero is not allowed!\n");
            }
            result = div_1(&ops, clnt);
            if (result == NULL) {
                clnt_perror(clnt, "DIV call failed");
                exit(1);
            }
            if (ops.b != 0) {
                printf("\nResult: %d / %d = %d\n", ops.a, ops.b, *result);
            }
            break;
            
        default:
            printf("\nInvalid operation selected!\n");
            break;
    }

    /* Clean up */
    clnt_destroy(clnt);
    return 0;
}
