package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Node top;

    /**
     * last;
     */
    private Node last;

    /**
     * {@link Object}
     */
    private Object[] arr = new Object[3];

    /**
     * size
     */
    private int size;
    /**
     * index of the current front item, if one exists
     */
    private int head;

    /**
     * index of next item to be added
     */
    private int tail;

    public QueueImpl() {

        this.last = null;
        this.size = 0;
    }

    public QueueImpl(int capacity) {

        arr = new Object[capacity];
    }

    public Node getTop() {

        return top;

    }

    @Override
    public void clear() {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
            size = 0;
        }
    }

    @Override
    public int size() {

        return size;

    }

    @Override
    public Iterator<Object> iterator() {

        return new IteratorImpl();
    }

    /*
     * (non-Javadoc)
     *
     * @see ua.nure.chekhunov
     */
    @Override
    public void enqueue(Object element) {

        Node oldlast = last;
        last = new Node();
        arr[tail] = element;
        tail = (tail + 1) % arr.length;
        last.next = null;
        if (!isEmpty()) {
            oldlast.next = last;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from empty queue");
        }
        Object item = arr[head];
        arr[head] = null;
        head = (head + 1) % arr.length;
        size--;
        return item;
    }

    @Override
    public Object top() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot peek into empty queue");
        }
        return arr[0];

    }

    public boolean isEmpty() {

        return size == 0;
    }

    public Object[] toArray() {

        return this.arr.clone();

    }


    @Override
    public String toString() {
        Iterator<Object> i = iterator();
        StringBuilder sb = new StringBuilder();

        if (!i.hasNext()) {
            return "[]";
        }

        sb.append('[');
        for (;;) {
            Object e = i.next();
            if (e == null) {
                return "[]";
            }
            sb.append(e == this ? "(this Collection)" : e);
            if (!i.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(", ");
        }
    }

    private static class Node {

        private Object element;

        private Node next;

        private Node prev;

        public Node() {
        }

        public Node(Object element) {
            this.element = element;
            this.next = null;
        }

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }

        public void setData(Object element) {
            this.element = element;
        }

        public Object getData() {
            return this.element;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private class IteratorImpl implements Iterator<Object> {

        private int pointer = -1;

        private boolean nextOrPrevious;

        private boolean flag = true;

        public int getPointer() {
            return pointer;
        }

        public void setPointer(int pointer) {
            this.pointer = pointer;
        }

        public boolean isNextOrPrevious() {
            return nextOrPrevious;
        }

        public void setNextOrPrevious(boolean nextOrPrevious) {
            this.nextOrPrevious = nextOrPrevious;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public boolean hasNext() {

            return (pointer < toArray().length - 1);

        }

        @Override
        public Object next() {

            if (pointer >= size()) {
                throw new NoSuchElementException("Error No Such Element from next");
            }

            flag = false;
            nextOrPrevious = false;
            return toArray()[++pointer];

        }

        @Override
        public void remove() {
            if (isFlag()) {
                throw new IllegalStateException();
            }
            int i = 0;
            if (!isNextOrPrevious()) {
                i = pointer--;
            } else {
                i = ++pointer;
            }
            Object[] arr2 = new Object[arr.length - 1];
            System.arraycopy(arr, 0, arr2, 0, i);
            System.arraycopy(arr, i + 1, arr2, i, arr2.length - i);
            arr = arr2;
            flag = true;
            if (isNextOrPrevious()) {
                --pointer;
            }
        }

    }

    public static void main(String[] args) {

        Queue queue = new QueueImpl(3);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println(queue);
        System.out.println(queue.size());

        queue.clear();
        System.out.println(queue);
        System.out.println(queue.size());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println(queue);
        System.out.println(queue.size());
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.print(queue);
        System.out.println(queue.top());

    }

}
