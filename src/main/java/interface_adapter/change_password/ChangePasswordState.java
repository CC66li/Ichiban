package interface_adapter.change_password;

import entity.CommonUser;

/**
 * The state for the Change Password View Model.
 */
public class ChangePasswordState {
    private String username = "";
    private String password = "";
    private float height = 0;
    private float weight = 0;
    private String gender = "";
    private int age = 0;
    private String passwordError;
    private String mealType;
    private String allergy;
    private String cuisineType;
    private String[] ingredient;

    public ChangePasswordState(CommonUser copy) {
        username = copy.getName();
        password = copy.getPassword();
        height = copy.getHeight();
        weight = copy.getWeight();
        gender = copy.getGender();
        age = copy.getAge();
        passwordError = null;
        mealType = copy.getMealType();
        allergy = copy.getAllergy();
        cuisineType = copy.getCuisineType();
        ingredient = copy.getIngredient();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String[] getIngredient() {
        return ingredient;
    }

    public void setIngredient(String[] ingredient) {
        this.ingredient = ingredient;
    }

    public void setLoginError(String errorMessage) {
        this.passwordError = errorMessage;
    }
}
