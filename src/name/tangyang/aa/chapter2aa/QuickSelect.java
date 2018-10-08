package name.tangyang.aa.chapter2aa;

import name.tangyang.aa.chapter7sort.HeapSort;
import name.tangyang.aa.chapter7sort.QuickSort;

/**
 * QuickSelect p207
 *
 */
public class QuickSelect {
    private static final int CUTOFF = 20;

    private static <AnyType extends Comparable<? super AnyType>>
    AnyType quickSelect(AnyType[] a, int k) {
        quickSelect(a, 0, a.length, k);
        return a[k - 1];
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void quickSelect(AnyType[] a, int left, int right, int k) {
        if (left + CUTOFF < right) {
            AnyType pivot = QuickSort.median(a, left, right);
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    HeapSort.swapReferences(a, i, j);
                } else {
                    break;
                }
            }
            HeapSort.swapReferences(a, i, j);
            if (k <= i) {
                quickSelect(a, left, left - 1, k);
            } else if (k >= i + 1) {
                quickSelect(a, i + 1, right, k);
            }
        } else {
            // do insertionSort
            int j;
            for (int p = left; p <= right; p++) {
                AnyType tmp = a[p];
                for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                    a[j] = a[j - 1];
                }
                a[j] = tmp;
            }
        }
    }
}
