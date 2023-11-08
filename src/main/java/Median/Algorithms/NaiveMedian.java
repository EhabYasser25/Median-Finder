package Median.Algorithms;
import java.util.Arrays;

public class NaiveMedian implements IMedian {

    int[] list;

    public NaiveMedian(int[] list) {
        this.list = list;
    }

    public int getMedian() {
        int n = list.length;
//        mergeSort(0, n-1);
        Arrays.sort(list);
        int k = (n-1) / 2;
        return list[k];
    }

    private void mergeSort(int start, int end) {
        if (start >= end)
            return;

        int mid = (start+end) / 2;
        mergeSort(start, mid);
        mergeSort(mid+1, end);
        merge(start, mid, end);
    }

    private void merge(int start, int mid, int end) {
        if (list[mid] <= list[mid+1])
            return;

        int i = start, j = mid+1, k = 0;
        int[] merged = new int[end-start+1];

        while (i <= mid && j <= end) {
            if (list[i] < list[j])
                merged[k++] = list[i++];
            else
                merged[k++] = list[j++];
        }

        if (j == end+1)
            while(i <= mid)
                merged[k++] = list[i++];

        if (i == mid+1)
            while(j <= end)
                merged[k++] = list[j++];

        for(i = start, j = 0; i <= end; i++, j++)
            list[i] = merged[j];
    }
}