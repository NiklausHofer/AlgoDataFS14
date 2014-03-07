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

void verbosePrintArray( long size, int a[] )
{
  // You can find the position of you number on the command line
  // like this (number 42 in the example):
  //  ./binarysearch | nl -v 0 - | grep -P '\s*[0-9]+\s+42$' | cut -f 1
  for( int i = 0; i< size; i++ )
    cout << "[" << i << "]:\t" << a[i] << endl;
}

void printArray( long size, int a[] )
{
  // You can find the position of you number on the command line
  // like this (number 42 in the example):
  //  ./binarysearch | nl -v 0 - | grep -P '\s*[0-9]+\s+42$' | cut -f 1
  for( int i = 0; i< size; i++ )
    cout << a[i] << endl;
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

void heapSort( long size, int a[], int verbosity = 0 )
{
  for( int i = 1; i < size; i++ )
    heapUp( size, a, i );

  if( verbosity == 2 )
    printArray( size, a );
  else if( verbosity >= 3 )
    verbosePrintArray( size, a );

  if( verbosity >= 1 )
  {
    cout << "==============" << endl;

    if( maxHeapCheck( size, a ) )
      cout << "Sort successful!" << endl;
    else
      cout << "Sort UNSUCCESSFUL!" << endl;

    cout << "==============" << endl;
  }

  for( int i = size-1; i > 0; i-- )
  {
    swap( size, a, 0, i );
    heapDown( size, a,0,i );
  }

  // Some code for recursion. But that doesn't work (yet)
  //for( int i = size/2; i>=0; i-- )
  //  heapDown( size, a, i, size);

  if( verbosity >= 1 )
  {
    cout << "==============" << endl;

    if( heapCheck( size, a ) )
      cout << "'Reversion' successful!" << endl;
    else
      cout << "'Reversion' UNSUCCESSFUL!" << endl;

    cout << "==============" << endl;
  }
}


int main( int ac, char* av[] )
{
  long size;
  long seed;
  string algorithm;
  //int searchedValue;
  int verbosity;

  po::options_description clio("Command line options");
  clio.add_options()
    ("help,h", "Print help message")
    ("verbose,v", po::value<int>(&verbosity)->default_value(0), "Set the level of verbosity")
    ("array-size", po::value<long>(&size)->default_value(1000), "Size of the array")
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

  if( verbosity == 1 )
    cout << "running in verbose mode" << endl;
  else if( verbosity == 2 )
    cout << "running in more verbose mode" << endl;
  else if( verbosity >= 3 )
    cout << "running in super verbose mode" << endl;

  if( seed == 0 )
  {
    time_t timeObj = time( NULL );
    seed = time( &timeObj );
    if( verbosity >= 1 )
      cout << "Seed, retrieved from current time: " << seed << endl;
  }
  else
  {
    if( verbosity >= 1 )
      cout << "seed has been defined by the user to be: " << seed << endl;
  }
  srand( seed );

  if( verbosity >= 1 )
    cout << "The size of the array will be: " << size << endl;

  int a [size];

  // Filling the array
  for( int i =0; i<size; i++ )
    a[i] = rand() % size + 1;

  heapSort( size, a, verbosity );

  if( verbosity == 2 )
    printArray( size, a );
  else if( verbosity >= 3 )
    verbosePrintArray( size, a );

  return 0;
}
