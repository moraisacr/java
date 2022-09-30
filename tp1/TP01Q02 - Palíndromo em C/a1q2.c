#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <locale.h>

char *strrev(char *str){
      char *p1, *p2;

      if (! str || ! *str)
            return str;
      for (p1 = str, p2 = str + strlen(str) - 1; p2 > p1; ++p1, --p2)
      {
            *p1 ^= *p2;
            *p2 ^= *p1;
            *p1 ^= *p2;
      }
      return str;
}
int main(){
    setlocale(LC_ALL, "Portuguese_Brasil");
    char linhai[1000];
    int i=1;
    int resp = 1;
    while(i!=0){
        char linha[1000];
        fgets(linha, 999, stdin);/r
        linha[strcspn(linha, "\n")] = 0;
        if(linha[0]=='F' && linha[1]=='I' && linha[2]=='M'){
            i=0;
        } else {
            strcpy(linhai,linha);
            strrev(linhai);
            resp = strcmp(linha, linhai);
            if(resp==0){
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }
    return 0;
}