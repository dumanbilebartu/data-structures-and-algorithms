package com.bartu.dsa;

public class AdelsonVelskiiLandisTree<T extends Comparable<? super T>> {
    private static class AvlNode<T>{
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T t,AvlNode<T> lt,AvlNode<T> rt){
            element = t;
            left = lt;
            right = rt;
            height = 0;
        }
        AvlNode(T t){
            this(t,null,null);
        }
    }
    private int height(AvlNode<T> t){
        return t == null ? -1 : t.height;
    }
    private AvlNode<T> root;
    public AdelsonVelskiiLandisTree(){ root = null;}
    public void makeEmpty(){ root = null;}
    public boolean isEmpty(){ return root == null;}
    private AvlNode<T> singleLL(AvlNode<T> k2) { //Left-Left Rotation
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.left),height(k1.right))+1;
        return k1;
    }
    private AvlNode<T> singleRR(AvlNode<T> k2){ //Right-Right Rotation
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.left),height(k1.right))+1;
        return k1;
    }
    private AvlNode<T> doubleRL(AvlNode<T> k3){ //Right-Left Rotation
        k3.right = singleLL(k3.right);
        return singleRR(k3);
    }
    private AvlNode<T> doubleLR(AvlNode<T> k3){ //Left-Right Rotation
        k3.left = singleRR(k3.left);
        return singleLL(k3);
    }
    private static final int ALLOWED_IMBALANCE = 1; //Balancing number
    private AvlNode<T> balance(AvlNode<T> node){
        if(height(node.left) - height(node.right) > ALLOWED_IMBALANCE){
            if(height(node.left.left) >= height(node.right.right)){ //LL is heavy
                node = singleLL(node);
            }else{
                node = doubleLR(node); //LR is heavy
            }
        }else if(height(node.right) - height(node.left) > ALLOWED_IMBALANCE){
            if(height(node.right.right) >= height(node.left.left)){ //RR is heavy
                node = singleRR(node);
            }else{
                node = doubleRL(node); //RL is heavy
            }
        }
        node.height = Math.max(height(node.left),height(node.right))+1;
        return node;
    }
    }
