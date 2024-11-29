package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final float height;
    private final float weight;
    private final String gender;
    private final int age;
    private final String mealType;
    private final String cuisineType;
    private final String allergy;
    private final String[] ingredient;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public float getHeight() {return height;}

    @Override
    public float getWeight() {return weight;}

    @Override
    public String getGender() {return gender;}

    @Override
    public int getAge() {return age;}

    @Override
    public String getMealType() {return mealType;}

    @Override
    public String getCuisineType() {return cuisineType;}

    @Override
    public String getAllergy() {return allergy;}

    @Override
    public String[] getIngredient() {return ingredient;}
}
