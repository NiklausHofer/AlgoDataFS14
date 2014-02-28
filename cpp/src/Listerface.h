#ifndef LISTERFACE_H
#define LISTERFACE_H

#include "Position.h"

template<typename T>
class Listerface
{
  private:

  protected:
    //Listerface() = 0;

  public:
    virtual Position<T> * first() = 0;
    virtual Position<T> * last() = 0;
    virtual bool isFirst(Position<T> *p) = 0;
    virtual bool isLast(Position<T> *p) = 0;
    virtual Position<T> * next(Position<T> *p) = 0;
    virtual Position<T> * previous(Position<T> *p) = 0;
    virtual T * replaceElement(Position<T> *p, T *o) = 0;
    virtual Position<T> * insertFirst(T *o) = 0;
    virtual Position<T> * insertLast(T *o) = 0;
    virtual Position<T> * insertBefore(Position<T> *p, T *o) = 0;
    virtual Position<T> * insertAfter(Position<T> *p, T *o) = 0;
    virtual void remove(Position<T> *p) = 0;
    //virtual Iterator<Position<T>> positions() = 0;
    //virtual Iterator<T> elements() = 0;
    virtual int size() = 0;
    virtual bool isEmpty() = 0;
};

#endif
