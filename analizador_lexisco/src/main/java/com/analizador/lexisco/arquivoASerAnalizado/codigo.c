#include <stdio.h>

int main() {
    float x = 5.4; 
    float y = 10;
    float z;

    z = x + y;

    if (z > 10) {
        printf("O resultado é maior que 10\n");
    } else {
        printf("O resultado é 10 ou menor\n");
    }

    return 0;
}
