#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <boost/program_options.hpp>

namespace po = boost::program_options;

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

void hPrintArray( long size, int a[] )
{
  for( int i = 0; i< size; i++ )
    cout << a[i] << "; ";
  cout << endl;
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
  int left = from;
  int right = to -1;
  int pivot = a[to];

  while( left < right )
  {
    while( a[left] <= pivot && left < to )
      left++;

    while( a[right] > pivot && right > from )
      right--;

    // Vertausche die Werte nur dann, wenn sich rechts und links
    // noch nicht ueberschnitten haben!
    if( left < right )
      swap( size, a, left, right );
  }

  // bringe den Pivot an die richtige Position
  if( a[left] > pivot )
    swap( size, a, left, to );

  return left;
}

/**
 *
 */
void quicksort( long size, int a[], int from, int to )
{
  if( from >= to )
    return;

  int pos = partition( size, a, from, to );
  // The element at pos is already at the right position for sure
  // so we don't need to include that in any further sorting.
  quicksort( size, a, from, pos-1 );
  quicksort( size, a, pos+1, to );
}

void quicksort( long size, int a[] )
{
  quicksort( size, a, 0, size-1);
}

int main( int ac, char* av[] )
{
  long size;
  long seed;
  string algorithm;
  bool verbose = false;
  int searchedValue;

  po::options_description clio("Command line options");
  clio.add_options()
    ("help,h", "Print help message")
    ("verbose,v", "Run in verbose mode")
    ("array-size", po::value<long>(&size)->default_value(1000), "Size of the array")
    ("algorithm,a", po::value<string>(&algorithm)->default_value("quicksort"), "Chose the algoirthm for sorting")
    ("seed,s", po::value<long>(&seed)->default_value(0), "Chose a seed for the SRNG")
    ("insecure", "Do not check if the array was sorted correcly")
    ("searched-value", po::value<int>(&searchedValue)->default_value(-1), "Value we're looking for in the array")
    ;

  po::variables_map vm;
  po::store(po::parse_command_line(ac, av, clio), vm);
  po::notify(vm);

  if( vm.count("help") )
  {
    cout << clio << endl;
    return 0;
  }

  verbose = vm.count("verbose");
  if( verbose )
    cout << "running in verbose mode" << endl;

  if( seed == 0 )
  {
    time_t timeObj = time( NULL );
    seed = time( &timeObj );
    if( verbose )
      cout << "Seed, retrieved from current time: " << seed << endl;
  }
  else
  {
    if(  verbose )
      cout << "seed has been defined by the user to be: " << seed << endl;
  }
  srand( seed );

  if( verbose )
    cout << "The size of the array will be: " << size << endl;

  int a [size];

  // Filling the array
  for( int i =0; i<size; i++ )
    a[i] = rand() % size + 1;

  if( algorithm == "quicksort" )
    quicksort(size, a);
  else if ( algorithm == "bubblesort" )
    bubbleSort(size, a);
  else 
  {
    cerr << "Sorry, unknown sorting algorithm \"" << algorithm << "\"" << endl;
    return 1;
  }

  if( verbose )
    cout << "Algorithm used for sorting: " << algorithm << endl;

  if( verbose )
    printArray( size, a );

  if(! vm.count("insecure") )
  {
    if( ! sortCheck( size, a ) )
    {
      cout << "WARNING: Array was not sorted correctly! Aborting now!" << endl;
      return 1;
    }
    else
      cout << "The array has been sorted correctly." << endl;
  }
  else if( verbose )
    cout << "WARNING: Not checking if the array was sorted correctly!" << endl;

  if( searchedValue >= 0 )
  {
    if( verbose )
      cout << "Looking for the value " << searchedValue << endl;

    cout << find( size, a, 42 ) << endl;
  }
  else
  {
    if( verbose )
      cout << "Not looking for any value" << endl;
  }

  return 0;
}
