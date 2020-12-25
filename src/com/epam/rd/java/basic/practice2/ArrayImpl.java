package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] array;
    private int size;
    private int lastIndex = 0;

    public ArrayImpl(int size) {
        this.size = size;
        array = new Object[size];
    }

    @Override
    public void clear() {
        Object[] arr = new Object[0];
        this.array = arr;
        lastIndex = 0;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl(array);
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentPosition = 0;
        Object[] arrayList;

        public IteratorImpl(Object[] newArray) {
            this.arrayList = newArray;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < lastIndex;
        }

        @Override
        public Object next() {
            if (currentPosition == lastIndex)
                throw new NoSuchElementException("There is no elem with this index");
            currentPosition++;
            return arrayList[currentPosition - 1];
        }
    }

    @Override
    public void add(Object element) {
        if (lastIndex > size)
            throw new IndexOutOfBoundsException("You can not add this element");
        if (lastIndex == size) {
            Object[] temp = new Object[array.length * 2];
            System.arraycopy(array, 0, temp, 0, array.length);
            array = temp;
            size = array.length;
        }
        array[lastIndex++] = element;
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index >= lastIndex) {
            throw new IndexOutOfBoundsException("You are out of the array");
        }
        array[index] = element;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= lastIndex) {
            throw new IndexOutOfBoundsException("Can not get the element with this " + index + " index");
        }
        return array[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element != null) {
            for (int i = 0; i < lastIndex; i++) {
                if ((array[i] != null) && array[i].equals(element)) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < lastIndex; i++) {
                if (array[i] == null)
                    return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if ((index <= lastIndex - 1) && (index > 0)) {
            Object[] temp = new Object[lastIndex - 1];
            System.arraycopy(array, 0, temp, 0, index);
            System.arraycopy(array, index + 1, temp, index, lastIndex - index - 1);
            array = temp;
            lastIndex--;
        } else
            throw new NoSuchElementException("There is no element with " + index + " index ");
    }

    @Override
    public String toString() {
        if (size > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            String ar;
            for (int i = 0; i < lastIndex; i++) {
                stringBuilder.append(array[i]).append(", ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            ar = stringBuilder.toString();
            return "[" + ar.substring(0, ar.length() - 1) + "]";
        } else
            return "[]";
    }

    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl(4);
        System.out.println(array.size());
        array.add("A");
        array.add("B");
        array.add("C");
        array.add(null);
        System.out.println(array.toString());
        System.out.println(array.size());
        array.add("D");
        array.add("E");
        System.out.println(array.toString());
        System.out.println(array.size());
        System.out.println(array.get(1));
        System.out.println(array.indexOf(null));
        array.set(2, "D");
        System.out.println(array.toString());
        array.remove(1);
        System.out.println(array.toString());
        System.out.println(array.size());
        array.clear();
        System.out.println(array.toString());
        System.out.println(array.size());
    }
}


