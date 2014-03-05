#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include "LinkedPosition.h"
//#include "Position.h"
#include "Listerface.h"
#include <string>
#include <stdexcept>

template<typename T>
class LinkedList : public Listerface<T>
{
  private:
    LinkedPosition<T> * firstElem;
    LinkedPosition<T> * lastElem;
    LinkedPosition<T> * myPos;
    LinkedPosition<T> * evaluate( Position<T> *p );
    int size;

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
    T * remove(Position<T> *p);
    //Iterator<Position<T>> positions() = 0;
    //Iterator<T> elements() = 0;
    int getSize();
    bool isEmpty();
};

template <typename T>
LinkedPosition<T> * LinkedList<T>::evaluate( Position<T> *p )
{
  LinkedPosition<T> * tmpPos = dynamic_cast<LinkedPosition<T>*>( p );

  if( tmpPos == NULL )
    throw std::runtime_error( "Passed Position belongs to some other type of list!" );

  if( tmpPos->list == NULL )
    throw std::runtime_error( "This Position is not a member of any list!" );

  if( tmpPos->list != this )
    throw std::runtime_error( "This Position belongs to another list of the same type" );

  return tmpPos;
}

template <typename T>
LinkedList<T>::LinkedList()
{
  firstElem = NULL;
  lastElem = NULL;
  size = 0;
}

template <typename T>
Position<T> * LinkedList<T>::first()
{
  return firstElem;
}

template <typename T>
Position<T> * LinkedList<T>::last()
{
  return lastElem;
}

template <typename T>
bool LinkedList<T>::isFirst( Position<T> *p )
{
  return p == firstElem;
}

template <typename T>
bool LinkedList<T>::isLast( Position<T> *p )
{
  return p == lastElem;
}

template <typename T>
Position<T> * LinkedList<T>::next( Position<T> *p )
{
  return evaluate( p )->next;
}

template <typename T>
Position<T> * LinkedList<T>::previous( Position<T> *p )
{
  return evaluate( p )->previous;
}

/**
 * replaces the element in that position
 * and returns the old Element
 */
template <typename T>
T * LinkedList<T>::replaceElement( Position<T> *p, T *o )
{
  LinkedPosition<T> * pos = evaluate( p );

  T * old = pos->element;
  pos->element = o;

  return old;
}

template <typename T>
Position<T> * LinkedList<T>::insertFirst( T *o )
{
  LinkedPosition<T> *tmpPos = new LinkedPosition<T>( this );
  tmpPos->element = o;

  if( firstElem != NULL )
  {
    firstElem->previous = tmpPos;
    tmpPos->next = firstElem;
  }

  if( lastElem == NULL )
    lastElem = tmpPos;

  firstElem = tmpPos;

  size++;

  return firstElem;
}

template <typename T>
Position<T> * LinkedList<T>::insertLast( T *o )
{
  LinkedPosition<T> *tmpPos = new LinkedPosition<T>( this );
  tmpPos->element = o;

  if( lastElem != NULL )
  {
    lastElem->next = tmpPos;
    tmpPos->previous = lastElem;
  }

  if( firstElem == NULL )
    firstElem = tmpPos;

  lastElem = tmpPos;

  size++;

  return lastElem;
}

template <typename T>
Position<T> * LinkedList<T>::insertBefore( Position<T> *p, T *o )
{
  LinkedPosition<T> * oldPos = evaluate( p );

  if( oldPos == firstElem )
    return insertFirst( o );

  LinkedPosition<T> * newPos = new LinkedPosition<T>( this );
  newPos->element = o;

  oldPos->previous->next = newPos;
  newPos->previous = oldPos->previous;

  oldPos->previous = newPos;
  newPos->next = oldPos;

  size++;

  return newPos;

}

template <typename T>
Position<T> * LinkedList<T>::insertAfter( Position<T> *p, T *o )
{
  LinkedPosition<T> * oldPos = evaluate( p );

  if( oldPos == lastElem )
    return insertLast( o );

  LinkedPosition<T> * newPos = new LinkedPosition<T>( this );
  newPos->element = o;

  oldPos->next->previous = newPos;
  newPos->next = oldPos->next;

  oldPos->next = newPos;
  newPos->previous = oldPos;

  size++;

  return newPos;
}

/**
 * Deletes a position from the list. 
 * The position will be deleted!
 * The object within that position will be passed back.
 * If you don't want it anymore, it's the callers responsibility
 * to delete it!
 */
template <typename T>
T * LinkedList<T>::remove( Position<T> *p )
{
  LinkedPosition<T> * pos = evaluate( p );

  T * obj = pos->element;

  if( pos == lastElem && pos == firstElem )
  {
    firstElem = NULL;
    lastElem = NULL;
  }
  else if( pos == lastElem )
  {
    lastElem = pos->previous;
    lastElem->next = NULL;
  }
  else if( pos == firstElem )
  {
    firstElem = pos->next;
    firstElem->previous = NULL;
  }
  else
  {
    pos->next->previous = pos->previous;
    pos->previous->next = pos->next;
  }

  delete pos;

  size--;

  return obj;
}

template <typename T>
int LinkedList<T>::getSize()
{
  return size; 
}

template <typename T>
bool LinkedList<T>::isEmpty()
{
  return ( firstElem == NULL );
}

#endif
