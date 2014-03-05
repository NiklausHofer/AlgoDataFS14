#ifndef POSITION_H
#define POSITION_H

template <typename T>
class Position
{
  private:

  protected:
    //Position();
    virtual ~Position();

  public:
    virtual T * getElement() = 0;

};

template <typename T>
Position<T>::~Position()
{
}

#endif
