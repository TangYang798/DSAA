package name.tangyang.aa.chapter5.cuckoohashing;

import java.util.Random;

/**
 * CuckooHashTable p138
 * @param <AnyType>
 */
public class CuckooHashTable<AnyType> {

    private int currentSize;
    private AnyType[] array;
    private final int numHashFunctions;
    private final HashFamily<? super AnyType> hashFunctions;
    private static final int DEFAULT_TABLE_SIZE = 10;
    private static final int ALLOWED_REHASHES = 10;
    private static final double MAX_LOAD = 0.4;

    public CuckooHashTable (HashFamily<? super AnyType> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<? super AnyType> hf, int size) {
        allocateArray(nextPrime(size));
        doclear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    public void makeEmpty() {
        doclear();
    }

    public boolean contains(AnyType x) {
        return findPos(x) != -1;
    }

    public boolean remove(AnyType x) {
        int pos = findPos(x);
        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }
        return pos != -1;
    }

    public boolean insert(AnyType x) {
        if (contains(x))
            return false;
        if (currentSize >= array.length * MAX_LOAD)
            expand();
        return insertHelper(x);
    }

    private int rehashes = 0;
    private Random r = new Random();
    private boolean insertHelper(AnyType x) {
        final int COUNT_LIMIT = 100;
        while (true) {
            int lastPos = -1;
            int pos;
            for (int count=0; count<COUNT_LIMIT; count++) {
                for (int i=0; i<numHashFunctions; i++) {
                    pos = myHash(x, i);
                    if (array[pos] == null){
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }
                int i = 0;
                do {
                    pos = myHash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);
                AnyType tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }
            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                rehash();// same table size, new hash functions
            }
        }
    }

    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        AnyType[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;
        for (AnyType str : oldArray)
            if (str != null)
                insert(str);
    }

    private int findPos(AnyType x) {
        for (int i=0; i<numHashFunctions; i++) {
            int pos = myHash(x, i);
            if (array[pos] != null && array[pos].equals(x))
                return pos;
        }
        return -1;
    }

    private int myHash(AnyType x, int which) {
        int hashVal = hashFunctions.hash(x, which);
        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;
        return hashVal;
    }

    private void doclear() {
        currentSize = 0;
        for (int i=0; i<array.length; i++)
            array[i] = null;
    }

    private int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        for (; !isPrime(n); n+=2)
            ;
        return n;
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i=3; i*i<=n; i+=2)
            if (n % i == 0)
                return false;
        return true;
    }

    private void allocateArray(int arraySize) {
        array = (AnyType[]) new Object[arraySize];
    }
}
