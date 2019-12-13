import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Your implementation of a circular singly linked list.
 *
 * @author Luis Pastrana
 * @userid lepl3
 * @GTID 903244251
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative "
                    + "index is not a valid input");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("No many "
                    + "elements in the LinkedList IndexOutOfBounds");
        }
        if (index == 0) {

            this.addToFront(data);

        } else if (index == size) {

            this.addToBack(data);

        } else {

            SinglyLinkedList<T> tempLinkedList = new SinglyLinkedList<T>();
            int counter = 0;
            while (counter < size) {
                if (counter == index) {
                    tempLinkedList.addToBack(data);
                }
                tempLinkedList.addToBack(head.getData());
                head = head.getNext();
                counter++;
            }
            this.head = tempLinkedList.getHead();
            size++;
        }

    }

    @Override
    public void addToFront(T data) {
        if (data == null) {throw new IllegalArgumentException("No null element can't be added");}
        if (size == 0) {
            this.addToBack(data);
        } else {
            LinkedListNode<T> firstNode = new LinkedListNode<T>(head.getData(), head.getNext());
            LinkedListNode<T> newNode = head;
            newNode.setData(data);
            newNode.setNext(firstNode);
            size++;
        }

    }

    @Override
    public void addToBack(T data) {
        if (data == null) {throw new IllegalArgumentException("No null element can't be added");}
        if (size == 0) {
            LinkedListNode<T> newArr = new LinkedListNode<T>(null, null);
            newArr.setData(data);
            newArr.setNext(newArr);
            this.head = newArr;
            this.size++;
        } else {
            LinkedListNode<T> firstNode = new LinkedListNode<T>(head.getData(), head.getNext());
            LinkedListNode<T> newNode = head;
            head = firstNode;
            newNode.setData(data);
            newNode.setNext(firstNode);
            this.size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        T removed = null;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index is not a valid input");
        }
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("No many elements in the LinkedList IndexOutOfBounds");
        }

        if (index == 0) {
            return this.removeFromFront();
        } else if (index == size - 1) {
            return this.removeFromBack();
        } else {
            SinglyLinkedList newLL = new SinglyLinkedList<T>();
            int counter = 0;
            while (counter < size) {
                if (counter != index) {
                    newLL.addToBack(head.getData());
                    head = head.getNext();
                    counter++;
                } else {
                    removed = head.getData();
                    head = head.getNext();
                    counter++;
                }
            }
            this.head = newLL.getHead();
            size--;
            return removed;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T removed = head.getData();
            SinglyLinkedList<T> temp = new SinglyLinkedList<T>();
            head = temp.getHead();
            size--;
            return removed;
        }
        size--;
        T removed = head.getData();
        head.setData(head.getNext().getData());
        head.setNext(head.getNext().getNext());
        return removed;
    }

    @Override
    public T removeFromBack() /*arreglar remove from index*/{
        SinglyLinkedList<T> temp = new SinglyLinkedList<T>();
        int counter = 0;
        T removed = null;
        while (counter < size) {
            if (counter == size - 1) {
                removed = head.getData();
            } else {
                temp.addToBack(head.getData());
            }
            head = head.getNext();
            counter++;
        }

        this.head = temp.getHead();
        size--;
        return removed;
    }


    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot remove a null element");
        }

        if (size == 0) {
            return null;
        }

        if (size == 1 && head.getData().equals(data)) {
            T dataRem = head.getData();
            SinglyLinkedList<T> temp = new SinglyLinkedList<>();
            head = temp.getHead();
            size--;
            return dataRem;
        }

        int counter = 0;
        LinkedListNode<T> pointer1 = this.head;
        LinkedListNode<T> pointer2 = this.head;
        boolean flag = pointer1.getData().equals(data);

        while (counter < size - 1) {
            if (pointer1.getNext().getData().equals(data)) {
                pointer2 = pointer1;
                flag = false;
            }

            counter++;
            pointer1 = pointer1.getNext();
        }

        if (flag) {
            pointer1.setNext(pointer1.getNext().getNext());
            head = head.getNext();
            size--;
            return pointer2.getData();

        } else if ( pointer2 == head ) {
            return null;
        }
        size--;
        T removed = pointer2.getNext().getData();
        pointer2.setNext(pointer2.getNext().getNext());
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index is not a valid input");
        }
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("No many elements in the LinkedList IndexOutOfBounds");
        }
        int counter = 0;
        if (index == 0) {
            return head.getData();
        } else {
            LinkedListNode<T> headCopy = new LinkedListNode<T>(head.getData(), head.getNext());
            while (counter < index) {
                headCopy = headCopy.getNext();
                counter++;
            }
            return headCopy.getData();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arrayLinkedList = new Object[size];

        if (!(arrayLinkedList.length == 0)) {
            arrayLinkedList[0] = head.getData();
        } else {
            return arrayLinkedList;
        }
        int counter = 0;
        LinkedListNode<T> headCopy = new LinkedListNode<T>(head.getData(), head.getNext());
        while (counter < size) {
            arrayLinkedList[counter] = headCopy.getData();
            headCopy = headCopy.getNext();
            counter++;
        }

        return arrayLinkedList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        this.size = 0;
        head = null;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}