import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordCounter {
    public static void main(String[] args) {
        String filename = "C:\\Users\\saisree\\Downloads\\html notes.txt"; // Replace with your file path
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int wordCount = 0;
            int totalWordLength = 0;
            HashMap<String, Integer> wordFrequency = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Remove punctuation and convert to lowercase
                    word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                    if (!word.isEmpty()) {
                        // Count total words
                        wordCount++;
                        // Add to total word length
                        totalWordLength += word.length();
                        // Count frequency of each word
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
            bufferedReader.close();

            // Calculate average word length
            double averageWordLength = (double) totalWordLength / wordCount;

            // Display results
            System.out.println("Total number of words: " + wordCount);
            System.out.println("Average word length: " + averageWordLength);
            System.out.println("\nWord frequency:");
            for (String word : wordFrequency.keySet()) {
                System.out.println(word + ": " + wordFrequency.get(word));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}