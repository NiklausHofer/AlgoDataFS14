#include <iostream>
#include <string>
#include "Position.h"
#include "LinkedPosition.h"
#include "Listerface.h"
#include "LinkedList.h"
#include "InvalidPosition.h"

int main( int argc, const char* argv[] )
{
  //LinkedList<int> * foo;
  //foo = new LinkedList<int>();

  LinkedList<std::string> * foo = new LinkedList<std::string>();
  LinkedPosition<std::string>* poo = (LinkedPosition<std::string>*)(foo->first());
  if( poo != NULL )
    std::cout << (std::string)(*(poo->getElement())) << std::endl;

  std::string * myString = new std::string("Hello World!");
  std::string * fooString = new std::string("Lorem ipsum");
  LinkedPosition<std::string>* p = (LinkedPosition<std::string>*)(foo->insertFirst( myString ));
  std::cout << (std::string)(*(p->getElement())) << std::endl;
  std::cout << foo->isFirst( p ) << std::endl;
  std::cout << foo->isLast( p ) << std::endl;

  p = (LinkedPosition<std::string>*)(foo->insertFirst( fooString ));
  std::cout << (std::string)(*(p->getElement())) << std::endl;
  std::cout << foo->isFirst( p ) << std::endl;
  std::cout << foo->isLast( p ) << std::endl;

  //foo->next( NULL );

  InvalidPosition<std::string> * pos = new InvalidPosition<std::string>();
  foo->next( pos );
  //LinkedPosition<std::string> * bar = new LinkedPosition<std::string>();
  //

  return 0;
}
