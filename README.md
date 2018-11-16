# CSC212P6
List Data Structures &amp; Testing

## About P6

### P6List Interface
Recall that an interface in Java defines methods but not implementations. My P6List is a little easier to implement than Java's List and it's a little more specific for the data structures we learned. These 11 methods already have stubs in the starter code for this assignment for all data structures.

```java
public interface P6List<T> {
  public T removeFront();
  public T removeBack();
  public T removeIndex(int index);
  
  public void addFront(T item);
  public void addBack(T item);
  public void addIndex(T item, int index);

  public T getFront();
  public T getBack();
  public T getIndex(int index);

  public int size();
  public boolean isEmpty();
}
```

### Errors:

Where appropriate, you should throw my error classes instead of ``NullPointerException``s or ``ArrayIndexOutOfBoundsException``s. It is good programming practice to not expose those to your users, and it also demonstrates that you're reasoning about your code and its failure cases.

I provide errors for:
- ``P6NotImplemented``, so I can quickly tell if you have not changed the starter code in a particular case. You will be deleting these errors, not adding them.
- ``EmptyListError``, when a remove or get operation is called on an empty list.
- ``BadIndexError``, when a getIndex, addIndex or removeIndex operation is called with an index that does not exist.
- ``RanOutOfSpaceError``, when an add method fails on a ``FixedSizeList``.

Prefer the most precise error: ``EmptyListError`` rather than a ``BadIndexError`` when looking for the 5th thing in an empty list.

### Test Code

Writing good test code is likely to be redundant. This is OK, as it is a stylistic (opinion) choice.
I have provided good test cases for most of FixedSizeList. In Lab, we will focus on getting the code-coverage of FixedSizeList to 100%.

## Rubric (115)

Note that the maximum here is lower than usual. This assignment has many sections where you are implementing what was discussed in class, and therefore has far less room for interpretation and extra-credit work.

In some ways this assignment is much easier than past assignments. In other ways, it is much more difficult. Do not leave it until the last minute.

### (30) Reflection, Code Compiles and Tests Run.

It is very important your code compiles and you do not change the directory structure. If you have issues importing the code into Eclipse, let me know and we can fix it together. I plan to grade these as automatically as possible.

### (0) Tests

I will not be grading your tests, but you must pass all of my tests.

### (10) GrowableList
This is newer, but I have completed part of the implementation for you.

### (10) SinglyLinkedList
We did a variation on this in Lab last week (where e.g., remove did not crash on an empty list). However, we have covered singly-linked lists in great detail.

### (20) DoublyLinkedList
Getting this right can be very tricky, even with tests. You may refer to [algorithmic pseudocode, if you cite it](https://en.wikipedia.org/wiki/Doubly_linked_list).

### (Max: 25) Efficiency Annotations.
Comment the methods in ``FixedSizeList``, ``GrowableList``, ``SinglyLinkedList``, ``DoublyLinkedList`` and ``ChunkyLinkedList`` with their big-O efficiency, where O(n) is the size of the list.

### (20) ChunkyLinkedList
This is a data structure described in the comments and in the partial code given. Work on this after you have implemented ``SinglyLinkedList`` for reasons that will become obvious.
