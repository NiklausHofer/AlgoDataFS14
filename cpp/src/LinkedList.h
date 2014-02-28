#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include "LinkedPosition.h"
//#include "Position.h"
#include "Listerface.h"

template<typename T>
class LinkedList : public Listerface<T>
{
  private:
    Position<T> * firstElem;
    Position<T> * lastElem;
    LinkedPosition<T> * myPos;

  public:
    LinkedList();
    Position<T> * first();
    Position<T> * last();
    bool isFirst(Position<T> *p);
    bool isLast(Position<T> *p);
    Position<T> * next(Position<T> *p);
    Position<T> * previous(Position<T> *p);
    T * replaceElement(Position<T> *p, T *o);
    Position<T> * insertFirst(T *o);
    Position<T> * insertLast(T *o);
    Position<T> * insertBefore(Position<T> *p, T *o);
    Position<T> * insertAfter(Position<T> *p, T *o);
    void remove(Position<T> *p);
    //Iterator<Position<T>> positions() = 0;
    //Iterator<T> elements() = 0;
    int size();
    bool isEmpty();
};

template <typename T>
LinkedList<T>::LinkedList()
{
  //LinkedPosition<T> * foo = new LinkedPosition<T>(this);
}

template <typename T>
Position<T> * LinkedList<T>::first()
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::last()
{
  return NULL;
}

template <typename T>
bool LinkedList<T>::isFirst( Position<T> *p )
{
  return NULL;
}

template <typename T>
bool LinkedList<T>::isLast( Position<T> *p )
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::next( Position<T> *p )
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::previous( Position<T> *p )
{
  return NULL;
}

template <typename T>
T * LinkedList<T>::replaceElement( Position<T> *p, T *o )
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::insertFirst( T *o )
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::insertLast( T *o )
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::insertBefore( Position<T> *p, T *o )
{
  return NULL;
}

template <typename T>
Position<T> * LinkedList<T>::insertAfter( Position<T> *p, T *o )
{
  return NULL;
}

template <typename T>
void LinkedList<T>::remove( Position<T> *p )
{
}

template <typename T>
int LinkedList<T>::size()
{
  return 0; }

template <typename T>
bool LinkedList<T>::isEmpty()
{
  return false;
}

#endif
