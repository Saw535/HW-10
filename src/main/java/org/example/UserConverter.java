package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserConverter {
    private static final Logger logger = Logger.getLogger(UserConverter.class.getName());

    public static void main(String[] args) {
        String inputFilePath = "src\\main\\java\\org\\example\\file-2.txt";
        String outputFilePath = "user.json";

        try {
            List<User> users = readUsersFromFile(inputFilePath);
            writeUsersToJsonFile(users, outputFilePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while processing the file.", e);
        }
    }

    private static List<User> readUsersFromFile(String filePath) throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    users.add(new User(name, age));
                }
            }
        }

        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
        }
    }
}