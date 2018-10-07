package name.tangyang.aa.chapter7sort;

/**
 * MergeSort p195
 *
 */
public class MergeSort {
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a, AnyType[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmp = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a, AnyType[] tmp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos < rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmp[tmpPos++] = a[leftPos++];
            } else {
                tmp[tmpPos++] = a[rightPos++];
            }
        }
        while (leftPos < leftEnd) {
            tmp[tmpPos++] = a[leftPos++];
        }
        while (rightPos <= rightEnd) {
            tmp[tmpPos++] = a[rightPos++];
        }
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

}
