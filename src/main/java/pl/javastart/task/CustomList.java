package pl.javastart.task;

import java.util.Arrays;

// Uzupełnij klasę. Możesz ją dowolnie modyfikować.
// Celem jest, żeby przechodziły testy w src/test/java/[...]/CustomListTest
@SuppressWarnings("unchecked")
public class CustomList<T> {
    private static final int DEFAULT_SIZE = 5;
    private Object[] defaultArray;
    private int size;

    public CustomList() {
        this.defaultArray = new Object[DEFAULT_SIZE];
    }

    public T get(int index) {
        if (index >= 0 && index <= size) {
            T element = null;
            for (int i = 0; i < size; i++) {
                if (index == i) {
                    element = (T) defaultArray[i];
                }
            }
            return element;
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public void add(T element) {
        checkCapacity();
        defaultArray[size] = element;
        size++;
    }

    public void add(int index, T element) {
        checkCapacity();
        System.arraycopy(defaultArray, 0, defaultArray, 0, index - 1);
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = element;
        size++;
    }

    private void checkCapacity() {
        if (size == defaultArray.length) {
            defaultArray = Arrays.copyOf(defaultArray, defaultArray.length * 2);
        }
    }

    public int size() {
        return size;
    }

    public void remove(int index) {
        if (index == size) {
            System.arraycopy(defaultArray, 0, defaultArray, 0, size - 1);
            size--;
        } else {
            System.arraycopy(defaultArray, 0, defaultArray, 0, index - 1);
            System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index);
            size--;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            T element = (T) defaultArray[i];
            if (i == size - 1) {
                builder.append(element);
            } else {
                builder.append(element).append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
