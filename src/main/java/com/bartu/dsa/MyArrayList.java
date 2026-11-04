package com.bartu.dsa;

public class MyArrayList<T> implements Iterable<T>{
    private static final int DEFAULT_SIZE = 10;
    private int theSize;
    private T [] theItems;

    private int size(){
        return theSize;
    }
    private boolean isClear(){
        return theSize == 0;
    }
    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_SIZE);
    }
    private void ensureCapacity(int newCapacity){
        if(newCapacity <= theSize ){
            return;
        }
        T oldItems[] = theItems;
        theItems = (T []) new Object[newCapacity];
        for(int i = 0; i < size();i++){
            theItems[i] = oldItems[i];
        }
    }
    private T remove(int idx){
        T removedItem = theItems[idx];
        for(int i = idx; i<size()-1; i++){
            theItems[i] = theItems[i+1];
        }
        theSize--;
        return removedItem;
    }
    public java.util.Iterator<T> iterator(){
        return new ArrayListIterator();
    }
    private class ArrayListIterator implements java.util.Iterator<T>{
        private int current = 0;
        public boolean hasNext(){
            return current < size();
        }
        public T next(){
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            return theItems[current++];
        }
        public void remove(){
            MyArrayList.this.remove(--current);
        }
    }
}
