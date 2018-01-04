#include <stdlib.h>
#include <stdio.h>

int main(int argc, char** argv) {
  
  // allocate an array of 100 integers
  int* array = (int*)malloc(100 * sizeof(int));
  
  for(int i=0; i<100; i++) {
    array[i] = i * i;
  }
  for(int i=0; i<100; i++) {
    printf("array[%d] = %d\n", i, array[i]);
  }

  // explicitly free the array
  free(array);
  
  // allocate a second array of 100 integers
  int* array2 = (int*)malloc(100 * sizeof(int));

  // no error for accessing the freed array
  array[3] = 120;

  // print the (uninitialized) values of array2
  for(int i=0; i<100; i++) {
    printf("array2[%d] = %d\n", i, array2[i]);
  }

  // print the (should have been freed) values of array1
  for(int i=0; i<100; i++) {
    printf("array[%d] = %d\n", i, array[i]);
  }
  
  return 0;
}
