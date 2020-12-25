package com.epam.rd.java.basic.practice2;

public interface Array extends Container {

    void add(Object var1);

    void set(int var1, Object var2);

    Object get(int var1);

    int indexOf(Object var1);

    void remove(int var1);

    void clear();

    /*// Add the specified element to the end.
    void add(Object element);

    // Sets the element at the specified position.
    void set(int index, Object element);

    // Returns the element at the specified position.
    Object get(int index);

    // Returns the index of the first occurrence of the specified element,
    // or -1 if this array does not contain the element.
    // (use 'equals' method to check an occurrence)
    int indexOf(Object element);

    // Removes the element at the specified position.
    void remove(int index);*/

}
