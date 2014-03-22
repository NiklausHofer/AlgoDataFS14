#ifndef AVLNODE_H
#define AVLNODE_H

template<typename K, typename T>
class MyAVLTree;

template<typename K, typename T>
class AVLNode
{
  private:
    AVLNode<K, T> * parent;
    AVLNode<K, T> * left;
    AVLNode<K, T> * right;
    MyAVLTree<K, T> * owner;

    K * key;
    T * element;

    K * getKey();
    bool isExternal();
    bool isLeftChild();
    bool isRightChild();
    void expand( K *, T * );


  public:
    T * getElement();

    friend class MyAVLTree<K, T>;
};

  template<typename K, typename T>
bool AVLNode<K, T>::isLeftChild()
{
  if( parent==NULL )
    return false;
  return parent->left == this;
}


  template<typename K, typename T>
bool AVLNode<K, T>::isRightChild()
{
  if( parent == NULL )
    return false;
  return parent->right == this;
}


  template<typename K, typename T>
bool AVLNode<K, T>::isExternal()
{
  return left == NULL; // is also true for right
  // must be ensured by author
}

template<typename K, typename T>

void AVLNode<K, T>::expand( K * key, T * elem )
{
  this->elem = elem;
  this->key = key;

  this->left = new AVLNode();
  this->right = new AVLNode();
  this->left->parent = this;
  this->right->parent = this;
}


  template<typename K, typename T>
K * AVLNode<K, T>::getKey()
{
  return key;
}


  template<typename K, typename T>
T * AVLNode<K, T>::getElement()
{
  return element;
}

/*

   template<typename K, typename T>
   AVLNode<K, T>::
   {
   }
   */


#endif
