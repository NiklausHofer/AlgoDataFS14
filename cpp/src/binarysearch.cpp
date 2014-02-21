#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */

using namespace std;

bool sortCheck( long size, int a[] )
{
  for( int i=0; i < size-1; i++ )
  {
    if( a[i] > a[i+1] )
      return false;
  }
  return true;
}

void swap( long size, int a[], int i, int k )
{
  if( i >= size && k >= size )
    return;

  int tmp = a[i];
  a[i] = a[k];
  a[k] = tmp;
  //cnt++;
}

void bubbleSort( long size, int a[] )
{
   //cnt = 0;
   int m = size-1;
   for( int i = m; i > 0; i-- )
   {
     for( int k=0; k < i; k++ )
     {
       if( a[k] > a[k+1] )
         swap( size, a, k, k+1 );
     }
   }
}

void printArray( long size, int a[] )
{
  // You can find the position of you number on the command line
  // like this (number 42 in the example):
  //  ./binarysearch | nl -v 0 - | grep -P '\s*[0-9]+\s+42$' | cut -f 1
  for( int i = 0; i< size; i++ )
    cout << a[i] << endl;
}

int find( long size, int a[], int search )
{
  int retval = -1;
  int from=0;
  int to=size-1;
  int med=((from+to)/2);

  int comp;
  while( to >= from )
  {
    comp = a[med];
    if( comp == search )
      return med;
    else if( comp < search )
    {
      from = med+1;
      med = (from+to)/2;
    }
    else
    {
     to=med-1;
     med = (from+to)/2;
    }
  }

  return retval;
}

/**
 * returns the partition 'pos' at the pivot.
 * After that, a[from .... pos-1] are all 
 * < pivot. Also, a[pos+1 ... to] are all >= pivot
 */
int partition( long size, int a[], int from, int to )
{
}

/**
 *
 */
void quicksort( long size, int a[], int from, int to )
{
  if( from >= to )
    return;

  int pos = partition( size, a, from, to );
  quicksort( size, a, from, pos-1 );
  quicksort( size, a, pos+1, to );
}

void quicksort( long size, int a[] )
{
  quicksort( size, a, 0, size-1);
}

int main( int ac, char* av[] )
{
  long size = 100;

  srand(52);

  int a [size];

  for( int i =0; i<size; i++ )
    a[i] = rand() % size + 1;

  //bubbleSort(size, a);
  quicksort(size, a);

  printArray( size, a );

  cout << sortCheck( size, a ) << endl;

  //cout << find( size, a, 42 ) << endl;

  return 0;
}
