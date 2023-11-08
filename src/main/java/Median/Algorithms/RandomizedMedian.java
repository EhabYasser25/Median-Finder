package Median.Algorithms;

public class RandomizedMedian implements IMedian {
    int[] list;

    public RandomizedMedian(int[] list) {
        this.list = list;
    }

    public int getMedian() {
        int n = list.length;
        int k = (n+1) / 2;
        return randomSelect(0, n-1, k);
    }

    private int randomSelect(int p, int q, int i) {
        if (p == q)
            return list[p];

        int r = randomizedPartition(p, q);
        int k = r-p+1;

        if (i == k)
            return list[r];

        if (i < k)
            return randomSelect(p, r-1, i);
        else
            return randomSelect(r+1, q, i-k);
    }

    private int randomizedPartition(int p, int q) {
        int i = (int) Math.round(p + Math.random() * (q-p));
        swap(p, i);
        return partition(p, q);
    }

    private int partition(int p, int q) {
        int x = list[p];
        int i = p;

        for (int j = p + 1; j <= q; j++)
            if (list[j] < x)
                swap(++i, j);

        swap(p, i);
        return i;
    }

    private void swap(int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
