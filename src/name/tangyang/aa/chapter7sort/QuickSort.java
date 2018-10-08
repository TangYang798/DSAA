package name.tangyang.aa.chapter7sort;

/**
 * QuickSort p204
 *
 */
public class QuickSort {
    private static final int CUTOFF = 20;

    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right) {
        if (left + CUTOFF < right) {
            AnyType pivot = median(a, left, right);
            int i = left, j = right;
            for (;;) {
                while (a[++i].compareTo(pivot) < 0){}
                while (a[++i].compareTo(pivot) > 0){}
                if (i < j) {
                    HeapSort.swapReferences(a, i, j);
                } else {
                    break;
                }
            }
            HeapSort.swapReferences(a, i, right - 1);
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        } else {
            InsertionSort.insertionSort(a);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> AnyType median(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            HeapSort.swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            HeapSort.swapReferences(a, right, left);
        }
        if (a[right].compareTo(a[center]) < 0) {
            HeapSort.swapReferences(a, right, center);
        }
        HeapSort.swapReferences(a, center, right - 1);
        return a[right - 1];
    }
}
