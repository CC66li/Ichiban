package use_case.logged_in;

/**
 * The Input Data for the Signup Use Case.
 */
public class LoggedInInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final float height;
    private final float weight;
    private final String gender;
    private final int age;


    public LoggedInInputData(String username, String password, String repeatPassword,
                             float height, float weight, String gender, int age) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    float getHeight() {return height;}

    float getWeight() { return weight;}

    String getGender() {return gender;}

    int getAge() {return age;}
}
