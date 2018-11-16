# CSC212P69
List Data Structures &amp; Testing (Revisited)

## About P9

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

## Rubric (100)

Right now, when I run all the tests in this project, I get the following output:

    
    Tests run: 126, Failures: 3, Errors: 97, Skipped: 0

So there are 26 passing tets, and 100 failing tests (3 + 97).

Your score on this assignment will range up to 100 -- the number of tests that your code pass (minus the 26 that already pass for ``FixedSizeList``), provided (1) there are no code compilation problems with your code (red lines or Problems in Eclipse).  (2) you do not rename any methods or modify the tests in any way, and (3) you resolve any infinite loops in your code (better to comment out that method and have it crash than run forever -- it prevents other tests from running.


