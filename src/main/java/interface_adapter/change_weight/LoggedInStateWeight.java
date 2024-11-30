package interface_adapter.change_weight;

import interface_adapter.change_weight.LoggedInStateWeight;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInStateWeight {
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

    public LoggedInStateWeight(LoggedInStateWeight copy) {
        username = copy.username;
        password = copy.password;
        height = copy.height;
        weight = copy.weight;
        gender = copy.gender;
        age = copy.age;
        passwordError = copy.passwordError;
    }

    // default
    public LoggedInStateWeight() {}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
