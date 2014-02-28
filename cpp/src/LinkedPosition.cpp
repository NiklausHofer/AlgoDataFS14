#include "Listerface.h"
#include "LinkedList.h"
#include "Position.h"
#include "LinkedPosition.h"

template <typename T>
LinkedPosition<T>::LinkedPosition( Listerface<T> * list )
{
  this->list = list;
}

template <typename T>
T * LinkedPosition<T>::getElement()
{
  return element;
}
