package app;

import javax.swing.JFrame;

/**
 * The Main class of the application.
 * <p>
 * This class serves as the entry point for the application, where all views
 * and use cases are initialized and the application is launched.
 */
public class Main {
    /**
     * The main method that builds and runs the application.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        // Create an instance of AppBuilder to configure the application
        final AppBuilder appBuilder = new AppBuilder();

        // Build the application by adding views and use cases
        final JFrame application = appBuilder

                                            .addLoginView() // Add the login view
                                            .addSignupView()    // Add the signup view
                                            .addLoggedInView()  // Add the logged-in view
                                            .addChangePasswordView() // Add the change password view
                                            .addChangeWeightView()  // Add the change weight view
                                            .addInputIngredientView()  // Add the input ingredient view
                                            .addSignupUseCase() //Configure the Signup use case
                                            .addLoginUseCase() // Configure the Login use case
                                            .addLogoutUseCase()     // Configure the Logout use case    
                                            .addCancelUseCase()    // Configure the Cancel use case
                                            .addChangePasswordUseCase() // Configure the Change Password use case
                                            .addLoggedInUseCase()  // Configure the Logged In use case
                                            .addChangeWeightUseCase() // Configure the Change Weight use case
                                            .addGetReceiptUseCase() // Configure the Get Receipt use case
                                            .build(); // Build the application

         // Pack the JFrame to fit its components
        application.pack();
        // Make the application visible
        application.setVisible(true);
    }
}
