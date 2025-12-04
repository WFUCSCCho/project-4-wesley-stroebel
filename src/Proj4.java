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

        ArrayList<String> sorted = new ArrayList<>(data);
        Collections.sort(sorted);

        ArrayList<String> shuffled = new ArrayList<>(data);
        Collections.shuffle(shuffled);

        ArrayList<String> reversed = new ArrayList<>(data);
        Collections.sort(reversed, Collections.reverseOrder());

        FileOutputStream fos = new FileOutputStream("analysis.txt", true);
        java.io.PrintWriter out = new java.io.PrintWriter(fos);

        testCase("sorted", sorted, out);
        testCase("shuffled", shuffled, out);
        testCase("reversed", reversed, out);

        out.close();
        System.out.println("Done. Results appended to analysis.txt.");
    }

    private static void testCase(String label, ArrayList<String> arr, java.io.PrintWriter out) {

        SeparateChainingHashTable<String> table = new SeparateChainingHashTable<>();

        long startInsert = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            table.insert(arr.get(i));
        }
        long endInsert = System.nanoTime();

        long startSearch = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            table.contains(arr.get(i));
        }
        long endSearch = System.nanoTime();

        long startDelete = System.nanoTime();
        for (int i = 0; i < arr.size(); i++) {
            table.remove(arr.get(i));
        }
        long endDelete = System.nanoTime();

        long insertTime = endInsert - startInsert;
        long searchTime = endSearch - startSearch;
        long deleteTime = endDelete - startDelete;

        System.out.println(label + " | insert: " + insertTime +
                " | search: " + searchTime +
                " | delete: " + deleteTime);

        out.println(label + "," + insertTime + "," + searchTime + "," + deleteTime);
    }
}


