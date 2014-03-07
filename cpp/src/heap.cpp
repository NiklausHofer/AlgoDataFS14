#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <stdexcept>
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

bool heapCheck( long size, int a[] )
{
  for( int i = size-1; i>1; i-- )
  {
    if( a[i] < a[(i-1)/2] )
      return false;
  }
  return true;
}

bool maxHeapCheck( long size, int a[] )
{
  for( int i = size-1; i>1; i-- )
    if( a[i] > a[(i-1)/2] )
      return false;

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

void printArray( long size, int a[] )
{
  // You can find the position of you number on the command line
  // like this (number 42 in the example):
  //  ./binarysearch | nl -v 0 - | grep -P '\s*[0-9]+\s+42$' | cut -f 1
  for( int i = 0; i< size; i++ )
    cout << "[" << i << "]:\t" << a[i] << endl;
}

void hPrintArray( long size, int a[] )
{
  for( int i = 0; i< size; i++ )
    cout << a[i] << "; ";
  cout << endl;
}

void heapUp( long size, int a[], int pos )
{
  if( pos+1 > size )
    throw std::range_error( "pos is out of range!" );

  int parent = ((pos-1)/2);

  while ( a[pos] > a[parent] )
  {
    swap( size, a, pos, parent );
    pos = parent;
    parent = ((pos-1)/2);
  }
}

void heapDown( long size, int a[], int pos, int len )
{
  if( pos+1 > size )
    throw std::range_error( "pos is out of range!" );

  int leftChild;
  int rightChild;

  int biggerChildPos = pos;

  do
  {
    if( a[biggerChildPos] > a[pos] )
    {
      swap( size, a, biggerChildPos, pos );
      pos = biggerChildPos;
    }

    leftChild = ((pos*2)+1);
    rightChild = ((pos*2)+2);

    if( rightChild < len && a[rightChild] > a[leftChild] )
      biggerChildPos = rightChild;
    else
      biggerChildPos = leftChild;

  } while (leftChild < len && a[biggerChildPos] > a[pos] );

}

void heapSort( long size, int a[] )
{
  for( int i = 1; i < size; i++ )
    heapUp( size, a, i );

  //printArray( size, a );
  cout << "==============" << endl;

  if( maxHeapCheck( size, a ) )
    cout << "Sort successful! \n" << endl;
  else
    cout << "Sort UNSUCCESSFUL! \n" << endl;

  cout << "==============" << endl;

  for( int i = size-1; i > 0; i-- )
  {
    swap( size, a, 0, i );
    heapDown( size, a,0,i );
  }

  cout << "==============" << endl;

  if( heapCheck( size, a ) )
    cout << "Sort successful! \n" << endl;
  else
    cout << "Sort UNSUCCESSFUL! \n" << endl;

  cout << "==============" << endl;
}


int main( int ac, char* av[] )
{
  long size;
  long seed;
  string algorithm;
  bool verbose = false;
  int searchedValue;
  //int verbosity;

  po::options_description clio("Command line options");
  clio.add_options()
    ("help,h", "Print help message")
    ("verbose,v", "Run in verbose mode")
    //("verbose,v", po::value<int>(&verbosity)->default_value(0), "Set the level of verbosity")
    ("array-size", po::value<long>(&size)->default_value(1000), "Size of the array")
    //("algorithm,a", po::value<string>(&algorithm)->default_value("quicksort"), "Chose the algoirthm for sorting")
    ("seed,s", po::value<long>(&seed)->default_value(0), "Chose a seed for the SRNG")
    //("insecure", "Do not check if the array was sorted correcly")
    //("searched-value", po::value<int>(&searchedValue)->default_value(-1), "Value we're looking for in the array")
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

  heapSort( size, a );

  if( verbose )
    printArray( size, a );

  /*
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
  */

  return 0;
}
