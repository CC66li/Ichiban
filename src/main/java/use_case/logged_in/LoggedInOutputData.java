package use_case.logged_in;

/**
 * Output Data for the Signup Use Case.
 */
public class LoggedInOutputData {

    private final String username;
    private final String password;

    private final float height;
    private final float weight;
    private final String gender;
    private final int age;
    private final String mealType;
    private final String cuisineType;
    private final String allergy;
    private final String[] ingredient;

    private final boolean useCaseFailed;

    public LoggedInOutputData(String username, String password, float height, float weight, String gender,
                              int age, String mealType, String cuisineType,
                              String allergy, String[] ingredient, boolean useCaseFailed) {
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.allergy = allergy;
        this.ingredient = ingredient;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public float getHeight() {
        return height;
    }

    public String getPassword() {
        return password;
    }

    public String[] getIngredient() {
        return ingredient;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getGender() {
        return gender;
    }

    public String getMealType() {
        return mealType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
