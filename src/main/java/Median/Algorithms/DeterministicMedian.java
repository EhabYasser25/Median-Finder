package Median.Algorithms;
import java.util.Arrays;

public class DeterministicMedian implements IMedian {
    int[] list;

    public DeterministicMedian(int[] list) {
        this.list = list;
    }

    public int getMedian() {
        int n = list.length;
        int k = (n+1) / 2;
        return deterministicSelect(0, n-1, k);
    }

    private int deterministicSelect(int p, int q, int i) {
        if (p == q)
            return list[p];

        int r = deterministicPartition(p, q);
        int k = r-p+1;

        if (i == k)
            return list[r];

        if (i < k)
            return deterministicSelect(p, r-1, i);
        else
            return deterministicSelect(r+1, q, i-k);
    }

    private int deterministicPartition(int p, int q) {
        int x = medianOfMedians(list, p, q);
        return partition(x, p, q);
    }

    private int medianOfMedians(int[] medians, int p, int q) {
        int n = q-p+1;

        if (n <= 5) {
            Arrays.sort(medians, p, q+1);
            return medians[p + (n-1) / 2];
        }

        int groups = n / 5;
        int[] newMedians = new int[groups];

        for (int i = 0; i < groups; i++) {
            int startIndex = p + i * 5;
            int endIndex = startIndex + 4;
            newMedians[i] = medianOfMedians(medians, startIndex, endIndex);
        }

        return medianOfMedians(newMedians, 0, groups - 1);
    }

    private int partition(int x, int p, int q) {
        while (p <= q) {
            while (list[p] < x)
                p++;
            while (list[q] > x)
                q--;

            if (p <= q)
                swap(p++, q--);
        }

        return p-1;
    }

    private void swap(int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
