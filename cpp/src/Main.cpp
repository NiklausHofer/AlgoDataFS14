#include <iostream>
#include <string>
#include "Position.h"
#include "LinkedPosition.h"
#include "Listerface.h"
#include "LinkedList.h"

int main( int argc, const char* argv[] )
{
  //LinkedList<int> * foo;
  //foo = new LinkedList<int>();

  LinkedList<std::string> * foo = new LinkedList<std::string>();
  std::string * myString = new std::string("Hello World!");
  LinkedPosition<std::string> * p = (LinkedPosition<std::string>*)(foo->insertFirst( myString ));
  std::cout << (std::string)(*(p->getElement())) << std::endl;
  //LinkedPosition<std::string> * bar = new LinkedPosition<std::string>();

  return 0;
}
