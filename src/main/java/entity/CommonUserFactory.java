package entity;


/**
 * A factory class for creating instances of {@link CommonUser}.
 * <p>
 * This class implements the {@link UserFactory} interface to provide
 * a standardized way to create {@link CommonUser} objects with specified attributes.
 */
public class CommonUserFactory implements UserFactory {

    /**
     * Creates a new {@link CommonUser} instance with the given attributes.
     *
     * @param name         the username of the user
     * @param password     the user's password
     * @param height       the user's height in meters
     * @param weight       the user's weight in kilograms
     * @param gender       the user's gender
     * @param age          the user's age in years
     * @param mealType     the user's preferred meal type (e.g., breakfast, lunch)
     * @param cuisineType  the user's preferred cuisine type (e.g., Italian, Mexican)
     * @param allergy      any allergy information associated with the user
     * @param ingredient   a list of ingredients associated with the user
     * @return a new {@link CommonUser} instance
     */
    @Override
    public User create(String name, String password, float height,
                       float weight, String gender, int age, String mealType,
                       String cuisineType, String allergy, String[] ingredient) {
        return new CommonUser(name, password, height, weight, gender, age, mealType,
                cuisineType, allergy, ingredient);
    }
}
