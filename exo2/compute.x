/* RPC Interface Definition for Computation Service */

/* Structure containing two integers */
struct operands {
    int a;
    int b;
};

/* Program definition */
program COMP_PROG {
    version COMP_VERS {
        int ADD(operands) = 1;   /* Addition */
        int SUB(operands) = 2;   /* Subtraction */
        int MUL(operands) = 3;   /* Multiplication */
        int DIV(operands) = 4;   /* Division */
    } = 1;  /* version number */
} = 0x20000002;  /* program number */
