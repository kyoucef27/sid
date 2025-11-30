#include "hello.h"
#include <stdio.h>
#include <stdlib.h>

/* Implementation of the HELLO procedure */
char **hello_1_svc(void *argp, struct svc_req *rqstp)
{
    static char *result;
    
    /* Static message to return */
    result = "Hello from RPC Server!";
    
    printf("Server: Received HELLO request\n");
    
    return &result;
}
