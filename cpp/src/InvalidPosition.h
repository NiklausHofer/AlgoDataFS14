#ifndef INVALIDPOSITION_H
#define INVALIDPOSITION_H

#include "Position.h"

template <typename T>
class InvalidPosition : public Position<T>
{
  private:
    T * element;

  public:
    T * getElement();
};

template <typename T>
T * InvalidPosition<T>::getElement()
{
  return element;
}

#endif
