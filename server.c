//server program

#include <stdio.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define PORT 8080

char Buffer[BUFSIZ];

main(){
    int c_socket,s_socket;
    struct sockaddr_in s_addr,c_addr;
    int len;
    int n;

    s_socket=socket(PF_INET,SOCK_STREAM,0); //소켓을 생성
  
    memset(&s_addr,0,sizeof(s_addr));        // 연결요청을 수신할 주소 설정
    s_addr.sin_addr.s_addr=htonl(INADDR_ANY);
    s_addr.sin_family = AF_INET;
    s_addr.sin_port = htons(PORT);

    if(bind(s_socket, (struct sockaddr *) &s_addr, sizeof(s_addr))==-1){    //소켓을 포트에 연결
        printf("Can not Bind\n");
        return -1;
    }

    if(listen(s_socket,5)==-1){    //커널 (kernel)에 개통 요청 //최대 5명 까지.
        printf("listen Fail \n");
        return -1;
    }

    while(1){  

        len = sizeof(c_addr);    //클라이언트 연결 요청 수신
        c_socket = accept(s_socket,(struct sockaddr *)&c_addr , &len);
          //클라이언트 요청 서비스 제공

    if((n=read(c_socket,Buffer,sizeof(Buffer)))<0){
            return(-1);
        }
    //n=strlen(Buffer);
        write(c_socket, Buffer , n );
      
        close(c_socket); //클라이언트와 연결 종료

    }

    close(s_socket);    //서버종료
}
 
