package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.change_weight.ChangeWeightUserDataAccessInterface;
import use_case.get_receipe.GetReceipeUserDataAccessInterface;
import use_case.logged_in.LoggedInUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * A File-based implementation of the User Data Access Object (DAO).
 * This class uses a CSV file to persist user data and perform CRUD operations.
 */

public class FileUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangeWeightUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LoggedInUserDataAccessInterface {

    private static final String HEADER = "username,password, weight, height, gender, age, mealType, cuisineType, allergy, ingredient";

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private String currentUsername;

    /**
     * Constructor for initializing the DAO with a CSV file and user factory.
     * @param csvPath the path to the CSV file
     * @param userFactory the factory for creating User objects
     * @throws IOException if the file cannot be read or written
     */
    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        csvFile = new File(csvPath);

        // Define the headers and their order
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("weight", 2);
        headers.put("height", 3);
        headers.put("gender", 4);
        headers.put("age", 5);
        headers.put("mealType", 6);
        headers.put("cuisineType", 7);
        headers.put("allergy", 8);
        headers.put("ingredient", 9);

        // Initialize the file or load data if it already exists
        if (csvFile.length() == 0) {
            save(); // Save headers if the file is empty
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                final String header = reader.readLine();
                if (!header.equals(HEADER)) {
                    throw new RuntimeException(String.format("header should be%n: %s%but was:%n%s", HEADER, header));
                }
                String row;
                while ((row = reader.readLine()) != null) {
                    final String[] col = row.split(",");
                    final String username = String.valueOf(col[headers.get("username")]);
                    final String password = String.valueOf(col[headers.get("password")]);
                    final String weight = String.valueOf(col[headers.get("weight")]);
                    final String height = String.valueOf(col[headers.get("height")]);
                    final String gender = String.valueOf(col[headers.get("gender")]);
                    final String age = String.valueOf(col[headers.get("age")]);
                    final String mealType = String.valueOf(col[headers.get("mealType")]);
                    final String cuisineType = String.valueOf(col[headers.get("cuisineType")]);
                    final String allergy = String.valueOf(col[headers.get("allergy")]);
                    final String ingredient = String.valueOf(col[headers.get("ingredient")]);
                    String[] listIngredient = ingredient.split(",");
                    final User user = userFactory.create(username, password, Float.parseFloat(height),
                            Float.parseFloat(weight), gender, Integer.parseInt(age),
                            mealType, cuisineType, allergy, listIngredient);
                    accounts.put(username, user);
                }
            }
        }
    }

    /**
     * Saves the current state of user data to the CSV file.
     */
    private void save() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (User user : accounts.values()) {
                final String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getName(), user.getPassword(), user.getWeight(),
                        user.getHeight(), user.getGender(), user.getAge(),
                        user.getMealType(), user.getCuisineType(), user.getAllergy(),
                        String.join(",", user.getIngredient()));
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void changePassword(User user) {
        accounts.put(user.getName(), user); // Update the User object
        save(); // Persist changes to file
    }

    @Override
    public void changeWeight(User user) {
        accounts.put(user.getName(), user); // Update the User object
        save(); // Persist changes to file
    }
}
