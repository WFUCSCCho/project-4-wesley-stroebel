//**********************************************************************
// @file: Proj4.java
// @description: @description: Driver program for evaluating hash table performance.
//               Loads a dataset, runs insertion, search, and deletion
//               operations under different input orderings, and records
//               execution time for analysis.
// @date: December 3, 2025
// **********************************************************************

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME
        // Read up to numLines lines from the input file and store them in an ArrayList
        ArrayList<String> data = new ArrayList<>();

        int count = 0;
        while (inputFileNameScanner.hasNextLine() && count < numLines) {
            String line = inputFileNameScanner.nextLine().trim();
            if (!line.isEmpty()) {
                data.add(line);
                count++;
            }
        }

        inputFileNameScanner.close();

// Create three versions of the dataset: sorted, shuffled, and reversed
        ArrayList<String> sorted = new ArrayList<>(data);
        Collections.sort(sorted);

        ArrayList<String> shuffled = new ArrayList<>(data);
        Collections.shuffle(shuffled);

        ArrayList<String> reversed = new ArrayList<>(data);
        Collections.sort(reversed, Collections.reverseOrder());

// Open analysis.txt and append timing results for each test case
        FileOutputStream fos = new FileOutputStream("analysis.txt", true);
        java.io.PrintWriter out = new java.io.PrintWriter(fos);

        testCase("sorted", sorted, out, numLines);
        testCase("shuffled", shuffled, out, numLines);
        testCase("reversed", reversed, out, numLines);

        out.close();
        System.out.println("Done. Results appended to analysis.txt.");
    }

    // Runs insert/search/delete timing tests on a given dataset version
    private static void testCase(String label,
                                 ArrayList<String> arr,
                                 java.io.PrintWriter out,
                                 int numLines) {

        SeparateChainingHashTable<String> table = new SeparateChainingHashTable<>();

        // Measure insertion time
        long startInsert = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            table.insert(arr.get(i));
        }
        long endInsert = System.nanoTime();

        // Measure search time
        long startSearch = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            table.contains(arr.get(i));
        }
        long endSearch = System.nanoTime();

        // Measure deletion time
        long startDelete = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            table.remove(arr.get(i));
        }
        long endDelete = System.nanoTime();

        long insertTime = endInsert - startInsert;
        long searchTime = endSearch - startSearch;
        long deleteTime = endDelete - startDelete;

        // Print timing results to console
        System.out.println(label + " | insert: " + insertTime +
                " | search: " + searchTime +
                " | delete: " + deleteTime);

        // Write timing results to analysis.txt (including N)
        out.println("N=" + numLines +
                " | " + label +
                " | insert: " + insertTime +
                " | search: " + searchTime +
                " | delete: " + deleteTime);
    }
}


