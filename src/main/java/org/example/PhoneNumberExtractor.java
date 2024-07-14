package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberExtractor {

    private static final Logger LOGGER = Logger.getLogger(PhoneNumberExtractor.class.getName());

    public static void main(String[] args) {
        String filePath = "src\\main\\java\\org\\example\\file-1.txt";
        List<String> validPhoneNumbers = getValidPhoneNumbers(filePath);
        Map<String, Integer> frequencyMap = getFrequencyMap(validPhoneNumbers);
        List<Map.Entry<String, Integer>> sortedEntries = sortByFrequency(frequencyMap);
        printSortedPhoneNumbers(sortedEntries);
    }

    private static List<String> getValidPhoneNumbers(String filePath) {
        List<String> validPhoneNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("^\\(\\d{3}\\) \\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}$");

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    validPhoneNumbers.add(line);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file", e);
        }

        return validPhoneNumbers;
    }

    private static Map<String, Integer> getFrequencyMap(List<String> validPhoneNumbers) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String phoneNumber : validPhoneNumbers) {
            frequencyMap.put(phoneNumber, frequencyMap.getOrDefault(phoneNumber, 0) + 1);
        }
        return frequencyMap;
    }

    private static List<Map.Entry<String, Integer>> sortByFrequency(Map<String, Integer> frequencyMap) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return entries;
    }

    private static void printSortedPhoneNumbers(List<Map.Entry<String, Integer>> sortedEntries) {
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey());
        }
    }
}