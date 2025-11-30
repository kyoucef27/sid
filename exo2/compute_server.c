#include "compute.h"
#include <stdio.h>
#include <stdlib.h>

/* Addition implementation */
int *add_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    result = argp->a + argp->b;
    printf("Server: ADD(%d, %d) = %d\n", argp->a, argp->b, result);
    
    return &result;
}

/* Subtraction implementation */
int *sub_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    result = argp->a - argp->b;
    printf("Server: SUB(%d, %d) = %d\n", argp->a, argp->b, result);
    
    return &result;
}

/* Multiplication implementation */
int *mul_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    result = argp->a * argp->b;
    printf("Server: MUL(%d, %d) = %d\n", argp->a, argp->b, result);
    
    return &result;
}

/* Division implementation with zero check */
int *div_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    if (argp->b == 0) {
        printf("Server: DIV(%d, %d) - Error: Division by zero!\n", argp->a, argp->b);
        result = 0;  /* Return 0 for division by zero */
    } else {
        result = argp->a / argp->b;
        printf("Server: DIV(%d, %d) = %d\n", argp->a, argp->b, result);
    }
    
    return &result;
}
