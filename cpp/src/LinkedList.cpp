#include <stdio.h>
#include "Listerface.h"
#include "LinkedList.h"
#include "Position.h"
#include "LinkedPosition.h"

template <typename T>
LinkedList<T>::LinkedList()
{
  LinkedPosition<T> * foo = new LinkedPosition<T>(this);
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
