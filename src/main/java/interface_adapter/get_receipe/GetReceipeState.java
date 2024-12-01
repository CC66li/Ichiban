package interface_adapter.get_receipe;

import interface_adapter.change_password.LoggedInState;

/**
 * The state for the GetReceipe View Model.
 */
public class GetReceipeState {
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

    public GetReceipeState(GetReceipeState copy) {
        username = copy.username;
        password = copy.password;
        height = copy.height;
        weight = copy.weight;
        gender = copy.gender;
        age = copy.age;
        passwordError = copy.passwordError;
        mealType = copy.getMealType();
        allergy = copy.getAllergy();
        cuisineType = copy.getCuisineType();
        ingredient = copy.getIngredient();
    }

    // default
    public GetReceipeState(){}

    public String getUsername() {
        return username;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getIngredient() {
        return ingredient;
    }

    public void setIngredient(String[] ingredient) {
        this.ingredient = ingredient;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
