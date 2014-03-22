#ifndef AVLTREE_H
#define AVLTREE_H

#include "AVLNode.h"


template<typename K, typename T>
class AVLTree
{
  private:
    AVLNode<K, T> * root;
    int size;

  public:
    AVLTree<K, T>();
    int getSize();
    void insert( K *, T *);

};

  template<typename K, typename T>
AVLTree<K, T>::AVLTree()
{
  root = new AVLNode<K, T>();
}

template<typename K, typename T>
int AVLTree<K, T>::getSize()
{
  return size;
}


template<typename K, typename T>
void AVLTree<K, T>::insert( K * key, T * elem )
{
  AVLNode<K, T> * node = root;

  while( ! node->isExternal() )
  {
    if( node->element > elem )
      node = node->left;
    else
      node = node->right;
  }

  node->expand( key, elem);
}

/*
template<typename K, typename T>
AVLTree<K, T>::
{
}
*/

#endif
