package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordFrequencyCounter {
    private static final Logger logger = Logger.getLogger(WordFrequencyCounter.class.getName());

    public static void main(String[] args) {
        String inputFilePath = "src\\main\\java\\org\\example\\words.txt";

        try {
            Map<String, Integer> wordFrequency = countWordFrequency(inputFilePath);
            printSortedWordFrequency(wordFrequency);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file", e);
        }
    }

    private static Map<String, Integer> countWordFrequency(String filePath) throws IOException {
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        }

        return wordFrequency;
    }

    private static void printSortedWordFrequency(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordFrequency.entrySet());
        sortedWords.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}