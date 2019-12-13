/**
 * Your implementation of an ArrayList.
 *
 * @author Luis Pastrana
 * @userid lepl3
 * @GTID 903244251
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Increment the slots available in the backing array to add more objects
     */
    private void resize() {
        T[] newArr =
            (T[]) new Object[size + ArrayListInterface.INITIAL_CAPACITY];

        for (int i = 0; i < size; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is greater than"
                    + " the size producing a IndexOutOfBoundsException");
        }

        if (data == null) {
            throw new IllegalArgumentException("Cannot insert "
                    + "null data into data structure");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("The index cannot be negative");
        }

       if (size == backingArray.length) {
            T[] newArr = (T[]) new Object[backingArray.length * 2 + 1];
            for (int i = 0, j = 0; i <= size; i++, j++) {
                if (index == i) {
                    newArr[i] = data;
                    j--;
                } else {
                    newArr[i] = backingArray[j];
                }
            }
            size++;
            backingArray = newArr;
       } else {
            T previous = null;
            for (int i = index; i <= size; i++) {
                if (i == index) {
                    previous  = backingArray[i];
                    backingArray[i] = data;
                } else {
                    T newPrev = backingArray[i];
                    backingArray[i] = previous;
                    previous = newPrev;
                }
            }
            size++;
       }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null "
                    + "data into data structure");
        }

        if (this.size == backingArray.length) {
            this.resize();
        }

        T[] newArr =  (T[]) new Object[backingArray.length];
        newArr[0] = data;
        size++;

        for (int i = 0; i < this.size; i++) {
            newArr[i + 1] = backingArray[i];
        }

        backingArray = newArr;

    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null "
                    + "data into data structure");
        }

        if (this.size == backingArray.length) {
            this.resize();
        }

        backingArray[size++] = data;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("The index cannot be negative");
        } else if (index >= size) {
            throw
                new IndexOutOfBoundsException("Index is greater than the"
                        + " size producing a IndexOutOfBoundsException");
        }
        T elementRemoved = backingArray[index];
        for (int i = index; i < this.size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[--size] = null;

        return elementRemoved;

    }

    @Override
    public T removeFromFront() {

        if (size == 0) {
            return null;
        }

        T elementRemoved = backingArray[0];
        for (int i = 0; i < this.size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return elementRemoved;
    }

    @Override
    public T removeFromBack() {

        if (size == 0) {
            return null;
        }

        T lastObject = backingArray[size - 1];
        backingArray[--size] = null;
        return lastObject;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("The index cannot be negative");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Index is greater than "
                        + "the size producing a IndexOutOfBoundsException");
        }

        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return (0 == this.size);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
