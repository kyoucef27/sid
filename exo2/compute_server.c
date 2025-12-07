#include "compute.h"
#include <stdio.h>
#include <stdlib.h>


int *add_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    result = argp->a + argp->b;
    printf("Server: ADD(%d, %d) = %d\n", argp->a, argp->b, result);
    
    return &result;
}

int *sub_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    result = argp->a - argp->b;
    printf("Server: SUB(%d, %d) = %d\n", argp->a, argp->b, result);
    
    return &result;
}


int *mul_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    result = argp->a * argp->b;
    printf("Server: MUL(%d, %d) = %d\n", argp->a, argp->b, result);
    
    return &result;
}

int *div_1_svc(operands *argp, struct svc_req *rqstp)
{
    static int result;
    
    if (argp->b == 0) {
-        printf("S-.....................................................................................................................................................................................................................................................................................................................................................................................................................................................-ö-----------e-r-ver::;
    } else {
        result = argp->a / argp->b;
        printf("Server: DIV(%d, %d) = %d\n", argp->a, argp->b, result);
    }
    
    return &result;
}
