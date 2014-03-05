#ifndef LINKEDPOSITION_H
#define LINKEDPOSITION_H

#include "Listerface.h"

// Can't include LinkedList, that would lead to circular dependencies
//#include "LinkedList.h"
//#include "Position.h"

//template <typename T>
//class Listerface;
// Forward declare LinkedList<T> 
template <typename T>
class LinkedList;
//class LinkedList : public Listerface<T>;

template <typename T>
class LinkedPosition : public Position<T>
{
  private:
    T * element;
    LinkedPosition * next;
    LinkedPosition * previous;
    Listerface<T> * list;
    LinkedPosition();
    LinkedPosition( Listerface<T> * list );
    ~LinkedPosition();

  public:
    T * getElement();

    friend class LinkedList<T>;
};

template <typename T>
LinkedPosition<T>::LinkedPosition()
{
}

template <typename T>
LinkedPosition<T>::LinkedPosition( Listerface<T> * list )
{
  this->list = list;
  next = NULL;
  previous = NULL;
}

template <typename T>
T * LinkedPosition<T>::getElement()
{
  return element;
}

template <typename T>
LinkedPosition<T>::~LinkedPosition()
{
  next = NULL;
  previous = NULL;
  list = NULL;
  element = NULL;
}

#endif
