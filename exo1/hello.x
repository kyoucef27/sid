/* RPC Interface Definition for Hello Service */

program HELLO_PROG {
    version HELLO_VERS {
        string HELLO(void) = 1;  /* procedure number 1 */
    } = 1;  /* version number */
} = 0x20000001;  /* program number */
