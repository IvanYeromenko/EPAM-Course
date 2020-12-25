package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public interface Container extends Iterable<Object> {

    // Удаляет все элементы.

    void clear();

    // Возвращает колличество элементов.

    int size();

    // Возвращает строковое представление этого контейнера.

    String toString();

    // Возвращает итератор по элементам.

    // Итератор должен реализовывать метод удаления.

    Iterator<Object> iterator();

}
