//client program

#include <stdio.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define PORT 9000
#define IPADDR "203.252.22.161"

main(){
    int c_socket;
    struct sockaddr_in c_addr;
    int len;
    int n;

    char rcvBuffer[BUFSIZ];
    char Buffer[100];
   


    scanf("%s",&Buffer);
    c_socket=socket(PF_INET,SOCK_STREAM,0);

    memset(&c_addr,0,sizeof(c_addr));
    c_addr.sin_addr.s_addr = inet_addr(IPADDR);
    c_addr.sin_family=AF_INET;
    c_addr.sin_port=htons(PORT);

    if(connect(c_socket,(struct sockaddr *) &c_addr, sizeof(c_addr))==-1){
        printf("Can not connect \n");
        close (c_socket);
        return -1;
    }
    n=strlen(Buffer);
    write(c_socket, Buffer,n);
    if((n=read(c_socket,rcvBuffer,sizeof(rcvBuffer)))<0){
        return(-1);
    }

    rcvBuffer[n] ='\0';
    printf("received Data : %s \n",rcvBuffer);

    close(c_socket);

}  
