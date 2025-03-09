import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class AnagramSolver {



    private AnagramSolver() {};

    public static HashMap<String, ArrayList<String>> anagrams(String filename) {
        HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String word = scanner.next().trim();
                String sortedWord = sortWord(word);
                anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
        return anagramMap;
    }

    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<String> mostFrequent = new ArrayList<>();
        for (ArrayList<String> list : anagrams.values()) {
            if (list.size() > mostFrequent.size()) {
                mostFrequent = list;
            }
        }
        return mostFrequent;
    }

    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        for (String key : anagrams.keySet()) {
            System.out.println(key + ": " + anagrams.get(key));
        }
    }

    private static String sortWord(String word) {
        char[] chars = word.toCharArray();
        java.util.Arrays.sort(chars);
        return new String(chars);
    }
}
