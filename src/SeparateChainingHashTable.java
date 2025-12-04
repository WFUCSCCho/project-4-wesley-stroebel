// **********************************************************************
// File: SeparateChainingHashTable.java
// Purpose: Core hash table structure using separate chaining.
//          Provides insert, contains, remove, and resizing logic.
// Author: Wesley Stroebel
// Date: December 3, 2025
// **********************************************************************

import java.util.LinkedList;
import java.util.List;

// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// void makeEmpty( )      --> Remove all items

public class SeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<AnyType>[] theLists;
    private int currentSize;

    // Constructor: creates a table with default size
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    // Constructor: creates a table with the next prime size >= given size
    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    // Inserts x into the hash table if it is not already present
    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];

        if (!whichList.contains(x)) {
            whichList.add(x);
            currentSize++;

            if (currentSize > theLists.length) {
                rehash(); // expand table when load factor gets too high
            }
        }
    }

    // Removes x from the hash table if it exists
    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (whichList.remove(x)) {
            currentSize--;
        }
    }

    // Returns true if x is stored in the hash table
    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    // Clears the entire hash table
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    // Rebuilds and resizes the hash table to reduce collisions
    private void rehash() {
        List<AnyType>[] oldLists = theLists;

        theLists = new LinkedList[nextPrime(oldLists.length * 2)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }

        currentSize = 0;

        for (int i = 0; i < oldLists.length; i++) {
            for (AnyType x : oldLists[i]) {
                insert(x);
            }
        }
    }

    // Computes the hash index for x
    private int myhash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= theLists.length;

        if (hashVal < 0) {
            hashVal += theLists.length;
        }

        return hashVal;
    }

    // Hash function for strings using polynomial rolling hash
    public static int hash(String key, int tableSize) {
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++) {
            hashVal = 37 * hashVal + key.charAt(i);
        }

        hashVal %= tableSize;

        if (hashVal < 0) {
            hashVal += tableSize;
        }

        return hashVal;
    }

    // Returns the next prime number >= n
    private static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }

        while (!isPrime(n)) {
            n += 2;
        }

        return n;
    }

    // Checks if n is prime
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        if (n == 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
