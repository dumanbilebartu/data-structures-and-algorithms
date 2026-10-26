package com.bartu.dsa.bst;

public class BinaryNode<AnyType> {
        AnyType element; //The data
        BinaryNode<AnyType> left; // Left child
        BinaryNode<AnyType> right; // Right child

        //Constructors
        BinaryNode(AnyType theElement){
            this(theElement,null,null);
        }

        BinaryNode(AnyType theElement , BinaryNode<AnyType> lt , BinaryNode<AnyType> rt){
            element = theElement;
            left = lt;
            right = rt;
        }
}
