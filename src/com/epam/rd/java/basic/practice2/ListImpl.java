package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node first;
    private int size;

    private static class Node {
        private final Object item;
        private Node next;

        private Node(Object item) {
            this.item = item;
            this.next = null;
        }

        private Node(Object item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public ListImpl() {
        first = null;
        size = 0;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentPosition = 0;
        Node head = first;

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public Object next() {
            if (currentPosition == size)
                throw new NoSuchElementException();
            currentPosition++;
            Object curr = head.item;
            head = head.next;
            return curr;
        }

    }

    @Override
    public void addFirst(Object element) {
        first = new Node(element, first);
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (size() == 0)
            addFirst(element);
        else {
            Node node = getNode(size() - 1);
            if (node != null) {
                node.next = new Node(element);
                size++;
            }
        }
    }

    private Node getNode(int index) {
        if (size() == 0)
            return null;
        else if (index >= 0 && index < size()) {
            Node node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }
        return null;
    }

    @Override
    public void removeFirst() {
        if (size > 0) {
            first = first.next;
            size--;
        } else throw new IndexOutOfBoundsException("You are out of the array");
    }

    @Override
    public void removeLast() {
        if (size() == 1)
            removeFirst();
        else if (size() > 0) {
            Node node = getNode(size() - 1);
            if (node != null) {
                node.next = null;
                size--;
            }
        }
    }

    @Override
    public Object getFirst() {
        if (size() == 0)
            return null;
        Node node = first;
        return node.item;
    }

    @Override
    public Object getLast() {
        if (size() == 0)
            return null;
        Node node = first;
        for (int i = 0; i < size() - 1; i++)
            node = node.next;
        return node.item;
    }

    @Override
    public Object search(Object element) {
        Node node = first;
        for (int i = 0; i < size(); i++) {
            if (element != null && element.equals(node.item))
                return node.item;
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node node = first;
        Node previous = null;
        for (int i = 0; i < size(); i++) {
            if (((element != null) && element.equals(node.item)) || ((element == null) && (node.item == null))) {
                if (i == 0) {
                    removeFirst();
                    return true;
                } else if (i == size() - 1) {
                    removeLast();
                    return true;
                } else {
                    previous.next = node.next;
                    size--;
                    return true;
                }
            }
            previous = node;
            node = node.next;
        }
        return false;
    }

    @Override
    public String toString() {
        if (size > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            String list;
            Node node = first;
            for (int i = 0; i < size(); i++) {
                stringBuilder.append(node.item).append(", ");
                node = node.next;
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            list = stringBuilder.toString();
            return "[" + list.substring(0, list.length() - 1) + "]";
        } else
            return "[]";
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        System.out.println(list.size());
        list.addFirst("B");
        list.addLast("C");
        list.addFirst("A");
        list.addLast(null);
        System.out.println(list.toString());
        System.out.println(list.size());
        list.addFirst("AA");
        list.addLast("D");
        System.out.println(list.toString());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.removeFirst();
        list.removeLast();
        System.out.println(list.toString());
        System.out.println(list.search("K"));
        System.out.println(list.search("A"));
        list.remove("B");
        System.out.println(list.toString());
        list.clear();
        System.out.println(list.toString());
        System.out.println(list.size());
    }
}