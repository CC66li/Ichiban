package use_case.change_password;

/**
 * Represents the input data required for the Change Password Use Case.
 * <p>
 * This class encapsulates the user's credentials and personal details
 * needed to update the user's password within the system.
 */
public class ChangePasswordInputData {

    private final String password; //User's password
    private final String username; //User's username
    private final float height; //User's height in meters
    private final float weight; //User's weight in kilograms
    private final String gender; //User's gender
    private final int age; //User's age

    /**
     * Constructs a new instance of ChangePasswordInputData.
     *
     * @param username the username of the user
     * @param password the user's password
     * @param height   the user's height in meters
     * @param weight   the user's weight in kilograms
     * @param gender   the user's gender
     * @param age      the user's age in years
     */
    public ChangePasswordInputData(String username, String password, float height, float weight,
                                   String gender, int age) {
        this.password = password;
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Retrieves the user's password.
     *
     * @return the password
     */
    String getPassword() {
        return password;
    }

    /**
     * Retrieves the user's username.
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Retrieves the user's weight in kilograms.
     *
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Retrieves the user's height in meters.
     *
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Retrieves the user's gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Retrieves the user's age in years.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }
}
