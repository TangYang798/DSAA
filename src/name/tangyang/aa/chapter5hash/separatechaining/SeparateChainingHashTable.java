package name.tangyang.aa.chapter5hash.separatechaining;

import java.util.LinkedList;
import java.util.List;

import static name.tangyang.aa.chapter5hash.forPrime.nextPrime;

/**
 * SeparateChainingHashTable p120
 * @param <AnyType>
 */
public class SeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theList;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theList = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theList.length; i++) {
            theList[i] = new LinkedList<>();
        }
    }

    public boolean contains(AnyType x) {
        List<AnyType> whichList = theList[myhash(x)];
        return whichList.contains(x);
    }

    public void insert(AnyType x) {
        List<AnyType> whichList = theList[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);
            if (++currentSize > theList.length) {
                rehash();
            }
        }
    }

    public void remove(AnyType x) {
        List<AnyType> whichList = theList[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public void makeEmpty() {
        for (int i = 0; i < theList.length; i++) {
            theList[i].clear();
        }
        currentSize = 0;
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= theList.length;
        if (hashVal < 0) {
            hashVal += theList.length;
        }
        return hashVal;
    }

    private void rehash() {
        List<AnyType>[] oldList = theList;
        theList = new List[nextPrime(2 * theList.length)];
        for (int i = 0; i < theList.length; i++) {
            theList[i] = new LinkedList<>();
        }
        currentSize = 0;
        for (int i = 0; i < oldList.length; i++) {
            for (AnyType item : oldList[i]) {
                insert(item);
            }
        }
    }
}
