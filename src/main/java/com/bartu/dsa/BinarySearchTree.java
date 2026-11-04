package com.bartu.dsa;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private static class BinaryNode<T>{ //Fundamental structure for BST
        T element; //Data
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T element){ //Root Creation
            this(element,null,null);
        }
        BinaryNode(T theElement , BinaryNode<T> lt , BinaryNode<T> rt ){ //Leaf creation
            element = theElement;
            left = lt;
            right = rt;
        }
    }
    private BinaryNode<T> root; //Basis node
    public BinarySearchTree(){
        root = null; //Create tree
    }
    public void makeEmpty(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    private boolean contains(T t,BinaryNode<T> node){ //Has a node.element or not
        if(node == null){ return false; }

        int compareResult = t.compareTo(node.element); //compare data

        if(compareResult < 0){ // sought-after data is less than node.element
            return contains(t,node.left);
        }else if(compareResult > 0){ // sought-after data is greater than node.element
            return contains(t,node.right);
        }else{
            return true;
        }
    }
    public boolean contains( T t){ //Encapsulation contains
        return contains(t, root );
    }
    private BinaryNode<T> findMin(BinaryNode<T> t){ //Find min value of subtree
        if(t == null){
             return null;
        }
        if(t.left == null){
            return t;
        } else {
           return findMin(t.left); // go leftest leaf
        }
    }
    private BinaryNode<T> findMax(BinaryNode<T> t){
      if(t != null){
          while(t.right != null){
              t = t.right; // go rightest leaf
          }
      }
      return t;
    }
    public T findMax(){ //Encapsulation findMax
        if(isEmpty()){
            throw new RuntimeException();
        }
        return findMax(root).element;
    }
    public T findMin(){ //Encapsulation findMin
        if(isEmpty()){
            throw new RuntimeException();
        }
        return findMin(root).element;
    }
    private BinaryNode<T> insert(T t , BinaryNode<T> node){ //insertion of the new node
        if(node == null){ // create BST
            return new BinaryNode<T>(t,null,null);
        }

        int compareResult = t.compareTo(node.element);

        if(compareResult < 0){
            node.left = insert(t , node.left); //recursion
        }else if(compareResult > 0){
            node.right = insert(t , node.right); //recursion
        }else{
            //Duplicates are prohibited
        }

        return node;
    }
    public void insert(T t){ //Encapsulation insert
        root = insert(t,root);
    }
    private BinaryNode<T> remove(T t,BinaryNode<T> node){ // removal process
        if(node == null){
            return null;
        }

        int compareResult = t.compareTo(node.element);

        if(compareResult < 0){
            node.left = remove(t, node.left);
        }else if(compareResult > 0){
            node.right = remove(t, node.right);
        }else if(node.left != null && node.right != null) { // node has two children
            /* less efficient way when leaf has a two children
            node.element = findMax(node.right).element;
            node.left = remove(node.element,node.left);
                        */
            //more efficient way
            BinaryNode<T> tmp,prev; // creation of the two nodes
                tmp = node.right;
                prev = node.right; // prev parent of the tmp
                while(tmp.left != null){ //find min value of right subtree
                    prev = tmp;
                    tmp = tmp.left;
                }
                node.element = tmp.element; //Copy element to node
                if(tmp == prev){
                    node.right = tmp.right; //old copy deletion
                }else{
                    prev.left = tmp.right; //old copy deletion
                }
        }else{
            node = (node.left != null) ? node.left : node.right; //node has one child
        }
        return node;
    }
    public void remove(T t){ root = remove(t,root);}
}
