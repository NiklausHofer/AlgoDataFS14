#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <boost/program_options.hpp>

namespace po = boost::program_options;

using namespace std;

void printArray( long size, int a[] )
{
  // You can find the position of you number on the command line
  // like this (number 42 in the example):
  //  ./binarysearch | nl -v 0 - | grep -P '\s*[0-9]+\s+42$' | cut -f 1
  for( int i = 0; i< size; i++ )
    cout << a[i] << endl;
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
    while( a[left] >= pivot && left < to )
      left++;

    while( a[right] < pivot && right > from )
      right--;

    // Vertausche die Werte nur dann, wenn sich rechts und links
    // noch nicht ueberschnitten haben!
    if( left < right )
      swap( size, a, left, right );
  }

  // bringe den Pivot an die richtige Position
  if( a[left] < pivot )
    swap( size, a, left, to );

  if( to-from == 1 )
    return ++left;

  return left;
}

/*
 * Partition the array a[] up to the p-th position.
 * Each call will only partition from to
 */
void qSelect( long size, int a[], int from, int to, int p, int verbosity = 0 )
{
  if( verbosity )
  {
    cout << "qSelect called!" << endl;
    cout << "\t p: " << p << endl;
    cout << "\t from: " << from << endl;
    cout << "\t to: " << to << endl;
    for( int i = from; i<= to; i++ )
      cout << "\t\t" << i << "\t: " << a[i] << endl;
  }

  // Check validity of the call
  if ( to > size || from > size )
    return;

  // If the range to be taken care of is negative, we abort
  if( to <= from ) 
    return;

  // partition the entire current range
  int pos = partition( size, a, from, to );

  if( verbosity )
    cout << "\t pos: " << pos << endl;



   if( pos == p )
     return;
  /*
   *  partitioned   unpartitioned
   * |-----------|-----------+--------|
   *             ^           ^
   *            pos          p
   */
   else if( pos < p )
     qSelect( size, a, pos, to, p, verbosity );
  /*
   *  partitioned             unpartitioned
   * |-----------+-----------|--------|
   *             ^           ^
   *             p          pos
   */
   else
     qSelect( size, a, from, pos-1, p, verbosity );

}

void qSelect_start( long size, int a[], int p, int verbosity = 0 )
{
  qSelect( size, a, 0, size-1, p, verbosity );
}

bool testSelect( long size, int a[], int p, int verbosity = 0 )
{
  // Find smallest element in the pivoted area
  int min = a[0];
  for( int i = 0; i <= p; i++ )
  {
    if( a[i] < min )
      min = a[i];
  }

  // Find largest element in the not pivoted area
  int max = a[p+1];
  for( int i = p; i <= size-1; i++ )
  {
    if( a[i] > max )
      max = a[i];
  }

  return min >= max;
}


int main( int ac, char* av[] )
{
  long size;
  long seed;
  string algorithm;
  bool verbose = false;
  int n;

  po::options_description clio("Command line options");
  clio.add_options()
    ("help,h", "Print help message")
    ("verbose,v", "Run in verbose mode")
    ("array-size", po::value<long>(&size)->default_value(1000), "Size of the array")
    ("seed,s", po::value<long>(&seed)->default_value(0), "Chose a seed for the SRNG")
    //("insecure", "Do not check if the array was sorted correcly")
    ("nvalue,n", po::value<int>(&n)->default_value(-1), "Find the n biggest elements")
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

  if( verbose )
    printArray( size, a );

  if( verbose )
    cout << "n: " << n << endl;

  qSelect_start( size, a, n, verbose );

  if( verbose )
    printArray( size, a );

  if( testSelect( size, a, n, verbose ) )
    cout << "Successful!" << endl;
  else
    cout << "FAIL!" << endl;


  return 0;
}
