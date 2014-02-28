#ifndef LINKEDPOSITION_H
#define LINKEDPOSITION_H

#include "Listerface.h"
#include "LinkedList.h"
#include "Position.h"

template <typename T>
class LinkedPosition : public Position<T>
{
  private:
    T * element;
    LinkedPosition * next;
    LinkedPosition * previous;
    Listerface<T> * list;
    LinkedPosition( Listerface<T> * list );

  public:
    T * getElement();
    //LinkedPosition();

    //friend class LinkedList<T>;
};

/*
template <typename T>
LinkedPosition<T>::LinkedPosition()
{
}
*/

/*
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
*/

#endif
