package entity;


/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, float height,
                       float weight, String gender, int age, String mealType,
                       String cuisineType, String allergy, String[] ingredient) {
        return new CommonUser(name, password, height, weight, gender, age, mealType,
                cuisineType, allergy, ingredient);
    }
}
