package Median.Algorithms;
import java.util.Arrays;

public class NaiveMedian implements IMedian {

    int[] list;

    public NaiveMedian(int[] list) {
        this.list = list;
    }

    public int getMedian() {
        int n = list.length;
        Arrays.sort(list);
        int k = (n-1) / 2;
        return list[k];
    }
}
