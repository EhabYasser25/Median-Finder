package Median;

import Median.Algorithms.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;


public class Main {
    public static int[] generateRandomArray(int n, int min, int max) {
        int[] randomArray = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
            randomArray[i] = min + random.nextInt(max-min+1);

        return randomArray;
    }

    public static int[] calculateTime(int size) {
        int[] totalTime = {0, 0, 0};
        int sizesNum = 5;
        int num = 5;
        boolean correctness = true;

        for (int i = 0; i < sizesNum; i++) {
            int[] arr = generateRandomArray(size, 1, Integer.MAX_VALUE);
            IMedian[] algorithms = {
                    new NaiveMedian(arr),
                    new RandomizedMedian(arr),
                    new DeterministicMedian(arr)
            };

            int[] medians = {0, 0, 0};

            for (int j = 0; j < algorithms.length; j++) {
                for (int k = 0; k < num; k++) {
                    int start = (int) System.currentTimeMillis();
                    medians[j] = algorithms[j].getMedian();
                    int end = (int) System.currentTimeMillis();
                    totalTime[j] += end-start;
                }
            }

            correctness = correctness && medians[0] == medians[1] && medians[0] == medians[2];
        }

        for (int i = 0; i < 3; i++)
            totalTime[i] /= sizesNum * num;

        System.out.println(correctness);

        return totalTime;
    }

    public static void main(String[] args) {
        try (FileWriter csvFile = new FileWriter("results.csv");
             CSVPrinter csvPrinter = new CSVPrinter(csvFile, CSVFormat.DEFAULT)) {

            csvPrinter.printRecord("Size", "NaiveMedian", "RandomizedMedian", "DeterministicMedian");

            for (int size = 200000; size <= 10000000; size += 200000) {
                int[] time = calculateTime(size);

                csvPrinter.printRecord(size, time[0], time[1], time[2]);

                System.out.println("Size: " + size);
                System.out.println("NaiveMedian Time (ms): " + time[0]);
                System.out.println("RandomizedMedian Time (ms): " + time[1]);
                System.out.println("DeterministicMedian Time (ms): " + time[2]);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
