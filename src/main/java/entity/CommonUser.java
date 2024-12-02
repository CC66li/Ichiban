package entity;

import java.util.ArrayList;

/**
 * A concrete implementation of the User interface representing a common user.
 * <p>
 * This class encapsulates user details such as name, password, physical attributes,
 * dietary preferences, and ingredient information.
 */
public class CommonUser implements User {

    // User attributes
    private final String name; // username
    private final String password; // password
    private final float height; // height in meters
    private final float weight; // weight in kilograms
    private final String gender;    //user's gender
    private final int age; // user's age
    private final String mealType; // user's meal type
    private final String cuisineType; // user's cuisine type
    private final String allergy; // user's allergy
    private final String[] ingredient; // user's ingredient

    /**
     * Constructs a new CommonUser object with the provided details.
     *
     * @param name         the username of the user
     * @param password     the user's password
     * @param height       the user's height in meters
     * @param weight       the user's weight in kilograms
     * @param gender       the user's gender
     * @param age          the user's age in years
     * @param mealType     the preferred meal type (e.g., breakfast, lunch)
     * @param cuisineType  the preferred cuisine type (e.g., Italian, Mexican)
     * @param allergy      allergy information, if any
     * @param ingredient   list of ingredients associated with the user
     */
    public CommonUser(String name, String password, float height,
                      float weight, String gender, int age, String mealType,
                      String cuisineType, String allergy, String[] ingredient) {
        this.name = name;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.allergy = allergy;
        this.ingredient = ingredient;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the height of the user in meters.
     *
     * @return the height
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Gets the weight of the user in kilograms.
     *
     * @return the weight
     */
    @Override
    public float getWeight() {
        return weight;
    }

    /**
     * Gets the user's gender.
     *
     * @return the gender
     */
    @Override
    public String getGender() {
        return gender;
    }

    /**
     * Gets the age of the user in years.
     *
     * @return the age
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * Gets the preferred meal type of the user.
     *
     * @return the meal type
     */
    @Override
    public String getMealType() {
        return mealType;
    }

    /**
     * Gets the preferred cuisine type of the user.
     *
     * @return the cuisine type
     */
    @Override
    public String getCuisineType() {
        return cuisineType;
    }

    /**
     * Gets the allergy information of the user.
     *
     * @return the allergy information
     */
    @Override
    public String getAllergy() {
        return allergy;
    }

    /**
     * Gets the list of ingredients associated with the user.
     *
     * @return an array of ingredient strings
     */
    @Override
    public String[] getIngredient() {
        return ingredient;
    }
}
