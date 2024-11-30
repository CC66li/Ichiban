package use_case.logged_in;

/**
 * The Input Data for the Signup Use Case.
 */
public class LoggedInInputData {

    private final float height;
    private final float weight;
    private final String gender;
    private final int age;
    private final String mealType;
    private final String cuisineType;
    private final String allergy;
    private final String[] ingredient;


    public LoggedInInputData(float height, float weight, String gender, int age,
                             String mealType, String cuisineType, String allergy, String[] ingredient) {

        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.allergy = allergy;
        this.ingredient = ingredient;
    }

    float getHeight() {return height;}

    float getWeight() { return weight;}

    String getGender() {return gender;}

    int getAge() {return age;}

    public String getMealType() {
        return mealType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getAllergy() {
        return allergy;
    }

    public String[] getIngredient() {
        return ingredient;
    }

    public double getBMR(){
        if (gender.equals("Male")){
            return (88.362 + (13.397 * this.weight) + (4.799 * this.height) - (5.677 * this.age));
        }
        else{
            return (447.593 + (9.247 * this.weight) + (3.098 * this.height) - (4.33 * this.age));
        }
    }
}
