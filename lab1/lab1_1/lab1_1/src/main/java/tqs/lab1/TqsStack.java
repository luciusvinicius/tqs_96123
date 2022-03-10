package tqs.lab1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private ArrayList<T> elements = new ArrayList<T>();
    private int limit = -1;

    public TqsStack() {

    }

    public TqsStack(int l) {
        limit = l;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void push(T elem) {
        if (limit < 0 || this.size() < limit)
            elements.add(elem);
        else
            throw new IllegalStateException();
    }

    public T pop() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }
        T elem = elements.get(0);
        elements.remove(0);
        return elem;
    }

    public T peek() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }
        T elem = elements.get(0);
        return elem;
    }


}